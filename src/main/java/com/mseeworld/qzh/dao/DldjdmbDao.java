/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Dldjdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface DldjdmbDao  extends BaseHibernateDao<Dldjdmb>{
  
  public List<Dldjdmb> getAll ();
}
