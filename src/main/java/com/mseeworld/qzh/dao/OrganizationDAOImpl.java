package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.model.AOrganization;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrganizationDAOImpl extends BaseHibernateDaoImpl<AOrganization> implements OrganizationDAO {

  public List<AOrganization> getOrgByParentId(long id) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from a_organization "
            + "where is_deleted=false and parent_id= " + id + " "
            + "order by org_id";
    Query q = session.createSQLQuery(sql).addEntity(AOrganization.class);

    return q.list();
  }

  public int getChildNumber(long id) {

    Session session = getCurrentSession();
    String sql = "select count(*) "
            + "from a_organization "
            + "where is_deleted=false and parent_id= " + id;
    Query q = session.createSQLQuery(sql);

    BigInteger otnId = (BigInteger) q.list().get(0);
    return otnId.intValue();
  }

  public void deleteOrgById(long id) {

    Session session = getCurrentSession();
    String sql = "update a_organization set is_deleted=true "
            + "where org_id=" + id;
    session.createSQLQuery(sql).executeUpdate();
  }
}
