package com.kaido.repository.db.handler.base;

import cn.hutool.core.util.StrUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.CacheConfig;
import com.kaido.repository.db.mapper.base.CacheConfigMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2023年03月27日 21:55
 * @desc
 */
@Repository
public class CacheConfigHandler extends TKHandler<CacheConfigMapper, CacheConfig> implements InitializingBean {

    private static final Cache<String, String> CACHE = CacheBuilder.newBuilder().concurrencyLevel(Runtime.getRuntime().availableProcessors())
            .initialCapacity(500).build();

    public CacheConfig selectByKey(String key) {
        Weekend<CacheConfig> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(CacheConfig::getKey, key);
        return this.baseMapper.selectOneByExample(weekend);
    }

    public String getFromCache(String key) {
        try {
            // 从缓存中获取  如果缓存中没有(真没有或者已经过期或者手动移除) 则从数据库中获取
            return CACHE.get(key, () -> Optional.ofNullable(selectByKey(key)).map(CacheConfig::getValue).orElse(StrUtil.EMPTY));
        } catch (Exception e) {
            return StrUtil.EMPTY;
        }
    }

    public List<CacheConfig> selectByParam(String key, String value, String desc) {
        Weekend<CacheConfig> weekend = thisWeekend();
        WeekendCriteria<CacheConfig, Object> weekendCriteria = weekend.weekendCriteria();
        if (StrUtil.isNotBlank(key)) {
            weekendCriteria.andLike(CacheConfig::getKey, likeString(key));
        }
        if (StrUtil.isNotBlank(value)) {
            weekendCriteria.andLike(CacheConfig::getValue, likeString(value));
        }
        if (StrUtil.isNotBlank(desc)) {
            weekendCriteria.andLike(CacheConfig::getDesc, likeString(desc));
        }
        weekend.orderBy("id").desc();
        return this.baseMapper.selectByExample(weekend);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * 项目启动时获取所有配置并存入缓存
         * 后期如果更新了数据库  这里也需要同步更新缓存
         * 方案1: 更新数据库的同时  编码更新缓存  只能更新单机   不推荐
         * 方案2: 更新时发送消息队列 同步所有机器缓存更新
         * 方案3: canal监听数据库  通过消息队列  同步所有机器缓存更新
         */
        Map<String, String> keyValueMap = selectAll().stream().collect(Collectors.toMap(CacheConfig::getKey, CacheConfig::getValue));
        CACHE.putAll(keyValueMap);
    }

}
