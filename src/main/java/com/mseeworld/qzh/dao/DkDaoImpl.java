/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Dk;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class DkDaoImpl extends BaseHibernateDaoImpl<Dk> implements DkDao {

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from dk ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public Dk getByQzbm(String dkbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from dk "
            + "where dkbm= '" + dkbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return (Dk) q.list().get(0);
  }

  public List<Dk> getCbDksByCbjyqzbm(String qzbm) {

    Session session = getCurrentSession();
    String sql = "select * from dk where dkbm in ( select dkbm from cbdkxx where coalesce(lzhtbm, '') = '' and cbjyqzbm='" + qzbm.trim() + "')";
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public List<Dk> getLzDksByCbjyqzbm(String qzbm) {

    Session session = getCurrentSession();
    String sql = "select * from dk where dkbm in ( select dkbm from cbdkxx where coalesce(lzhtbm, '') != '' and cbjyqzbm='" + qzbm.trim() + "')";
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public Dk getLzDkByLzhtbm(String lzhtbm) {

    Session session = getCurrentSession();
    String sql = "select * from dk where dkbm in ( select dkbm from cbdkxx where lzhtbm='" + lzhtbm.trim() + "')";
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return (Dk)q.list().get(0);
  }

  public List<Dk> getFirstNOfAll(int limit) {

    Session session = getCurrentSession();
    String sql = "select * from dk order by id limit " + limit;
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public List<Dk> getDkOfNullChbtId(int limit) {

    Session session = getCurrentSession();
    String sql = "select * from dk where cbht_id<1 or cbht_id IS NULL order by id limit " + limit;
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public List<Dk> getByOrgId(int orgId, int limit) {

    Session session = getCurrentSession();
    String sql = "select * from dk where org_id=" + orgId + " order by id";
    if (limit > 0) {
      sql += " limit " + limit;
    }
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public List<Dk> getByCbhtId(int cbhtId, int limit) {

    Session session = getCurrentSession();
    String sql = "select * from dk where cbht_id=" + cbhtId + " order by id";
    if (limit > 0) {
      sql += " limit " + limit;
    }
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }

  public void updateCbhtId(String dkIds, String cbhtId) {

    Session session = getCurrentSession();
    String sql = "update dk set cbht_id =" + cbhtId + " where id in (" + dkIds + ")";
    session.createSQLQuery(sql).executeUpdate();
  }

  public void deleteCbhtByIds(String dkIds) {

    Session session = getCurrentSession();
    String sql = "update dk set cbht_id =0 where id in (" + dkIds + ")";
    session.createSQLQuery(sql).executeUpdate();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from dk where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
