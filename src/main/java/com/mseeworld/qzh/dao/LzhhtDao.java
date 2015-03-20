/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Lzht;
import java.util.List;

/**
 *
 * @author xy
 */
public interface LzhhtDao extends BaseHibernateDao<Lzht> {
  
  public List<Lzht> getByYcbhtbm(String cbhtbm);
  
  public Lzht getByLzhtbm(String lzhtbm);

  public List<Lzht> getByOrgId(int orgId, int limit);

  public List<Lzht> getFirstNOfAll(int limit);
  
}
