/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzhf;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzhfDao extends BaseHibernateDao<CbjyqzQzhf> {

  public List<CbjyqzQzhf> getByOrgId(long orgId);

  public List<CbjyqzQzhf> getFirstNOfAll2(int start, int size, int parentId);
}
