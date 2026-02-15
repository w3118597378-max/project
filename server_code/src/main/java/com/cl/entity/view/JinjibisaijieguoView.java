package com.cl.entity.view;

import com.cl.entity.JinjibisaijieguoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 晋级比赛结果
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@TableName("jinjibisaijieguo")
public class JinjibisaijieguoView  extends JinjibisaijieguoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JinjibisaijieguoView(){
	}
 
 	public JinjibisaijieguoView(JinjibisaijieguoEntity jinjibisaijieguoEntity){
 	try {
			BeanUtils.copyProperties(this, jinjibisaijieguoEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
