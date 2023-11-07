package com.kaido.repository.db.entity.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_user")
public class SysUser implements Serializable {
    /**
     * 主键  用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 账户
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 用户真实姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 是否有效（0-无效；1-有效）
     */
    @Column(name = "user_status")
    private Boolean userStatus;

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