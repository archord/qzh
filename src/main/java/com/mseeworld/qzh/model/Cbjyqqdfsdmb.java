package com.mseeworld.qzh.model;
// Generated Jan 2, 2015 1:00:04 PM by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cbjyqqdfsdmb generated by hbm2java
 */
@Entity
@Table(name="cbjyqqdfsdmb"
    ,schema="public"
)
public class Cbjyqqdfsdmb  implements java.io.Serializable {


     private String dm;
     private String qdfs;

    public Cbjyqqdfsdmb() {
    }

	
    public Cbjyqqdfsdmb(String dm) {
        this.dm = dm;
    }
    public Cbjyqqdfsdmb(String dm, String qdfs) {
       this.dm = dm;
       this.qdfs = qdfs;
    }
   
     @Id 
    
    @Column(name="dm", unique=true, nullable=false, length=3)
    public String getDm() {
        return this.dm;
    }
    
    public void setDm(String dm) {
        this.dm = dm;
    }
    
    @Column(name="qdfs")
    public String getQdfs() {
        return this.qdfs;
    }
    
    public void setQdfs(String qdfs) {
        this.qdfs = qdfs;
    }




}


