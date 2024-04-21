package io.github.zhoujunlin94.kaido.repository.db.mapper.base;

import io.github.zhoujunlin94.kaido.repository.db.entity.base.SysUser;
import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends TKMapper<SysUser> {
}