package com.arthur.bean.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arthur.bean.details.BeanInfo;
import com.arthur.bean.service.BeanService;
import com.arthur.bean.utils.JsonResultMapUtils;
import com.arthur.bean.utils.JsonResultMapUtils.Code;

@RequestMapping(value = "bean-browser")
@Controller
public class PackageBeanController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PackageBeanController.class);

	@Autowired
	private BeanService beanService;

	@RequestMapping(value = "/name", method = { RequestMethod.GET }, produces = "application/json")
	public @ResponseBody
	Map<String, Object> searchByName(
			@RequestParam(value = "beanName") String beanName) {
		try {
			List<BeanInfo> searchedBeans = beanService.searchByName(beanName);
			return JsonResultMapUtils.getCodeAndMesMap(Code.SUCCESS,
					searchedBeans);
		} catch (Exception e) {
			LOGGER.warn("", e);
			return JsonResultMapUtils.getCodeAndMesMap(Code.ERROR, null);
		}
	}
}
