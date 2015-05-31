/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzbf;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzbfDao extends BaseHibernateDao<CbjyqzQzbf> {

  public List<CbjyqzQzbf> getByOrgId(long orgId);

  public List<CbjyqzQzbf> getFirstNOfAll2(int start, int size, int parentId);
}
