package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.LzhhtDao;
import com.mseeworld.qzh.bean.Lzht;
import com.mseeworld.qzh.bean.Lzht1;
import com.mseeworld.qzh.model.Lzht2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/lzht")
public class LzhhtController {

  private LzhhtDao lzhtDao;

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setLzhhtDao(LzhhtDao lzhtDao) {
    this.lzhtDao = lzhtDao;
  }

  @RequestMapping(value = "/list_lzht", method = RequestMethod.GET)
  public void listAll(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);

    String porgId = request.getParameter("orgId");
    System.out.println("porgId=" + porgId);
    int iorgId = 0;
    if (porgId != null && !porgId.isEmpty()) {
      iorgId = Integer.parseInt(porgId);
    }

    int total = lzhtDao.count().intValue();
    List<Lzht2> objs = lzhtDao.getFirstNOfAll2(istart, isize, iorgId);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (Lzht2 cbf : objs) {
      try {
        String tStr = ow.writeValueAsString(cbf);
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

  @RequestMapping(value = "/add_lzht", method = RequestMethod.POST)
  public void add(@ModelAttribute Lzht1 obj, Map model, HttpServletResponse response, PrintWriter writer) {

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    try {
//      Lzhht aa = new Lzhht();
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String tStr = ow.writeValueAsString(obj);
      Lzht obj1 = new Lzht(obj);
//      System.out.println(tStr);
      lzhtDao.saveOrUpdate(obj1);

      writer.write("{success:true, msg:'流转合同保存成功!'}");
    } catch (IOException ex) {
      writer.write("{success:false, msg:'流转合同保存失败!'}");
      Logger.getLogger(DkController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @RequestMapping(value = "/update_lzht", method = RequestMethod.GET)
  public void update(HttpServletRequest request, PrintWriter writer) {

    String lzhtIds = request.getParameter("lzhtIds");
    String cbhtId = request.getParameter("cbhtId");
    System.out.println("lzhtIds=" + lzhtIds);
    System.out.println("cbhtId=" + cbhtId);

    writer.write("{success:true, msg:'流转合同保存成功!'}");
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/remove_lzht", method = RequestMethod.POST)
  public void delete(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    lzhtDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }
}
