package com.kaido.repository.db.mapper.base;

import com.kaido.repository.db.entity.base.CacheConfig;
import com.you.meet.nice.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CacheConfigMapper extends TKMapper<CacheConfig> {
}