package com.kaido.internal.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * spring redis实现
 *
 * @author zhoujl
 */
@Slf4j
@Component
@ConditionalOnBean(RedisTemplate.class)
public class RedisHelper {

    private static RedisTemplate<String, Object> redisTemplate;

    public RedisHelper(@Autowired RedisTemplate<String, Object> redisTemplate) {
        RedisHelper.redisTemplate = redisTemplate;
    }

    public static <T> T executeLua(String lua, List<String> keys, List<String> args, Class<T> resultType) {
        DefaultRedisScript<T> defaultRedisScript = new DefaultRedisScript<>(lua, resultType);
        return redisTemplate.execute(defaultRedisScript, keys, args.toArray());
    }

    public static Long delete(String... keys) {
        return redisTemplate.delete(CollUtil.newArrayList(keys));
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public static Long deleteByPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (CollUtil.isEmpty(keys)) {
            return 0L;
        }
        return redisTemplate.delete(keys);
    }

    public static Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static <T> T getStr(String key, Class<T> cls) {
        try {
            if (StrUtil.isNotBlank(key)) {
                String value = (String) redisTemplate.boundValueOps(key).get();
                return JSONObject.parseObject(value, cls);
            }
        } catch (Exception e) {
            log.error("redis getStr出错啦!", e);
        }
        return null;
    }

    public static <T> T getStr(String key, TypeReference<T> cls) {
        try {
            if (StrUtil.isNotBlank(key)) {
                String value = (String) redisTemplate.boundValueOps(key).get();
                return JSONObject.parseObject(value, cls.getType());
            }
        } catch (Exception e) {
            log.error("redis getStr出错啦!", e);
        }
        return null;
    }

    public static String getStr(String key) {
        try {
            if (StrUtil.isNotBlank(key)) {
                return (String) redisTemplate.boundValueOps(key).get();
            }
        } catch (Exception e) {
            log.error("redis getStr出错啦!", e);
        }
        return null;
    }

    public static Boolean setStr(String key, String value) {
        Boolean result = Boolean.FALSE;
        try {
            if (StrUtil.isNotBlank(key)) {
                redisTemplate.boundValueOps(key).set(value);
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("redis setStr出错啦!", e);
        }
        return result;
    }

    public static Boolean setStr(String key, Object value) {
        Boolean result = Boolean.FALSE;
        try {
            if (StrUtil.isNotBlank(key)) {
                redisTemplate.boundValueOps(key).set(JSONObject.toJSONString(value));
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("redis setStr出错啦!", e);
        }
        return result;
    }

    public static Boolean setStr(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        Boolean result = Boolean.FALSE;
        try {
            if (StrUtil.isNotBlank(key)) {
                redisTemplate.boundValueOps(key).set(JSONObject.toJSONString(value), expireTime, timeUnit);
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("redis setStr出错啦!", e);
        }
        return result;
    }

    public static Boolean setStrIfAbsent(String key, Object value) {
        Boolean result = Boolean.FALSE;
        try {
            if (StrUtil.isNotBlank(key)) {
                redisTemplate.boundValueOps(key).setIfAbsent(JSONObject.toJSONString(value));
                result = Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("redis setStrIfAbsent出错啦!", e);
        }
        return result;
    }

    public static Long getExpire(String key) {
        return redisTemplate.boundValueOps(key).getExpire();
    }

    public static Boolean expire(String key, Long expireTime, TimeUnit timeUnit) {
        return redisTemplate.expire(key, expireTime, timeUnit);
    }

    public static Boolean expireAt(String key, Date date) {
        return redisTemplate.execute(connection ->
                connection.expireAt(rawKey(key), date.getTime() / 1000), true);
    }

    public static Long incr(String key) {
        return redisTemplate.execute((RedisCallback<Long>) redisConnection ->
                redisConnection.incr(key.getBytes())
        );
    }

    public static Long incrBy(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.incrBy(key.getBytes(), value));
    }

    public static Long decr(String key) {
        return redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.decr(key.getBytes()));
    }

    public static Long decrBy(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.decrBy(key.getBytes(), value));
    }

    public static Long deleteHashKeys(String key, Object... hashKeys) {
        return redisTemplate.boundHashOps(key).delete(hashKeys);
    }

    public static Map getEntries(String key) {
        return redisTemplate.boundHashOps(key).entries();
    }

    public static Long getHashSize(String key) {
        return redisTemplate.boundHashOps(key).size();
    }

    public static Boolean putHash(String key, Object hashKey, Object value) {
        Boolean result = Boolean.FALSE;
        try {
            redisTemplate.boundHashOps(key).put(hashKey, value);
            result = Boolean.TRUE;
        } catch (Exception e) {
            log.error("putHash出错啦!", e);
        }
        return result;
    }

    public static Boolean putHashAll(String key, Map<String, String> hashKeyAndValue) {
        try {
            redisTemplate.boundHashOps(key).putAll(hashKeyAndValue);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("putHashAll出错啦!", e);
            return Boolean.FALSE;
        }
    }

    public static Object getHash(String key, Object hashKey) {
        return redisTemplate.boundHashOps(key).get(hashKey);
    }

    public static Long hashIncrBy(String key, Object hashKey, Long value) {
        return redisTemplate.boundHashOps(key).increment(hashKey, value);
    }

    public static Long delHashByKey(String key, Object hashKey) {
        return redisTemplate.boundHashOps(key).delete(hashKey);
    }

    public static Long addSet(String key, String... values) {
        return redisTemplate.boundSetOps(key).add(values);
    }

    public static Set<Object> getSet(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    public static Long getSetSize(String key) {
        return redisTemplate.boundSetOps(key).size();
    }

    public static Object popSet(String key) {
        return redisTemplate.boundSetOps(key).pop();
    }

    public static Boolean addZSet(String key, Object value, double score) {
        return redisTemplate.boundZSetOps(key).add(value, score);
    }

    public static Set<Object> rangeByScore(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    public static Long removeZSet(String key, Object... values) {
        return redisTemplate.boundZSetOps(key).remove(values);
    }

    public static Long leftPushAll(String key, Collection<String> strCollection, Date expireDate) {
        Long result = 0L;
        if (CollUtil.isNotEmpty(strCollection)) {
            result = redisTemplate.boundListOps(key).leftPushAll(strCollection);
            expireAt(key, expireDate);
        }
        return result;
    }

    public static Object leftPop(String key) {
        return redisTemplate.boundListOps(key).leftPop();
    }

    public static Object rightPop(String key) {
        return redisTemplate.boundListOps(key).rightPop();
    }

    public static Long ttl(String key, TimeUnit timeUnit) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.ttl(key.getBytes(), timeUnit));
    }

    public static void publishMsg(String channel, String msg) {
        redisTemplate.convertAndSend(channel, msg);
    }


    //================================私有方法======================================

    private static byte[] rawKey(Object key) {
        if (key instanceof byte[]) {
            return (byte[]) key;
        }
        RedisSerializer<Object> keySerializer = (RedisSerializer<Object>) redisTemplate.getKeySerializer();
        return keySerializer.serialize(key);
    }

    //================================私有方法======================================

}
