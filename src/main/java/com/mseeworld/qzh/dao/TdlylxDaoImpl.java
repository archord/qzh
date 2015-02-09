/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Tdlylx;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class TdlylxDaoImpl extends BaseHibernateDaoImpl<Tdlylx> implements TdlylxDao{
  
  public List<Tdlylx> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from tdlylx order by id asc";
    Query q = session.createSQLQuery(sql).addEntity(Tdlylx.class);

    return q.list();
  }
  
  public Tdlylx getByName(String name){
    

    Session session = getCurrentSession();
    String sql = "select * from tdlylx where lbmc='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Tdlylx.class);

    if (q.list().size() > 0) {
      return (Tdlylx) q.list().get(0);
    } else {
      return null;
    }
  }
}
