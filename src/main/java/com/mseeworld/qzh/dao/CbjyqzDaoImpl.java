/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqz;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzDaoImpl extends BaseHibernateDaoImpl<Cbjyqz> implements CbjyqzDao{
  
  public List<Cbjyqz> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz.class);

    return q.list();
  }
  
  
  public List<Cbjyqz> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqz order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
