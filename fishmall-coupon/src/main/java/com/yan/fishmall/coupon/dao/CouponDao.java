package com.yan.fishmall.coupon.dao;

import com.yan.fishmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author caiyiyan
 * @email cyiyangg@gmail.com
 * @date 2020-08-03 08:21:43
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
