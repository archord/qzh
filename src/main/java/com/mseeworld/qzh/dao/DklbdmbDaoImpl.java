/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Dklbdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class DklbdmbDaoImpl  extends BaseHibernateDaoImpl<Dklbdmb> implements DklbdmbDao{
  
  public List<Dklbdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from dklbdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Dklbdmb.class);

    return q.list();
  }

  public Dklbdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from dklbdmb where dkxz='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Dklbdmb.class);

    if (q.list().size() > 0) {
      return (Dklbdmb) q.list().get(0);
    } else {
      Dklbdmb obj = new Dklbdmb();
      obj.setDm("99");
      return obj;
    }
  }
}
