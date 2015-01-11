/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqqdfsdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbjyqqdfsdmbDaoImpl  extends BaseHibernateDaoImpl<Cbjyqqdfsdmb> implements CbjyqqdfsdmbDao{
  
  public List<Cbjyqqdfsdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from cbjyqqdfsdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Cbjyqqdfsdmb.class);

    return q.list();
  }
}
