package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.AUser;
import com.mseeworld.qzh.model.AUser2;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class UserDAOImpl extends BaseHibernateDaoImpl<AUser> implements UserDAO {

  public void deleteByIds(final String ids) {

    String sql = "delete from a_user where id in(" + ids + ")";
    Session session = getCurrentSession();
    session.createSQLQuery(sql).executeUpdate();
  }

  @Override
  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from a_user ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }
  

  public List<AUser2> getFirstNOfAll2(int start, int size, int parentId) {

    Session session = getCurrentSession();
    String sql = "select u.*, o.org_name "
            + "from a_user u "
            + "left join a_organization o on u.org_id=o.org_id";
    if (parentId != 0) {
      sql += " where o.org_id=" + parentId;
    }
    sql += " order by u.id desc ";
    Query q = session.createSQLQuery(sql).addEntity(AUser2.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  @Override
  public Boolean checkUser(AUser user) {
    
    Session session = getCurrentSession();
    String sql = "select * from a_user where name='"+user.getName().trim()+"' and password='"+user.getPassword()+"'";
    
    Query q = session.createSQLQuery(sql).addEntity(AUser.class);
    if (!q.list().isEmpty()) {
      return true;
    }else{
      return false;
    }
  }
  
  public Boolean exist(AUser user) {
    
    Session session = getCurrentSession();
    String sql = "select * from a_user where name='"+user.getName().trim()+"'";
    Query q = session.createSQLQuery(sql).addEntity(AUser.class);
    if (!q.list().isEmpty()) {
      return true;
    }else{
      return false;
    }
  }
}
