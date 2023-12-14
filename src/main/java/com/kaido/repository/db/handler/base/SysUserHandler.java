package com.kaido.repository.db.handler.base;

import cn.hutool.core.util.StrUtil;
import com.kaido.dto.sa.SysUserPageParamDTO;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.mapper.base.SysUserMapper;
import com.you.meet.nice.tk_mybatis.handler.TKHandler;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;
import java.util.Objects;

@Repository
public class SysUserHandler extends TKHandler<SysUserMapper, SysUser> {

    public SysUser selectByAccountName(String accountName) {
        Weekend<SysUser> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysUser::getAccountName, accountName)
                .andEqualTo(SysUser::getUserStatus, Boolean.TRUE);
        return baseMapper.selectOneByExample(weekend);
    }

    public List<SysUser> selectByParam(SysUserPageParamDTO paramDTO) {
        Weekend<SysUser> weekend = thisWeekend();
        WeekendCriteria<SysUser, Object> weekendCriteria = weekend.weekendCriteria();
        if (StrUtil.isNotBlank(paramDTO.getAccountName())) {
            weekendCriteria.andLike(SysUser::getAccountName, likeString(paramDTO.getAccountName()));
        }
        if (StrUtil.isNotBlank(paramDTO.getUserName())) {
            weekendCriteria.andLike(SysUser::getUserName, likeString(paramDTO.getUserName()));
        }
        if (Objects.nonNull(paramDTO.getUserStatus())) {
            weekendCriteria.andEqualTo(SysUser::getUserStatus, paramDTO.getUserStatus());
        }
        weekend.excludeProperties("userPassword");
        weekend.orderBy("id").desc();
        return baseMapper.selectByExample(weekend);
    }


}
