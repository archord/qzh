/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.service;

import com.mseeworld.qzh.dao.CbflxdmbDao;
import com.mseeworld.qzh.dao.CbjyqqdfsdmbDao;
import com.mseeworld.qzh.dao.CybzdmbDao;
import com.mseeworld.qzh.dao.DklbdmbDao;
import com.mseeworld.qzh.dao.DldjdmbDao;
import com.mseeworld.qzh.dao.FbfDao;
import com.mseeworld.qzh.dao.SfdmbDao;
import com.mseeworld.qzh.dao.SyqsxDao;
import com.mseeworld.qzh.dao.TdytdmbDao;
import com.mseeworld.qzh.dao.XbdmbDao;
import com.mseeworld.qzh.dao.ZjlxdmbDao;
import com.mseeworld.qzh.model.Fbf;
import com.mseeworld.qzh.model.Zjlxdmb;
import com.mseeworld.qzh.util.ExcelReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author xy
 */
public class ModelParser {

  private CbflxdmbDao cbflxdmbDao;   //承包方类型
  private CbjyqqdfsdmbDao cbjyqqdfsdmbDao; //承包经营权取得方式
  private CybzdmbDao cybzdmbDao; //成员备注
  private DklbdmbDao dklbdmbDao; //地块类比
  private DldjdmbDao dldjdmbDao; //地力等级
  private SfdmbDao SfdmbDao; //是否
  private SyqsxDao SyqsxDao; //所有权属性
  private TdytdmbDao TdytdmbDao; //土地用途
  private XbdmbDao xbdmbDao;  //性别类型
  private ZjlxdmbDao zjlxdmbDao; //证件类型
  
  private FbfDao fbfDao;

  public void parseData(String fileName) {
    try {
      Workbook wb = ExcelReader.createWb(fileName);
      Sheet sheet = ExcelReader.getSheet(wb, 0);
      List<String[]> list = ExcelReader.listFromSheet(sheet);

      List<Fbf> fbfs = getFbfs(list);
      for (Fbf obj : fbfs) {
        System.out.println("fbfbm=" + obj.getFbfbm());
        fbfDao.save(obj);
      }

    } catch (IOException ex) {
      Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public List<Fbf> getFbfs(List<String[]> values) {

    ArrayList<Fbf> objs = new ArrayList<Fbf>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 11) {
        try {
          Fbf obj = new Fbf();
          obj.setFbfbm(ts[0].trim());
          obj.setFbfmc(ts[1].trim());
          obj.setFbffzrxm(ts[2].trim());
          Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[3].trim());
          obj.setFzrzjlx(zjlx.getDm());
          obj.setFzrzjhm(ts[4].trim());
          obj.setLxdh(ts[5].trim());
          obj.setFbfdz(ts[6].trim());
          obj.setYzbm(ts[7].trim());
          obj.setFbfdcy(ts[8].trim());
          obj.setFbfdcrq(format.parse(ts[9].trim()));
          obj.setFbfdcjs(ts[10].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }

    return objs;
  }

  /**
   * @param cbflxdmbDao the cbflxdmbDao to set
   */
  @Resource
  public void setCbflxdmbDao(CbflxdmbDao cbflxdmbDao) {
    this.cbflxdmbDao = cbflxdmbDao;
  }

  /**
   * @param cbjyqqdfsdmbDao the cbjyqqdfsdmbDao to set
   */
  @Resource
  public void setCbjyqqdfsdmbDao(CbjyqqdfsdmbDao cbjyqqdfsdmbDao) {
    this.cbjyqqdfsdmbDao = cbjyqqdfsdmbDao;
  }

  /**
   * @param cybzdmbDao the cybzdmbDao to set
   */
  @Resource
  public void setCybzdmbDao(CybzdmbDao cybzdmbDao) {
    this.cybzdmbDao = cybzdmbDao;
  }

  /**
   * @param dklbdmbDao the dklbdmbDao to set
   */
  @Resource
  public void setDklbdmbDao(DklbdmbDao dklbdmbDao) {
    this.dklbdmbDao = dklbdmbDao;
  }

  /**
   * @param dldjdmbDao the dldjdmbDao to set
   */
  @Resource
  public void setDldjdmbDao(DldjdmbDao dldjdmbDao) {
    this.dldjdmbDao = dldjdmbDao;
  }

  /**
   * @param SfdmbDao the SfdmbDao to set
   */
  @Resource
  public void setSfdmbDao(SfdmbDao SfdmbDao) {
    this.SfdmbDao = SfdmbDao;
  }

  /**
   * @param SyqsxDao the SyqsxDao to set
   */
  @Resource
  public void setSyqsxDao(SyqsxDao SyqsxDao) {
    this.SyqsxDao = SyqsxDao;
  }

  /**
   * @param TdytdmbDao the TdytdmbDao to set
   */
  @Resource
  public void setTdytdmbDao(TdytdmbDao TdytdmbDao) {
    this.TdytdmbDao = TdytdmbDao;
  }

  /**
   * @param xbdmbDao the xbdmbDao to set
   */
  @Resource
  public void setXbdmbDao(XbdmbDao xbdmbDao) {
    this.xbdmbDao = xbdmbDao;
  }

  /**
   * @param zjlxdmbDao the zjlxdmbDao to set
   */
  @Resource
  public void setZjlxdmbDao(ZjlxdmbDao zjlxdmbDao) {
    this.zjlxdmbDao = zjlxdmbDao;
  }

  /**
   * @param fbfDao the fbfDao to set
   */
  @Resource
  public void setFbfDao(FbfDao fbfDao) {
    this.fbfDao = fbfDao;
  }
}
