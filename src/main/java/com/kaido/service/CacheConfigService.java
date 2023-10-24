package com.kaido.service;

import com.github.pagehelper.PageInfo;
import com.kaido.dto.CacheConfigDTO;
import com.kaido.dto.CacheConfigPageQueryDTO;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:41
 * @desc
 */
public interface CacheConfigService {

    PageInfo<CacheConfigDTO> page(CacheConfigPageQueryDTO queryDTO);

    int add(CacheConfigDTO cacheConfigDTO);

    boolean delete(Integer id);

    boolean update(CacheConfigDTO cacheConfigDTO);

}
