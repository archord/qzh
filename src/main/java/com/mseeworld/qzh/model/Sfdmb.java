package com.mseeworld.qzh.model;
// Generated Jan 2, 2015 1:00:04 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sfdmb generated by hbm2java
 */
@Entity
@Table(name="sfdmb"
    ,schema="public"
)
public class Sfdmb  implements java.io.Serializable {


     private char dm;
     private String sf;

    public Sfdmb() {
    }

	
    public Sfdmb(char dm) {
        this.dm = dm;
    }
    public Sfdmb(char dm, String sf) {
       this.dm = dm;
       this.sf = sf;
    }
   
     @Id 
    
    @Column(name="dm", unique=true, nullable=false, length=1)
    public char getDm() {
        return this.dm;
    }
    
    public void setDm(char dm) {
        this.dm = dm;
    }
    
    @Column(name="sf")
    public String getSf() {
        return this.sf;
    }
    
    public void setSf(String sf) {
        this.sf = sf;
    }




}

