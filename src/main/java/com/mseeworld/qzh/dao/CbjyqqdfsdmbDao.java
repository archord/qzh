/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqqdfsdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqqdfsdmbDao  extends BaseHibernateDao<Cbjyqqdfsdmb>{
  
  public List<Cbjyqqdfsdmb> getAll ();
}
