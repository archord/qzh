/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbflxdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class CbflxdmbDaoImpl  extends BaseHibernateDaoImpl<Cbflxdmb> implements CbflxdmbDao{
  
  public List<Cbflxdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from cbflxdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Cbflxdmb.class);

    return q.list();
  }
}
