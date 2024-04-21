package io.github.zhoujunlin94.kaido.service.config.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigDTO;
import io.github.zhoujunlin94.kaido.dto.config.CacheConfigPageQueryDTO;
import io.github.zhoujunlin94.kaido.repository.db.entity.base.CacheConfig;
import io.github.zhoujunlin94.kaido.repository.db.handler.base.CacheConfigHandler;
import io.github.zhoujunlin94.kaido.service.config.CacheConfigService;
import io.github.zhoujunlin94.meet.tk_mybatis.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2023年07月05日 22:42
 * @desc
 */
@Service
@RequiredArgsConstructor
public class CacheConfigServiceImpl implements CacheConfigService {

    private final CacheConfigHandler cacheConfigHandler;

    @Override
    public PageInfo<CacheConfigDTO> page(CacheConfigPageQueryDTO queryDTO) {
        PageInfo<CacheConfig> modelPageInfo = PageHelper.startPage(queryDTO.getPageNo(), queryDTO.getPageSize())
                .doSelectPageInfo(() -> cacheConfigHandler.selectByParam(queryDTO.getKey(), queryDTO.getValue(), queryDTO.getDesc()));
        return PageUtil.copy(modelPageInfo, () -> BeanUtil.copyToList(modelPageInfo.getList(), CacheConfigDTO.class));
    }

    @Override
    public boolean add(CacheConfigDTO cacheConfigDTO) {
        CacheConfig model = BeanUtil.toBean(cacheConfigDTO, CacheConfig.class);
        return cacheConfigHandler.insertSelective(model) == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return cacheConfigHandler.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean update(CacheConfigDTO cacheConfigDTO) {
        CacheConfig model = BeanUtil.toBean(cacheConfigDTO, CacheConfig.class);
        return cacheConfigHandler.updateByPrimaryKeySelective(model) == 1;
    }

}
