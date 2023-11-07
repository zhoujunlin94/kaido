package com.kaido.repository.db.handler.base;

import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysUserRole;
import com.kaido.repository.db.mapper.base.SysUserRoleMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserRoleHandler extends TKHandler<SysUserRoleMapper, SysUserRole> {
}
