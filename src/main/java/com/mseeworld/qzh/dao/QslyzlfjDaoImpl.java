/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Qslyzlfj;
import java.math.BigInteger;
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
  
  public List<Qslyzlfj> getFirstNOfAll(int start, int size){

    Session session = getCurrentSession();
    String sql = "select * from qslyzlfj order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Qslyzlfj.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from qslyzlfj where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from qslyzlfj ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  @Override
  public void deleteAndSave(Qslyzlfj obj){
    
    Session session = getCurrentSession();
    String sql = "delete from qslyzlfj where cbjyqzbm='" + obj.getCbjyqzbm().trim() + "' and zlfjbh='"+obj.getZlfjbh()+"'";
    session.createSQLQuery(sql).executeUpdate();
    
    super.save(obj);
  }
}
