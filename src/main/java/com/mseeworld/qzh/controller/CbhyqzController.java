package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbhtDao;
import com.mseeworld.qzh.dao.CbjyqzDao;
import com.mseeworld.qzh.model.Cbht;
import com.mseeworld.qzh.model.Cbjyqz;
import com.mseeworld.qzh.util.NumberFormatUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cbjyqz")
public class CbhyqzController {

  private CbjyqzDao cbjyqzDao;
  private CbhtDao cbhtDao;

  @Resource
  public void setCbjyqzDao(CbjyqzDao cbjyqzDao) {
    this.cbjyqzDao = cbjyqzDao;
  }
  
  @Resource
  public void setCbhtDao(CbhtDao cbhtDao) {
    this.cbhtDao = cbhtDao;
  }

  @RequestMapping(value = "/listall_cbjyqz", method = RequestMethod.GET)
  public void listAll(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    int total = cbjyqzDao.count().intValue();
    List<Cbjyqz> objs = cbjyqzDao.getFirstNOfAll(istart, isize);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (Cbjyqz cbjyqz : objs) {
      try {
        String tStr = ow.writeValueAsString(cbjyqz);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < objs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/add_cbjyqz", method = RequestMethod.POST)
  public void add(@ModelAttribute("cbjyqz") Cbjyqz obj,HttpServletResponse response, PrintWriter writer) throws IOException  {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    cbjyqzDao.saveOrUpdate(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'承包合同信息保存成功!'}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbjyqz", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    cbjyqzDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }


  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    NumberFormatUtil.registerDoubleFormat(binder);
  }
}
