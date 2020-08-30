package com.yan.fishmall.product.web;

import com.yan.fishmall.product.entity.CategoryEntity;
import com.yan.fishmall.product.service.CategoryService;
import com.yan.fishmall.product.vo.Catalog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    RedissonClient redisson;

    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping({"/", "/index.html"})
    public String IndexPage(Model model){

        //TODO 1. 查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Category();

        //视图解析器进行拼串
        //classpath:/template/xxx.html
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }

    //index/catalog.json
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catalog2Vo>>  getCatalogJson(){

        Map<String, List<Catalog2Vo>> map = categoryService.getCatalogJson();
        return map;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        //1. 获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redisson.getLock("my-lock");

        //2. 加锁
        lock.lock();   //阻塞式等待
        //2.1 锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
        //2.2 加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s后自动删除
         try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception ex) {

        } finally {
            //3. 解锁
            System.out.println("释放锁..." + Thread.currentThread().getId());
            lock.unlock();;
        }
        return "hello";
    }

    //保证一定能读到最新数据，修改期间，写锁是一个排它锁（互斥锁、独享锁），读是一个共享锁
    //写锁没释放读就必须等待
    //读+读：相当于无锁，并发读，只会在redis中记录当前所有读锁，都能加锁成功
    //写+读：等待写锁释放
    //写+写：阻塞方式
    //读+写：有读锁，写也需要等待
    //只要有写的存在，都必须等待
    @GetMapping("write")
    @ResponseBody
    public String writeValue(){

        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.writeLock();
        try {
            //1. 改数据加写锁，读数据加读锁
            rLock.lock();
            System.out.println("写锁加锁成功..." + Thread.currentThread().getId());
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("写锁释放..." + Thread.currentThread().getId());
        }

        return s;
    }

    @GetMapping("read")
    @ResponseBody
    public String readValue(){

        RReadWriteLock lock = redisson.getReadWriteLock("rw-lock");
        String s = "";
        //加读锁
        RLock rLock = lock.readLock();
        rLock.lock();
        System.out.println("读锁加锁成功..." + Thread.currentThread().getId());
        try {
            s = redisTemplate.opsForValue().get("writeValue");
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("读锁释放..." + Thread.currentThread().getId());
        }

        return s;
    }
}
