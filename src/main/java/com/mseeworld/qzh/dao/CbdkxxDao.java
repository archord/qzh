/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbdkxx;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbdkxxDao extends BaseHibernateDao<Cbdkxx> {
  
  public Cbdkxx getByQzbm(String qzbm);

  public List<Cbdkxx> getCbdkxxsByOrgId(long orgId);

  public List<Cbdkxx> getFirstNOfAll(int start, int size);
}
