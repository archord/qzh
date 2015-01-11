/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Dklbdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface DklbdmbDao  extends BaseHibernateDao<Dklbdmb>{
  
  public List<Dklbdmb> getAll ();
}
