package com.lhw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.lhw.dao.UserMapper;
import com.lhw.pojo.User;
import com.lhw.util.MD5Util;
import com.lhw.util.RedisCacheUtil;
@Service
@Controller
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(){
		return "register";
	}
	
	@ResponseBody
	@RequestMapping("/userRegister")
	public void userRegister(@RequestBody User user,HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		System.out.print(user.getUsername());
		if(user.getUsername() == "" || user.getPassword() == "" || user.getPhone() == "" || user.getAddress() == "" || user.getUsername() == null || user.getPassword() == null || user.getPhone() == null || user.getAddress() == null){
			pw.write("error");
			System.out.print("qweqweqwweqweq");
			return;
		}
		if(user.getUsername() != null){
			Example example = new Example(User.class);
		    Criteria criteria = example.createCriteria();  
		    criteria.andEqualTo("username", user.getUsername());
		    List<User> list = userMapper.selectByExample(example);
		    if(list != null && list.size() > 0){
		    	pw.write("exist");
		    }else{
		    	int i = userMapper.insert(user);
		    	if(i>0){
		    		String token = MD5Util.md5Decode(user.getUsername());
		    		RedisCacheUtil.set(token, user, 3600L);
		    		pw.write(token);
		    	}
		    	else pw.write("error");
		    }
		}else{
			pw.write("error");
		}
	}
	
	@ResponseBody
	@RequestMapping("/userLogin")
	public void userLogin(@RequestBody User user,HttpServletResponse response) throws IOException{
		PrintWriter pw = response.getWriter();
		if(user.getUsername() == "" || user.getPassword() == "" || user.getUsername() == null || user.getPassword() == null ){
			pw.write("error");
			return;
		}else{
			System.out.print(user.getUsername());
			System.out.print(user.getPassword());
			Example example = new Example(User.class);
		    Criteria criteria = example.createCriteria();  
		    criteria.andEqualTo("username", user.getUsername());
		    criteria.andEqualTo("password", user.getPassword());
		    List<User> list = userMapper.selectByExample(example);
		    System.out.print(list);
		    if(list != null && list.size() > 0){
		    	String token = MD5Util.md5Decode(list.get(0).getUsername());
		    	if(RedisCacheUtil.get(token) != null) RedisCacheUtil.remove(token);
		    	RedisCacheUtil.set(token, list.get(0), 3600L);
		    	pw.write(token);
		    }else{
		    	pw.write("error");
		    }
		}
	}
	
	@ResponseBody
	@RequestMapping("/checkToken")
	public int checkToken(@RequestBody String token){
		int flag = 0;
    	User user = (User)RedisCacheUtil.get(token);
	    if(user != null){
	    	flag = 1;
	    	RedisCacheUtil.remove(token);
	    	RedisCacheUtil.set(token, user, 3600L);
	    }else{
	    	flag = 0;
	    }
		return flag;
	}
	
	
}
