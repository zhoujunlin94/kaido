package com.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.mapper.base.SysResourceMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

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
        return this.baseMapper.selectByExample(weekend);
    }

}
