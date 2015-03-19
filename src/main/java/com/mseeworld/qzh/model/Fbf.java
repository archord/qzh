package com.mseeworld.qzh.model;
// Generated Sep 6, 2014 9:23:12 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Fbf generated by hbm2java
 */
@Entity
@Table(name = "fbf", schema = "public")
public class Fbf implements java.io.Serializable {

  private long id;
  private long orgId;
  private String fbfbm;
  private String fbfmc;
  private String fbffzrxm;
  private Character fzrzjlx;
  private String fzrzjhm;
  private String lxdh;
  private String fbfdz;
  private String yzbm;
  private String fbfdcy;
  private Date fbfdcrq;
  private String fbfdcjs;

  public Fbf() {
  }

  public Fbf(long id) {
    this.id = id;
  }

  public Fbf(long id, String fbfbm, String fbfmc, String fbffzrxm, Character fzrzjlx, String fzrzjhm, String lxdh, String fbfdz, String yzbm, String fbfdcy, Date fbfdcrq, String fbfdcjs) {
    this.id = id;
    this.fbfbm = fbfbm;
    this.fbfmc = fbfmc;
    this.fbffzrxm = fbffzrxm;
    this.fzrzjlx = fzrzjlx;
    this.fzrzjhm = fzrzjhm;
    this.lxdh = lxdh;
    this.fbfdz = fbfdz;
    this.yzbm = yzbm;
    this.fbfdcy = fbfdcy;
    this.fbfdcrq = fbfdcrq;
    this.fbfdcjs = fbfdcjs;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fbf_seq")
  @SequenceGenerator(name = "fbf_seq", sequenceName = "fbf_id_seq",allocationSize=1)
  @Column(name = "id", unique = true, nullable = false)
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "fbfbm", length = 14)
  public String getFbfbm() {
    return this.fbfbm;
  }

  public void setFbfbm(String fbfbm) {
    this.fbfbm = fbfbm;
  }

  @Column(name = "fbfmc", length = 50)
  public String getFbfmc() {
    return this.fbfmc;
  }

  public void setFbfmc(String fbfmc) {
    this.fbfmc = fbfmc;
  }

  @Column(name = "fbffzrxm", length = 50)
  public String getFbffzrxm() {
    return this.fbffzrxm;
  }

  public void setFbffzrxm(String fbffzrxm) {
    this.fbffzrxm = fbffzrxm;
  }

  @Column(name = "fzrzjlx", length = 1)
  public Character getFzrzjlx() {
    return this.fzrzjlx;
  }

  public void setFzrzjlx(Character fzrzjlx) {
    this.fzrzjlx = fzrzjlx;
  }

  @Column(name = "fzrzjhm", length = 30)
  public String getFzrzjhm() {
    return this.fzrzjhm;
  }

  public void setFzrzjhm(String fzrzjhm) {
    this.fzrzjhm = fzrzjhm;
  }

  @Column(name = "lxdh", length = 15)
  public String getLxdh() {
    return this.lxdh;
  }

  public void setLxdh(String lxdh) {
    this.lxdh = lxdh;
  }

  @Column(name = "fbfdz", length = 100)
  public String getFbfdz() {
    return this.fbfdz;
  }

  public void setFbfdz(String fbfdz) {
    this.fbfdz = fbfdz;
  }

  @Column(name = "yzbm", length = 6)
  public String getYzbm() {
    return this.yzbm;
  }

  public void setYzbm(String yzbm) {
    this.yzbm = yzbm;
  }

  @Column(name = "fbfdcy", length = 300)
  public String getFbfdcy() {
    return this.fbfdcy;
  }

  public void setFbfdcy(String fbfdcy) {
    this.fbfdcy = fbfdcy;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fbfdcrq", length = 13)
  public Date getFbfdcrq() {
    return this.fbfdcrq;
  }

  public void setFbfdcrq(Date fbfdcrq) {
    this.fbfdcrq = fbfdcrq;
  }

  @Column(name = "fbfdcjs", length = 300)
  public String getFbfdcjs() {
    return this.fbfdcjs;
  }

  public void setFbfdcjs(String fbfdcjs) {
    this.fbfdcjs = fbfdcjs;
  }

  /**
   * @return the orgId
   */
  @Column(name = "org_id", length = 300)
  public long getOrgId() {
    return orgId;
  }

  /**
   * @param orgId the orgId to set
   */
  public void setOrgId(long orgId) {
    this.orgId = orgId;
  }
}
