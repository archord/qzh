/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Dk;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class DkDaoImpl extends BaseHibernateDaoImpl<Dk> implements DkDao{
  
  public List<Dk> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from dk "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }
  
  
  public List<Dk> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from dk order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Dk.class);

    return q.list();
  }
}
