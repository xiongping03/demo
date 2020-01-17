package com.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userManager")
public class UserManagerController {
	@RequestMapping("/getUserInfo")
	public @ResponseBody String getUserInfo(HttpServletRequest request){
		
		return "AAAAAAAAAA";
	}
}
