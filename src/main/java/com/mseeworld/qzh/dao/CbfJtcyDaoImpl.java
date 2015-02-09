/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbfJtcy;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbfJtcyDaoImpl extends BaseHibernateDaoImpl<CbfJtcy> implements CbfJtcyDao {

  public List<CbfJtcy> getCbfsByCbfBm(String cbfbm) {

    if (cbfbm == null) {
      return new ArrayList();
    }

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf_jtcy "
            + "where cbfbm='" + cbfbm.trim() + "'"
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbfJtcy.class);

    return q.list();
  }

  public List<CbfJtcy> getFirstNOfAll(int n) {

    Session session = getCurrentSession();
    String sql = "select * from cbf_jtcy order by id limit " + n;
    Query q = session.createSQLQuery(sql).addEntity(CbfJtcy.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbf_jtcy where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
