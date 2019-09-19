package com.secs.framework.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.secs.framework.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
