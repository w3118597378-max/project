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

import com.cl.entity.LanqiusaishiEntity;
import com.cl.entity.view.LanqiusaishiView;

import com.cl.service.LanqiusaishiService;
import com.cl.service.TokenService;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 篮球赛事
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/lanqiusaishi")
public class LanqiusaishiController {
    @Autowired
    private LanqiusaishiService lanqiusaishiService;

    @Autowired
    private StoreupService storeupService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LanqiusaishiEntity lanqiusaishi,
                                                                                                                                                                HttpServletRequest request){
                                    EntityWrapper<LanqiusaishiEntity> ew = new EntityWrapper<LanqiusaishiEntity>();
                                                                                                                                                                                                                                                    
        
        
        PageUtils page = lanqiusaishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lanqiusaishi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,LanqiusaishiEntity lanqiusaishi,
		HttpServletRequest request){
        EntityWrapper<LanqiusaishiEntity> ew = new EntityWrapper<LanqiusaishiEntity>();

		PageUtils page = lanqiusaishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lanqiusaishi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LanqiusaishiEntity lanqiusaishi){
       	EntityWrapper<LanqiusaishiEntity> ew = new EntityWrapper<LanqiusaishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( lanqiusaishi, MPUtil.camelToSnake("lanqiusaishi")));
        return R.ok().put("data", lanqiusaishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LanqiusaishiEntity lanqiusaishi){
        EntityWrapper< LanqiusaishiEntity> ew = new EntityWrapper< LanqiusaishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( lanqiusaishi, MPUtil.camelToSnake("lanqiusaishi")));
		LanqiusaishiView lanqiusaishiView =  lanqiusaishiService.selectView(ew);
		return R.ok("查询篮球赛事成功").put("data", lanqiusaishiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LanqiusaishiEntity lanqiusaishi = lanqiusaishiService.selectById(id);
		lanqiusaishi = lanqiusaishiService.selectView(new EntityWrapper<LanqiusaishiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(lanqiusaishi,deSens);
        return R.ok().put("data", lanqiusaishi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LanqiusaishiEntity lanqiusaishi = lanqiusaishiService.selectById(id);
		lanqiusaishi = lanqiusaishiService.selectView(new EntityWrapper<LanqiusaishiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(lanqiusaishi,deSens);
        return R.ok().put("data", lanqiusaishi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LanqiusaishiEntity lanqiusaishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lanqiusaishi);
        lanqiusaishiService.insert(lanqiusaishi);
        return R.ok().put("data",lanqiusaishi.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LanqiusaishiEntity lanqiusaishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(lanqiusaishi);
        lanqiusaishiService.insert(lanqiusaishi);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LanqiusaishiEntity lanqiusaishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lanqiusaishi);
        lanqiusaishiService.updateById(lanqiusaishi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        lanqiusaishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
