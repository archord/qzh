/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbjyqzQzhf;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzQzhfDaoImpl extends BaseHibernateDaoImpl<CbjyqzQzhf> implements CbjyqzQzhfDao{
  
  public List<CbjyqzQzhf> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz_qzhf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzhf.class);

    return q.list();
  }
  
  
  public List<CbjyqzQzhf> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqz_qzhf order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzhf.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz_qzhf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
