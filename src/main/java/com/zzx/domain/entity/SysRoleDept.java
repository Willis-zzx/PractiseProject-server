package com.zzx.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description 角色部门关联表
 * @Author zhouzixin
 * @Date 2021-10-15 22:48:49
 */

@Entity
@Table(name = "sys_role_dept")
public class SysRoleDept implements Serializable {

    private static final long serialVersionUID = 7438398846863299578L;

    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Long deptId;

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "{" +
                "roleId='" + roleId + '\'' +
                "deptId='" + deptId + '\'' +
                '}';
    }

}
