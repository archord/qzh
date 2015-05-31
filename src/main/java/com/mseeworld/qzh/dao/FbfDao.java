/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Fbf;
import java.util.List;

/**
 *
 * @author xy
 */
public interface FbfDao extends BaseHibernateDao<Fbf> {
  
  public Fbf getByFbfbm(String fbfbm);

  public List<Fbf> getFbfsByOrgId(long orgId);

  public List<Fbf> getFirstNOfAll(int start, int size);
}
