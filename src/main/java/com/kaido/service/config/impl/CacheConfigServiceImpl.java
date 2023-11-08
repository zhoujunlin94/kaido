package com.kaido.service.config.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaido.dto.config.CacheConfigDTO;
import com.kaido.dto.config.CacheConfigPageQueryDTO;
import com.kaido.repository.db.entity.base.CacheConfig;
import com.kaido.repository.db.handler.base.CacheConfigHandler;
import com.kaido.service.config.CacheConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        PageInfo<CacheConfigDTO> resultPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(modelPageInfo, resultPageInfo);
        resultPageInfo.setList(BeanUtil.copyToList(modelPageInfo.getList(), CacheConfigDTO.class));
        return resultPageInfo;
    }

    @Override
    public int add(CacheConfigDTO cacheConfigDTO) {
        CacheConfig model = BeanUtil.toBean(cacheConfigDTO, CacheConfig.class);
        cacheConfigHandler.insertSelective(model);
        return model.getId();
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
