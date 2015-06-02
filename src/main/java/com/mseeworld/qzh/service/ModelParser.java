/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.service;

import com.mseeworld.qzh.bean.AOrganization;
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
  
  private OrganizationDAO orgDao;
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
  
  public String parseData(String fileName) {
    StringBuilder sb = new StringBuilder("");
    try {
      Workbook wb = ExcelReader.createWb(fileName);
      
      Sheet sheet = ExcelReader.getSheet(wb, 0);
      List<String[]> list = ExcelReader.listFromSheet(sheet);
      String orgName = getOrgName(list);
      if (orgName == null || orgName.isEmpty()) {
        return "组织机构表->县（市）名称不能为空，终止导入。";
      }
      AOrganization org = new AOrganization();
      org.setOrgName(orgName);
      org.setParentId(0);
      org.setIsDeleted(false);
      orgDao.saveByName(org);
      
      sheet = ExcelReader.getSheet(wb, 1);
      list = ExcelReader.listFromSheet(sheet);
      List<Fbf> fbfs = getFbfs(list);
      int i = 1;
      int delTotal = 0;
      String tstr = "";
      for (Fbf obj : fbfs) {
        if (obj.getFbfdz() != null && !obj.getFbfdz().isEmpty()) {
          AOrganization torg = orgDao.getOrgByName(obj.getFbfdz(), org.getOrgId());
          if (torg != null) {
            obj.setOrgId(torg.getOrgId());
            int tnum = fbfDao.deleteAndSave(obj);
            if (tnum > 0) {
//              sb.append("发包方，第").append(i).append("行与前面有重复。<br/>");
              tstr += i + ",";
            }
            delTotal += tnum;
//        fbfDao.save(obj);
          } else {
            return "发包方，第" + i + "行，发包方地址（组织机构）不完整，终止导入。";
          }
        }
        i++;
      }
      if (delTotal > 0) {
        sb.append("发包方，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 2);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbf> cbfs = getCbfs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Cbf obj : cbfs) {
        if (obj.getCbfdz() != null && !obj.getCbfdz().isEmpty()) {
          AOrganization torg = orgDao.getOrgByName(obj.getCbfdz(), org.getOrgId());
          if (torg != null) {
            obj.setOrgId(torg.getOrgId());
            int tnum = cbfDao.deleteAndSave(obj);
            
            if (tnum > 0) {
//              sb.append("承包方，第").append(i).append("行与前面有重复。<br/>");
              tstr += i + ",";
            }
            delTotal += tnum;
//        fbfDao.save(obj);
          } else {
            return "承包方，第" + i + "行，发包方地址（组织机构）不完整，终止导入。";
          }
        }
        i++;
      }
      if (delTotal > 0) {
        sb.append("承包方，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 3);
      list = ExcelReader.listFromSheet(sheet);
      List<CbfJtcy> cbfJtcys = getCbfJtcys(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (CbfJtcy obj : cbfJtcys) {
        int tnum = cbfJtcyDao.deleteAndSave(obj);
        if (tnum > 0) {
//          sb.append("承包方家庭成员，第").append(i).append("行与前面有重复。<br/>");
          tstr += i + ",";
        }
        delTotal += tnum;
        i++;
      }
      if (delTotal > 0) {
        sb.append("承包方家庭成员，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 4);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbht> cbhts = getCbhts(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Cbht obj : cbhts) {
        Cbf tcbf = cbfDao.getByCbfbm(obj.getCbfbm());
        if (tcbf != null) {
          obj.setOrgId(tcbf.getOrgId());
          int tnum = cbhtDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("承包合同，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "承包合同，第" + i + "行，承包方编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("承包合同，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 5);
      list = ExcelReader.listFromSheet(sheet);
      List<Lzht> lzhts = getLzhhts(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Lzht obj : lzhts) {
        Cbf tcbf = cbfDao.getByCbfbm(obj.getCbfbm());
        if (tcbf != null) {
          obj.setOrgId(tcbf.getOrgId());
          int tnum = lzhhtDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("流转合同，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "流转合同，第" + i + "行，承包方编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("流转合同，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 7);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbdkxx> cbdkxxs = getCbdkxxs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Cbdkxx obj : cbdkxxs) {
        int tnum = cbdkxxDao.deleteAndSave(obj);
        if (tnum > 0) {
//          sb.append("承包地块，第").append(i).append("行与前面有重复。<br/>");
          tstr += i + ",";
        }
        delTotal += tnum;
        i++;
      }
      if (delTotal > 0) {
        sb.append("承包地块，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 6);
      list = ExcelReader.listFromSheet(sheet);
      List<Dk> dks = getDks(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Dk obj : dks) {
        Cbf tcbf = cbfDao.getByDkbm(obj.getDkbm());
        if (tcbf != null) {
          obj.setOrgId(tcbf.getOrgId());
          int tnum = dkDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("地块，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "地块，第" + i + "行，地块编码对应的承包地块信息不能找到承包方信息，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("地块，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 8);
      list = ExcelReader.listFromSheet(sheet);
      List<Qslyzlfj> qslyzlfjs = getQslyzlfjs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Qslyzlfj obj : qslyzlfjs) {
        int tnum = qslyzlfjDao.deleteAndSave(obj);
        if (tnum > 0) {
//          sb.append("权属来源，第").append(i).append("行与前面有重复。<br/>");
          tstr += i + ",";
        }
        delTotal += tnum;
        i++;
      }
      if (delTotal > 0) {
        sb.append("权属来源，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 9);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbjyqzdjb> cbjyqzdjbs = getCbjyqzdjbs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Cbjyqzdjb obj : cbjyqzdjbs) {
        Cbf tcbf = cbfDao.getByCbfbm(obj.getCbfbm());
        if (tcbf != null) {
          obj.setOrgId(tcbf.getOrgId());
          int tnum = cbjyqzdjbDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("承包经营权证登记簿，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "承包经营权证登记簿，第" + i + "行，承包方编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("承包经营权证登记簿，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 10);
      list = ExcelReader.listFromSheet(sheet);
      List<Cbjyqz> cbjyqzs = getCbjyqzs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (Cbjyqz obj : cbjyqzs) {
        Cbjyqzdjb djb = cbjyqzdjbDao.getByQzbm(obj.getCbjyqzbm());
        if (djb != null) {
          obj.setOrgId(djb.getOrgId());
          int tnum = cbjyqzDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("承包经营权证，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "承包经营权证，第" + i + "行，承包经营权证编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("承包经营权证，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 11);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzbf> cbjyqzQzbfs = getCbjyqzQzbfs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (CbjyqzQzbf obj : cbjyqzQzbfs) {
        Cbjyqzdjb djb = cbjyqzdjbDao.getByQzbm(obj.getCbjyqzbm());
        if (djb != null) {
          obj.setOrgId(djb.getOrgId());
          int tnum = cbjyqzQzbfDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("权证补发，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "权证补发，第" + i + "行，承包经营权证编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("权证补发，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 12);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzhf> cbjyqzQzhfs = getCbjyqzQzhfs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (CbjyqzQzhf obj : cbjyqzQzhfs) {
        Cbjyqzdjb djb = cbjyqzdjbDao.getByQzbm(obj.getCbjyqzbm());
        if (djb != null) {
          obj.setOrgId(djb.getOrgId());
          int tnum = cbjyqzQzhfDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("权证换发，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "权证换发，第" + i + "行，承包经营权证编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("权证换发，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
      sheet = ExcelReader.getSheet(wb, 13);
      list = ExcelReader.listFromSheet(sheet);
      List<CbjyqzQzzx> cbjyqzQzzxs = getCbjyqzQzzxs(list);
      i = 1;
      delTotal = 0;
      tstr = "";
      for (CbjyqzQzzx obj : cbjyqzQzzxs) {
        Cbjyqzdjb djb = cbjyqzdjbDao.getByQzbm(obj.getCbjyqzbm());
        if (djb != null) {
          obj.setOrgId(djb.getOrgId());
          int tnum = cbjyqzQzzxDao.deleteAndSave(obj);
          if (tnum > 0) {
//            sb.append("权证注销，第").append(i).append("行与前面有重复。<br/>");
            tstr += i + ",";
          }
          delTotal += tnum;
          i++;
        } else {
          return "权证注销，第" + i + "行，承包经营权证编码不存在，终止导入。";
        }
      }
      if (delTotal > 0) {
        sb.append("权证注销，第").append(tstr.substring(0, tstr.length() - 1)).append("行重复，共").append(delTotal).append("行，已删除重复项。<br/>");
      }
      
    } catch (IOException ex) {
      Logger.getLogger(ModelParser.class.getName()).log(Level.SEVERE, null, ex);
    }
    return sb.toString();
  }
  
  public String getOrgName(List<String[]> values) {
    
    if (values.size() == 0) {
      return null;
    } else {
      return values.get(0)[1].trim();
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
        if (syqsxdmb != null) {
          obj.setSyqxz(syqsxdmb.getDm());
        }
        Dklbdmb dklbdmb = dklbdmbDao.getByName(ts[3].trim());
        if (dklbdmb != null) {
          obj.setDklb(dklbdmb.getDm());
        }
        Tdlylx tdlylx = tdlylxDao.getByName(ts[4].trim());
        if (tdlylx != null) {
          obj.setTdlylx(tdlylx.getLbbm());
        }
        Dldjdmb dldjdmb = dldjdmbDao.getByName(ts[5].trim());
        if (dldjdmb != null) {
          obj.setDldj(dldjdmb.getDm());
        }
        Tdytdmb tdytdmb = tdytdmbDao.getByName(ts[6].trim());
        if (tdytdmb != null) {
          obj.setTdyt(tdytdmb.getDm());
        }
        Sfdmb sfdmb = sfdmbDao.getByName(ts[7].trim());
        if (sfdmb != null) {
          obj.setSfjbnt(sfdmb.getDm());
        }
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

  /**
   * @param orgDao the orgDao to set
   */
  @Resource
  public void setOrgDao(OrganizationDAO orgDao) {
    this.orgDao = orgDao;
  }
}
