/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbf;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbfDao extends BaseHibernateDao<Cbf> {
  
  public Cbf getByCbfbm(String cbfbm);

  public List<Cbf> getCbfsByOrgId(long orgId);

  public List<Cbf> getFirstNOfAll2(int start, int size, int parentId);
}
