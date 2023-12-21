package com.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.kaido.repository.db.entity.base.SysRoleResource;
import com.kaido.repository.db.mapper.base.SysRoleResourceMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Collections;
import java.util.List;

@Repository
public class SysRoleResourceHandler extends TKHandler<SysRoleResourceMapper, SysRoleResource> {

    public List<Integer> getUserRoleResourceIds(Integer userId) {
        return this.baseMapper.getUserRoleResourceIds(userId);
    }

    public List<SysRoleResource> getRoleResourceByRoleId(Integer roleId) {
        return getRoleResourceByRoleIds(Collections.singletonList(roleId));
    }

    public List<SysRoleResource> getRoleResourceByRoleIds(List<Integer> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        Weekend<SysRoleResource> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysRoleResource::getRoleId, roleIds);
        return this.baseMapper.selectByExample(weekend);
    }

    public int deleteByRoleId(Integer roleId) {
        Weekend<SysRoleResource> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysRoleResource::getRoleId, roleId);
        return this.baseMapper.deleteByExample(weekend);
    }

    public int deleteByResourceId(Integer resourceId) {
        Weekend<SysRoleResource> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysRoleResource::getResourceId, resourceId);
        return this.baseMapper.deleteByExample(weekend);
    }

    public int batchInsert(List<SysRoleResource> roleResources) {
        return CollUtil.isEmpty(roleResources) ? 0 : this.baseMapper.batchInsert(roleResources);
    }

}
