package com.mseeworld.qzh.view;
// Generated Oct 28, 2014 10:12:26 PM by Hibernate Tools 3.2.1.GA

import javax.persistence.Column;


public class CbhtView implements java.io.Serializable {

  private long id;
  private String cbhtbm;
  private String fbfbm;
  private String cbfbm;
  private String cbfmc;
  private Long orgId;

  public CbhtView() {
  }

  public CbhtView(long id, String cbhtbm) {
    this.id = id;
    this.cbhtbm = cbhtbm;
  }

  public CbhtView(long id, String cbhtbm, String fbfbm, String cbfbm, String cbfmc) {
    this.id = id;
    this.cbhtbm = cbhtbm;
    this.fbfbm = fbfbm;
    this.cbfbm = cbfbm;
    this.cbfmc = cbfmc;
    this.orgId = orgId;
  }

  @Column(name = "id", unique = true, nullable = false)
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "cbhtbm", nullable = false, length = 19)
  public String getCbhtbm() {
    return this.cbhtbm;
  }

  public void setCbhtbm(String cbhtbm) {
    this.cbhtbm = cbhtbm;
  }

  @Column(name = "fbfbm", length = 14)
  public String getFbfbm() {
    return this.fbfbm;
  }

  public void setFbfbm(String fbfbm) {
    this.fbfbm = fbfbm;
  }

  @Column(name = "cbfbm", length = 18)
  public String getCbfbm() {
    return this.cbfbm;
  }

  public void setCbfbm(String cbfbm) {
    this.cbfbm = cbfbm;
  }

  @Column(name = "org_id")
  public Long getOrgId() {
    return this.orgId;
  }

  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  @Column(name = "cbfmc")
  public String getCbfmc() {
    return cbfmc;
  }

  public void setCbfmc(String cbfmc) {
    this.cbfmc = cbfmc;
  }
}
