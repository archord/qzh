/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Lzht;
import com.mseeworld.qzh.model.Lzht2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface LzhhtDao extends BaseHibernateDao<Lzht> {
  
  public List<Lzht> getByYcbhtbm(String cbhtbm);
  
  public Lzht getByLzhtbm(String lzhtbm);

  public List<Lzht> getByOrgId(int orgId, int limit);

  public List<Lzht2> getFirstNOfAll2(int start, int size, int parentId);
  
}
