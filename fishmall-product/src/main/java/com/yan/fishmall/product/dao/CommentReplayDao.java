package com.yan.fishmall.product.dao;

import com.yan.fishmall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author caiyiyan
 * @email cyiyangg@gmail.com
 * @date 2020-08-02 19:17:37
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
