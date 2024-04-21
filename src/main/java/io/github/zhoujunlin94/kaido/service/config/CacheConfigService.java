package io.github.zhoujunlin94.kaido.service.config;

import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigDTO;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigPageQueryDTO;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:41
 * @desc
 */
public interface CacheConfigService {

    PageInfo<CacheConfigDTO> page(CacheConfigPageQueryDTO queryDTO);

    boolean add(CacheConfigDTO cacheConfigDTO);

    boolean delete(Integer id);

    boolean update(CacheConfigDTO cacheConfigDTO);

}
