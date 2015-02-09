/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Lzht;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class LzhhtDaoImpl extends BaseHibernateDaoImpl<Lzht> implements LzhhtDao{
  
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
