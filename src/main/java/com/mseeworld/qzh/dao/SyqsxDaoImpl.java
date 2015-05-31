/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Syqsxdmb;
import java.math.BigInteger;
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

  public Syqsxdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from syqsxdmb where syqsx='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Syqsxdmb.class);

    if (q.list().size() > 0) {
      return (Syqsxdmb) q.list().get(0);
    } else {
      return null;
    }
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from syqsxdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
    
}
