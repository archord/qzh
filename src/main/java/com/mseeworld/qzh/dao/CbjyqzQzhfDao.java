/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzhf;
import com.mseeworld.qzh.model.CbjyqzQzhf2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzhfDao extends BaseHibernateDao<CbjyqzQzhf> {

  public List<CbjyqzQzhf> getByOrgId(long orgId);

  public List<CbjyqzQzhf2> getFirstNOfAll2(int start, int size, int parentId);
}
