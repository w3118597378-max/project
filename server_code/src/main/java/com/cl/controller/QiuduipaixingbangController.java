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

import com.cl.entity.QiuduipaixingbangEntity;
import com.cl.entity.view.QiuduipaixingbangView;

import com.cl.service.QiuduipaixingbangService;
import com.cl.service.TokenService;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 球队排行榜
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/qiuduipaixingbang")
public class QiuduipaixingbangController {
    @Autowired
    private QiuduipaixingbangService qiuduipaixingbangService;

    @Autowired
    private StoreupService storeupService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QiuduipaixingbangEntity qiuduipaixingbang,
                                                                                                                                                                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                if(tableName.equals("qiuduiduizhang")) {
                    qiuduipaixingbang.setDuizhangzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                            EntityWrapper<QiuduipaixingbangEntity> ew = new EntityWrapper<QiuduipaixingbangEntity>();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
        
        
        PageUtils page = qiuduipaixingbangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuduipaixingbang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QiuduipaixingbangEntity qiuduipaixingbang,
		HttpServletRequest request){
        EntityWrapper<QiuduipaixingbangEntity> ew = new EntityWrapper<QiuduipaixingbangEntity>();

		PageUtils page = qiuduipaixingbangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qiuduipaixingbang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QiuduipaixingbangEntity qiuduipaixingbang){
       	EntityWrapper<QiuduipaixingbangEntity> ew = new EntityWrapper<QiuduipaixingbangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qiuduipaixingbang, MPUtil.camelToSnake("qiuduipaixingbang")));
        return R.ok().put("data", qiuduipaixingbangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QiuduipaixingbangEntity qiuduipaixingbang){
        EntityWrapper< QiuduipaixingbangEntity> ew = new EntityWrapper< QiuduipaixingbangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qiuduipaixingbang, MPUtil.camelToSnake("qiuduipaixingbang")));
		QiuduipaixingbangView qiuduipaixingbangView =  qiuduipaixingbangService.selectView(ew);
		return R.ok("查询球队排行榜成功").put("data", qiuduipaixingbangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QiuduipaixingbangEntity qiuduipaixingbang = qiuduipaixingbangService.selectById(id);
		qiuduipaixingbang = qiuduipaixingbangService.selectView(new EntityWrapper<QiuduipaixingbangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiuduipaixingbang,deSens);
        return R.ok().put("data", qiuduipaixingbang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QiuduipaixingbangEntity qiuduipaixingbang = qiuduipaixingbangService.selectById(id);
		qiuduipaixingbang = qiuduipaixingbangService.selectView(new EntityWrapper<QiuduipaixingbangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qiuduipaixingbang,deSens);
        return R.ok().put("data", qiuduipaixingbang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QiuduipaixingbangEntity qiuduipaixingbang, HttpServletRequest request){
        if(qiuduipaixingbangService.selectCount(new EntityWrapper<QiuduipaixingbangEntity>().eq("qiuduimingcheng", qiuduipaixingbang.getQiuduimingcheng()))>0) {
            return R.error("球队名称已存在");
        }
    	//ValidatorUtils.validateEntity(qiuduipaixingbang);
        qiuduipaixingbangService.insert(qiuduipaixingbang);
        return R.ok().put("data",qiuduipaixingbang.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QiuduipaixingbangEntity qiuduipaixingbang, HttpServletRequest request){
        if(qiuduipaixingbangService.selectCount(new EntityWrapper<QiuduipaixingbangEntity>().eq("qiuduimingcheng", qiuduipaixingbang.getQiuduimingcheng()))>0) {
            return R.error("球队名称已存在");
        }
    	//ValidatorUtils.validateEntity(qiuduipaixingbang);
        qiuduipaixingbangService.insert(qiuduipaixingbang);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QiuduipaixingbangEntity qiuduipaixingbang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qiuduipaixingbang);
        qiuduipaixingbangService.updateById(qiuduipaixingbang);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QiuduipaixingbangEntity> list = new ArrayList<QiuduipaixingbangEntity>();
        for(Long id : ids) {
            QiuduipaixingbangEntity qiuduipaixingbang = qiuduipaixingbangService.selectById(id);
            qiuduipaixingbang.setSfsh(sfsh);
            qiuduipaixingbang.setShhf(shhf);
            list.add(qiuduipaixingbang);
        }
        qiuduipaixingbangService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        qiuduipaixingbangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	








}
