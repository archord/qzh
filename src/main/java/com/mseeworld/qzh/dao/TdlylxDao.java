/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Tdlylx;
import java.util.List;

/**
 *
 * @author xy
 */
public interface TdlylxDao extends BaseHibernateDao<Tdlylx> {

  public List<Tdlylx> getAll();
}
