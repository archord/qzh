/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Sfdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class SfdmbDaoImpl  extends BaseHibernateDaoImpl<Sfdmb> implements SfdmbDao{
  
  public List<Sfdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from sfdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Sfdmb.class);

    return q.list();
  }
}
