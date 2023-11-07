package com.kaido.repository.db.handler.base;

import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysRoleResource;
import com.kaido.repository.db.mapper.base.SysRoleResourceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleResourceHandler extends TKHandler<SysRoleResourceMapper, SysRoleResource> {
}
