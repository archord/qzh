/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Zjlxdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class ZjlxdmbDaoImpl  extends BaseHibernateDaoImpl<Zjlxdmb> implements ZjlxdmbDao{
  
  public List<Zjlxdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from zjlxdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Zjlxdmb.class);

    return q.list();
  }
}
