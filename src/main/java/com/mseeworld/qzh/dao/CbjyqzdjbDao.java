/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqzdjb;
import com.mseeworld.qzh.model.Cbjyqzdjb2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzdjbDao extends BaseHibernateDao<Cbjyqzdjb> {

  public List<Cbjyqzdjb> getByOrgId(long orgId);

  public List<Cbjyqzdjb2> getFirstNOfAll(int start, int size, int parentId);

  public Cbjyqzdjb getByQzbm(String qzbm);
}
