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
 * 比赛报名
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("bisaibaoming")
public class BisaibaomingEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BisaibaomingEntity() {
		
	}
	
	public BisaibaomingEntity(T t) {
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
	 * 比赛时间
	 */
					
	private String bisaishijian;
	
	/**
	 * 比赛场地
	 */
					
	private String bisaichangdi;
	
	/**
	 * 参赛队员
	 */
					
	private String cansaiduiyuan;
	
	/**
	 * 报名时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date baomingshijian;
	
	/**
	 * 队长账号
	 */
					
	private String duizhangzhanghao;
	
	/**
	 * 队长姓名
	 */
					
	private String duizhangxingming;
	
	/**
	 * 球队名称
	 */
					
	private String qiuduimingcheng;
	
	/**
	 * 裁判账号
	 */
					
	private String caipanzhanghao;
	
	/**
	 * 裁判姓名
	 */
					
	private String caipanxingming;
	
	/**
	 * 是否审核
	 */
					
	private String sfsh;
	
	/**
	 * 回复内容
	 */
					
	private String shhf;
	

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
	 * 设置：参赛队员
	 */
	public void setCansaiduiyuan(String cansaiduiyuan) {
		this.cansaiduiyuan = cansaiduiyuan;
	}
	/**
	 * 获取：参赛队员
	 */
	public String getCansaiduiyuan() {
		return cansaiduiyuan;
	}
	/**
	 * 设置：报名时间
	 */
	public void setBaomingshijian(Date baomingshijian) {
		this.baomingshijian = baomingshijian;
	}
	/**
	 * 获取：报名时间
	 */
	public Date getBaomingshijian() {
		return baomingshijian;
	}
	/**
	 * 设置：队长账号
	 */
	public void setDuizhangzhanghao(String duizhangzhanghao) {
		this.duizhangzhanghao = duizhangzhanghao;
	}
	/**
	 * 获取：队长账号
	 */
	public String getDuizhangzhanghao() {
		return duizhangzhanghao;
	}
	/**
	 * 设置：队长姓名
	 */
	public void setDuizhangxingming(String duizhangxingming) {
		this.duizhangxingming = duizhangxingming;
	}
	/**
	 * 获取：队长姓名
	 */
	public String getDuizhangxingming() {
		return duizhangxingming;
	}
	/**
	 * 设置：球队名称
	 */
	public void setQiuduimingcheng(String qiuduimingcheng) {
		this.qiuduimingcheng = qiuduimingcheng;
	}
	/**
	 * 获取：球队名称
	 */
	public String getQiuduimingcheng() {
		return qiuduimingcheng;
	}
	/**
	 * 设置：裁判账号
	 */
	public void setCaipanzhanghao(String caipanzhanghao) {
		this.caipanzhanghao = caipanzhanghao;
	}
	/**
	 * 获取：裁判账号
	 */
	public String getCaipanzhanghao() {
		return caipanzhanghao;
	}
	/**
	 * 设置：裁判姓名
	 */
	public void setCaipanxingming(String caipanxingming) {
		this.caipanxingming = caipanxingming;
	}
	/**
	 * 获取：裁判姓名
	 */
	public String getCaipanxingming() {
		return caipanxingming;
	}
	/**
	 * 设置：是否审核
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
	/**
	 * 设置：回复内容
	 */
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	/**
	 * 获取：回复内容
	 */
	public String getShhf() {
		return shhf;
	}

}
