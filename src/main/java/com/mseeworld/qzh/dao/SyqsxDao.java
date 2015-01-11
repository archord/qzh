/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Syqsxdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface SyqsxDao extends BaseHibernateDao<Syqsxdmb> {

  public List<Syqsxdmb> getAll ();
}
