package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.dao.UserDAO;
import com.mseeworld.qzh.model.AUser;
import com.mseeworld.qzh.util.HibernateUtil;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO {

	private HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}
	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public boolean checkUserName(String username) {
		// TODO Auto-generated method stub
		String hql = "from AUser u where u.name='"+username+"'";
		if(hibernateUtil.exeQuery(hql).size() > 0){
			return true;
		}
		return false;
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		String hql = "delete from AUser where id="+id;
		return hibernateUtil.exeDelete(hql);
	}

	public List<AUser> findAll() {
		// TODO Auto-generated method stub
		String hql = "from AUser";
		return hibernateUtil.exeQuery(hql);
	}

	public AUser login(String name, String password) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
		AUser user = null;
		String hql = "from AUser where name='"+name+"' and password='"+password+"'";
		
		user=(AUser)hibernateUtil.exeQuery(hql).get(0);
		
		return user;
	}

	public AUser save(AUser user) {
		// TODO Auto-generated method stub
		AUser u = null;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			u=(AUser)session.load(AUser.class, session.save(user));
			
			transaction.commit();
			//hibernateUtil.closeSession(session);
		}catch(HibernateException he){
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return u;
	}

	public boolean update(AUser user) {
		boolean flag = false;
		Transaction transaction = null;
		Session session  = null;
		try{
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			AUser u=(AUser)session.load(AUser.class, user.getId());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setAddr(user.getAddr());
			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			u.setQq(user.getQq());
			
			transaction.commit();
			flag = true;
		}catch(HibernateException he){
			flag = false;
			he.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}finally{
			hibernateUtil.closeSession(session);
		}
		return flag;
	}
	public List<AUser> findAll(int start, int end) {
		// TODO Auto-generated method stub
		String hql = "from AUser";
		return hibernateUtil.exeQueryPage(hql, start, end);
	}

}
