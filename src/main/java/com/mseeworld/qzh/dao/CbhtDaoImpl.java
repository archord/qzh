/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbht;
import com.mseeworld.qzh.view.CbhtView;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbhtDaoImpl extends BaseHibernateDaoImpl<Cbht> implements CbhtDao {

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbht ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public Cbht getByCbhtbm(String cbhtbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbht "
            + "where cbhtbm= '" + cbhtbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);

    return (Cbht) q.list().get(0);
  }

  public Cbht getCbhtById(long id) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbht "
            + "where id= " + id;
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);

    return (Cbht)q.list().get(0);
  }
  
  public List<Cbht> getByOrgId(long orgId) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbht "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);

    return q.list();
  }

  public CbhtView getCbhtViewByCbfmc(String cbfmc) {

    Session session = getCurrentSession();
    String sql = "select h.id, h.cbhtbm, h.fbfbm, h.cbfbm, c.cbfmc "
            + "from cbht h "
            + "inner join cbf c on h.cbfbm=c.cbfbm and c.cbfmc like '%" + cbfmc + "%'";
    Query q = session.createSQLQuery(sql);
    List lst = q.list();
    if (lst.size() > 0) {
      Iterator itor = lst.iterator();

      CbhtView obj = new CbhtView();
      if (itor.hasNext()) {
        Object[] row = (Object[]) itor.next();
        try {
          obj.setId((Long) row[0]);
          obj.setCbhtbm((String) row[1]);
          obj.setFbfbm((String) row[2]);
          obj.setFbfbm((String) row[3]);
          obj.setCbfmc((String) row[4]);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return obj;
    } else {
      return null;
    }
  }

  public List<CbhtView> getFirstNOfCbhtView(int n) {

    ArrayList<CbhtView> objs = new ArrayList();

    Session session = getCurrentSession();
    String sql = "select h.id, h.cbhtbm, h.fbfbm, h.cbfbm, c.cbfmc "
            + "from cbht h "
            + "left join cbf c on h.cbfbm=c.cbfbm "
            + "order by id limit " + n;
    Query q = session.createSQLQuery(sql);
    List lst = q.list();
    if (lst.size() > 0) {
      Iterator itor = lst.iterator();

      while (itor.hasNext()) {
        CbhtView obj = new CbhtView();
        Object[] row = (Object[]) itor.next();
        try {
          BigInteger id = (BigInteger) row[0];
          obj.setId(id.longValue());
          obj.setCbhtbm((String) row[1]);
          obj.setFbfbm((String) row[2]);
          obj.setCbfbm((String) row[3]);
          obj.setCbfmc((String) row[4]);
        } catch (Exception e) {
          e.printStackTrace();
        }
        objs.add(obj);
      }
    } else {
    }
    return objs;
  }

  public List<Cbht> getFirstNOfAll(int start, int size) {

    Session session = getCurrentSession();
    String sql = "select * from cbht order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbht where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
