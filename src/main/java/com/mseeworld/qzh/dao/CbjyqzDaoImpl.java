/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqz;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzDaoImpl extends BaseHibernateDaoImpl<Cbjyqz> implements CbjyqzDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqz ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<Cbjyqz> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz.class);

    return q.list();
  }
  
  
  public List<Cbjyqz> getFirstNOfAll(int start, int size){

    Session session = getCurrentSession();
    String sql = "select * from cbjyqz order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
