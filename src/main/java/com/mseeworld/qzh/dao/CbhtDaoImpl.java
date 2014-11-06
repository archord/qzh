/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbht;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbhtDaoImpl extends BaseHibernateDaoImpl<Cbht> implements CbhtDao{
  
  public List<Cbht> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbht "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);

    return q.list();
  }
  
  
  public List<Cbht> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbht order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Cbht.class);

    return q.list();
  }
}
