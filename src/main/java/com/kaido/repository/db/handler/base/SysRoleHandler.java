package com.kaido.repository.db.handler.base;

import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysRole;
import com.kaido.repository.db.mapper.base.SysRoleMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleHandler extends TKHandler<SysRoleMapper, SysRole> {

}
