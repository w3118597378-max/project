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

import com.cl.entity.CansaixueshengEntity;
import com.cl.entity.view.CansaixueshengView;

import com.cl.service.CansaixueshengService;
import com.cl.service.TokenService;
import com.cl.service.EmailregistercodeService;
import com.cl.entity.EmailregistercodeEntity;

/**
 * 参赛学生
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-29 16:15:38
 */
@RestController
@RequestMapping("/cansaixuesheng")
public class CansaixueshengController {
    @Autowired
    private CansaixueshengService cansaixueshengService;
    @Autowired
    private EmailregistercodeService emailregistercodeService;



	@Autowired
	private TokenService tokenService;

	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		CansaixueshengEntity u = cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", username));
        if(u==null || !u.getMima().equals(password)) {
            return R.error("账号或密码不正确");
        }
    		String token = tokenService.generateToken(u.getId(), username,"cansaixuesheng",  "参赛学生" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody CansaixueshengEntity cansaixuesheng, @RequestParam(required = false) String emailcode){
    	//ValidatorUtils.validateEntity(cansaixuesheng);
                            CansaixueshengEntity u = cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", cansaixuesheng.getXuehao()));
                                                                                                		if(u!=null) {
			return R.error("注册用户已存在");
		}
                //校验邮箱验证码
                u =cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("email", cansaixuesheng.getEmail()));
                if(u!=null) {
                        return R.error("邮箱已被注册");
                }
                List<EmailregistercodeEntity> emailregistercodeList = emailregistercodeService.selectList(
                                                                                new EntityWrapper<EmailregistercodeEntity>().eq("role", "用户")
                                                                                .eq("email", cansaixuesheng.getEmail()).orderBy("addtime", false));
                boolean emailValidate = false;
                if(emailregistercodeList!=null && emailregistercodeList.size()>0) {
                        if(emailregistercodeList.get(0).getCode().equals(emailcode)) {
                                emailValidate = true;
                        }
                }
                if(!emailValidate) return R.error("邮箱验证码不正确");
		Long uId = new Date().getTime();
		cansaixuesheng.setId(uId);
        cansaixueshengService.insert(cansaixuesheng);
        return R.ok();
    }

    /**
     * 发送邮件
     */
    @IgnoreAuth
    @RequestMapping("/sendemail")
    public R sendemail(@RequestParam String email){
        String code = CommonUtil.getRandomNumber(4);
        EmailregistercodeEntity emailregistercode = new EmailregistercodeEntity();
        emailregistercode.setCode(code);
        emailregistercode.setRole("用户");
        emailregistercode.setEmail(email);
        emailregistercodeService.insert(emailregistercode);
        CommonUtil.sendEmail(email, "用户注册","您的注册验证码是【"+code+"】，请不要把验证码泄漏给其他人，如非本人请勿操作。");
        return R.ok();
    }

    /**
     * 发送邮件
     */
    @RequestMapping("/sendemail/content")
    public R sendEmailMessage(String email, String title, String content) {
        org.springframework.util.Assert.hasLength(email, "邮箱不能为空");
        org.springframework.util.Assert.hasLength(title, "标题不能为空");
        org.springframework.util.Assert.hasLength(content, "内容不能为空");
        CommonUtil.sendEmail(email, title, content);
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
        return R.ok().put("data", cansaixueshengService.selectView(new EntityWrapper<CansaixueshengEntity>().eq("id", id)));
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	CansaixueshengEntity u = cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        cansaixueshengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CansaixueshengEntity cansaixuesheng,
                                                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
                                                                                                                                                                                                                                
        
        
        PageUtils page = cansaixueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cansaixuesheng), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CansaixueshengEntity cansaixuesheng,
		HttpServletRequest request){
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();

		PageUtils page = cansaixueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cansaixuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CansaixueshengEntity cansaixuesheng){
       	EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( cansaixuesheng, MPUtil.camelToSnake("cansaixuesheng")));
        return R.ok().put("data", cansaixueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CansaixueshengEntity cansaixuesheng){
        EntityWrapper< CansaixueshengEntity> ew = new EntityWrapper< CansaixueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( cansaixuesheng, MPUtil.camelToSnake("cansaixuesheng")));
		CansaixueshengView cansaixueshengView =  cansaixueshengService.selectView(ew);
		return R.ok("查询参赛学生成功").put("data", cansaixueshengView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CansaixueshengEntity cansaixuesheng = cansaixueshengService.selectById(id);
		cansaixuesheng = cansaixueshengService.selectView(new EntityWrapper<CansaixueshengEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cansaixuesheng,deSens);
        return R.ok().put("data", cansaixuesheng);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CansaixueshengEntity cansaixuesheng = cansaixueshengService.selectById(id);
		cansaixuesheng = cansaixueshengService.selectView(new EntityWrapper<CansaixueshengEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cansaixuesheng,deSens);
        return R.ok().put("data", cansaixuesheng);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CansaixueshengEntity cansaixuesheng, HttpServletRequest request){
        if(cansaixueshengService.selectCount(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", cansaixuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        if(cansaixueshengService.selectCount(new EntityWrapper<CansaixueshengEntity>().eq("email", cansaixuesheng.getEmail()))>0) {
            return R.error("邮箱已存在");
        }
    	cansaixuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(cansaixuesheng);
        CansaixueshengEntity u = cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", cansaixuesheng.getXuehao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		cansaixuesheng.setId(new Date().getTime());
        cansaixueshengService.insert(cansaixuesheng);
        return R.ok().put("data",cansaixuesheng.getId());
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CansaixueshengEntity cansaixuesheng, HttpServletRequest request){
        if(cansaixueshengService.selectCount(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", cansaixuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        if(cansaixueshengService.selectCount(new EntityWrapper<CansaixueshengEntity>().eq("email", cansaixuesheng.getEmail()))>0) {
            return R.error("邮箱已存在");
        }
    	cansaixuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(cansaixuesheng);
        CansaixueshengEntity u = cansaixueshengService.selectOne(new EntityWrapper<CansaixueshengEntity>().eq("xuehao", cansaixuesheng.getXuehao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		cansaixuesheng.setId(new Date().getTime());
        cansaixueshengService.insert(cansaixuesheng);
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CansaixueshengEntity cansaixuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cansaixuesheng);
        cansaixueshengService.updateById(cansaixuesheng);//全部更新
        if(null!=cansaixuesheng.getXuehao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(cansaixuesheng.getXuehao());
                            tokenEntity.setUsername(cansaixuesheng.getXuehao());
                        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", cansaixuesheng.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        cansaixueshengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    	





    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_cansaixuesheng_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("method", func);
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? xColumnName : yColumnName, order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(cansaixueshengService.selectValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_cansaixuesheng_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? Arrays.asList(xColumnName) : Arrays.asList(yColumnNames), order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(cansaixueshengService.selectValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_cansaixuesheng_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("timeStatType", timeStatType);
        params.put("method", func);
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? xColumnName : yColumnName, order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(cansaixueshengService.selectTimeStatValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_cansaixuesheng_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? Arrays.asList(xColumnName) : Arrays.asList(yColumnNames), order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(cansaixueshengService.selectTimeStatValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_cansaixuesheng_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", MPUtil.camelToSnake(columnName));
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            params.put("order", request.getParameter("order"));
            String orderType = request.getParameter("orderType");
            params.put("orderType", StringUtils.isNotBlank(orderType) && orderType.equals("x") ? MPUtil.camelToSnake(columnName) : "total");
        }
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(cansaixueshengService.selectGroup(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,CansaixueshengEntity cansaixuesheng, HttpServletRequest request){
        EntityWrapper<CansaixueshengEntity> ew = new EntityWrapper<CansaixueshengEntity>();
        int count = cansaixueshengService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cansaixuesheng), params), params));
        return R.ok().put("data", count);
    }


}
