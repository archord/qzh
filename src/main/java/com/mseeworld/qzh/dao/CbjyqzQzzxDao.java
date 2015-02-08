/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.CbjyqzQzzx;
import java.util.List;

/**
 *
 * @author xy
 */
public interface CbjyqzQzzxDao extends BaseHibernateDao<CbjyqzQzzx> {

  public List<CbjyqzQzzx> getByOrgId(long orgId);

  public List<CbjyqzQzzx> getFirstNOfAll(int n);
}
