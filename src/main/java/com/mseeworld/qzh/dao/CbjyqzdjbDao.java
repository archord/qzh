/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqzdjb;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzdjbDao extends BaseHibernateDao<Cbjyqzdjb> {

  public List<Cbjyqzdjb> getByOrgId(long orgId);

  public List<Cbjyqzdjb> getFirstNOfAll(int n);

  public Cbjyqzdjb getByQzbm(String qzbm);
}
