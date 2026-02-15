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
 * 比赛成绩
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("bisaichengji")
public class BisaichengjiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BisaichengjiEntity() {
		
	}
	
	public BisaichengjiEntity(T t) {
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
	 * 比赛场地
	 */
					
	private String bisaichangdi;
	
	/**
	 * 比赛时间
	 */
					
	private String bisaishijian;
	
	/**
	 * 球队名称
	 */
					
	private String qiuduimingcheng;
	
	/**
	 * 球队积分
	 */
					
	private Integer qiuduijifen;
	
	/**
	 * 比赛排名
	 */
					
	private Integer bisaipaiming;
	
	/**
	 * 成绩比分
	 */
					
	private String chengjibifen;
	
	/**
	 * 比赛结果
	 */
					
	private String bisaijieguo;
	
	/**
	 * 特殊事件
	 */
					
	private String teshushijian;
	
	/**
	 * 判罚说明
	 */
					
	private String panfashuoming;
	
	/**
	 * 发布时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date fabushijian;
	
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
	 * 设置：球队积分
	 */
	public void setQiuduijifen(Integer qiuduijifen) {
		this.qiuduijifen = qiuduijifen;
	}
	/**
	 * 获取：球队积分
	 */
	public Integer getQiuduijifen() {
		return qiuduijifen;
	}
	/**
	 * 设置：比赛排名
	 */
	public void setBisaipaiming(Integer bisaipaiming) {
		this.bisaipaiming = bisaipaiming;
	}
	/**
	 * 获取：比赛排名
	 */
	public Integer getBisaipaiming() {
		return bisaipaiming;
	}
	/**
	 * 设置：成绩比分
	 */
	public void setChengjibifen(String chengjibifen) {
		this.chengjibifen = chengjibifen;
	}
	/**
	 * 获取：成绩比分
	 */
	public String getChengjibifen() {
		return chengjibifen;
	}
	/**
	 * 设置：比赛结果
	 */
	public void setBisaijieguo(String bisaijieguo) {
		this.bisaijieguo = bisaijieguo;
	}
	/**
	 * 获取：比赛结果
	 */
	public String getBisaijieguo() {
		return bisaijieguo;
	}
	/**
	 * 设置：特殊事件
	 */
	public void setTeshushijian(String teshushijian) {
		this.teshushijian = teshushijian;
	}
	/**
	 * 获取：特殊事件
	 */
	public String getTeshushijian() {
		return teshushijian;
	}
	/**
	 * 设置：判罚说明
	 */
	public void setPanfashuoming(String panfashuoming) {
		this.panfashuoming = panfashuoming;
	}
	/**
	 * 获取：判罚说明
	 */
	public String getPanfashuoming() {
		return panfashuoming;
	}
	/**
	 * 设置：发布时间
	 */
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
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
