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
 * 晋级比赛结果
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("jinjibisaijieguo")
public class JinjibisaijieguoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JinjibisaijieguoEntity() {
		
	}
	
	public JinjibisaijieguoEntity(T t) {
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
	 * 晋级赛事
	 */
					
	private String jinjisaishi;
	
	/**
	 * 主队球队
	 */
					
	private String zhuduiqiudui;
	
	/**
	 * 客队球队
	 */
					
	private String keduiqiudui;
	
	/**
	 * 主场球员
	 */
					
	private String zhuchangqiuyuan;
	
	/**
	 * 客场球员
	 */
					
	private String kechangqiuyuan;
	
	/**
	 * 最终比分
	 */
					
	private String zuizhongbifen;
	
	/**
	 * 争议情况
	 */
					
	private String zhengyiqingkuang;
	
	/**
	 * 提交时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date tijiaoshijian;
	
	/**
	 * 裁判账号
	 */
					
	private String caipanzhanghao;
	
	/**
	 * 裁判姓名
	 */
					
	private String caipanxingming;
	

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
	 * 设置：晋级赛事
	 */
	public void setJinjisaishi(String jinjisaishi) {
		this.jinjisaishi = jinjisaishi;
	}
	/**
	 * 获取：晋级赛事
	 */
	public String getJinjisaishi() {
		return jinjisaishi;
	}
	/**
	 * 设置：主队球队
	 */
	public void setZhuduiqiudui(String zhuduiqiudui) {
		this.zhuduiqiudui = zhuduiqiudui;
	}
	/**
	 * 获取：主队球队
	 */
	public String getZhuduiqiudui() {
		return zhuduiqiudui;
	}
	/**
	 * 设置：客队球队
	 */
	public void setKeduiqiudui(String keduiqiudui) {
		this.keduiqiudui = keduiqiudui;
	}
	/**
	 * 获取：客队球队
	 */
	public String getKeduiqiudui() {
		return keduiqiudui;
	}
	/**
	 * 设置：主场球员
	 */
	public void setZhuchangqiuyuan(String zhuchangqiuyuan) {
		this.zhuchangqiuyuan = zhuchangqiuyuan;
	}
	/**
	 * 获取：主场球员
	 */
	public String getZhuchangqiuyuan() {
		return zhuchangqiuyuan;
	}
	/**
	 * 设置：客场球员
	 */
	public void setKechangqiuyuan(String kechangqiuyuan) {
		this.kechangqiuyuan = kechangqiuyuan;
	}
	/**
	 * 获取：客场球员
	 */
	public String getKechangqiuyuan() {
		return kechangqiuyuan;
	}
	/**
	 * 设置：最终比分
	 */
	public void setZuizhongbifen(String zuizhongbifen) {
		this.zuizhongbifen = zuizhongbifen;
	}
	/**
	 * 获取：最终比分
	 */
	public String getZuizhongbifen() {
		return zuizhongbifen;
	}
	/**
	 * 设置：争议情况
	 */
	public void setZhengyiqingkuang(String zhengyiqingkuang) {
		this.zhengyiqingkuang = zhengyiqingkuang;
	}
	/**
	 * 获取：争议情况
	 */
	public String getZhengyiqingkuang() {
		return zhengyiqingkuang;
	}
	/**
	 * 设置：提交时间
	 */
	public void setTijiaoshijian(Date tijiaoshijian) {
		this.tijiaoshijian = tijiaoshijian;
	}
	/**
	 * 获取：提交时间
	 */
	public Date getTijiaoshijian() {
		return tijiaoshijian;
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

}
