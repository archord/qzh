package com.mseeworld.qzh.service;

import java.util.List;

import com.mseeworld.qzh.model.AUser;
/**
 * �û�ҵ�������
 * @author ��ʢ��
 */
public interface UserService {
	/**�û�ע��*/
	public AUser register(AUser user) throws Exception;
	/**ɾ���û�*/
	public boolean remove(String[] ids);
	/**�޸��û�*/
	public boolean modify(AUser p);
	/**��ѯ�����û�*/
	public List<AUser> getPersons();
	/**分页显示所有用户*/
	public List<AUser> getPersons(int start, int end);
	/**�û���½*/
	public AUser login(String name,String password)throws IndexOutOfBoundsException;
	/**����û����Ƿ����*/
	public boolean checkUserName(String username);
	
	public int getCount();
}
