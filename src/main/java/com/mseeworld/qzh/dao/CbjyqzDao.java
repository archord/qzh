/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.Cbjyqz;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzDao extends BaseHibernateDao<Cbjyqz> {

  public List<Cbjyqz> getByOrgId(long orgId);

  public List<Cbjyqz> getFirstNOfAll(int start, int size);
}
