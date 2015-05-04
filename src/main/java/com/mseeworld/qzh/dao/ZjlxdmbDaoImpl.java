/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Zjlxdmb;
import java.math.BigInteger;
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

  public Zjlxdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from zjlxdmb where zjlx='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Zjlxdmb.class);

    if (q.list().size() > 0) {
      return (Zjlxdmb) q.list().get(0);
    } else {
      Zjlxdmb obj =  new Zjlxdmb();
      obj.setDm('9');
      return obj;
    }
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from zjlxdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
}
