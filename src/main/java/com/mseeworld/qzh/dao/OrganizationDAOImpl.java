package com.mseeworld.qzh.dao;

import com.mseeworld.qzh.bean.AOrganization;
import com.mseeworld.qzh.model.AOrganization2;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class OrganizationDAOImpl extends BaseHibernateDaoImpl<AOrganization> implements OrganizationDAO {

  @Override
  public AOrganization getByOrgId(long orgId) {

    Session session = getCurrentSession();
    String sql = "select * "
            + "from a_organization "
            + "where org_id=" + orgId;
    Query q = session.createSQLQuery(sql).addEntity(AOrganization.class);
    if (q.list().size() > 0) {
      return (AOrganization) q.list().get(0);
    } else {
      return null;
    }
  }

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

  public void deleteOrgById(String ids) {

    Session session = getCurrentSession();
    String sql = "update a_organization set is_deleted=true  where org_id in(" + ids + ")";
    session.createSQLQuery(sql).executeUpdate();
  }

  public Number count() {

    Session session = getCurrentSession();
    String sql = "select count(*) from a_organization ";
    int tNum = 0;
    Query q = session.createSQLQuery(sql);
    if (!q.list().isEmpty()) {
      BigInteger objId = (BigInteger) q.list().get(0);
      tNum = objId.intValue();
    }
    return tNum;
  }

  public List<AOrganization2> getFirstNOfAll2(int start, int size, int parentId) {

    Session session = getCurrentSession();
    String sql = "select org.*, o.org_name parent_name "
            + "from a_organization org "
            + "left join a_organization o on org.parent_id=o.org_id "
            + "where org.is_deleted=false ";
    if (parentId != 0) {
      sql += " and org.parent_id=" + parentId;
    }
    sql += " order by org.org_id desc";
    Query q = session.createSQLQuery(sql).addEntity(AOrganization2.class);
    q.setFirstResult(start);
    q.setMaxResults(size);
    return q.list();
  }

  @Override
  public void saveByName(AOrganization org) {

    String sql = "select * from a_organization where parent_id=" + org.getParentId() + " and org_name='" + org.getOrgName().trim() + "'";
    Session session = getCurrentSession();
    Query q = session.createSQLQuery(sql).addEntity(AOrganization.class);
    if (q.list().size() > 0) {
      AOrganization torg = (AOrganization) q.list().get(0);
      org.setOrgId(torg.getOrgId());
    } else {
      super.save(org);
    }
  }

  @Override
  public AOrganization getOrgByName(String orgName, long parentId) {

    //一级
    int shiIdx = orgName.indexOf("市");
    int xianIdx = orgName.indexOf("县");
    //二级
    int xiangIdx = orgName.indexOf("乡");
    int zhenIdx = orgName.indexOf("镇");
    //三级
    int cunIdx = orgName.indexOf("村");
    //四级
    int zuIdx = orgName.indexOf("组");
    int duiIdx = orgName.indexOf("队");

    String name1 = "", name2 = "", name3 = "", name4 = "";
    if (xianIdx > 0) {
      name1 = orgName.substring(0, xianIdx + 1);
      if (xiangIdx > 0) {
        name2 = orgName.substring(xianIdx + 1, xiangIdx + 1);
      } else if (zhenIdx > 0) {
        name2 = orgName.substring(xianIdx + 1, zhenIdx + 1);
      }
    } else if (shiIdx > 0) {
      name1 = orgName.substring(0, shiIdx + 1);
      if (xiangIdx > 0) {
        name2 = orgName.substring(shiIdx + 1, xiangIdx + 1);
      } else if (zhenIdx > 0) {
        name2 = orgName.substring(shiIdx + 1, zhenIdx + 1);
      }
    } else {
      if (xiangIdx > 0) {
        name2 = orgName.substring(0, xiangIdx + 1);
      } else if (zhenIdx > 0) {
        name2 = orgName.substring(0, zhenIdx + 1);
      }
    }
    if (cunIdx > 0) {
      if (xiangIdx > 0) {
        name3 = orgName.substring(xiangIdx + 1, cunIdx + 1);
      } else if (zhenIdx > 0) {
        name3 = orgName.substring(zhenIdx + 1, cunIdx + 1);
      }
    }
    if (zuIdx > 0) {
      name4 = orgName.substring(cunIdx + 1, zuIdx + 1);
    } else if (duiIdx > 0) {
      name4 = orgName.substring(cunIdx + 1, duiIdx + 1);
    }

    AOrganization org2 = new AOrganization();
    AOrganization org3 = new AOrganization();
    AOrganization org4 = new AOrganization();

    if (!name2.isEmpty()) {
      org2.setOrgName(name2);
      org2.setParentId(parentId);
      org2.setIsDeleted(false);
      this.saveByName(org2);
      if (!name3.isEmpty()) {
        org3.setOrgName(name3);
        org3.setParentId(org2.getOrgId());
        org3.setIsDeleted(false);
        this.saveByName(org3);
        if (!name4.isEmpty()) {
          org4.setOrgName(name4);
          org4.setParentId(org3.getOrgId());
          org4.setIsDeleted(false);
          this.saveByName(org4);
          return org4;
        }
        return org3;
      }
      return org2;
    }

    return null;
  }
}
