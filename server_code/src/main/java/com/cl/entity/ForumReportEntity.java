package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 论坛举报
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("forum_report")
public class ForumReportEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ForumReportEntity() {
		
	}
	
	public ForumReportEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 论坛Id
	 */
					
	private Long forumId;
	
	/**
	 * 帖子标题
	 */
					
	private String title;
	
	/**
	 * 举报人id
	 */
					
	private Long reportUserid;
	
	/**
	 * 举报人账号
	 */
					
	private String reportUsername;
	
	/**
	 * 被举报人id
	 */
					
	private Long reportedUserid;
	
	/**
	 * 被举报人账号
	 */
					
	private String reportedUsername;
	
	/**
	 * 举报原因
	 */
					
	private String reason;
	
	/**
	 * 举报图片
	 */
					
	private String images;
	
	/**
	 * 处理建议
	 */
					
	private String suggestion;
	
	/**
	 * 处理状态
	 */
					
	private String status;
	
	/**
	 * 举报类型
	 */
					
	private String reportType;
	
	/**
	 * 用户id
	 */
					
	private Long userid;
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：论坛Id
	 */
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	/**
	 * 获取：论坛Id
	 */
	public Long getForumId() {
		return forumId;
	}
	/**
	 * 设置：帖子标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：帖子标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：举报人id
	 */
	public void setReportUserid(Long reportUserid) {
		this.reportUserid = reportUserid;
	}
	/**
	 * 获取：举报人id
	 */
	public Long getReportUserid() {
		return reportUserid;
	}
	/**
	 * 设置：举报人账号
	 */
	public void setReportUsername(String reportUsername) {
		this.reportUsername = reportUsername;
	}
	/**
	 * 获取：举报人账号
	 */
	public String getReportUsername() {
		return reportUsername;
	}
	/**
	 * 设置：被举报人id
	 */
	public void setReportedUserid(Long reportedUserid) {
		this.reportedUserid = reportedUserid;
	}
	/**
	 * 获取：被举报人id
	 */
	public Long getReportedUserid() {
		return reportedUserid;
	}
	/**
	 * 设置：被举报人账号
	 */
	public void setReportedUsername(String reportedUsername) {
		this.reportedUsername = reportedUsername;
	}
	/**
	 * 获取：被举报人账号
	 */
	public String getReportedUsername() {
		return reportedUsername;
	}
	/**
	 * 设置：举报原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：举报原因
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：举报图片
	 */
	public void setImages(String images) {
		this.images = images;
	}
	/**
	 * 获取：举报图片
	 */
	public String getImages() {
		return images;
	}
	/**
	 * 设置：处理建议
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	/**
	 * 获取：处理建议
	 */
	public String getSuggestion() {
		return suggestion;
	}
	/**
	 * 设置：处理状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：处理状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：举报类型
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * 获取：举报类型
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}

}
