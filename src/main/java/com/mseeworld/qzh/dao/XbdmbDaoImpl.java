/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Xbdmb;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author xy
 */
public class XbdmbDaoImpl  extends BaseHibernateDaoImpl<Xbdmb> implements XbdmbDao{
  
  public List<Xbdmb> getAll() {
    
    Session session = getCurrentSession();
    String sql = "select * from xbdmb order by dm asc";
    Query q = session.createSQLQuery(sql).addEntity(Xbdmb.class);

    return q.list();
  }
}
