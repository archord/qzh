/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqzdjb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzdjbDaoImpl extends BaseHibernateDaoImpl<Cbjyqzdjb> implements CbjyqzdjbDao{
  
  public List<Cbjyqzdjb> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqzdjb "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb.class);

    return q.list();
  }
  
  
  public List<Cbjyqzdjb> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqzdjb order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqzdjb.class);

    return q.list();
  }
}
