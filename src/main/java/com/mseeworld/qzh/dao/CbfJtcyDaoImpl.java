/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbfJtcy;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbfJtcyDaoImpl extends BaseHibernateDaoImpl<CbfJtcy> implements CbfJtcyDao {

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbf_jtcy ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

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

  public List<CbfJtcy> getFirstNOfAll2(int start, int size, int parentId) {

    Session session = getCurrentSession();
//    String sql = "select * from cbf_jtcy order by id desc ";
    String sql = "select obj.*, org.org_name "
            + "from cbf_jtcy obj "
            + "left join a_organization org on obj.org_id=org.org_id";
    if (parentId != 0) {
      sql += " where obj.org_id=" + parentId;
    }
    sql += " order by obj.id desc ";
    Query q = session.createSQLQuery(sql).addEntity(CbfJtcy.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbf_jtcy where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
  
  @Override
  public void deleteAndSave(CbfJtcy obj){
    
    Session session = getCurrentSession();
    String sql = "delete from cbf_jtcy where cbfbm='" + obj.getCbfbm().trim() + "' and cyxm='"+obj.getCyxm()+"'";
    session.createSQLQuery(sql).executeUpdate();
    
    super.save(obj);
  }
}
