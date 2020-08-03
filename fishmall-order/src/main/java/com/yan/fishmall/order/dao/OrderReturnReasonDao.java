package com.yan.fishmall.order.dao;

import com.yan.fishmall.order.entity.OrderReturnReasonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因
 * 
 * @author caiyiyan
 * @email cyiyangg@gmail.com
 * @date 2020-08-03 08:33:36
 */
@Mapper
public interface OrderReturnReasonDao extends BaseMapper<OrderReturnReasonEntity> {
	
}
