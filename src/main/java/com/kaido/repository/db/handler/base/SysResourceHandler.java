package com.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.kaido.dto.sa.SysResourcePageParamDTO;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.mapper.base.SysResourceMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Objects;

@Repository
public class SysResourceHandler extends TKHandler<SysResourceMapper, SysResource> {

    public List<SysResource> getResource(List<Integer> resourceIds) {
        if (CollUtil.isEmpty(resourceIds)) {
            return Lists.newArrayList();
        }
        Weekend<SysResource> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysResource::getId, resourceIds)
                .andEqualTo(SysResource::getResourceStatus, Boolean.TRUE);
        weekend.orderBy("resourceOrder").asc();
        return baseMapper.selectByExample(weekend);
    }

    public List<SysResource> selectByParam(SysResourcePageParamDTO paramDTO) {
        Weekend<SysResource> weekend = thisWeekend();
        WeekendCriteria<SysResource, Object> weekendCriteria = weekend.weekendCriteria();
        if (StrUtil.isNotBlank(paramDTO.getResourceCode())) {
            weekendCriteria.andLike(SysResource::getResourceCode, likeString(paramDTO.getResourceCode()));
        }
        if (StrUtil.isNotBlank(paramDTO.getResourceName())) {
            weekendCriteria.andLike(SysResource::getResourceName, likeString(paramDTO.getResourceName()));
        }
        if (Objects.nonNull(paramDTO.getResourceType())) {
            weekendCriteria.andEqualTo(SysResource::getResourceType, paramDTO.getResourceType());
        }
        if (Objects.nonNull(paramDTO.getResourceStatus())) {
            weekendCriteria.andEqualTo(SysResource::getResourceStatus, paramDTO.getResourceStatus());
        }
        return baseMapper.selectByExample(weekend);
    }

}
