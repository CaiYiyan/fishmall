package com.yan.fishmall.member.dao;

import com.yan.fishmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author caiyiyan
 * @email cyiyangg@gmail.com
 * @date 2020-08-03 08:24:05
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
