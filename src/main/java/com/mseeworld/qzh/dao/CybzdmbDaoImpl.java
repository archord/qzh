/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cybzdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CybzdmbDaoImpl  extends BaseHibernateDaoImpl<Cybzdmb> implements CybzdmbDao{
  
  public List<Cybzdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from cybzdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Cybzdmb.class);

    return q.list();
  }
}
