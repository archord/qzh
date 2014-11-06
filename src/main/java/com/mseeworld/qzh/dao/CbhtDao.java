/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbht;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbhtDao extends BaseHibernateDao<Cbht> {

  public List<Cbht> getByOrgId(long orgId);

  public List<Cbht> getFirstNOfAll(int n);
}
