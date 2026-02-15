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

import com.cl.entity.QiuduiduizhangEntity;
import com.cl.entity.view.QiuduiduizhangView;

import com.cl.service.QiuduiduizhangService;
import com.cl.service.TokenService;

/**
 * 球队队长
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/qiuduiduizhang")
public class QiuduiduizhangController {
    @Autowired
    private QiuduiduizhangService qiuduiduizhangService;



	@Autowired
	private TokenService tokenService;

	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		QiuduiduizhangEntity u = qiuduiduizhangService.selectOne(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
    		String token = tokenService.generateToken(u.getId(), username,"qiuduiduizhang",  "球队队长" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody QiuduiduizhangEntity qiuduiduizhang){
    	//ValidatorUtils.validateEntity(qiuduiduizhang);
                            QiuduiduizhangEntity u = qiuduiduizhangService.selectOne(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", qiuduiduizhang.getDuizhangzhanghao()));
                                                                                                            		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		qiuduiduizhang.setId(uId);
        qiuduiduizhangService.insert(qiuduiduizhang);
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
        return R.ok().put("data", qiuduiduizhangService.selectView(new EntityWrapper<QiuduiduizhangEntity>().eq("id", id)));
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	QiuduiduizhangEntity u = qiuduiduizhangService.selectOne(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        qiuduiduizhangService.updateById(u);
        return R.ok("密码已重置为：123456");
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiuduiduizhangEntity qiuduiduizhang,
                                                                                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<QiuduiduizhangEntity> ew = new EntityWrapper<QiuduiduizhangEntity>();
                                                                                                                                                                                                                                                                                
        
        
        PageUtils page = qiuduiduizhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuduiduizhang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QiuduiduizhangEntity qiuduiduizhang,
		HttpServletRequest request){
        EntityWrapper<QiuduiduizhangEntity> ew = new EntityWrapper<QiuduiduizhangEntity>();

		PageUtils page = qiuduiduizhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuduiduizhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiuduiduizhangEntity qiuduiduizhang){
       	EntityWrapper<QiuduiduizhangEntity> ew = new EntityWrapper<QiuduiduizhangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiuduiduizhang, MPUtil.camelToSnake("qiuduiduizhang")));
        return R.ok().put("data", qiuduiduizhangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiuduiduizhangEntity qiuduiduizhang){
        EntityWrapper< QiuduiduizhangEntity> ew = new EntityWrapper< QiuduiduizhangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiuduiduizhang, MPUtil.camelToSnake("qiuduiduizhang")));
		QiuduiduizhangView qiuduiduizhangView =  qiuduiduizhangService.selectView(ew);
		return R.ok("查询球队队长成功").put("data", qiuduiduizhangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiuduiduizhangEntity qiuduiduizhang = qiuduiduizhangService.selectById(id);
		qiuduiduizhang = qiuduiduizhangService.selectView(new EntityWrapper<QiuduiduizhangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiuduiduizhang,deSens);
        return R.ok().put("data", qiuduiduizhang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiuduiduizhangEntity qiuduiduizhang = qiuduiduizhangService.selectById(id);
		qiuduiduizhang = qiuduiduizhangService.selectView(new EntityWrapper<QiuduiduizhangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiuduiduizhang,deSens);
        return R.ok().put("data", qiuduiduizhang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiuduiduizhangEntity qiuduiduizhang, HttpServletRequest request){
        if(qiuduiduizhangService.selectCount(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", qiuduiduizhang.getDuizhangzhanghao()))>0) {
            return R.error("队长账号已存在");
        }
    	qiuduiduizhang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(qiuduiduizhang);
        QiuduiduizhangEntity u = qiuduiduizhangService.selectOne(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", qiuduiduizhang.getDuizhangzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		qiuduiduizhang.setId(new Date().getTime());
        qiuduiduizhangService.insert(qiuduiduizhang);
        return R.ok().put("data",qiuduiduizhang.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiuduiduizhangEntity qiuduiduizhang, HttpServletRequest request){
        if(qiuduiduizhangService.selectCount(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", qiuduiduizhang.getDuizhangzhanghao()))>0) {
            return R.error("队长账号已存在");
        }
    	qiuduiduizhang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(qiuduiduizhang);
        QiuduiduizhangEntity u = qiuduiduizhangService.selectOne(new EntityWrapper<QiuduiduizhangEntity>().eq("duizhangzhanghao", qiuduiduizhang.getDuizhangzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		qiuduiduizhang.setId(new Date().getTime());
        qiuduiduizhangService.insert(qiuduiduizhang);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiuduiduizhangEntity qiuduiduizhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiuduiduizhang);
        qiuduiduizhangService.updateById(qiuduiduizhang);//全部更新
        if(null!=qiuduiduizhang.getDuizhangzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(qiuduiduizhang.getDuizhangzhanghao());
                            tokenEntity.setUsername(qiuduiduizhang.getDuizhangzhanghao());
                        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", qiuduiduizhang.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QiuduiduizhangEntity> list = new ArrayList<QiuduiduizhangEntity>();
        for(Long id : ids) {
            QiuduiduizhangEntity qiuduiduizhang = qiuduiduizhangService.selectById(id);
            qiuduiduizhang.setSfsh(sfsh);
            qiuduiduizhang.setShhf(shhf);
            list.add(qiuduiduizhang);
        }
        qiuduiduizhangService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiuduiduizhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
