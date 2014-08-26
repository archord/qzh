package com.mseeworld.qzh.dao;

import java.util.List;

import com.mseeworld.qzh.model.AUser;
/**
 * �û���ݷ��ʶ���ӿ�
 * @author ��ʢ��
 */
public interface UserDAO {
	/**�û�ע��*/
	public AUser save(AUser user);
	/**ɾ���û�*/
	public boolean delete(int id);
	/**�޸��û�*/
	public boolean update(AUser user);
	/**��ѯ�����û�*/
	public List<AUser> findAll();
	/**分页显示所有用户*/
	public List<AUser> findAll(int start, int end);
	/**�û���½*/
	public AUser login(String name,String password)throws IndexOutOfBoundsException;
	/**����û����Ƿ����,���ڷ���true,���򷵻�false*/
	public boolean checkUserName(String username);
}
