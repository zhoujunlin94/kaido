package com.kaido.repository.db.handler.base;

import com.kaido.internal.mybatis.TKHandler;
import com.kaido.repository.db.entity.base.SysResource;
import com.kaido.repository.db.mapper.base.SysResourceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SysResourceHandler extends TKHandler<SysResourceMapper, SysResource> {
}
