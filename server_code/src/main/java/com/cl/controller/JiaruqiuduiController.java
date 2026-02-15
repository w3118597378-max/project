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

import com.cl.entity.JiaruqiuduiEntity;
import com.cl.entity.view.JiaruqiuduiView;

import com.cl.service.JiaruqiuduiService;
import com.cl.service.TokenService;

/**
 * 加入球队
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/jiaruqiudui")
public class JiaruqiuduiController {
    @Autowired
    private JiaruqiuduiService jiaruqiuduiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaruqiuduiEntity jiaruqiudui,
                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                        if(tableName.equals("qiuduiduizhang")) {
                    jiaruqiudui.setDuizhangzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                        if(tableName.equals("cansaixuesheng")) {
                    jiaruqiudui.setXuehao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                EntityWrapper<JiaruqiuduiEntity> ew = new EntityWrapper<JiaruqiuduiEntity>();
                                                                                                                                                                                                                                                    
        
        
        PageUtils page = jiaruqiuduiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaruqiudui), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiaruqiuduiEntity jiaruqiudui,
		HttpServletRequest request){
        EntityWrapper<JiaruqiuduiEntity> ew = new EntityWrapper<JiaruqiuduiEntity>();

		PageUtils page = jiaruqiuduiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaruqiudui), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaruqiuduiEntity jiaruqiudui){
       	EntityWrapper<JiaruqiuduiEntity> ew = new EntityWrapper<JiaruqiuduiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaruqiudui, MPUtil.camelToSnake("jiaruqiudui")));
        return R.ok().put("data", jiaruqiuduiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaruqiuduiEntity jiaruqiudui){
        EntityWrapper< JiaruqiuduiEntity> ew = new EntityWrapper< JiaruqiuduiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaruqiudui, MPUtil.camelToSnake("jiaruqiudui")));
		JiaruqiuduiView jiaruqiuduiView =  jiaruqiuduiService.selectView(ew);
		return R.ok("查询加入球队成功").put("data", jiaruqiuduiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaruqiuduiEntity jiaruqiudui = jiaruqiuduiService.selectById(id);
		jiaruqiudui = jiaruqiuduiService.selectView(new EntityWrapper<JiaruqiuduiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaruqiudui,deSens);
        return R.ok().put("data", jiaruqiudui);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaruqiuduiEntity jiaruqiudui = jiaruqiuduiService.selectById(id);
		jiaruqiudui = jiaruqiuduiService.selectView(new EntityWrapper<JiaruqiuduiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaruqiudui,deSens);
        return R.ok().put("data", jiaruqiudui);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaruqiuduiEntity jiaruqiudui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaruqiudui);
        jiaruqiuduiService.insert(jiaruqiudui);
        return R.ok().put("data",jiaruqiudui.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaruqiuduiEntity jiaruqiudui, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiaruqiudui);
        jiaruqiuduiService.insert(jiaruqiudui);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaruqiuduiEntity jiaruqiudui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaruqiudui);
        jiaruqiuduiService.updateById(jiaruqiudui);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<JiaruqiuduiEntity> list = new ArrayList<JiaruqiuduiEntity>();
        for(Long id : ids) {
            JiaruqiuduiEntity jiaruqiudui = jiaruqiuduiService.selectById(id);
            jiaruqiudui.setSfsh(sfsh);
            jiaruqiudui.setShhf(shhf);
            list.add(jiaruqiudui);
        }
        jiaruqiuduiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaruqiuduiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
