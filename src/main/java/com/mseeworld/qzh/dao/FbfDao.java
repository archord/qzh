/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Fbf;
import com.mseeworld.qzh.model.Fbf2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface FbfDao extends BaseHibernateDao<Fbf> {
  
  public Fbf getByFbfbm(String fbfbm);

  public List<Fbf> getFbfsByOrgId(long orgId);

  public List<Fbf2> getFirstNOfAll2(int start, int size, int parentId);
}
