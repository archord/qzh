/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbjyqzQzzx;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzQzzxDaoImpl extends BaseHibernateDaoImpl<CbjyqzQzzx> implements CbjyqzQzzxDao{
  
  public List<CbjyqzQzzx> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzzx.class);

    return q.list();
  }
  
  
  public List<CbjyqzQzzx> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqz order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzzx.class);

    return q.list();
  }
}
