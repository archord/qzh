/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Qslyzlfj;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class QslyzlfjDaoImpl extends BaseHibernateDaoImpl<Qslyzlfj> implements QslyzlfjDao{
  
  public List<Qslyzlfj> getQslyzlfjsByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from qslyzlfj "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Qslyzlfj.class);

    return q.list();
  }
  
  public List<Qslyzlfj> getFirstNOfAll(int n){
    
    Session session = getCurrentSession();
    String sql = "select * from qslyzlfj order by id limit "+ n;
    Query q = session.createSQLQuery(sql).addEntity(Qslyzlfj.class);

    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from qslyzlfj where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
}
