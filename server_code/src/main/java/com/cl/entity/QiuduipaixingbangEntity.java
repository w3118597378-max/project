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
 * 球队排行榜
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@TableName("qiuduipaixingbang")
public class QiuduipaixingbangEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public QiuduipaixingbangEntity() {
		
	}
	
	public QiuduipaixingbangEntity(T t) {
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
	 * 教练名称
	 */
					
	private String jiaolianmingcheng;
	
	/**
	 * 球队名称
	 */
					
	private String qiuduimingcheng;
	
	/**
	 * 球队logo
	 */
					
	private String qiuduilogo;
	
	/**
	 * 球衣
	 */
					
	private String qiuyi;
	
	/**
	 * 球员人数
	 */
					
	private Integer qiuyuanrenshu;
	
	/**
	 * 积分
	 */
					
	private Integer jifen;
	
	/**
	 * 胜
	 */
					
	private Integer sheng;
	
	/**
	 * 负
	 */
					
	private Integer fu;
	
	/**
	 * 平
	 */
					
	private Integer ping;
	
	/**
	 * 胜率
	 */
					
	private String shenglv;
	
	/**
	 * 是否在招募
	 */
					
	private String shifouzaizhaomu;
	
	/**
	 * 创立时间
	 */
					
	private String chuanglishijian;
	
	/**
	 * 球队口号
	 */
					
	private String qiuduikouhao;
	
	/**
	 * 球员名单
	 */
					
	private String qiuyuanmingdan;
	
	/**
	 * 球队介绍
	 */
					
	private String qiuduijieshao;
	
	/**
	 * 队长账号
	 */
					
	private String duizhangzhanghao;
	
	/**
	 * 队长姓名
	 */
					
	private String duizhangxingming;
	
	/**
	 * 收藏数量
	 */
					
	private Integer storeupNumber;
	
	/**
	 * 评论数
	 */
					
	private Integer discussNumber;
	
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
	 * 设置：教练名称
	 */
	public void setJiaolianmingcheng(String jiaolianmingcheng) {
		this.jiaolianmingcheng = jiaolianmingcheng;
	}
	/**
	 * 获取：教练名称
	 */
	public String getJiaolianmingcheng() {
		return jiaolianmingcheng;
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
	 * 设置：球队logo
	 */
	public void setQiuduilogo(String qiuduilogo) {
		this.qiuduilogo = qiuduilogo;
	}
	/**
	 * 获取：球队logo
	 */
	public String getQiuduilogo() {
		return qiuduilogo;
	}
	/**
	 * 设置：球衣
	 */
	public void setQiuyi(String qiuyi) {
		this.qiuyi = qiuyi;
	}
	/**
	 * 获取：球衣
	 */
	public String getQiuyi() {
		return qiuyi;
	}
	/**
	 * 设置：球员人数
	 */
	public void setQiuyuanrenshu(Integer qiuyuanrenshu) {
		this.qiuyuanrenshu = qiuyuanrenshu;
	}
	/**
	 * 获取：球员人数
	 */
	public Integer getQiuyuanrenshu() {
		return qiuyuanrenshu;
	}
	/**
	 * 设置：积分
	 */
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}
	/**
	 * 获取：积分
	 */
	public Integer getJifen() {
		return jifen;
	}
	/**
	 * 设置：胜
	 */
	public void setSheng(Integer sheng) {
		this.sheng = sheng;
	}
	/**
	 * 获取：胜
	 */
	public Integer getSheng() {
		return sheng;
	}
	/**
	 * 设置：负
	 */
	public void setFu(Integer fu) {
		this.fu = fu;
	}
	/**
	 * 获取：负
	 */
	public Integer getFu() {
		return fu;
	}
	/**
	 * 设置：平
	 */
	public void setPing(Integer ping) {
		this.ping = ping;
	}
	/**
	 * 获取：平
	 */
	public Integer getPing() {
		return ping;
	}
	/**
	 * 设置：胜率
	 */
	public void setShenglv(String shenglv) {
		this.shenglv = shenglv;
	}
	/**
	 * 获取：胜率
	 */
	public String getShenglv() {
		return shenglv;
	}
	/**
	 * 设置：是否在招募
	 */
	public void setShifouzaizhaomu(String shifouzaizhaomu) {
		this.shifouzaizhaomu = shifouzaizhaomu;
	}
	/**
	 * 获取：是否在招募
	 */
	public String getShifouzaizhaomu() {
		return shifouzaizhaomu;
	}
	/**
	 * 设置：创立时间
	 */
	public void setChuanglishijian(String chuanglishijian) {
		this.chuanglishijian = chuanglishijian;
	}
	/**
	 * 获取：创立时间
	 */
	public String getChuanglishijian() {
		return chuanglishijian;
	}
	/**
	 * 设置：球队口号
	 */
	public void setQiuduikouhao(String qiuduikouhao) {
		this.qiuduikouhao = qiuduikouhao;
	}
	/**
	 * 获取：球队口号
	 */
	public String getQiuduikouhao() {
		return qiuduikouhao;
	}
	/**
	 * 设置：球员名单
	 */
	public void setQiuyuanmingdan(String qiuyuanmingdan) {
		this.qiuyuanmingdan = qiuyuanmingdan;
	}
	/**
	 * 获取：球员名单
	 */
	public String getQiuyuanmingdan() {
		return qiuyuanmingdan;
	}
	/**
	 * 设置：球队介绍
	 */
	public void setQiuduijieshao(String qiuduijieshao) {
		this.qiuduijieshao = qiuduijieshao;
	}
	/**
	 * 获取：球队介绍
	 */
	public String getQiuduijieshao() {
		return qiuduijieshao;
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
	 * 设置：收藏数量
	 */
	public void setStoreupNumber(Integer storeupNumber) {
		this.storeupNumber = storeupNumber;
	}
	/**
	 * 获取：收藏数量
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
