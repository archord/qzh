/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Lzht;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class LzhhtDaoImpl extends BaseHibernateDaoImpl<Lzht> implements LzhhtDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from lzht ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<Lzht> getByYcbhtbm(String cbhtbm){
    
    Session session = getCurrentSession();
    String sql = "select * from lzht where ycbhtbm='"+ cbhtbm.trim() +"' ";
    Query q = session.createSQLQuery(sql).addEntity(Lzht.class);
    return q.list();
  }

  public Lzht getByLzhtbm(String lzhtbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from lzht "
            + "where lzhtbm= '" + lzhtbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Lzht.class);

    return (Lzht) q.list().get(0);
  }
  
  public List<Lzht> getFirstNOfAll(int limit){
    
    Session session = getCurrentSession();
    String sql = "select * from lzht order by id limit "+ limit;
    Query q = session.createSQLQuery(sql).addEntity(Lzht.class);

    return q.list();
  }
  
  public List<Lzht> getByOrgId(int orgId, int limit){
    
    Session session = getCurrentSession();
    String sql = "select * from lzht where org_id="+ orgId +" order by id";
    if(limit>0){
      sql += " limit "+ limit;
    }
    Query q = session.createSQLQuery(sql).addEntity(Lzht.class);

    return q.list();
  }
  
  public void deleteByIds(final String ids) {

    String sql = "delete from lzht where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }

}
