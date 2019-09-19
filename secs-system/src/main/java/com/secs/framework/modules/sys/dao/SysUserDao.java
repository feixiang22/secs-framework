

package com.secs.framework.modules.sys.dao;

import java.util.List;

import com.secs.framework.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

	/**
	 * 查询用户的所有权限
	 * 
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

}
