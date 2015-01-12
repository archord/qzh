/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbfJtcy;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbfJtcyDaoImpl extends BaseHibernateDaoImpl<CbfJtcy> implements CbfJtcyDao{
  
  public List<CbfJtcy> getCbfsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbf_jtcy "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbfJtcy.class);

    return q.list();
  }
  
  public List<CbfJtcy> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbf_jtcy order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(CbfJtcy.class);

    return q.list();
  }
}
