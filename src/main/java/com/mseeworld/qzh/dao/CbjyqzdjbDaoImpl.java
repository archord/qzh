/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqzdjb;
import com.mseeworld.qzh.model.Cbjyqzdjb2;
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

  public List<Cbjyqzdjb2> getFirstNOfAll(int start, int size, int parentId) {
    System.out.println("start="+start);
    System.out.println("size="+size);
    System.out.println("parentId="+parentId);

    Session session = getCurrentSession();
//    String sql = "select * from cbjyqzdjb order by id desc ";
    String sql = "select obj.*, org.org_name "
            + "from cbjyqzdjb obj "
            + "left join a_organization org on obj.org_id=org.org_id";
    if (parentId != 0) {
      sql += " where obj.org_id=" + parentId;
    }
    sql += " order by obj.id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb2.class);
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
