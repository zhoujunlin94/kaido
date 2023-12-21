package com.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.kaido.repository.db.entity.base.SysUserRole;
import com.kaido.repository.db.mapper.base.SysUserRoleMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SysUserRoleHandler extends TKHandler<SysUserRoleMapper, SysUserRole> {

    public List<Integer> selectRoleIdsByUserId(Integer userId) {
        return selectUserRoles(userId).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

    public List<SysUserRole> selectUserRoles(Integer userId) {
        return selectUserRoles(Collections.singletonList(userId));
    }

    public List<SysUserRole> selectUserRoles(List<Integer> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        Weekend<SysUserRole> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysUserRole::getUserId, userIds);
        return baseMapper.selectByExample(weekend);
    }

    public int deleteByUserId(Integer userId) {
        Weekend<SysUserRole> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysUserRole::getUserId, userId);
        return baseMapper.deleteByExample(weekend);
    }

    public int deleteByRoleId(Integer roleId) {
        Weekend<SysUserRole> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysUserRole::getRoleId, roleId);
        return baseMapper.deleteByExample(weekend);
    }

    public int batchInsert(List<SysUserRole> userRoleList) {
        return CollUtil.isEmpty(userRoleList) ? 0 : baseMapper.batchInsert(userRoleList);
    }

}
