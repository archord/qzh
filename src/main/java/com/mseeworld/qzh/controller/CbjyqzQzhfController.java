package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbjyqzQzhfDao;
import com.mseeworld.qzh.model.CbjyqzQzhf;
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
@RequestMapping("/cbjyqzQzhf")
public class CbjyqzQzhfController {

  private CbjyqzQzhfDao cbjyqzQzhfDao;

  @Resource
  public void setCbjyqzQzhfDao(CbjyqzQzhfDao cbjyqzQzhfDao) {
    this.cbjyqzQzhfDao = cbjyqzQzhfDao;
  }

  @RequestMapping(value = "/listall_cbjyqzQzhf", method = RequestMethod.GET)
  public void listAll(HttpServletRequest request, PrintWriter writer) {
    int n = 10;
    List<CbjyqzQzhf> objs = cbjyqzQzhfDao.getFirstNOfAll(n);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(objs.size());
    rstStr.append(",rows:[");
    int i = 0;
    for (CbjyqzQzhf cbjyqzQzhf : objs) {
      try {
        String tStr = ow.writeValueAsString(cbjyqzQzhf);
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

  @RequestMapping(value = "/add_cbjyqzQzhf", method = RequestMethod.POST)
  public void add(@ModelAttribute("cbjyqzQzhf") CbjyqzQzhf obj,HttpServletResponse response, PrintWriter writer) throws IOException  {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    cbjyqzQzhfDao.saveOrUpdate(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'承包合同信息保存成功!'}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbjyqzQzhf", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    cbjyqzQzhfDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }


  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    NumberFormatUtil.registerDoubleFormat(binder);
  }
}
