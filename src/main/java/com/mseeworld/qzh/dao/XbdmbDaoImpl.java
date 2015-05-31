/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Xbdmb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class XbdmbDaoImpl  extends BaseHibernateDaoImpl<Xbdmb> implements XbdmbDao{
  
  public List<Xbdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from xbdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Xbdmb.class);

    return q.list();
  }

  public Xbdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from xbdmb where xb='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Xbdmb.class);

    if (q.list().size() > 0) {
      return (Xbdmb) q.list().get(0);
    } else {
      return null;
    }
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from xbdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
}
