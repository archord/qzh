/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqzdjb;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzdjbDaoImpl extends BaseHibernateDaoImpl<Cbjyqzdjb> implements CbjyqzdjbDao {

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqzdjb ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public Cbjyqzdjb getByQzbm(String qzbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqzdjb "
            + "where cbjyqzbm= '" + qzbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb.class);

    return (Cbjyqzdjb) q.list().get(0);
  }

  public List<Cbjyqzdjb> getByOrgId(long orgId) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqzdjb "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb.class);

    return q.list();
  }

  public List<Cbjyqzdjb> getFirstNOfAll(int start, int size) {

    Session session = getCurrentSession();
    String sql = "select * from cbjyqzdjb order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqzdjb where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
