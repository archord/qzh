/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqqdfsdmb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqqdfsdmbDaoImpl extends BaseHibernateDaoImpl<Cbjyqqdfsdmb> implements CbjyqqdfsdmbDao {

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqqdfsdmb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public List<Cbjyqqdfsdmb> getAll() {

    Session session = getCurrentSession();
    String sql = "select * from cbjyqqdfsdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqqdfsdmb.class);

    return q.list();
  }

  public Cbjyqqdfsdmb getByName(String name) {

    Session session = getCurrentSession();
    String sql = "select * from cbjyqqdfsdmb where qdfs='" + name.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqqdfsdmb.class);

    if (q.list().size() > 0) {
      return (Cbjyqqdfsdmb) q.list().get(0);
    } else {
      Cbjyqqdfsdmb obj = new Cbjyqqdfsdmb();
      obj.setDm("900");
      return obj;
    }
  }

  public Cbjyqqdfsdmb getByDm(String dm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqqdfsdmb "
            + "where dm='" + dm.trim() + "'";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqqdfsdmb.class);

    if (q.list().size() > 0) {
      return (Cbjyqqdfsdmb) q.list().get(0);
    } else {
      return null;
    }
  }
}
