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

import com.cl.entity.JinjishenqingEntity;
import com.cl.entity.view.JinjishenqingView;

import com.cl.service.JinjishenqingService;
import com.cl.service.TokenService;

/**
 * 晋级申请
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/jinjishenqing")
public class JinjishenqingController {
    @Autowired
    private JinjishenqingService jinjishenqingService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JinjishenqingEntity jinjishenqing,
                                                                                                                                                                                                    HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                            if(tableName.equals("caipanyuan")) {
                    jinjishenqing.setCaipanzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                    if(tableName.equals("qiuduiduizhang")) {
                    jinjishenqing.setDuizhangzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                    EntityWrapper<JinjishenqingEntity> ew = new EntityWrapper<JinjishenqingEntity>();
                                                                                                                                                                                                                                                                                                                
        
        
        PageUtils page = jinjishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinjishenqing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JinjishenqingEntity jinjishenqing,
		HttpServletRequest request){
        EntityWrapper<JinjishenqingEntity> ew = new EntityWrapper<JinjishenqingEntity>();

		PageUtils page = jinjishenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jinjishenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JinjishenqingEntity jinjishenqing){
       	EntityWrapper<JinjishenqingEntity> ew = new EntityWrapper<JinjishenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jinjishenqing, MPUtil.camelToSnake("jinjishenqing")));
        return R.ok().put("data", jinjishenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JinjishenqingEntity jinjishenqing){
        EntityWrapper< JinjishenqingEntity> ew = new EntityWrapper< JinjishenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jinjishenqing, MPUtil.camelToSnake("jinjishenqing")));
		JinjishenqingView jinjishenqingView =  jinjishenqingService.selectView(ew);
		return R.ok("查询晋级申请成功").put("data", jinjishenqingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JinjishenqingEntity jinjishenqing = jinjishenqingService.selectById(id);
		jinjishenqing = jinjishenqingService.selectView(new EntityWrapper<JinjishenqingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinjishenqing,deSens);
        return R.ok().put("data", jinjishenqing);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JinjishenqingEntity jinjishenqing = jinjishenqingService.selectById(id);
		jinjishenqing = jinjishenqingService.selectView(new EntityWrapper<JinjishenqingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jinjishenqing,deSens);
        return R.ok().put("data", jinjishenqing);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JinjishenqingEntity jinjishenqing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jinjishenqing);
        jinjishenqingService.insert(jinjishenqing);
        return R.ok().put("data",jinjishenqing.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JinjishenqingEntity jinjishenqing, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jinjishenqing);
        jinjishenqingService.insert(jinjishenqing);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JinjishenqingEntity jinjishenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jinjishenqing);
        jinjishenqingService.updateById(jinjishenqing);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<JinjishenqingEntity> list = new ArrayList<JinjishenqingEntity>();
        for(Long id : ids) {
            JinjishenqingEntity jinjishenqing = jinjishenqingService.selectById(id);
            jinjishenqing.setSfsh(sfsh);
            jinjishenqing.setShhf(shhf);
            list.add(jinjishenqing);
        }
        jinjishenqingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jinjishenqingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
