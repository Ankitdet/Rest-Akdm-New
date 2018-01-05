package com.test.ws.entities;

// Generated Jan 1, 2018 2:31:13 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserSkills generated by hbm2java
 */
@Entity
@Table(name = "user_skills", catalog = "xboxlive_akdm")
public class UserSkills implements java.io.Serializable {

	private Integer userSkillId;
	private int userId;
	private int skillId;

	public UserSkills() {
	}

	public UserSkills(int userId, int skillId) {
		this.userId = userId;
		this.skillId = skillId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_skill_id", unique = true, nullable = false)
	public Integer getUserSkillId() {
		return this.userSkillId;
	}

	public void setUserSkillId(Integer userSkillId) {
		this.userSkillId = userSkillId;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "skill_id", nullable = false)
	public int getSkillId() {
		return this.skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

}
