package com.secs.framework.common.utils;

/**
 * Redis所有Keys
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
