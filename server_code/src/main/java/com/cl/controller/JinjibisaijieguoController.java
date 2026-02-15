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

import com.cl.entity.JinjibisaijieguoEntity;
import com.cl.entity.view.JinjibisaijieguoView;

import com.cl.service.JinjibisaijieguoService;
import com.cl.service.TokenService;

/**
 * 晋级比赛结果
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/jinjibisaijieguo")
public class JinjibisaijieguoController {
    @Autowired
    private JinjibisaijieguoService jinjibisaijieguoService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JinjibisaijieguoEntity jinjibisaijieguo,
                                                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                            if(tableName.equals("caipanyuan")) {
                    jinjibisaijieguo.setCaipanzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<JinjibisaijieguoEntity> ew = new EntityWrapper<JinjibisaijieguoEntity>();
                                                                                                                                                                                                                                                                                                    
        
        
        PageUtils page = jinjibisaijieguoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinjibisaijieguo), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JinjibisaijieguoEntity jinjibisaijieguo,
		HttpServletRequest request){
        EntityWrapper<JinjibisaijieguoEntity> ew = new EntityWrapper<JinjibisaijieguoEntity>();

		PageUtils page = jinjibisaijieguoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinjibisaijieguo), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JinjibisaijieguoEntity jinjibisaijieguo){
       	EntityWrapper<JinjibisaijieguoEntity> ew = new EntityWrapper<JinjibisaijieguoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jinjibisaijieguo, MPUtil.camelToSnake("jinjibisaijieguo")));
        return R.ok().put("data", jinjibisaijieguoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JinjibisaijieguoEntity jinjibisaijieguo){
        EntityWrapper< JinjibisaijieguoEntity> ew = new EntityWrapper< JinjibisaijieguoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jinjibisaijieguo, MPUtil.camelToSnake("jinjibisaijieguo")));
		JinjibisaijieguoView jinjibisaijieguoView =  jinjibisaijieguoService.selectView(ew);
		return R.ok("查询晋级比赛结果成功").put("data", jinjibisaijieguoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JinjibisaijieguoEntity jinjibisaijieguo = jinjibisaijieguoService.selectById(id);
		jinjibisaijieguo = jinjibisaijieguoService.selectView(new EntityWrapper<JinjibisaijieguoEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinjibisaijieguo,deSens);
        return R.ok().put("data", jinjibisaijieguo);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JinjibisaijieguoEntity jinjibisaijieguo = jinjibisaijieguoService.selectById(id);
		jinjibisaijieguo = jinjibisaijieguoService.selectView(new EntityWrapper<JinjibisaijieguoEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinjibisaijieguo,deSens);
        return R.ok().put("data", jinjibisaijieguo);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JinjibisaijieguoEntity jinjibisaijieguo, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jinjibisaijieguo);
        jinjibisaijieguoService.insert(jinjibisaijieguo);
        return R.ok().put("data",jinjibisaijieguo.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JinjibisaijieguoEntity jinjibisaijieguo, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jinjibisaijieguo);
        jinjibisaijieguoService.insert(jinjibisaijieguo);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JinjibisaijieguoEntity jinjibisaijieguo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jinjibisaijieguo);
        jinjibisaijieguoService.updateById(jinjibisaijieguo);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jinjibisaijieguoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
