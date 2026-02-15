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

import com.cl.entity.BisaibaomingEntity;
import com.cl.entity.view.BisaibaomingView;

import com.cl.service.BisaibaomingService;
import com.cl.service.TokenService;

/**
 * 比赛报名
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:39
 */
@RestController
@RequestMapping("/bisaibaoming")
public class BisaibaomingController {
    @Autowired
    private BisaibaomingService bisaibaomingService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BisaibaomingEntity bisaibaoming,
                                                                                                                                                                                                                        HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                        if(tableName.equals("qiuduiduizhang")) {
                    bisaibaoming.setDuizhangzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                        if(tableName.equals("caipanyuan")) {
                    bisaibaoming.setCaipanzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                    EntityWrapper<BisaibaomingEntity> ew = new EntityWrapper<BisaibaomingEntity>();
                                                                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = bisaibaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaibaoming), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BisaibaomingEntity bisaibaoming,
		HttpServletRequest request){
        EntityWrapper<BisaibaomingEntity> ew = new EntityWrapper<BisaibaomingEntity>();

		PageUtils page = bisaibaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bisaibaoming), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BisaibaomingEntity bisaibaoming){
       	EntityWrapper<BisaibaomingEntity> ew = new EntityWrapper<BisaibaomingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bisaibaoming, MPUtil.camelToSnake("bisaibaoming")));
        return R.ok().put("data", bisaibaomingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BisaibaomingEntity bisaibaoming){
        EntityWrapper< BisaibaomingEntity> ew = new EntityWrapper< BisaibaomingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bisaibaoming, MPUtil.camelToSnake("bisaibaoming")));
		BisaibaomingView bisaibaomingView =  bisaibaomingService.selectView(ew);
		return R.ok("查询比赛报名成功").put("data", bisaibaomingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BisaibaomingEntity bisaibaoming = bisaibaomingService.selectById(id);
		bisaibaoming = bisaibaomingService.selectView(new EntityWrapper<BisaibaomingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaibaoming,deSens);
        return R.ok().put("data", bisaibaoming);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BisaibaomingEntity bisaibaoming = bisaibaomingService.selectById(id);
		bisaibaoming = bisaibaomingService.selectView(new EntityWrapper<BisaibaomingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bisaibaoming,deSens);
        return R.ok().put("data", bisaibaoming);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BisaibaomingEntity bisaibaoming, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisaibaoming);
        bisaibaomingService.insert(bisaibaoming);
        return R.ok().put("data",bisaibaoming.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BisaibaomingEntity bisaibaoming, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bisaibaoming);
        bisaibaomingService.insert(bisaibaoming);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BisaibaomingEntity bisaibaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bisaibaoming);
        bisaibaomingService.updateById(bisaibaoming);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<BisaibaomingEntity> list = new ArrayList<BisaibaomingEntity>();
        for(Long id : ids) {
            BisaibaomingEntity bisaibaoming = bisaibaomingService.selectById(id);
            bisaibaoming.setSfsh(sfsh);
            bisaibaoming.setShhf(shhf);
            list.add(bisaibaoming);
        }
        bisaibaomingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bisaibaomingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
