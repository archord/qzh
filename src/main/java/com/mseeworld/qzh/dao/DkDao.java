/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Dk;
import com.mseeworld.qzh.model.Dk2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface DkDao extends BaseHibernateDao<Dk> {

  public Dk getByQzbm(String dkbm);

  public List<Dk> getCbDksByCbjyqzbm(String qzbm);

  public List<Dk> getLzDksByCbjyqzbm(String qzbm);

  public Dk getLzDkByLzhtbm(String lzhtbm);

  public List<Dk> getByOrgId(int orgId, int limit);

  public List<Dk2> getByCbhtId(int start, int size, int cbhtId);

  public List<Dk2> getByCbhtbm(int start, int size, String cbhtbm);

  public List<Dk2> getFirstNOfAll2(int start, int size, int parentId);

  public List<Dk> getDkOfNullChbtId(int limit);

  public void updateCbhtId(String dkIds, String cbhtId);

  public void deleteCbhtByIds(String dkIds);
}
