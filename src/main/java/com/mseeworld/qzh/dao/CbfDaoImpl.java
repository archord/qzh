/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbf;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbfDaoImpl extends BaseHibernateDaoImpl<Cbf> implements CbfDao {

  public Cbf getByCbfbm(String cbfbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf "
            + "where cbfbm= '" + cbfbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    return (Cbf) q.list().get(0);
  }

  public List<Cbf> getCbfsByOrgId(long orgId) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    return q.list();
  }

  public List<Cbf> getFirstNOfAll(int n) {

    Session session = getCurrentSession();
    String sql = "select * from cbf order by id limit " + n;
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
