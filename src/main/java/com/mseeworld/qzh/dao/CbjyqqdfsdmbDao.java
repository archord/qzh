/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqqdfsdmb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqqdfsdmbDao extends BaseHibernateDao<Cbjyqqdfsdmb> {

  public List<Cbjyqqdfsdmb> getAll();

  public Cbjyqqdfsdmb getByDm(String dm);
}
