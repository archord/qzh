/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Sfdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface SfdmbDao  extends BaseHibernateDao<Sfdmb>{
  
  public List<Sfdmb> getAll ();
  
  public Sfdmb getByDm(char dm);
}
