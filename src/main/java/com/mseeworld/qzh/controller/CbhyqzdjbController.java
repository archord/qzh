package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbhtDao;
import com.mseeworld.qzh.dao.CbjyqzdjbDao;
import com.mseeworld.qzh.bean.Cbht;
import com.mseeworld.qzh.bean.Cbjyqzdjb;
import java.io.IOException;
import java.io.PrintWriter;
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
@RequestMapping("/cbjyqzdjb")
public class CbhyqzdjbController {

  private CbjyqzdjbDao cbjyqzdjbDao;
  private CbhtDao cbhtDao;

  @Resource
  public void setCbjyqzdjbDao(CbjyqzdjbDao cbjyqzdjbDao) {
    this.cbjyqzdjbDao = cbjyqzdjbDao;
  }
  
  @Resource
  public void setCbhtDao(CbhtDao cbhtDao) {
    this.cbhtDao = cbhtDao;
  }

  @RequestMapping(value = "/listall_cbjyqzdjb", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    String porgId = request.getParameter("orgId");
    System.out.println("porgId="+porgId);
    int iorgId = 0;
    if(porgId!=null && !porgId.isEmpty()){
      iorgId = Integer.parseInt(porgId);
    }
    
    int total = cbjyqzdjbDao.count().intValue();
    List<Cbjyqzdjb> cbjyqzdjbs = cbjyqzdjbDao.getFirstNOfAll2(istart, isize, iorgId);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (Cbjyqzdjb cbjyqzdjb : cbjyqzdjbs) {
      try {
        String tStr = ow.writeValueAsString(cbjyqzdjb);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < cbjyqzdjbs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/add_cbjyqzdjb", method = RequestMethod.GET)
  public void addPeople(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) {

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");
    
    String cbhtids = request.getParameter("cbhtIds");
    String cbhtid[] = cbhtids.split(",");
    for(String id : cbhtid){
      Cbht cbht = cbhtDao.getCbhtById(Long.parseLong(id));
      Cbjyqzdjb obj = new Cbjyqzdjb();
      obj.setCbfbm(cbht.getCbfbm());
      obj.setCbfs(cbht.getCbfs());
      obj.setCbjyqzbm(cbht.getCbhtbm()); // todo
      obj.setCbqx("1"); //todo
      obj.setCbqxq(cbht.getCbqxq());
      obj.setCbqxz(cbht.getCbqxz());
      obj.setDksyt(""); //todo
      obj.setFbfbm(cbht.getFbfbm());
      cbjyqzdjbDao.save(obj);
    }

//    try {
//      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//      Cbjyqzdjb obj1 = new Cbjyqzdjb();
//
//      cbjyqzdjbDao.saveOrUpdate(obj1);
//      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//      String tStr = ow.writeValueAsString(obj1);
//
//      writer.write("{success:true, msg:'承包合同信息保存成功!', data:" + tStr + "}");
//    } catch (IOException ex) {
//      writer.write("{success:false, msg:'承包合同信息保存失败!'}");
//      Logger.getLogger(CbhyqzdjbController.class.getName()).log(Level.SEVERE, null, ex);
//    }
      writer.write("{success:true, msg:'承包合同信息保存成功!', data:" + "" + "}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbjyqzdjb", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    cbjyqzdjbDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }

}
