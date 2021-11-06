package com.zzx.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  用户角色关联表
 * @Author  zhouzixin
 * @Date 2021-10-15 22:50:58
 */

@Entity
@Table ( name ="sys_user_role" )
public class SysUserRole  implements Serializable {

	private static final long serialVersionUID =  4510375581436087131L;

	/**
	 * 用户ID
	 */
	@Id
   	@Column(name = "user_id" )
	private Long userId;

	/**
	 * 角色ID
	 */
   	@Column(name = "role_id" )
	private Long roleId;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "{" +
					"userId='" + userId + '\'' +
					"roleId='" + roleId + '\'' +
				'}';
	}

}
