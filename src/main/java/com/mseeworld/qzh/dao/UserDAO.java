package com.mseeworld.qzh.dao;

import java.util.List;
import com.mseeworld.qzh.model.AUser;
import com.mseeworld.qzh.model.AUser2;

public interface UserDAO  extends BaseHibernateDao<AUser>{

  public Boolean checkUser(AUser user);
  
  public Boolean exist(AUser user);
  
  public List<AUser2> getFirstNOfAll2(int start, int size);
}
