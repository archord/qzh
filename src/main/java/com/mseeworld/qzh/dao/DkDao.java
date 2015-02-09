/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Dk;
import java.util.List;

/**
 *
 * @author xy
 */
public interface DkDao extends BaseHibernateDao<Dk> {

  public List<Dk> getByOrgId(int orgId, int limit);

  public List<Dk> getByCbhtId(int cbhtId, int limit);

  public List<Dk> getFirstNOfAll(int limit);

  public List<Dk> getDkOfNullChbtId(int limit);

  public void updateCbhtId(String dkIds, String cbhtId);
  
  public void deleteCbhtByIds(String dkIds);
}
