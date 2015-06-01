/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.CbjyqzQzzx;
import com.mseeworld.qzh.model.CbjyqzQzzx2;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzzxDao extends BaseHibernateDao<CbjyqzQzzx> {

  public List<CbjyqzQzzx> getByOrgId(long orgId);

  public List<CbjyqzQzzx2> getFirstNOfAll2(int start, int size, int parentId);
}
