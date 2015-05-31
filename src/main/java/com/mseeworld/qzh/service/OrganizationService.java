package com.mseeworld.qzh.service;

import com.mseeworld.qzh.bean.AOrganization;

public interface OrganizationService {

  public void save(AOrganization org);
  
  public String getOrgTree(long pid);
}
