
package com.secs.framework.modules.sys.controller;

import java.util.Map;

import com.secs.framework.common.utils.PageUtils;
import com.secs.framework.common.utils.R;
import com.secs.framework.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-03-08 10:40:56
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = sysLogService.queryPage(params);

		return R.ok().put("page", page);
	}

}
