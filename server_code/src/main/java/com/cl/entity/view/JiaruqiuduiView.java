package com.cl.entity.view;

import com.cl.entity.JiaruqiuduiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 加入球队
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@TableName("jiaruqiudui")
public class JiaruqiuduiView  extends JiaruqiuduiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JiaruqiuduiView(){
	}
 
 	public JiaruqiuduiView(JiaruqiuduiEntity jiaruqiuduiEntity){
 	try {
			BeanUtils.copyProperties(this, jiaruqiuduiEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
