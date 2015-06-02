/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbf;
import com.mseeworld.qzh.model.Cbf2;
import java.math.BigInteger;
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

  public Cbf getByDkbm(String dkbm) {

    Session session = getCurrentSession();
    String sql = "select o1.* "
            + "from cbf o1 "
            + "inner join cbdkxx o2 on o2.cbfbm=o1.cbfbm and o2.dkbm= '" + dkbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    if (q.list().size() > 0) {
      return (Cbf) q.list().get(0);
    } else {
      return null;
    }
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

  public List<Cbf2> getFirstNOfAll2(int start, int size, int parentId) {

    Session session = getCurrentSession();
//    String sql = "select * from cbf order by id desc ";
    String sql = "select obj.*, org.org_name "
            + "from cbf obj "
            + "left join a_organization org on obj.org_id=org.org_id";
    if (parentId != 0) {
      sql += " where obj.org_id=" + parentId;
    }
    sql += " order by obj.id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbf2.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbf ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  @Override
  public int deleteAndSave(Cbf obj) {

    Session session = getCurrentSession();
    String sql = "delete from cbf where cbfbm='" + obj.getCbfbm().trim() + "'";
    int num = session.createSQLQuery(sql).executeUpdate();

    super.save(obj);
    return num;
  }
}
