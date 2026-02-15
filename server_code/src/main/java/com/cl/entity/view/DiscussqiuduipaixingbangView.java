package com.cl.entity.view;

import com.cl.entity.DiscussqiuduipaixingbangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 球队排行榜评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
@TableName("discussqiuduipaixingbang")
public class DiscussqiuduipaixingbangView  extends DiscussqiuduipaixingbangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussqiuduipaixingbangView(){
	}
 
 	public DiscussqiuduipaixingbangView(DiscussqiuduipaixingbangEntity discussqiuduipaixingbangEntity){
 	try {
			BeanUtils.copyProperties(this, discussqiuduipaixingbangEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
