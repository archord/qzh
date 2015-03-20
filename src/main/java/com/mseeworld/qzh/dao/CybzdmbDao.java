/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cybzdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CybzdmbDao  extends BaseHibernateDao<Cybzdmb>{
  
  public List<Cybzdmb> getAll ();
  
  public Cybzdmb getByDm(char dm);
}
