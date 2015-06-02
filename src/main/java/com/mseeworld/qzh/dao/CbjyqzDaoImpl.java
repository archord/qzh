/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqz;
import com.mseeworld.qzh.model.Cbjyqz2;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzDaoImpl extends BaseHibernateDaoImpl<Cbjyqz> implements CbjyqzDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqz ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<Cbjyqz> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz.class);

    return q.list();
  }
  
  
  public List<Cbjyqz2> getFirstNOfAll2(int start, int size, int parentId){

    Session session = getCurrentSession();
//    String sql = "select * from cbjyqz order by id desc ";
    String sql = "select obj.*, org.org_name "
            + "from cbjyqz obj "
            + "left join a_organization org on obj.org_id=org.org_id";
    if (parentId != 0) {
      sql += " where obj.org_id=" + parentId;
    }
    sql += " order by obj.id desc ";
    
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqz2.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
  
  @Override
  public int deleteAndSave(Cbjyqz obj){
    
    Session session = getCurrentSession();
    String sql = "delete from cbjyqz where cbjyqzbm='" + obj.getCbjyqzbm().trim() + "'";
    int num = session.createSQLQuery(sql).executeUpdate();
    
    super.save(obj);
    return num;
  }
}
