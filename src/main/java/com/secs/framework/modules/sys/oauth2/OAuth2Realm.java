package com.secs.framework.modules.sys.oauth2;

import com.secs.framework.common.exception.RRException;
import com.secs.framework.common.utils.JwtUtil;
import com.secs.framework.modules.sys.entity.SysUserEntity;
import com.secs.framework.modules.sys.entity.SysUserTokenEntity;
import com.secs.framework.modules.sys.service.ShiroService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * 认证
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-20 14:00
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String accessToken = (String) token.getPrincipal();
//
//        //根据accessToken，查询用户信息
//        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
//        //token失效
//        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw new IncorrectCredentialsException("token失效，请重新登录");
//        }
//
//        //查询用户信息
//        SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());
//        //账号锁定
//        if(user.getStatus() == 0){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }
//
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
//        return info;


        String accessToken = (String) token.getPrincipal();
        if(StringUtils.isBlank(accessToken)){
            throw new IncorrectCredentialsException("token不能为空");
        }
        //解析token
        Claims claims = jwtUtil.parseToken(accessToken);
        //token失效
        if(claims == null || jwtUtil.isTokenExpired(claims.getExpiration())){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //获取userId
        Long userId = Long.valueOf(claims.getSubject());
        //获取过期时间
        Date expiration = claims.getExpiration();

        //查询用户信息
        SysUserEntity user = shiroService.queryUser(userId);
        //账户不存在
        if(user == null){
            throw new LockedAccountException("账户不存在");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
