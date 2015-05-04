/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cybzdmb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CybzdmbDaoImpl  extends BaseHibernateDaoImpl<Cybzdmb> implements CybzdmbDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cybzdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
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
      Cybzdmb obj = new Cybzdmb();
      obj.setDm('9');
      return obj;
    }
  }
  
  public Cybzdmb getByDm(char dm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cybzdmb "
            + "where dm='" + dm + "'";
    Query q = session.createSQLQuery(sql).addEntity(Cybzdmb.class);

    return (Cybzdmb) q.list().get(0);
  }
}
