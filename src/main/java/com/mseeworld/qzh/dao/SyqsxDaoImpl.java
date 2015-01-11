/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Syqsxdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class SyqsxDaoImpl extends BaseHibernateDaoImpl<Syqsxdmb> implements SyqsxDao{

  public List<Syqsxdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from syqsxdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Syqsxdmb.class);

    return q.list();
  }
    
}
