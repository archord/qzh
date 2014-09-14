package com.mseeworld.qzh.service;

import com.mseeworld.qzh.model.AOrganization;

public interface OrganizationService {

  public void save(AOrganization org);
  
  public String getOrgTree(long pid);
}
