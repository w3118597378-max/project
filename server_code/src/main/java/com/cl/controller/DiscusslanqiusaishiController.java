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

import com.cl.entity.DiscusslanqiusaishiEntity;
import com.cl.entity.view.DiscusslanqiusaishiView;

import com.cl.service.DiscusslanqiusaishiService;
import com.cl.service.TokenService;

/**
 * 篮球赛事评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
@RestController
@RequestMapping("/discusslanqiusaishi")
public class DiscusslanqiusaishiController {
    @Autowired
    private DiscusslanqiusaishiService discusslanqiusaishiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusslanqiusaishiEntity discusslanqiusaishi,
                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<DiscusslanqiusaishiEntity> ew = new EntityWrapper<DiscusslanqiusaishiEntity>();
                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = discusslanqiusaishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslanqiusaishi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscusslanqiusaishiEntity discusslanqiusaishi,
		HttpServletRequest request){
        EntityWrapper<DiscusslanqiusaishiEntity> ew = new EntityWrapper<DiscusslanqiusaishiEntity>();

		PageUtils page = discusslanqiusaishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslanqiusaishi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusslanqiusaishiEntity discusslanqiusaishi){
       	EntityWrapper<DiscusslanqiusaishiEntity> ew = new EntityWrapper<DiscusslanqiusaishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusslanqiusaishi, MPUtil.camelToSnake("discusslanqiusaishi")));
        return R.ok().put("data", discusslanqiusaishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusslanqiusaishiEntity discusslanqiusaishi){
        EntityWrapper< DiscusslanqiusaishiEntity> ew = new EntityWrapper< DiscusslanqiusaishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusslanqiusaishi, MPUtil.camelToSnake("discusslanqiusaishi")));
		DiscusslanqiusaishiView discusslanqiusaishiView =  discusslanqiusaishiService.selectView(ew);
		return R.ok("查询篮球赛事评论表成功").put("data", discusslanqiusaishiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusslanqiusaishiEntity discusslanqiusaishi = discusslanqiusaishiService.selectById(id);
		discusslanqiusaishi = discusslanqiusaishiService.selectView(new EntityWrapper<DiscusslanqiusaishiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discusslanqiusaishi,deSens);
        return R.ok().put("data", discusslanqiusaishi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusslanqiusaishiEntity discusslanqiusaishi = discusslanqiusaishiService.selectById(id);
		discusslanqiusaishi = discusslanqiusaishiService.selectView(new EntityWrapper<DiscusslanqiusaishiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discusslanqiusaishi,deSens);
        return R.ok().put("data", discusslanqiusaishi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusslanqiusaishiEntity discusslanqiusaishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusslanqiusaishi);
        discusslanqiusaishiService.insert(discusslanqiusaishi);
        return R.ok().put("data",discusslanqiusaishi.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusslanqiusaishiEntity discusslanqiusaishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discusslanqiusaishi);
        discusslanqiusaishiService.insert(discusslanqiusaishi);
        return R.ok();
    }
     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscusslanqiusaishiEntity discusslanqiusaishi = discusslanqiusaishiService.selectOne(new EntityWrapper<DiscusslanqiusaishiEntity>().eq("", username));
        return R.ok().put("data", discusslanqiusaishi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusslanqiusaishiEntity discusslanqiusaishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusslanqiusaishi);
        discusslanqiusaishiService.updateById(discusslanqiusaishi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusslanqiusaishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusslanqiusaishiEntity discusslanqiusaishi, HttpServletRequest request,String pre){
        EntityWrapper<DiscusslanqiusaishiEntity> ew = new EntityWrapper<DiscusslanqiusaishiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discusslanqiusaishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusslanqiusaishi), params), params));
        return R.ok().put("data", page);
    }








}
