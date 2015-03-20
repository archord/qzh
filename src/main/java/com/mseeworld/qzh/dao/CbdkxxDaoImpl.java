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

  public Cbdkxx getByQzbm(String qzbm) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbdkxx "
            + "where cbjyqzbm= '" + qzbm.trim() + "' ";
    Query q = session.createSQLQuery(sql).addEntity(Cbdkxx.class);

    return (Cbdkxx) q.list().get(0);
  }
  
  public List<Cbdkxx> getCbdkxxsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbdkxx "
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

  public void deleteByIds(final String ids) {

    String sql = "delete from cbdkxx where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
