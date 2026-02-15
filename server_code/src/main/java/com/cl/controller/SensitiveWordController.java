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

import com.cl.entity.SensitiveWordEntity;
import com.cl.entity.view.SensitiveWordView;

import com.cl.service.SensitiveWordService;
import com.cl.service.TokenService;

/**
 * 敏感词
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/sensitiveword")
public class SensitiveWordController {
    @Autowired
    private SensitiveWordService sensitiveWordService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,SensitiveWordEntity sensitiveWord,
                        HttpServletRequest request){
                                    EntityWrapper<SensitiveWordEntity> ew = new EntityWrapper<SensitiveWordEntity>();
                            
        
        
        PageUtils page = sensitiveWordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, sensitiveWord), params), params));
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
    public R list(@RequestParam Map<String, Object> params,SensitiveWordEntity sensitiveWord,
		HttpServletRequest request){
        EntityWrapper<SensitiveWordEntity> ew = new EntityWrapper<SensitiveWordEntity>();

		PageUtils page = sensitiveWordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, sensitiveWord), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( SensitiveWordEntity sensitiveWord){
       	EntityWrapper<SensitiveWordEntity> ew = new EntityWrapper<SensitiveWordEntity>();
      	ew.allEq(MPUtil.allEQMapPre( sensitiveWord, MPUtil.camelToSnake("sensitiveWord")));
        return R.ok().put("data", sensitiveWordService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(SensitiveWordEntity sensitiveWord){
        EntityWrapper< SensitiveWordEntity> ew = new EntityWrapper< SensitiveWordEntity>();
 		ew.allEq(MPUtil.allEQMapPre( sensitiveWord, MPUtil.camelToSnake("sensitiveWord")));
		SensitiveWordView sensitiveWordView =  sensitiveWordService.selectView(ew);
		return R.ok("查询敏感词成功").put("data", sensitiveWordView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        SensitiveWordEntity sensitiveWord = sensitiveWordService.selectById(id);
		sensitiveWord = sensitiveWordService.selectView(new EntityWrapper<SensitiveWordEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(sensitiveWord,deSens);
        return R.ok().put("data", sensitiveWord);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        SensitiveWordEntity sensitiveWord = sensitiveWordService.selectById(id);
		sensitiveWord = sensitiveWordService.selectView(new EntityWrapper<SensitiveWordEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(sensitiveWord,deSens);
        return R.ok().put("data", sensitiveWord);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SensitiveWordEntity sensitiveWord, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(sensitiveWord);
        sensitiveWordService.insert(sensitiveWord);
        return R.ok().put("data",sensitiveWord.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody SensitiveWordEntity sensitiveWord, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(sensitiveWord);
        sensitiveWordService.insert(sensitiveWord);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody SensitiveWordEntity sensitiveWord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(sensitiveWord);
        sensitiveWordService.updateById(sensitiveWord);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        sensitiveWordService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
