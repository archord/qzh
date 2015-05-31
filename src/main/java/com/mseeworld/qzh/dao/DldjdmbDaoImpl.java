/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Dldjdmb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class DldjdmbDaoImpl  extends BaseHibernateDaoImpl<Dldjdmb> implements DldjdmbDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from dldjdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<Dldjdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from dldjdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Dldjdmb.class);

    return q.list();
  }

  public Dldjdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from dldjdmb where dldj='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Dldjdmb.class);

    if (q.list().size() > 0) {
      return (Dldjdmb) q.list().get(0);
    } else {
      return null;
    }
  }
}
