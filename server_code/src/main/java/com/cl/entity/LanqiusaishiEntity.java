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
 * 篮球赛事
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@TableName("lanqiusaishi")
public class LanqiusaishiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public LanqiusaishiEntity() {
		
	}
	
	public LanqiusaishiEntity(T t) {
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
	 * 赛事名称
	 */
					
	private String saishimingcheng;
	
	/**
	 * 封面
	 */
					
	private String fengmian;
	
	/**
	 * 比赛场地
	 */
					
	private String bisaichangdi;
	
	/**
	 * 比赛时间
	 */
					
	private String bisaishijian;
	
	/**
	 * 比赛赛制
	 */
					
	private String bisaisaizhi;
	
	/**
	 * 比赛规则
	 */
					
	private String bisaiguize;
	
	/**
	 * 比赛赛程
	 */
					
	private String bisaisaicheng;
	
	/**
	 * 收藏数
	 */
					
	private Integer storeupNumber;
	
	/**
	 * 评论数
	 */
					
	private Integer discussNumber;
	

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
	 * 设置：赛事名称
	 */
	public void setSaishimingcheng(String saishimingcheng) {
		this.saishimingcheng = saishimingcheng;
	}
	/**
	 * 获取：赛事名称
	 */
	public String getSaishimingcheng() {
		return saishimingcheng;
	}
	/**
	 * 设置：封面
	 */
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
	/**
	 * 设置：比赛场地
	 */
	public void setBisaichangdi(String bisaichangdi) {
		this.bisaichangdi = bisaichangdi;
	}
	/**
	 * 获取：比赛场地
	 */
	public String getBisaichangdi() {
		return bisaichangdi;
	}
	/**
	 * 设置：比赛时间
	 */
	public void setBisaishijian(String bisaishijian) {
		this.bisaishijian = bisaishijian;
	}
	/**
	 * 获取：比赛时间
	 */
	public String getBisaishijian() {
		return bisaishijian;
	}
	/**
	 * 设置：比赛赛制
	 */
	public void setBisaisaizhi(String bisaisaizhi) {
		this.bisaisaizhi = bisaisaizhi;
	}
	/**
	 * 获取：比赛赛制
	 */
	public String getBisaisaizhi() {
		return bisaisaizhi;
	}
	/**
	 * 设置：比赛规则
	 */
	public void setBisaiguize(String bisaiguize) {
		this.bisaiguize = bisaiguize;
	}
	/**
	 * 获取：比赛规则
	 */
	public String getBisaiguize() {
		return bisaiguize;
	}
	/**
	 * 设置：比赛赛程
	 */
	public void setBisaisaicheng(String bisaisaicheng) {
		this.bisaisaicheng = bisaisaicheng;
	}
	/**
	 * 获取：比赛赛程
	 */
	public String getBisaisaicheng() {
		return bisaisaicheng;
	}
	/**
	 * 设置：收藏数
	 */
	public void setStoreupNumber(Integer storeupNumber) {
		this.storeupNumber = storeupNumber;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupNumber() {
		return storeupNumber;
	}
	/**
	 * 设置：评论数
	 */
	public void setDiscussNumber(Integer discussNumber) {
		this.discussNumber = discussNumber;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussNumber() {
		return discussNumber;
	}

}
