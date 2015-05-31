package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbflxdmbDao;
import com.mseeworld.qzh.dao.CbjyqqdfsdmbDao;
import com.mseeworld.qzh.dao.CybzdmbDao;
import com.mseeworld.qzh.dao.DklbdmbDao;
import com.mseeworld.qzh.dao.DldjdmbDao;
import com.mseeworld.qzh.dao.SfdmbDao;
import com.mseeworld.qzh.dao.SyqsxDao;
import com.mseeworld.qzh.dao.TdlylxDao;
import com.mseeworld.qzh.dao.TdytdmbDao;
import com.mseeworld.qzh.dao.XbdmbDao;
import com.mseeworld.qzh.dao.ZjlxdmbDao;
import com.mseeworld.qzh.bean.Cbflxdmb;
import com.mseeworld.qzh.bean.Cbjyqqdfsdmb;
import com.mseeworld.qzh.bean.Cybzdmb;
import com.mseeworld.qzh.bean.Dklbdmb;
import com.mseeworld.qzh.bean.Dldjdmb;
import com.mseeworld.qzh.bean.Sfdmb;
import com.mseeworld.qzh.bean.Syqsxdmb;
import com.mseeworld.qzh.bean.Tdlylx;
import com.mseeworld.qzh.bean.Tdytdmb;
import com.mseeworld.qzh.bean.Xbdmb;
import com.mseeworld.qzh.bean.Zjlxdmb;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/combobox")
public class ComboboxController {

  private SyqsxDao syqsxDao;
  private CbjyqqdfsdmbDao cbjyqqdfsdmbDao;
  private CbflxdmbDao cbflxdmbDao;
  private CybzdmbDao cybzdmbDao;
  private DklbdmbDao dklbdmbDao;
  private DldjdmbDao dldjdmbDao;
  private SfdmbDao sfdmbDao;
  private TdytdmbDao tdytdmbDao;
  private XbdmbDao xbdmbDao;
  private ZjlxdmbDao zjlxdmbDao;
  private TdlylxDao tdlylxDao;

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setSyqsxDao(SyqsxDao syqsxDao) {
    this.syqsxDao = syqsxDao;
  }

  @RequestMapping(value = "/get_all_tdlylx", method = RequestMethod.GET)
  public void listAllTdlylx(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Tdlylx> objs = tdlylxDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Tdlylx obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_syqsx", method = RequestMethod.GET)
  public void listAllSyqsx(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Syqsxdmb> objs = syqsxDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Syqsxdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_cbflx", method = RequestMethod.GET)
  public void listAllcbflx(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Cbflxdmb> objs = cbflxdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Cbflxdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_cbjyqqdfs", method = RequestMethod.GET)
  public void listAllCbjyqqdfs(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Cbjyqqdfsdmb> objs = cbjyqqdfsdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Cbjyqqdfsdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_cybz", method = RequestMethod.GET)
  public void listAllCybz(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Cybzdmb> objs = cybzdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Cybzdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_dklb", method = RequestMethod.GET)
  public void listAllDklb(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Dklbdmb> objs = dklbdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Dklbdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_dldj", method = RequestMethod.GET)
  public void listAllDldj(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Dldjdmb> objs = dldjdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Dldjdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_sfdmb", method = RequestMethod.GET)
  public void listAllSf(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Sfdmb> objs = sfdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Sfdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_tdyt", method = RequestMethod.GET)
  public void listAllTdyt(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Tdytdmb> objs = tdytdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Tdytdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_xb", method = RequestMethod.GET)
  public void listAllXb(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Xbdmb> objs = xbdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Xbdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/get_all_zjlx", method = RequestMethod.GET)
  public void listAllZjlx(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {
    int n = 10;


    List<Zjlxdmb> objs = zjlxdmbDao.getAll();
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("[");
    int i = 0;
    for (Zjlxdmb obj : objs) {
      try {
        String tStr = ow.writeValueAsString(obj);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset=utf-8");
    writer.write(rstStr.toString());
  }

  /**
   * @param cbjyqqdfsdmbDao the cbjyqqdfsdmbDao to set
   */
  @Resource
  public void setCbjyqqdfsdmbDao(CbjyqqdfsdmbDao cbjyqqdfsdmbDao) {
    this.cbjyqqdfsdmbDao = cbjyqqdfsdmbDao;
  }

  /**
   * @param cbflxdmbDao the cbflxdmbDao to set
   */
  @Resource
  public void setCbflxdmbDao(CbflxdmbDao cbflxdmbDao) {
    this.cbflxdmbDao = cbflxdmbDao;
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
   * @param sfdmbDao the sfdmbDao to set
   */
  @Resource
  public void setSfdmbDao(SfdmbDao sfdmbDao) {
    this.sfdmbDao = sfdmbDao;
  }

  /**
   * @param tdytdmbDao the tdytdmbDao to set
   */
  @Resource
  public void setTdytdmbDao(TdytdmbDao tdytdmbDao) {
    this.tdytdmbDao = tdytdmbDao;
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
   * @param tdlylxDao the tdlylxDao to set
   */
  @Resource
  public void setTdlylxDao(TdlylxDao tdlylxDao) {
    this.tdlylxDao = tdlylxDao;
  }

}
