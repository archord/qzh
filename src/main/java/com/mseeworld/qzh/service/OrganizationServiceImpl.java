package com.mseeworld.qzh.service;

import com.mseeworld.qzh.dao.OrganizationDAO;
import com.mseeworld.qzh.bean.AOrganization;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class OrganizationServiceImpl implements OrganizationService {

  private OrganizationDAO orgDao;

  /**
   * @param orgDao the orgDao to set
   */
  @Resource
  public void setOrgDao(OrganizationDAO orgDao) {
    this.orgDao = orgDao;
  }

  public void save(AOrganization org) {
    orgDao.save(org);
  }

  public String getOrgTree(long pid) {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    List<AOrganization> orgs = orgDao.getOrgByParentId(pid);
    StringBuilder rstStr = new StringBuilder("");
    if (pid == 0) {
      rstStr.append("[");
    }

    for (int i = 0; i < orgs.size(); i++) {

      AOrganization org = orgs.get(i);
      rstStr.append("{id:'");
      rstStr.append(org.getOrgId());
      rstStr.append("',text:'");
      rstStr.append(org.getOrgName());
      rstStr.append("',iconCls: 'tree_model_lv");
      rstStr.append(org.getOrgLevel());
      rstStr.append("',expandable:true,");
      rstStr.append("expanded:true,");
      if (org.getParentId() == 0) {
        rstStr.append("parent:'root'");
      } else {
        rstStr.append("parent:'");
        rstStr.append(pid);
        rstStr.append("'");
      }

      try {
        rstStr.append(",");
        String tStr = ow.writeValueAsString(org);
        tStr = tStr.replace('{', ' ');
        tStr = tStr.replace('}', ' ');
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }

      int childNum = orgDao.getChildNumber(org.getOrgId());
      if (childNum > 0) {
        rstStr.append(",leaf:false,children:[");
        rstStr.append(getOrgTree(org.getOrgId()));
        rstStr.append("]");
      }else{
        rstStr.append(",leaf:true");
      }
      if (i == orgs.size() - 1) {
        rstStr.append("}");
      } else {
        rstStr.append("},");
      }
    }
    if (pid == 0) {
      rstStr.append("]");
    }

    return rstStr.toString();
  }
}
