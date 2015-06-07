/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Sfdmb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class SfdmbDaoImpl  extends BaseHibernateDaoImpl<Sfdmb> implements SfdmbDao{
  
  public List<Sfdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from sfdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Sfdmb.class);

    return q.list();
  }

  public Sfdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from sfdmb where sf='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Sfdmb.class);

    if (q.list().size() > 0) {
      return (Sfdmb) q.list().get(0);
    } else {
      return null;
    }
  }
  

  public Sfdmb getByDm(char dm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from sfdmb "
            + "where dm='" + dm + "'";
    Query q = session.createSQLQuery(sql).addEntity(Sfdmb.class);

    if (q.list().size() > 0) {
      return (Sfdmb) q.list().get(0);
    } else {
      return null;
    }
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from sfdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
}
