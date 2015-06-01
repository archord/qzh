/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzbf;
import com.mseeworld.qzh.model.CbjyqzQzbf2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzbfDao extends BaseHibernateDao<CbjyqzQzbf> {

  public List<CbjyqzQzbf> getByOrgId(long orgId);

  public List<CbjyqzQzbf2> getFirstNOfAll2(int start, int size, int parentId);
}
