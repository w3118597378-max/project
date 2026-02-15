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

import com.cl.entity.TokenEntity;
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

import com.cl.entity.CaipanyuanEntity;
import com.cl.entity.view.CaipanyuanView;

import com.cl.service.CaipanyuanService;
import com.cl.service.TokenService;

/**
 * 裁判员
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/caipanyuan")
public class CaipanyuanController {
    @Autowired
    private CaipanyuanService caipanyuanService;



	@Autowired
	private TokenService tokenService;

	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		CaipanyuanEntity u = caipanyuanService.selectOne(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
    		String token = tokenService.generateToken(u.getId(), username,"caipanyuan",  "裁判员" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody CaipanyuanEntity caipanyuan){
    	//ValidatorUtils.validateEntity(caipanyuan);
                            CaipanyuanEntity u = caipanyuanService.selectOne(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", caipanyuan.getCaipanzhanghao()));
                                                                        		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		caipanyuan.setId(uId);
        caipanyuanService.insert(caipanyuan);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        return R.ok().put("data", caipanyuanService.selectView(new EntityWrapper<CaipanyuanEntity>().eq("id", id)));
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	CaipanyuanEntity u = caipanyuanService.selectOne(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        caipanyuanService.updateById(u);
        return R.ok("密码已重置为：123456");
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaipanyuanEntity caipanyuan,
                                                                                                        HttpServletRequest request){
                                    EntityWrapper<CaipanyuanEntity> ew = new EntityWrapper<CaipanyuanEntity>();
                                                                                                                                                            
        
        
        PageUtils page = caipanyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caipanyuan), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CaipanyuanEntity caipanyuan,
		HttpServletRequest request){
        EntityWrapper<CaipanyuanEntity> ew = new EntityWrapper<CaipanyuanEntity>();

		PageUtils page = caipanyuanService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caipanyuan), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaipanyuanEntity caipanyuan){
       	EntityWrapper<CaipanyuanEntity> ew = new EntityWrapper<CaipanyuanEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caipanyuan, MPUtil.camelToSnake("caipanyuan")));
        return R.ok().put("data", caipanyuanService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaipanyuanEntity caipanyuan){
        EntityWrapper< CaipanyuanEntity> ew = new EntityWrapper< CaipanyuanEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caipanyuan, MPUtil.camelToSnake("caipanyuan")));
		CaipanyuanView caipanyuanView =  caipanyuanService.selectView(ew);
		return R.ok("查询裁判员成功").put("data", caipanyuanView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaipanyuanEntity caipanyuan = caipanyuanService.selectById(id);
		caipanyuan = caipanyuanService.selectView(new EntityWrapper<CaipanyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caipanyuan,deSens);
        return R.ok().put("data", caipanyuan);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaipanyuanEntity caipanyuan = caipanyuanService.selectById(id);
		caipanyuan = caipanyuanService.selectView(new EntityWrapper<CaipanyuanEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caipanyuan,deSens);
        return R.ok().put("data", caipanyuan);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaipanyuanEntity caipanyuan, HttpServletRequest request){
        if(caipanyuanService.selectCount(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", caipanyuan.getCaipanzhanghao()))>0) {
            return R.error("裁判账号已存在");
        }
    	caipanyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caipanyuan);
        CaipanyuanEntity u = caipanyuanService.selectOne(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", caipanyuan.getCaipanzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		caipanyuan.setId(new Date().getTime());
        caipanyuanService.insert(caipanyuan);
        return R.ok().put("data",caipanyuan.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaipanyuanEntity caipanyuan, HttpServletRequest request){
        if(caipanyuanService.selectCount(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", caipanyuan.getCaipanzhanghao()))>0) {
            return R.error("裁判账号已存在");
        }
    	caipanyuan.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(caipanyuan);
        CaipanyuanEntity u = caipanyuanService.selectOne(new EntityWrapper<CaipanyuanEntity>().eq("caipanzhanghao", caipanyuan.getCaipanzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		caipanyuan.setId(new Date().getTime());
        caipanyuanService.insert(caipanyuan);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaipanyuanEntity caipanyuan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caipanyuan);
        caipanyuanService.updateById(caipanyuan);//全部更新
        if(null!=caipanyuan.getCaipanzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(caipanyuan.getCaipanzhanghao());
                            tokenEntity.setUsername(caipanyuan.getCaipanzhanghao());
                        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", caipanyuan.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caipanyuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
