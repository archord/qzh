/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Fbf;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class FbfDaoImpl extends BaseHibernateDaoImpl<Fbf> implements FbfDao{
  
  public List<Fbf> getFbfsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from fbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Fbf.class);

    return q.list();
  }
  
  public List<Fbf> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from fbf order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Fbf.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from fbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
