package com.kaido.repository.db.handler.base;

import com.kaido.constant.Constant;
import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.mapper.base.SysUserMapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.weekend.Weekend;

@Repository
public class SysUserHandler extends TKHandler<SysUserMapper, SysUser> {

    public SysUser selectByAccountName(String accountName) {
        Weekend<SysUser> weekend = thisWeekend();
        weekend.weekendCriteria().andEqualTo(SysUser::getAccountName, accountName)
                .andEqualTo(SysUser::getUserStatus, Constant.EFFECT);
        return baseMapper.selectOneByExample(weekend);
    }

}
