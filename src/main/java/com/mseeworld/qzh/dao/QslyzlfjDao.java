/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Qslyzlfj;
import java.util.List;

/**
 *
 * @author xy
 */
public interface QslyzlfjDao extends BaseHibernateDao<Qslyzlfj> {

  public List<Qslyzlfj> getQslyzlfjsByOrgId(long orgId);

  public List<Qslyzlfj> getFirstNOfAll(int n);
}
