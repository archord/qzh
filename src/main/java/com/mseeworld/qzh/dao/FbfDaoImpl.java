/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Fbf;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class FbfDaoImpl extends BaseHibernateDaoImpl<Fbf> implements FbfDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from fbf ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public Fbf getByFbfbm(String fbfbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from fbf "
            + "where fbfbm= '" + fbfbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Fbf.class);

    return (Fbf) q.list().get(0);
  }
  
  public List<Fbf> getFbfsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from fbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Fbf.class);

    return q.list();
  }
  
  public List<Fbf> getFirstNOfAll(int start, int size){

    Session session = getCurrentSession();
    String sql = "select * from fbf order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Fbf.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from fbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
