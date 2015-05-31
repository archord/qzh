/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbflxdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbflxdmbDao  extends BaseHibernateDao<Cbflxdmb>{
  
  public List<Cbflxdmb> getAll ();
}
