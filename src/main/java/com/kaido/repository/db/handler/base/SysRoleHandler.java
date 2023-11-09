package com.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.kaido.constant.Constant;
import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.mapper.base.SysRoleMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

@Repository
public class SysRoleHandler extends TKHandler<SysRoleMapper, SysRole> {

    public List<SysRole> selectByRoleIds(List<Integer> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        Weekend<SysRole> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysRole::getId, ids).andEqualTo(SysRole::getRoleStatus, Constant.EFFECT);
        return baseMapper.selectByExample(weekend);
    }

}
