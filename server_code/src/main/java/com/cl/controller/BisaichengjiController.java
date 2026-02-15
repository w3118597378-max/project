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

import com.cl.entity.BisaichengjiEntity;
import com.cl.entity.view.BisaichengjiView;

import com.cl.service.BisaichengjiService;
import com.cl.service.TokenService;

/**
 * 比赛成绩
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/bisaichengji")
public class BisaichengjiController {
    @Autowired
    private BisaichengjiService bisaichengjiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BisaichengjiEntity bisaichengji,
                                                                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("caipanyuan")) {
                    bisaichengji.setCaipanzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<BisaichengjiEntity> ew = new EntityWrapper<BisaichengjiEntity>();
                                                                                                                                                                                                                                                                                                                                    
        
        
        PageUtils page = bisaichengjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaichengji), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BisaichengjiEntity bisaichengji,
		HttpServletRequest request){
        EntityWrapper<BisaichengjiEntity> ew = new EntityWrapper<BisaichengjiEntity>();

		PageUtils page = bisaichengjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaichengji), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BisaichengjiEntity bisaichengji){
       	EntityWrapper<BisaichengjiEntity> ew = new EntityWrapper<BisaichengjiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bisaichengji, MPUtil.camelToSnake("bisaichengji")));
        return R.ok().put("data", bisaichengjiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BisaichengjiEntity bisaichengji){
        EntityWrapper< BisaichengjiEntity> ew = new EntityWrapper< BisaichengjiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bisaichengji, MPUtil.camelToSnake("bisaichengji")));
		BisaichengjiView bisaichengjiView =  bisaichengjiService.selectView(ew);
		return R.ok("查询比赛成绩成功").put("data", bisaichengjiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BisaichengjiEntity bisaichengji = bisaichengjiService.selectById(id);
		bisaichengji = bisaichengjiService.selectView(new EntityWrapper<BisaichengjiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaichengji,deSens);
        return R.ok().put("data", bisaichengji);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BisaichengjiEntity bisaichengji = bisaichengjiService.selectById(id);
		bisaichengji = bisaichengjiService.selectView(new EntityWrapper<BisaichengjiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaichengji,deSens);
        return R.ok().put("data", bisaichengji);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BisaichengjiEntity bisaichengji, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisaichengji);
        bisaichengjiService.insert(bisaichengji);
        return R.ok().put("data",bisaichengji.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BisaichengjiEntity bisaichengji, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisaichengji);
        bisaichengjiService.insert(bisaichengji);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BisaichengjiEntity bisaichengji, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisaichengji);
        bisaichengjiService.updateById(bisaichengji);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bisaichengjiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
