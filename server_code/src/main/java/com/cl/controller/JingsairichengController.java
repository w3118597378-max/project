package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.JingsairichengEntity;
import com.cl.entity.view.JingsairichengView;

import com.cl.service.JingsairichengService;
import com.cl.service.TokenService;

/**
 * 竞赛日程
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/jingsairicheng")
public class JingsairichengController {
    @Autowired
    private JingsairichengService jingsairichengService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JingsairichengEntity jingsairicheng,
                                                                                                                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<JingsairichengEntity> ew = new EntityWrapper<JingsairichengEntity>();
                                                                                                                                                                                                                                                                                                                                
        
        
        PageUtils page = jingsairichengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingsairicheng), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JingsairichengEntity jingsairicheng,
		HttpServletRequest request){
        EntityWrapper<JingsairichengEntity> ew = new EntityWrapper<JingsairichengEntity>();

		PageUtils page = jingsairichengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jingsairicheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JingsairichengEntity jingsairicheng){
       	EntityWrapper<JingsairichengEntity> ew = new EntityWrapper<JingsairichengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jingsairicheng, MPUtil.camelToSnake("jingsairicheng")));
        return R.ok().put("data", jingsairichengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JingsairichengEntity jingsairicheng){
        EntityWrapper< JingsairichengEntity> ew = new EntityWrapper< JingsairichengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jingsairicheng, MPUtil.camelToSnake("jingsairicheng")));
		JingsairichengView jingsairichengView =  jingsairichengService.selectView(ew);
		return R.ok("查询竞赛日程成功").put("data", jingsairichengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JingsairichengEntity jingsairicheng = jingsairichengService.selectById(id);
		jingsairicheng = jingsairichengService.selectView(new EntityWrapper<JingsairichengEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jingsairicheng,deSens);
        return R.ok().put("data", jingsairicheng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JingsairichengEntity jingsairicheng = jingsairichengService.selectById(id);
		jingsairicheng = jingsairichengService.selectView(new EntityWrapper<JingsairichengEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jingsairicheng,deSens);
        return R.ok().put("data", jingsairicheng);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JingsairichengEntity jingsairicheng, HttpServletRequest request){
        if(jingsairichengService.selectCount(new EntityWrapper<JingsairichengEntity>().eq("saishimingcheng", jingsairicheng.getSaishimingcheng()))>0) {
            return R.error("赛事名称已存在");
        }
    	//ValidatorUtils.validateEntity(jingsairicheng);
        jingsairichengService.insert(jingsairicheng);
        return R.ok().put("data",jingsairicheng.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JingsairichengEntity jingsairicheng, HttpServletRequest request){
        if(jingsairichengService.selectCount(new EntityWrapper<JingsairichengEntity>().eq("saishimingcheng", jingsairicheng.getSaishimingcheng()))>0) {
            return R.error("赛事名称已存在");
        }
    	//ValidatorUtils.validateEntity(jingsairicheng);
        jingsairichengService.insert(jingsairicheng);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JingsairichengEntity jingsairicheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jingsairicheng);
        jingsairichengService.updateById(jingsairicheng);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jingsairichengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
