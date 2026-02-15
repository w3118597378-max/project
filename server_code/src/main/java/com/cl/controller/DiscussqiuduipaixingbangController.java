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

import com.cl.entity.DiscussqiuduipaixingbangEntity;
import com.cl.entity.view.DiscussqiuduipaixingbangView;

import com.cl.service.DiscussqiuduipaixingbangService;
import com.cl.service.TokenService;

/**
 * 球队排行榜评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:40
 */
@RestController
@RequestMapping("/discussqiuduipaixingbang")
public class DiscussqiuduipaixingbangController {
    @Autowired
    private DiscussqiuduipaixingbangService discussqiuduipaixingbangService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussqiuduipaixingbangEntity discussqiuduipaixingbang,
                                                                                                                                                                                        HttpServletRequest request){
                                    EntityWrapper<DiscussqiuduipaixingbangEntity> ew = new EntityWrapper<DiscussqiuduipaixingbangEntity>();
                                                                                                                                                                                                                                                                                            
        
        
        PageUtils page = discussqiuduipaixingbangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqiuduipaixingbang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussqiuduipaixingbangEntity discussqiuduipaixingbang,
		HttpServletRequest request){
        EntityWrapper<DiscussqiuduipaixingbangEntity> ew = new EntityWrapper<DiscussqiuduipaixingbangEntity>();

		PageUtils page = discussqiuduipaixingbangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqiuduipaixingbang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussqiuduipaixingbangEntity discussqiuduipaixingbang){
       	EntityWrapper<DiscussqiuduipaixingbangEntity> ew = new EntityWrapper<DiscussqiuduipaixingbangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussqiuduipaixingbang, MPUtil.camelToSnake("discussqiuduipaixingbang")));
        return R.ok().put("data", discussqiuduipaixingbangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussqiuduipaixingbangEntity discussqiuduipaixingbang){
        EntityWrapper< DiscussqiuduipaixingbangEntity> ew = new EntityWrapper< DiscussqiuduipaixingbangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussqiuduipaixingbang, MPUtil.camelToSnake("discussqiuduipaixingbang")));
		DiscussqiuduipaixingbangView discussqiuduipaixingbangView =  discussqiuduipaixingbangService.selectView(ew);
		return R.ok("查询球队排行榜评论表成功").put("data", discussqiuduipaixingbangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussqiuduipaixingbangEntity discussqiuduipaixingbang = discussqiuduipaixingbangService.selectById(id);
		discussqiuduipaixingbang = discussqiuduipaixingbangService.selectView(new EntityWrapper<DiscussqiuduipaixingbangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqiuduipaixingbang,deSens);
        return R.ok().put("data", discussqiuduipaixingbang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussqiuduipaixingbangEntity discussqiuduipaixingbang = discussqiuduipaixingbangService.selectById(id);
		discussqiuduipaixingbang = discussqiuduipaixingbangService.selectView(new EntityWrapper<DiscussqiuduipaixingbangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqiuduipaixingbang,deSens);
        return R.ok().put("data", discussqiuduipaixingbang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussqiuduipaixingbangEntity discussqiuduipaixingbang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussqiuduipaixingbang);
        discussqiuduipaixingbangService.insert(discussqiuduipaixingbang);
        return R.ok().put("data",discussqiuduipaixingbang.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussqiuduipaixingbangEntity discussqiuduipaixingbang, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussqiuduipaixingbang);
        discussqiuduipaixingbangService.insert(discussqiuduipaixingbang);
        return R.ok();
    }
     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussqiuduipaixingbangEntity discussqiuduipaixingbang = discussqiuduipaixingbangService.selectOne(new EntityWrapper<DiscussqiuduipaixingbangEntity>().eq("", username));
        return R.ok().put("data", discussqiuduipaixingbang);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussqiuduipaixingbangEntity discussqiuduipaixingbang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqiuduipaixingbang);
        discussqiuduipaixingbangService.updateById(discussqiuduipaixingbang);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussqiuduipaixingbangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussqiuduipaixingbangEntity discussqiuduipaixingbang, HttpServletRequest request,String pre){
        EntityWrapper<DiscussqiuduipaixingbangEntity> ew = new EntityWrapper<DiscussqiuduipaixingbangEntity>();
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
		PageUtils page = discussqiuduipaixingbangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqiuduipaixingbang), params), params));
        return R.ok().put("data", page);
    }








}
