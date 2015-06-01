/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzzx;
import com.mseeworld.qzh.model.CbjyqzQzzx2;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqzQzzxDaoImpl extends BaseHibernateDaoImpl<CbjyqzQzzx> implements CbjyqzQzzxDao{

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from cbjyqz_qzzx ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  
  public List<CbjyqzQzzx> getByOrgId(long orgId){
    
    Session session = getCurrentSession();
    String sql = "select * "
            + "from cbjyqz_qzzx "
            + "where org_id= " + orgId + " "
            + "order by id";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzzx.class);

    return q.list();
  }
  
  
  public List<CbjyqzQzzx2> getFirstNOfAll2(int start, int size, int parentId){

    Session session = getCurrentSession();
//    String sql = "select * from cbjyqz_qzzx order by id desc ";
    String sql = "select obj.*, org.org_name "
            + "from cbjyqz_qzzx obj "
            + "left join a_organization org on obj.org_id=org.org_id";
    if (parentId != 0) {
      sql += " where obj.org_id=" + parentId;
    }
    sql += " order by obj.id desc ";
    Query q = session.createSQLQuery(sql).addEntity(CbjyqzQzzx2.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  public void deleteByIds(final String ids) {

    String sql = "delete from cbjyqz_qzzx where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }
  
  @Override
  public void deleteAndSave(CbjyqzQzzx obj){
    
    Session session = getCurrentSession();
    String sql = "delete from cbjyqz_qzzx where cbjyqzbm='" + obj.getCbjyqzbm().trim() + "'";
    session.createSQLQuery(sql).executeUpdate();
    
    super.save(obj);
  }
}
