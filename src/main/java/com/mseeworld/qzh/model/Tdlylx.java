/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author xy
 */
@Entity
@Table(name = "cbf", schema = "public")
public class Tdlylx implements java.io.Serializable {
  
  private int id;
  private String lbbm;
  private String lbmc;
  private String hy;

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tdlylx_seq")
  @SequenceGenerator(name = "tdlylx_seq", sequenceName = "tdlylx_id_seq",allocationSize=1)
  @Column(name = "id", unique = true, nullable = false)
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the lbbm
   */
  @Column(name = "lbbm")
  public String getLbbm() {
    return lbbm;
  }

  /**
   * @param lbbm the lbbm to set
   */
  public void setLbbm(String lbbm) {
    this.lbbm = lbbm;
  }

  /**
   * @return the lbmc
   */
  @Column(name = "lbmc")
  public String getLbmc() {
    return lbmc;
  }

  /**
   * @param lbmc the lbmc to set
   */
  public void setLbmc(String lbmc) {
    this.lbmc = lbmc;
  }

  /**
   * @return the hy
   */
  @Column(name = "hy")
  public String getHy() {
    return hy;
  }

  /**
   * @param hy the hy to set
   */
  public void setHy(String hy) {
    this.hy = hy;
  }
  
}
