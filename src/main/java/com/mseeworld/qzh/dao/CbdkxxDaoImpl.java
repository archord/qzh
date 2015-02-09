/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbdkxx;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbdkxxDaoImpl extends BaseHibernateDaoImpl<Cbdkxx> implements CbdkxxDao{
  
  public List<Cbdkxx> getCbdkxxsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbdkxx.class);

    return q.list();
  }
  
  public List<Cbdkxx> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbdkxx order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Cbdkxx.class);

    return q.list();
  }
}
