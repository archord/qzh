/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Xbdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface XbdmbDao  extends BaseHibernateDao<Xbdmb>{
  
  public List<Xbdmb> getAll ();
}
