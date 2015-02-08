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

  public Cybzdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from cybzdmb where cybz='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Cybzdmb.class);

    if (q.list().size() > 0) {
      return (Cybzdmb) q.list().get(0);
    } else {
      return null;
    }
  }
}
