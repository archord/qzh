/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbjyqzQzbf;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzQzbfDaoImpl extends BaseHibernateDaoImpl<CbjyqzQzbf> implements CbjyqzQzbfDao{
  
  public List<CbjyqzQzbf> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz_qzbf "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzbf.class);

    return q.list();
  }
  
  
  public List<CbjyqzQzbf> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqz_qzbf order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzbf.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz_qzbf where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
