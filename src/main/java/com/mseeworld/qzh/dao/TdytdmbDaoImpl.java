/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Tdytdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class TdytdmbDaoImpl  extends BaseHibernateDaoImpl<Tdytdmb> implements TdytdmbDao{
  
  public List<Tdytdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from tdytdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Tdytdmb.class);

    return q.list();
  }
}
