package com.kaido.repository.db.mapper.base;

import com.kaido.repository.db.entity.base.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleResourceMapper extends tk.mybatis.mapper.common.Mapper<SysRoleResource> {

    List<Integer> getUserRoleResourceIds(@Param("userId") Integer userId);

}