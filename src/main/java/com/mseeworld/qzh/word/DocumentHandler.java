package com.mseeworld.qzh.word;

import com.mseeworld.qzh.dao.CbdkxxDao;
import com.mseeworld.qzh.dao.CbfDao;
import com.mseeworld.qzh.dao.CbfJtcyDao;
import com.mseeworld.qzh.dao.CbhtDao;
import com.mseeworld.qzh.dao.CbjyqqdfsdmbDao;
import com.mseeworld.qzh.dao.CbjyqzdjbDao;
import com.mseeworld.qzh.dao.CybzdmbDao;
import com.mseeworld.qzh.dao.DkDao;
import com.mseeworld.qzh.dao.FbfDao;
import com.mseeworld.qzh.dao.LzhhtDao;
import com.mseeworld.qzh.dao.QslyzlfjDao;
import com.mseeworld.qzh.dao.SfdmbDao;
import com.mseeworld.qzh.bean.Cbdkxx;
import com.mseeworld.qzh.bean.Cbf;
import com.mseeworld.qzh.bean.CbfJtcy;
import com.mseeworld.qzh.bean.Cbht;
import com.mseeworld.qzh.bean.Cbjyqqdfsdmb;
import com.mseeworld.qzh.bean.Cbjyqzdjb;
import com.mseeworld.qzh.bean.Cybzdmb;
import com.mseeworld.qzh.bean.Dk;
import com.mseeworld.qzh.bean.Fbf;
import com.mseeworld.qzh.bean.Lzht;
import com.mseeworld.qzh.bean.Sfdmb;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.util.Calendar;
import javax.annotation.Resource;

public class DocumentHandler {

  private String qzbm;
  private String storePath;
  private FbfDao fbfDao;
  private CbfDao cbfDao;
  private CbfJtcyDao cbfJtcyDao;
  private CbhtDao cbhtDao;
  private LzhhtDao lzhhtDao;
  private DkDao dkDao;
  private CbdkxxDao cbdkxxDao;
  private CbjyqzdjbDao cbjyqzdjbDao;
  private CbjyqqdfsdmbDao cbjyqqdfsdmbDao; //承包经营权取得方式
  private SfdmbDao sfdmbDao; //是否
  private CybzdmbDao cybzdmbDao; //成员备注
  private Configuration configuration = null;

  public DocumentHandler() {
    configuration = new Configuration();
    configuration.setDefaultEncoding("utf-8");
  }

  public String createDoc(String bm, String path) {
    this.qzbm = bm;
    this.storePath = path;
    // 要填入模本的数据文件
    Map<String, Object> dataMap = new HashMap<String, Object>();
    getData(dataMap);
    // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
    // 这里我们的模板是放在com.havenliu.document.template包下面
    configuration.setClassForTemplateLoading(this.getClass(), "/");
    Template t = null;
    try {
      // qztemplate.ftl为要装载的模板
      t = configuration.getTemplate("qztemplate.ftl");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Calendar cal = Calendar.getInstance();//使用日历类
    String fileName = "qzh" + cal.getTimeInMillis() + ".doc";
    System.out.println("fileName=" + fileName);
    File outFile = new File(storePath + "/" + fileName);
    Writer out = null;
    try {
      out = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(outFile), "UTF-8"));
    } catch (FileNotFoundException | UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }

    try {
      t.process(dataMap, out);
    } catch (TemplateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fileName;
  }

  /**
   * 注意dataMap里存放的数据Key值要与模板中的参数相对应
   *
   * @param dataMap
   */
  private void getData(Map<String, Object> dataMap) {

    Calendar cal = Calendar.getInstance();//使用日历类
    int year = cal.get(Calendar.YEAR);//得到年
    int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
    int day = cal.get(Calendar.DAY_OF_MONTH);//得到天

    Cbdkxx cbdkxx = cbdkxxDao.getByQzbm(qzbm);
    Cbjyqzdjb qz = cbjyqzdjbDao.getByQzbm(qzbm);
    Fbf fbf = fbfDao.getByFbfbm(cbdkxx.getFbfbm());
    Cbf cbf = cbfDao.getByCbfbm(cbdkxx.getCbfbm());
    Cbht cbht = cbhtDao.getByCbhtbm(cbdkxx.getCbhtbm());
    List<Lzht> lzhts = lzhhtDao.getByYcbhtbm(cbdkxx.getCbhtbm());
    Cbjyqqdfsdmb qzqdfs = cbjyqqdfsdmbDao.getByDm(cbht.getCbfs());
    List<CbfJtcy> cbfs = cbfJtcyDao.getCbfsByCbfBm(cbdkxx.getCbfbm());
    List<Dk> dks = dkDao.getCbDksByCbjyqzbm(qzbm);
    int dknum = dks.size();

    dataMap.put("qzhbm1", "GB0001001");
    dataMap.put("qzh_org1", "阿克苏市");
    dataMap.put("qzh_org2", "阿克苏市");
    dataMap.put("qzh_org3", "阿克苏市");
    dataMap.put("year1", year+"");
    dataMap.put("num1", "000001");
    dataMap.put("year2", year+"");
    dataMap.put("month2", month);
    dataMap.put("day2", day);
    dataMap.put("fbfmc", fbf.getFbfmc());
    dataMap.put("fbffzrxm", cbf.getCbfmc());
    dataMap.put("fzrzjhm", cbf.getCbfzjhm());
    dataMap.put("cbfs", qzqdfs.getQdfs()); //取得方式
    dataMap.put("cbfzz", cbf.getCbfdz());
    dataMap.put("postalcode", cbf.getYzbm());
    dataMap.put("phone", cbf.getLxdh());
    dataMap.put("cbhtbm", cbdkxx.getCbhtbm());
    cal.setTime(cbht.getCbqxq());
    dataMap.put("y3", cal.get(Calendar.YEAR)+"");
    dataMap.put("m3", cal.get(Calendar.MONTH) + 1);
    dataMap.put("d3", cal.get(Calendar.DAY_OF_MONTH));
    cal.setTime(cbht.getCbqxz());
    dataMap.put("y4", cal.get(Calendar.YEAR)+"");
    dataMap.put("m4", cal.get(Calendar.MONTH) + 1);
    dataMap.put("d4", cal.get(Calendar.DAY_OF_MONTH));
    dataMap.put("sum_area", cbht.getHtzmj());
    dataMap.put("lands", cbht.getCbdkzs());

    if (dknum > 3) {
      if (dknum % 3 == 2) {
        dataMap.put("dkmc3", dks.get(dknum - 2).getDkmc());
        dataMap.put("dkbm3", dks.get(dknum - 2).getDkbm());
        dataMap.put("dkmj3", dks.get(dknum - 2).getScmj());
        Sfdmb sf = sfdmbDao.getByDm(dks.get(dknum - 2).getSfjbnt());
        dataMap.put("sfnt3", sf.getSf());  //是否
        dataMap.put("dz3", dks.get(dknum - 2).getDkdz());
        dataMap.put("xz3", dks.get(dknum - 2).getDkxz());
        dataMap.put("nz3", dks.get(dknum - 2).getDknz());
        dataMap.put("bz3", dks.get(dknum - 2).getDkbz());

        dataMap.put("dkmc4", dks.get(dknum - 1).getDkmc());
        dataMap.put("dkbm4", dks.get(dknum - 1).getDkbm());
        dataMap.put("dkmj4", dks.get(dknum - 1).getScmj());
        sf = sfdmbDao.getByDm(dks.get(dknum - 1).getSfjbnt());
        dataMap.put("sfnt4", sf.getSf());  //是否
        dataMap.put("dz4", dks.get(dknum - 1).getDkdz());
        dataMap.put("xz4", dks.get(dknum - 1).getDkxz());
        dataMap.put("nz4", dks.get(dknum - 1).getDknz());
        dataMap.put("bz4", dks.get(dknum - 1).getDkbz());
      } else if (dknum % 3 == 1) {
        dataMap.put("dkmc3", dks.get(dknum - 1).getDkmc());
        dataMap.put("dkbm3", dks.get(dknum - 1).getDkbm());
        dataMap.put("dkmj3", dks.get(dknum - 1).getScmj());
        Sfdmb sf = sfdmbDao.getByDm(dks.get(dknum - 1).getSfjbnt());
        dataMap.put("sfnt3", sf.getSf());  //是否
        dataMap.put("dz3", dks.get(dknum - 1).getDkdz());
        dataMap.put("xz3", dks.get(dknum - 1).getDkxz());
        dataMap.put("nz3", dks.get(dknum - 1).getDknz());
        dataMap.put("bz3", dks.get(dknum - 1).getDkbz());
        dataMap.put("dkmc4", "");
        dataMap.put("dkbm4", "");
        dataMap.put("dkmj4", "");
        dataMap.put("sfnt4", "");
        dataMap.put("dz4", "");
        dataMap.put("xz4", "");
        dataMap.put("nz4", "");
        dataMap.put("bz4", "");
      } else {
        dataMap.put("dkmc3", "");
        dataMap.put("dkbm3", "");
        dataMap.put("dkmj3", "");
        dataMap.put("sfnt3", "");  //是否
        dataMap.put("dz3", "");
        dataMap.put("xz3", "");
        dataMap.put("nz3", "");
        dataMap.put("bz3", "");
        dataMap.put("dkmc4", "");
        dataMap.put("dkbm4", "");
        dataMap.put("dkmj4", "");
        dataMap.put("sfnt4", "");
        dataMap.put("dz4", "");
        dataMap.put("xz4", "");
        dataMap.put("nz4", "");
        dataMap.put("bz4", "");
      }
    }
    cal.setTime(cbht.getQdsj());
    dataMap.put("y5", cal.get(Calendar.YEAR)+"");
    dataMap.put("m5", cal.get(Calendar.MONTH) + 1);
    dataMap.put("d5", cal.get(Calendar.DAY_OF_MONTH));

    List<Jtcytable> _table1 = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Jtcytable t1 = new Jtcytable();
      if (i < cbfs.size()) {
        t1.setT1rl_idnt(cbfs.get(i).getCyzjhm());
        t1.setT1rl_name(cbfs.get(i).getCyxm());
        Cybzdmb cybz = cybzdmbDao.getByDm(cbfs.get(i).getCybz());
        t1.setT1rl_note(cybz.getCybz());
        t1.setT1rl_rel(cbfs.get(i).getYhzgx());
      } else {
        t1.setT1rl_idnt(" ");
        t1.setT1rl_name(" ");
        t1.setT1rl_note(" ");
        t1.setT1rl_rel(" ");
      }
      _table1.add(t1);
    }
    dataMap.put("jtcytable", _table1);

    List<Dktable1> _table2 = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Dktable1 t = new Dktable1();
      if (i < dks.size()) {
        t.setT2dkmc(dks.get(i).getDkmc());
        t.setT2dkbm(dks.get(i).getDkbm());
        t.setT2dkmj(dks.get(i).getScmj().toString());
        Sfdmb sf = sfdmbDao.getByDm(dks.get(i).getSfjbnt());
        t.setT2sfnt(sf.getSf());  //是否
        t.setT2dz(dks.get(i).getDkdz());
        t.setT2xz(dks.get(i).getDkxz());
        t.setT2nz(dks.get(i).getDknz());
        t.setT2bz(dks.get(i).getDkbz());
      } else {
        t.setT2bz("");
        t.setT2dkbm("");
        t.setT2dkmc("");
        t.setT2dkmj("");
        t.setT2dz("");
        t.setT2nz("");
        t.setT2sfnt("");
        t.setT2xz("");
      }
      _table2.add(t);
    }
    dataMap.put("dktable1", _table2);

    int dktable2num = dks.size() - 5;
    dataMap.put("dktable2num", dktable2num);
    List<Dktable2> _table3 = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      Dktable2 t = new Dktable2();
      if (i < dktable2num) {
        t.setT3dkmc(dks.get(i + 3).getDkmc());
        t.setT3dkbm(dks.get(i + 3).getDkbm());
        t.setT3dkmj(dks.get(i + 3).getScmj().toString());
        Sfdmb sf = sfdmbDao.getByDm(dks.get(i + 3).getSfjbnt());
        t.setT3sfnt(sf.getSf());  //是否
        t.setT3dz(dks.get(i + 3).getDkdz());
        t.setT3xz(dks.get(i + 3).getDkxz());
        t.setT3nz(dks.get(i + 3).getDknz());
        t.setT3bz(dks.get(i + 3).getDkbz());
      } else {
        t.setT3bz("");
        t.setT3dkbm("");
        t.setT3dkmc("");
        t.setT3dkmj("");
        t.setT3dz("");
        t.setT3nz("");
        t.setT3sfnt("");
        t.setT3xz("");
      }
      _table3.add(t);
    }
    dataMap.put("dktable2", _table3);

    List<Bgtable> _table4 = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      Bgtable t = new Bgtable();
      if (i < lzhts.size()) {
        t.setT4bgcbf(lzhts.get(i).getCbfbm());
        t.setT4bgsrf(lzhts.get(i).getSrfbm());
        qzqdfs = cbjyqqdfsdmbDao.getByDm(lzhts.get(i).getLzfs());
        t.setT4bgbgfs(qzqdfs.getQdfs());
        t.setT4bgmj(lzhts.get(i).getLzmj().toString());
        t.setT4bghtbh(lzhts.get(i).getLzhtbm());
        Dk dk = dkDao.getLzDkByLzhtbm(lzhts.get(i).getLzhtbm());
        t.setT4bgdkbm(dk.getDkbm());
        t.setT4bgbmqz("");
      } else {
        t.setT4bgbgfs("");
        t.setT4bgbmqz("");
        t.setT4bgcbf("");
        t.setT4bgdkbm("");
        t.setT4bghtbh("");
        t.setT4bgmj("");
        t.setT4bgsrf("");
      }
      _table4.add(t);
    }
    dataMap.put("bgtable", _table4);

    List<String> images = new ArrayList<>();
    images.add(getImageStr(storePath+"/images/dk1.jpg"));
    images.add(getImageStr(storePath+"/images/dk2.jpg"));
    images.add(getImageStr(storePath+"/images/dk3.jpg"));
    images.add(getImageStr(storePath+"/images/dk4.jpg"));
    images.add(getImageStr(storePath+"/images/dk5.jpg"));
    dataMap.put("images", images);
  }

  public String getImageStr(String imgFile) {
    InputStream in = null;
    byte[] data = null;
    try {
      in = new FileInputStream(imgFile);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(data);
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
   * @param cbjyqzdjbDao the cbjyqzdjbDao to set
   */
  @Resource
  public void setCbjyqzdjbDao(CbjyqzdjbDao cbjyqzdjbDao) {
    this.cbjyqzdjbDao = cbjyqzdjbDao;
  }

  /**
   * @param configuration the configuration to set
   */
  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  /**
   * @return the qzbm
   */
  public String getQzbm() {
    return qzbm;
  }

  /**
   * @param qzbm the qzbm to set
   */
  public void setQzbm(String qzbm) {
    this.qzbm = qzbm;
  }

  /**
   * @param cbjyqqdfsdmbDao the cbjyqqdfsdmbDao to set
   */
  @Resource
  public void setCbjyqqdfsdmbDao(CbjyqqdfsdmbDao cbjyqqdfsdmbDao) {
    this.cbjyqqdfsdmbDao = cbjyqqdfsdmbDao;
  }

  /**
   * @param sfdmbDao the sfdmbDao to set
   */
  @Resource
  public void setSfdmbDao(SfdmbDao sfdmbDao) {
    this.sfdmbDao = sfdmbDao;
  }

  /**
   * @param cybzdmbDao the cybzdmbDao to set
   */
  @Resource
  public void setCybzdmbDao(CybzdmbDao cybzdmbDao) {
    this.cybzdmbDao = cybzdmbDao;
  }
}
