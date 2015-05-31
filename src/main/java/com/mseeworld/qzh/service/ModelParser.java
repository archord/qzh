/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.service;

import com.mseeworld.qzh.bean.Cbf;
import com.mseeworld.qzh.bean.Dldjdmb;
import com.mseeworld.qzh.bean.Dk;
import com.mseeworld.qzh.bean.Tdytdmb;
import com.mseeworld.qzh.bean.Sfdmb;
import com.mseeworld.qzh.bean.Cbht;
import com.mseeworld.qzh.bean.Fbf;
import com.mseeworld.qzh.bean.CbjyqzQzzx;
import com.mseeworld.qzh.bean.Qslyzlfj;
import com.mseeworld.qzh.bean.Zjlxdmb;
import com.mseeworld.qzh.bean.Tdlylx;
import com.mseeworld.qzh.bean.CbfJtcy;
import com.mseeworld.qzh.bean.Cbjyqqdfsdmb;
import com.mseeworld.qzh.bean.Lzht;
import com.mseeworld.qzh.bean.Cbjyqzdjb;
import com.mseeworld.qzh.bean.Cbflxdmb;
import com.mseeworld.qzh.bean.CbjyqzQzhf;
import com.mseeworld.qzh.bean.Cbjyqz;
import com.mseeworld.qzh.bean.Cbdkxx;
import com.mseeworld.qzh.bean.Dklbdmb;
import com.mseeworld.qzh.bean.Xbdmb;
import com.mseeworld.qzh.bean.CbjyqzQzbf;
import com.mseeworld.qzh.bean.Syqsxdmb;
import com.mseeworld.qzh.bean.Cybzdmb;
import com.mseeworld.qzh.dao.*;
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
  private SfdmbDao sfdmbDao; //是否
  private SyqsxDao syqsxDao; //所有权属性
  private TdytdmbDao tdytdmbDao; //土地用途
  private XbdmbDao xbdmbDao;  //性别类型
  private ZjlxdmbDao zjlxdmbDao; //证件类型
  private TdlylxDao tdlylxDao;
  private FbfDao fbfDao;
  private CbfDao cbfDao;
  private CbfJtcyDao cbfJtcyDao;
  private CbhtDao cbhtDao;
  private LzhhtDao lzhhtDao;
  private DkDao dkDao;
  private CbdkxxDao cbdkxxDao;
  private QslyzlfjDao qslyzlfjDao;  //权属来源
  private CbjyqzdjbDao cbjyqzdjbDao;
  private CbjyqzDao cbjyqzDao;
  private CbjyqzQzbfDao cbjyqzQzbfDao;
  private CbjyqzQzhfDao cbjyqzQzhfDao;
  private CbjyqzQzzxDao cbjyqzQzzxDao;

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

      sheet = ExcelReader.getSheet(wb, 1);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbf> cbfs = getCbfs(list);
      for (Cbf obj : cbfs) {
        System.out.println("cbfbm=" + obj.getCbfbm());
        cbfDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 2);
      list = ExcelReader.listFromSheet(sheet);
      List<CbfJtcy> cbfJtcys = getCbfJtcys(list);
      for (CbfJtcy obj : cbfJtcys) {
        System.out.println("cbfJtcys=" + obj.getCbfbm());
        cbfJtcyDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 3);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbht> cbhts = getCbhts(list);
      for (Cbht obj : cbhts) {
        System.out.println("cbhts=" + obj.getCbhtbm());
        cbhtDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 4);
      list = ExcelReader.listFromSheet(sheet);
      List<Lzht> lzhts = getLzhhts(list);
      for (Lzht obj : lzhts) {
        System.out.println("lzhts=" + obj.getLzhtbm());
        lzhhtDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 5);
      list = ExcelReader.listFromSheet(sheet);
      List<Dk> dks = getDks(list);
      for (Dk obj : dks) {
        System.out.println("dks=" + obj.getDkbm());
        dkDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 6);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbdkxx> cbdkxxs = getCbdkxxs(list);
      for (Cbdkxx obj : cbdkxxs) {
        System.out.println("cbdkxx=" + obj.getDkbm());
        cbdkxxDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 7);
      list = ExcelReader.listFromSheet(sheet);
      List<Qslyzlfj> qslyzlfjs = getQslyzlfjs(list);
      for (Qslyzlfj obj : qslyzlfjs) {
        System.out.println("Qslyzlfj=" + obj.getCbjyqzbm());
        qslyzlfjDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 8);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbjyqzdjb> cbjyqzdjbs = getCbjyqzdjbs(list);
      for (Cbjyqzdjb obj : cbjyqzdjbs) {
        System.out.println("Cbjyqzdjb=" + obj.getCbjyqzbm());
        cbjyqzdjbDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 9);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbjyqz> cbjyqzs = getCbjyqzs(list);
      for (Cbjyqz obj : cbjyqzs) {
        System.out.println("cbjyqzs=" + obj.getCbjyqzbm());
        cbjyqzDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 10);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzbf> cbjyqzQzbfs = getCbjyqzQzbfs(list);
      for (CbjyqzQzbf obj : cbjyqzQzbfs) {
        System.out.println("CbjyqzQzbf=" + obj.getCbjyqzbm());
        cbjyqzQzbfDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 11);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzhf> cbjyqzQzhfs = getCbjyqzQzhfs(list);
      for (CbjyqzQzhf obj : cbjyqzQzhfs) {
        System.out.println("CbjyqzQzhf=" + obj.getCbjyqzbm());
        cbjyqzQzhfDao.save(obj);
      }

      sheet = ExcelReader.getSheet(wb, 12);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzzx> cbjyqzQzzxs = getCbjyqzQzzxs(list);
      for (CbjyqzQzzx obj : cbjyqzQzzxs) {
        System.out.println("cbjyqzQzzxs=" + obj.getCbjyqzbm());
        cbjyqzQzzxDao.save(obj);
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
          if (ts[0] == null) {
            obj.setFbfbm("");
          } else {
            obj.setFbfbm(ts[0].trim());
          }
          if (ts[1] == null) {
            obj.setFbfmc("");
          } else {
            obj.setFbfmc(ts[1].trim());
          }
          if (ts[2] == null) {
            obj.setFbffzrxm("");
          } else {
            obj.setFbffzrxm(ts[2].trim());
          }
          if (ts[3] != null) {
            Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[3].trim());
            obj.setFzrzjlx(zjlx.getDm());
          }
          if (ts[4] == null) {
            obj.setFzrzjhm("");
          } else {
            obj.setFzrzjhm(ts[4].trim());
          }
          if (ts[5] == null) {
            obj.setLxdh("");
          } else {
            obj.setLxdh(ts[5].trim());
          }
          if (ts[6] == null) {
            obj.setFbfdz("");
          } else {
            obj.setFbfdz(ts[6].trim());
          }
          if (ts[7] == null) {
            obj.setYzbm("");
          } else {
            obj.setYzbm(ts[7].trim());
          }
          if (ts[8] == null) {
            obj.setFbfdcy("");
          } else {
            obj.setFbfdcy(ts[8].trim());
          }
          if (ts[9] != null && !ts[9].isEmpty()) {
            obj.setFbfdcrq(format.parse(ts[9].trim()));
          }
          if (ts[10] == null) {
            obj.setFbfdcjs("");
          } else {
            obj.setFbfdcjs(ts[10].trim());
          }
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<Cbf> getCbfs(List<String[]> values) {

    ArrayList<Cbf> objs = new ArrayList<Cbf>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 18) {
        try {
          Cbf obj = new Cbf();
          obj.setCbfbm(ts[0].trim());
          Cbflxdmb Cbflxdmb = cbflxdmbDao.getByName(ts[1].trim());
          obj.setCbflx(Cbflxdmb.getDm());
          obj.setCbfmc(ts[2].trim());
          Xbdmb xbdmb = xbdmbDao.getByName(ts[3].trim());
          obj.setCbfxb(xbdmb.getDm());
          obj.setCbfmz(ts[4].trim());
          System.out.println("query:" + ts[5].trim() + ":");
          Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[5].trim());
          obj.setCbfzjlx(zjlx.getDm());
          if (ts[6] == null) {
            obj.setCbfzjhm("");
          } else {
            obj.setCbfzjhm(ts[6].trim());
          }
          obj.setCbfdz(ts[7].trim());
          obj.setYzbm(ts[8].trim());
          obj.setLxdh(ts[9].trim());
          if (ts[10] != null && !ts[10].isEmpty()) {
            obj.setCbfcysl(Integer.parseInt(ts[10].trim()));
          }
          if (ts[11] != null && !ts[11].isEmpty()) {
            obj.setCbfdcrq(format.parse(ts[11].trim()));
          }
          obj.setCbfdcy(ts[12].trim());
          obj.setCbfdcjs(ts[13].trim());
          obj.setGsjs(ts[14].trim());
          obj.setGsjsr(ts[15].trim());
          if (ts[16] != null && !ts[16].isEmpty()) {
            obj.setGsshrq(format.parse(ts[16].trim()));
          }
          obj.setGsshr(ts[17].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<CbfJtcy> getCbfJtcys(List<String[]> values) {

    ArrayList<CbfJtcy> objs = new ArrayList<CbfJtcy>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 9) {
        CbfJtcy obj = new CbfJtcy();
        obj.setCbfbm(ts[0].trim());
        obj.setCyxm(ts[1].trim());
        Xbdmb xbdmb = xbdmbDao.getByName(ts[2].trim());
        obj.setCyxb(xbdmb.getDm());
        obj.setCymz(ts[3].trim());
        Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[4].trim());
        obj.setCyzjlx(zjlx.getDm());
        obj.setCyzjhm(ts[5].trim());
        obj.setYhzgx(ts[6].trim());
        Cybzdmb cybzdmb = cybzdmbDao.getByName(ts[7].trim());
        obj.setCybz(cybzdmb.getDm());
        Sfdmb sfdmb = sfdmbDao.getByName(ts[8].trim());
        obj.setSfgyr(sfdmb.getDm());
        objs.add(obj);
      }
    }
    return objs;
  }

  public List<Cbht> getCbhts(List<String[]> values) {

    ArrayList<Cbht> objs = new ArrayList<Cbht>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 10) {
        try {
          Cbht obj = new Cbht();
          obj.setCbhtbm(ts[0].trim());
          if (ts[1] == null || ts[1].isEmpty()) {
            obj.setYcbhtbm(" ");
          } else {
            obj.setYcbhtbm(ts[1].trim());
          }
          obj.setFbfbm(ts[2].trim());
          obj.setCbfbm(ts[3].trim());
          Cbjyqqdfsdmb cbjyqqdfsdmb = cbjyqqdfsdmbDao.getByName(ts[4].trim());
          obj.setCbfs(cbjyqqdfsdmb.getDm());
          if (ts[5] != null && !ts[5].isEmpty()) {
            obj.setCbqxq(format.parse(ts[5].trim()));
          }
          if (ts[6] != null && !ts[6].isEmpty()) {
            obj.setCbqxz(format.parse(ts[6].trim()));
          }
          if (ts[7] != null && !ts[7].isEmpty()) {
            obj.setHtzmj(Float.parseFloat(ts[7].trim()));
          }
          if (ts[8] != null && !ts[8].isEmpty()) {
            obj.setCbdkzs(Integer.parseInt(ts[8].trim()));
          }
          if (ts[9] != null && !ts[9].isEmpty()) {
            obj.setQdsj(format.parse(ts[9].trim()));
          }
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<Lzht> getLzhhts(List<String[]> values) {

    ArrayList<Lzht> objs = new ArrayList<Lzht>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 14) {
        try {
          Lzht obj = new Lzht();
          obj.setYcbhtbm(ts[0].trim());
          obj.setLzhtbm(ts[1].trim());
          obj.setCbfbm(ts[2].trim());
          obj.setSrfbm(ts[3].trim());
          Cbjyqqdfsdmb cbjyqqdfsdmb = cbjyqqdfsdmbDao.getByName(ts[4].trim());
          obj.setLzfs(cbjyqqdfsdmb.getDm());
          obj.setLzqx(ts[5].trim());
          if (ts[6] != null && !ts[6].isEmpty()) {
            obj.setLzqxksrq(format.parse(ts[6].trim()));
          }
          if (ts[7] != null && !ts[7].isEmpty()) {
            obj.setLzqxjsrq(format.parse(ts[7].trim()));
          }
          if (ts[8] != null && !ts[8].isEmpty()) {
            obj.setLzmj(Float.parseFloat(ts[8].trim()));
          }
          if (ts[9] != null && !ts[9].isEmpty()) {
            obj.setLzdks(Integer.parseInt(ts[9].trim()));
          }
          Tdytdmb tdytdmb1 = tdytdmbDao.getByName(ts[10].trim());
          obj.setLzqtdyt(tdytdmb1.getDm());
          Tdytdmb tdytdmb2 = tdytdmbDao.getByName(ts[11].trim());
          obj.setLzhtdyt(tdytdmb2.getDm());
          obj.setLzjgsm(ts[12].trim());
          if (ts[13] != null && !ts[13].isEmpty()) {
            obj.setHtqdrq(format.parse(ts[13].trim()));
          }
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<Dk> getDks(List<String[]> values) {

    ArrayList<Dk> objs = new ArrayList<Dk>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 15) {
        Dk obj = new Dk();
        obj.setDkbm(ts[0].trim());
        obj.setDkmc(ts[1].trim());
        Syqsxdmb syqsxdmb = syqsxDao.getByName(ts[2].trim());
        obj.setSyqxz(syqsxdmb.getDm());
        Dklbdmb dklbdmb = dklbdmbDao.getByName(ts[3].trim());
        obj.setDklb(dklbdmb.getDm());
        Tdlylx tdlylx = tdlylxDao.getByName(ts[4].trim());
        obj.setTdlylx(tdlylx.getLbbm());
        Dldjdmb dldjdmb = dldjdmbDao.getByName(ts[5].trim());
        obj.setDldj(dldjdmb.getDm());
        Tdytdmb tdytdmb = tdytdmbDao.getByName(ts[6].trim());
        obj.setTdyt(tdytdmb.getDm());
        Sfdmb sfdmb = sfdmbDao.getByName(ts[7].trim());
        obj.setSfjbnt(sfdmb.getDm());
        if (ts[8] != null && !ts[8].isEmpty()) {
          obj.setScmj(Float.parseFloat(ts[8].trim()));
        }
        obj.setDkdz(ts[9].trim());
        obj.setDkxz(ts[10].trim());
        obj.setDknz(ts[11].trim());
        obj.setDkbz(ts[12].trim());
        obj.setDkbzxx(ts[13].trim());
        obj.setZjrxm(ts[14].trim());
        objs.add(obj);
      }
    }
    return objs;
  }

  public List<Cbdkxx> getCbdkxxs(List<String[]> values) {

    ArrayList<Cbdkxx> objs = new ArrayList<Cbdkxx>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 8) {
        Cbdkxx obj = new Cbdkxx();
        obj.setDkbm(ts[0].trim());
        obj.setFbfbm(ts[1].trim());
        obj.setCbfbm(ts[2].trim());
        Cbjyqqdfsdmb cbjyqqdfsdmb = cbjyqqdfsdmbDao.getByName(ts[3].trim());
        obj.setCbjyqqdfs(cbjyqqdfsdmb.getDm());
        if (ts[4] != null && !ts[4].isEmpty()) {
          obj.setHtmj(Float.parseFloat(ts[4].trim()));
        }
        obj.setCbhtbm(ts[5].trim());
        if (null == ts[6] || ts[6].isEmpty()) {
          obj.setLzhtbm(" ");
        } else {
          obj.setLzhtbm(ts[6].trim());
        }
        obj.setCbjyqzbm(ts[7].trim());
        objs.add(obj);
      }
    }
    return objs;
  }

  public List<Qslyzlfj> getQslyzlfjs(List<String[]> values) {

    ArrayList<Qslyzlfj> objs = new ArrayList<Qslyzlfj>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 5) {
        try {
          Qslyzlfj obj = new Qslyzlfj();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setZlfjbh(ts[1].trim());
          obj.setZlfjmc(ts[2].trim());
          if (ts[3] != null && !ts[3].isEmpty()) {
            obj.setZlfjrq(format.parse(ts[3].trim()));
          }
          obj.setFj(ts[4].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<Cbjyqzdjb> getCbjyqzdjbs(List<String[]> values) {

    ArrayList<Cbjyqzdjb> objs = new ArrayList<Cbjyqzdjb>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 8) {
        try {
          Cbjyqzdjb obj = new Cbjyqzdjb();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setFbfbm(ts[1].trim());
          obj.setCbfbm(ts[2].trim());
          Cbjyqqdfsdmb cbjyqqdfsdmb = cbjyqqdfsdmbDao.getByName(ts[3].trim());
          obj.setCbfs(cbjyqqdfsdmb.getDm());
          obj.setCbqx(ts[4].trim());
          if (ts[5] != null && !ts[5].isEmpty()) {
            obj.setCbqxq(format.parse(ts[5].trim()));
          }
          if (ts[6] != null && !ts[6].isEmpty()) {
            obj.setCbqxz(format.parse(ts[6].trim()));
          }
          obj.setDksyt(ts[7].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<Cbjyqz> getCbjyqzs(List<String[]> values) {

    ArrayList<Cbjyqz> objs = new ArrayList<Cbjyqz>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 8) {
        try {
          Cbjyqz obj = new Cbjyqz();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setFzjg(ts[1].trim());
          if (ts[2] != null && !ts[2].isEmpty()) {
            obj.setFzrq(format.parse(ts[2].trim()));
          }
          Sfdmb sfdmb = sfdmbDao.getByName(ts[3].trim());
          obj.setQzsfly(sfdmb.getDm());
          if (ts[4] != null && !ts[4].isEmpty()) {
            obj.setQzlqrq(format.parse(ts[4].trim()));
          }
          obj.setQzlqrxm(ts[5].trim());
          Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[6].trim());
          obj.setQzlqrzjlx(zjlx.getDm());
          obj.setQzlqrzjhm(ts[7].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<CbjyqzQzbf> getCbjyqzQzbfs(List<String[]> values) {

    ArrayList<CbjyqzQzbf> objs = new ArrayList<CbjyqzQzbf>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 7) {
        try {
          CbjyqzQzbf obj = new CbjyqzQzbf();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setQzbfyy(ts[1].trim());
          if (ts[2] != null && !ts[2].isEmpty()) {
            obj.setBfrq(format.parse(ts[2].trim()));
          }
          if (ts[3] != null && !ts[3].isEmpty()) {
            obj.setQzbflqrq(format.parse(ts[3].trim()));
          }
          obj.setQzbflqrxm(ts[4].trim());
          Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[5].trim());
          obj.setBflqrzjlx(zjlx.getDm());
          obj.setBflqrzjhm(ts[6].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<CbjyqzQzhf> getCbjyqzQzhfs(List<String[]> values) {

    ArrayList<CbjyqzQzhf> objs = new ArrayList<CbjyqzQzhf>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 7) {
        try {
          CbjyqzQzhf obj = new CbjyqzQzhf();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setQzhfyy(ts[1].trim());
          if (ts[2] != null && !ts[2].isEmpty()) {
            obj.setHfrq(format.parse(ts[2].trim()));
          }
          if (ts[3] != null && !ts[3].isEmpty()) {
            obj.setQzhflqrq(format.parse(ts[3].trim()));
          }
          obj.setQzhflqrxm(ts[4].trim());
          Zjlxdmb zjlx = zjlxdmbDao.getByName(ts[5].trim());
          obj.setHflqrzjlx(zjlx.getDm());
          obj.setHflqrzjhm(ts[6].trim());
          objs.add(obj);
        } catch (ParseException ex) {
          Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return objs;
  }

  public List<CbjyqzQzzx> getCbjyqzQzzxs(List<String[]> values) {

    ArrayList<CbjyqzQzzx> objs = new ArrayList<CbjyqzQzzx>();
    DateFormat format = new SimpleDateFormat("yyyyMMdd");
    int i = 0;
    for (String[] ts : values) {
      if (i == 0) {
        i++;
        continue;
      }
      if (ts.length == 3) {
        try {
          CbjyqzQzzx obj = new CbjyqzQzzx();
          obj.setCbjyqzbm(ts[0].trim());
          obj.setZxyy(ts[1].trim());
          if (ts[2] != null && !ts[2].isEmpty()) {
            obj.setZxrq(format.parse(ts[2].trim()));
          }
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

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setCbfDao(CbfDao cbfDao) {
    this.cbfDao = cbfDao;
  }

  /**
   * @param cbfJtcyDao the cbfJtcyDao to set
   */
  @Resource
  public void setCbfJtcyDao(CbfJtcyDao cbfJtcyDao) {
    this.cbfJtcyDao = cbfJtcyDao;
  }

  /**
   * @param cbhtDao the cbhtDao to set
   */
  @Resource
  public void setCbhtDao(CbhtDao cbhtDao) {
    this.cbhtDao = cbhtDao;
  }

  /**
   * @param lzhhtDao the lzhhtDao to set
   */
  @Resource
  public void setLzhhtDao(LzhhtDao lzhhtDao) {
    this.lzhhtDao = lzhhtDao;
  }

  /**
   * @param dkDao the dkDao to set
   */
  @Resource
  public void setDkDao(DkDao dkDao) {
    this.dkDao = dkDao;
  }

  /**
   * @param cbdkxxDao the cbdkxxDao to set
   */
  @Resource
  public void setCbdkxxDao(CbdkxxDao cbdkxxDao) {
    this.cbdkxxDao = cbdkxxDao;
  }

  /**
   * @param qslyzlfjDao the qslyzlfjDao to set
   */
  @Resource
  public void setQslyzlfjDao(QslyzlfjDao qslyzlfjDao) {
    this.qslyzlfjDao = qslyzlfjDao;
  }

  /**
   * @param cbjyqzdjbDao the cbjyqzdjbDao to set
   */
  @Resource
  public void setCbjyqzdjbDao(CbjyqzdjbDao cbjyqzdjbDao) {
    this.cbjyqzdjbDao = cbjyqzdjbDao;
  }

  /**
   * @param cbjyqzDao the cbjyqzDao to set
   */
  @Resource
  public void setCbjyqzDao(CbjyqzDao cbjyqzDao) {
    this.cbjyqzDao = cbjyqzDao;
  }

  /**
   * @param cbjyqzQzbfDao the cbjyqzQzbfDao to set
   */
  @Resource
  public void setCbjyqzQzbfDao(CbjyqzQzbfDao cbjyqzQzbfDao) {
    this.cbjyqzQzbfDao = cbjyqzQzbfDao;
  }

  /**
   * @param cbjyqzQzhfDao the cbjyqzQzhfDao to set
   */
  @Resource
  public void setCbjyqzQzhfDao(CbjyqzQzhfDao cbjyqzQzhfDao) {
    this.cbjyqzQzhfDao = cbjyqzQzhfDao;
  }

  /**
   * @param cbjyqzQzzxDao the cbjyqzQzzxDao to set
   */
  @Resource
  public void setCbjyqzQzzxDao(CbjyqzQzzxDao cbjyqzQzzxDao) {
    this.cbjyqzQzzxDao = cbjyqzQzzxDao;
  }

  /**
   * @param sfdmbDao the sfdmbDao to set
   */
  @Resource
  public void setSfdmbDao(SfdmbDao sfdmbDao) {
    this.sfdmbDao = sfdmbDao;
  }

  /**
   * @param syqsxDao the syqsxDao to set
   */
  @Resource
  public void setSyqsxDao(SyqsxDao syqsxDao) {
    this.syqsxDao = syqsxDao;
  }

  /**
   * @param tdytdmbDao the tdytdmbDao to set
   */
  @Resource
  public void setTdytdmbDao(TdytdmbDao tdytdmbDao) {
    this.tdytdmbDao = tdytdmbDao;
  }

  /**
   * @param tdlylxDao the tdlylxDao to set
   */
  @Resource
  public void setTdlylxDao(TdlylxDao tdlylxDao) {
    this.tdlylxDao = tdlylxDao;
  }
}
