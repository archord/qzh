package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.AOrganization;
import java.util.List;
/**
 * 分类数据访问对象接口
 * @author 邱盛华
 */
public interface OrganizationDAO extends BaseHibernateDao<AOrganization>{

  public List<AOrganization> getOrgByParentId(long id);
  
  public int getChildNumber(long id);
  
  public void deleteOrgById(String ids);
  
  public List<AOrganization> getFirstNOfAll(int start, int size);
}
