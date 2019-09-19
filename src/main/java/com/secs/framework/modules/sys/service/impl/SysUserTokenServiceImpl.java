package com.secs.framework.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.secs.framework.common.utils.JwtUtil;
import com.secs.framework.common.utils.R;
import com.secs.framework.modules.sys.dao.SysUserDao;
import com.secs.framework.modules.sys.dao.SysUserTokenDao;
import com.secs.framework.modules.sys.entity.SysUserTokenEntity;
import com.secs.framework.modules.sys.oauth2.TokenGenerator;
import com.secs.framework.modules.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public R createToken(long userId) {
//		//生成一个token
//		String token = TokenGenerator.generateValue();
//
//		//当前时间
//		Date now = new Date();
//		//过期时间
//		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
//
//		//判断是否生成过token
//		SysUserTokenEntity tokenEntity = this.selectById(userId);
//		if(tokenEntity == null){
//			tokenEntity = new SysUserTokenEntity();
//			tokenEntity.setUserId(userId);
//			tokenEntity.setToken(token);
//			tokenEntity.setUpdateTime(now);
//			tokenEntity.setExpireTime(expireTime);
//
//			//保存token
//			this.insert(tokenEntity);
//		}else{
//			tokenEntity.setToken(token);
//			tokenEntity.setUpdateTime(now);
//			tokenEntity.setExpireTime(expireTime);
//
//			//更新token
//			this.updateById(tokenEntity);
//		}
//
//		R r = R.ok().put("token", token).put("expire", EXPIRE);

		//生成token
		String token = jwtUtil.generateToken(userId);
		Long expire = jwtUtil.getExpire();
		R r = R.ok().put("token", token).put("expire", jwtUtil.getExpire());
		return r;
	}

	@Override
	public void logout(long userId) {
//		//生成一个token
//		String token = TokenGenerator.generateValue();
//
//		//修改token
//		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
//		tokenEntity.setUserId(userId);
//		tokenEntity.setToken(token);
//		this.updateById(tokenEntity);

		//设置token过期

	}
}
