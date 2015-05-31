package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbfJtcyDao;
import com.mseeworld.qzh.bean.CbfJtcy;
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
@RequestMapping("/cbfjtcy")
public class CbfJtcyController {

  private CbfJtcyDao cbfJtcyDao;

  @Resource
  public void setCbfJtcyDao(CbfJtcyDao cbfJtcyDao) {
    this.cbfJtcyDao = cbfJtcyDao;
  }

  @RequestMapping(value = "/listall_cbfjtcy", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {

    String cbfbm = request.getParameter("cbfbm");

    int n = 10;
    List<CbfJtcy> cbfs = cbfJtcyDao.getCbfsByCbfBm(cbfbm);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(cbfs.size());
    rstStr.append(",rows:[");
    int i = 0;
    for (CbfJtcy cbf : cbfs) {
      try {
        String tStr = ow.writeValueAsString(cbf);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < cbfs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/add_cbfJtcy", method = RequestMethod.POST)
  public void add(@ModelAttribute("cbfJtcy") CbfJtcy obj, HttpServletResponse response, PrintWriter writer) throws IOException {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    cbfJtcyDao.save(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'发包方信息保存成功!'}");
  }

  @RequestMapping(value = "/update_cbfJtcy", method = RequestMethod.POST)
  public void update(@ModelAttribute("cbfJtcy") CbfJtcy obj, HttpServletResponse response, PrintWriter writer) throws IOException {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    cbfJtcyDao.update(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'发包方信息保存成功!'}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbfjtcy", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    cbfJtcyDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    NumberFormatUtil.registerDoubleFormat(binder);
  }
}
