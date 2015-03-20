/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbht;
import com.mseeworld.qzh.view.CbhtView;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbhtDao extends BaseHibernateDao<Cbht> {
  
  public Cbht getByCbhtbm(String cbhtbm);
  
  public Cbht getCbhtById(long id) ;

  public List<Cbht> getByOrgId(long orgId);

  public List<Cbht> getFirstNOfAll(int n);

  public List<CbhtView> getFirstNOfCbhtView(int n);

  public CbhtView getCbhtViewByCbfmc(String cbfmc);
}
