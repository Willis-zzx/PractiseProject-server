package com.zzx.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  用户岗位关联表
 * @Author  zhouzixin
 * @Date 2021-10-15 22:50:03
 */

@Entity
@Table ( name ="sys_user_post" )
public class SysUserPost  implements Serializable {

	private static final long serialVersionUID =  3935548852442700205L;

	/**
	 * 用户ID
	 */
	@Id
   	@Column(name = "user_id" )
	private Long userId;

	/**
	 * 岗位ID
	 */
   	@Column(name = "post_id" )
	private Long postId;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPostId() {
		return this.postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "{" +
					"userId='" + userId + '\'' +
					"postId='" + postId + '\'' +
				'}';
	}

}
