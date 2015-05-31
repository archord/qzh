/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Zjlxdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface ZjlxdmbDao  extends BaseHibernateDao<Zjlxdmb>{
  
  public List<Zjlxdmb> getAll ();
}
