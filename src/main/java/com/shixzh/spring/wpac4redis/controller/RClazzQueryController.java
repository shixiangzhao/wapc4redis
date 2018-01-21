package com.shixzh.spring.wpac4redis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shixzh.spring.wpac4redis.service.RedisService;
import com.shixzh.spring.wpac4redis.util.RedisTool;

@RequestMapping("redis")
@Controller
public class RClazzQueryController {

	RedisService rs = RedisTool.getRedisService();

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String execute(HttpServletRequest request) {

		return "SUCCESS";
	}
}
