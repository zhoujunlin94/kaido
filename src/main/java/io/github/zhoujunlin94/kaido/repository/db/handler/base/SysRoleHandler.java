package io.github.zhoujunlin94.kaido.repository.db.handler.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import io.github.zhoujunlin94.kaido.dto.sa.SysRolePageParamDTO;
import io.github.zhoujunlin94.kaido.repository.db.entity.base.SysRole;
import io.github.zhoujunlin94.kaido.repository.db.mapper.base.SysRoleMapper;
import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Objects;

@Repository
public class SysRoleHandler extends TKHandler<SysRoleMapper, SysRole> {

    public List<SysRole> selectByRoleIds(List<Integer> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        Weekend<SysRole> weekend = thisWeekend();
        weekend.weekendCriteria().andIn(SysRole::getId, ids).andEqualTo(SysRole::getRoleStatus, Boolean.TRUE);
        return baseMapper.selectByExample(weekend);
    }

    public List<SysRole> selectByParam(SysRolePageParamDTO pageParamDTO) {
        Weekend<SysRole> weekend = thisWeekend();
        WeekendCriteria<SysRole, Object> weekendCriteria = weekend.weekendCriteria();
        if (StrUtil.isNotBlank(pageParamDTO.getRoleCode())) {
            weekendCriteria.andLike(SysRole::getRoleCode, likeString(pageParamDTO.getRoleCode()));
        }
        if (StrUtil.isNotBlank(pageParamDTO.getRoleName())) {
            weekendCriteria.andLike(SysRole::getRoleName, likeString(pageParamDTO.getRoleName()));
        }
        if (Objects.nonNull(pageParamDTO.getRoleStatus())) {
            weekendCriteria.andEqualTo(SysRole::getRoleStatus, pageParamDTO.getRoleStatus());
        }
        weekend.orderBy("id").desc();
        return baseMapper.selectByExample(weekend);
    }

}
