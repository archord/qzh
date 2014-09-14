/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbf;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbfDaoImpl extends BaseHibernateDaoImpl<Cbf> implements CbfDao{
  
  public List<Cbf> getCbfsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    return q.list();
  }
  
  public List<Cbf> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbf order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Cbf.class);

    return q.list();
  }
}
