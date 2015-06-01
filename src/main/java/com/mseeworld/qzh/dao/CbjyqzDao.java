/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.Cbjyqz;
import com.mseeworld.qzh.model.Cbjyqz2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzDao extends BaseHibernateDao<Cbjyqz> {

  public List<Cbjyqz> getByOrgId(long orgId);

  public List<Cbjyqz2> getFirstNOfAll2(int start, int size, int parentId);
}
