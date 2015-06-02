package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.AOrganization;
import com.mseeworld.qzh.model.AOrganization2;
import java.util.List;
/**
 * 分类数据访问对象接口
 * @author 邱盛华
 */
public interface OrganizationDAO extends BaseHibernateDao<AOrganization>{

  public List<AOrganization> getOrgByParentId(long id);
  
  public int getChildNumber(long id);
  
  public void deleteOrgById(String ids);
  
  public List<AOrganization2> getFirstNOfAll2(int start, int size, int parentId);
  
  public void saveByName(AOrganization org);
  
  public AOrganization getOrgByName(String orgName, long parentId);
  
  public AOrganization getByOrgId(long orgId);
}
