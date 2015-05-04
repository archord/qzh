/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbjyqzQzbf;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzQzbfDaoImpl extends BaseHibernateDaoImpl<CbjyqzQzbf> implements CbjyqzQzbfDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqz_qzbf ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<CbjyqzQzbf> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz_qzbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzbf.class);

    return q.list();
  }
  
  
  public List<CbjyqzQzbf> getFirstNOfAll(int start, int size){

    Session session = getCurrentSession();
    String sql = "select * from cbjyqz_qzbf order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzbf.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz_qzbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
