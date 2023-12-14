package com.kaido.repository.db.handler.base;

import com.kaido.repository.db.entity.base.SysUserRole;
import com.kaido.repository.db.mapper.base.SysUserRoleMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SysUserRoleHandler extends TKHandler<SysUserRoleMapper, SysUserRole> {

    public List<Integer> selectRoleIdByUserId(Integer userId) {
        Weekend<SysUserRole> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysUserRole::getUserId, userId);
        weekend.selectProperties("roleId");
        return baseMapper.selectByExample(weekend).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
    }

}
