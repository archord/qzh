package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbfDao;
import com.mseeworld.qzh.dao.CbfJtcyDao;
import com.mseeworld.qzh.bean.Cbf;
import com.mseeworld.qzh.bean.CbfJtcy;
import com.mseeworld.qzh.bean.Dk;
import com.mseeworld.qzh.util.NumberFormatUtil;
import java.beans.PropertyEditorSupport;
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
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cbf")
public class CbfController {

  private CbfDao cbfDao;
  private CbfJtcyDao cbfJtcyDao;

  @Resource
  public void setCbfDao(CbfDao cbfDao) {
    this.cbfDao = cbfDao;
  }

  @RequestMapping(value = "/listall_cbf", method = RequestMethod.GET)
  public void listAll(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    int total = cbfDao.count().intValue();
    List<Cbf> cbfs = cbfDao.getFirstNOfAll(istart, isize);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (Cbf cbf : cbfs) {
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

  @RequestMapping(value = "/add_cbf", method = RequestMethod.POST)
  public void add(@ModelAttribute("cbf") Cbf obj, HttpServletResponse response, PrintWriter writer) throws IOException {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    cbfDao.saveOrUpdate(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'发包方信息保存成功!'}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_cbf", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

//		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
    String ids = request.getParameter("ids");
    cbfDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    NumberFormatUtil.registerDoubleFormat(binder);
  }

  /**
   * @param cbfJtcyDao the cbfJtcyDao to set
   */
  @Resource
  public void setCbfJtcyDao(CbfJtcyDao cbfJtcyDao) {
    this.cbfJtcyDao = cbfJtcyDao;
  }
}
