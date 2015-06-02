package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbhtDao;
import com.mseeworld.qzh.bean.Cbht;
import com.mseeworld.qzh.bean.Cbht1;
import com.mseeworld.qzh.model.Cbht2;
import com.mseeworld.qzh.view.CbhtView;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cbht")
public class CbhtController {

  private CbhtDao cbhtDao;

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setCbhtDao(CbhtDao cbhtDao) {
    this.cbhtDao = cbhtDao;
  }

  @RequestMapping(value = "/listall_cbht", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    String porgId = request.getParameter("orgId");
    
    int iorgId = 0;
    if(porgId!=null && !porgId.isEmpty()){
      iorgId = Integer.parseInt(porgId);
    }
    
    int total = cbhtDao.count().intValue();
    List<Cbht2> cbhts = cbhtDao.getFirstNOfAll2(istart, isize, iorgId);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (Cbht2 cbht : cbhts) {
      try {
        String tStr = ow.writeValueAsString(cbht);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < cbhts.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/listall_cbht2", method = RequestMethod.GET)
  public void listAllCbht2(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    String porgId = request.getParameter("orgId");
    
    int iorgId = 0;
    if(porgId!=null && !porgId.isEmpty()){
      iorgId = Integer.parseInt(porgId);
    }
    
    int total = cbhtDao.count().intValue();
    List<CbhtView> cbhts = cbhtDao.getFirstNOfCbhtView(istart, isize, iorgId);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(cbhts.size());
    rstStr.append(",rows:[");
    int i = 0;
    for (CbhtView cbht : cbhts) {
      try {
        String tStr = ow.writeValueAsString(cbht);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (i++ < cbhts.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/add_cbht", method = RequestMethod.POST)
  public void addPeople(@ModelAttribute Cbht1 obj, HttpServletResponse response, PrintWriter writer) {

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Cbht obj1 = new Cbht();
      obj1.setCbdkzs(obj.getCbdkzs());
      obj1.setCbfbm(obj.getCbfbm());
      obj1.setCbfs(obj.getCbfs());
      obj1.setCbhtbm(obj.getCbhtbm());
      obj1.setFbfbm(obj.getFbfbm());
      obj1.setHtzmj(obj.getHtzmj());
      obj1.setId(obj.getId());
      obj1.setYcbhtbm(obj.getYcbhtbm());
      obj1.setOrgId(obj.getOrgId());
      try {
        if (!obj.getCbqxq().trim().isEmpty()) {
          obj1.setCbqxq(sdf.parse(obj.getCbqxq()));
        }
        if (!obj.getCbqxz().trim().isEmpty()) {
          obj1.setCbqxz(sdf.parse(obj.getCbqxz()));
        }
        if (!obj.getQdsj().trim().isEmpty()) {
          obj1.setQdsj(sdf.parse(obj.getQdsj()));
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      cbhtDao.saveOrUpdate(obj1);
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String tStr = ow.writeValueAsString(obj1);
//      System.out.println(tStr);

      writer.write("{success:true, msg:'承包合同信息保存成功!', data:" + tStr + "}");
    } catch (IOException ex) {
      writer.write("{success:false, msg:'承包合同信息保存失败!'}");
      Logger.getLogger(CbhtController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbht", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    cbhtDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }
}
