package com.kaido.repository.db.handler.base;

import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysUser;
import com.kaido.repository.db.mapper.base.SysUserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserHandler extends TKHandler<SysUserMapper, SysUser> {
}
