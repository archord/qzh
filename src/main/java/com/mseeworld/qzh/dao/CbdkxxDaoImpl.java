/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbdkxx;
import java.math.BigInteger;
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
  
  public List<Cbdkxx> getFirstNOfAll(int start, int size){

    Session session = getCurrentSession();
    String sql = "select * from cbdkxx order by id desc ";
    Query q = session.createSQLQuery(sql).addEntity(Cbdkxx.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbdkxx where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbdkxx ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  @Override
  public void deleteAndSave(Cbdkxx obj){
    
    Session session = getCurrentSession();
    String sql = "delete from cbdkxx where dkbm='" + obj.getDkbm().trim() + "' and fbfbm='"+obj.getFbfbm()+"' and cbfbm='"+obj.getCbfbm()+"' and cbhtbm='"+obj.getCbhtbm()+"' ";
    session.createSQLQuery(sql).executeUpdate();
    
    super.save(obj);
  }
}
