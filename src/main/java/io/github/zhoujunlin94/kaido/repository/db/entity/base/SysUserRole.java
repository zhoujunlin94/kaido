package io.github.zhoujunlin94.kaido.repository.db.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户角色表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "roleId"})
@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 是否删除 1已删除 0正常数据
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 创建用户
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}