package com.mseeworld.qzh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.mseeworld.qzh.dao.UserDAO;
import com.mseeworld.qzh.model.AUser;
import com.mseeworld.qzh.service.UserService;
import com.mseeworld.qzh.util.QshException;

public class UserServiceImpl implements UserService {
	
	UserDAO userDao;
	
	public UserDAO getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean checkUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.checkUserName(username);
	}

	public List<AUser> getPersons() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public AUser login(String name, String password) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		return userDao.login(name, password);
	}

	public boolean modify(AUser p) {
		// TODO Auto-generated method stub
		return userDao.update(p);
	}

	public AUser register(AUser user) throws QshException{
		// TODO Auto-generated method stub
		AUser u = null;
		if(!checkUserName(user.getName())){
			u=userDao.save(user);
		}else{
			throw new QshException("用户名已存在！");
		}
		return u;
	}

	public boolean remove(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		for(int i=0;i<ids.length;i++){
			
			flag = userDao.delete(Integer.parseInt(ids[i])); 
		}
		return flag;
			
	}
	public List<AUser> getPersons(int start, int end) {
		// TODO Auto-generated method stub
		return userDao.findAll(start, end);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return this.getPersons().size();
	}

}
