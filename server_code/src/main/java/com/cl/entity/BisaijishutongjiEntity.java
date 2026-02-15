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
 * 比赛技术统计
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("bisaijishutongji")
public class BisaijishutongjiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BisaijishutongjiEntity() {
		
	}
	
	public BisaijishutongjiEntity(T t) {
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
	 * 球队名称
	 */
					
	private String qiuduimingcheng;
	
	/**
	 * 得分
	 */
					
	private Integer defen;
	
	/**
	 * 犯规
	 */
					
	private Integer fangui;
	
	/**
	 * 暂停
	 */
					
	private Integer zanting;
	
	/**
	 * 记录时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date jilushijian;
	
	/**
	 * 裁判账号
	 */
					
	private String caipanzhanghao;
	
	/**
	 * 裁判姓名
	 */
					
	private String caipanxingming;
	
	/**
	 * 跨表用户id
	 */
					
	private Long crossuserid;
	
	/**
	 * 跨表主键id
	 */
					
	private Long crossrefid;
	

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
	 * 设置：得分
	 */
	public void setDefen(Integer defen) {
		this.defen = defen;
	}
	/**
	 * 获取：得分
	 */
	public Integer getDefen() {
		return defen;
	}
	/**
	 * 设置：犯规
	 */
	public void setFangui(Integer fangui) {
		this.fangui = fangui;
	}
	/**
	 * 获取：犯规
	 */
	public Integer getFangui() {
		return fangui;
	}
	/**
	 * 设置：暂停
	 */
	public void setZanting(Integer zanting) {
		this.zanting = zanting;
	}
	/**
	 * 获取：暂停
	 */
	public Integer getZanting() {
		return zanting;
	}
	/**
	 * 设置：记录时间
	 */
	public void setJilushijian(Date jilushijian) {
		this.jilushijian = jilushijian;
	}
	/**
	 * 获取：记录时间
	 */
	public Date getJilushijian() {
		return jilushijian;
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
	 * 设置：跨表用户id
	 */
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
	/**
	 * 设置：跨表主键id
	 */
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}

}
