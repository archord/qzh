package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.DkDao;
import com.mseeworld.qzh.model.Dk;
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
@RequestMapping("/dk")
public class DkController {

  private DkDao dkDao;

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setDkDao(DkDao dkDao) {
    this.dkDao = dkDao;
  }

  @RequestMapping(value = "/listall_dk", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {
    int n = 10;
    List<Dk> cbfs = dkDao.getFirstNOfAll(n);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(10);
    rstStr.append(",rows:[");
    int i = 0;
    for (Dk cbf : cbfs) {
      try {
        String tStr = ow.writeValueAsString(cbf);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (i++ < cbfs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  @RequestMapping(value = "/add_dk", method = RequestMethod.POST)
  public void addPeople(@ModelAttribute("dk") Dk obj, Map model, HttpServletResponse response, PrintWriter writer) {

      //    String id = request.getParameter("id");
//    String orgId = request.getParameter("orgId");
//
//    String dkbm = request.getParameter("dkbm");
//    String dkmc = request.getParameter("dkmc");
//    String syqxz = request.getParameter("syqxz");
//    String dklb = request.getParameter("dklb");
//    String tdlylx = request.getParameter("tdlylx");
//    String dldj = request.getParameter("dldj");
//    String tdyt = request.getParameter("tdyt");
//    String sfjbnt = request.getParameter("sfjbnt");
//    String scmj = request.getParameter("scmj");
//    String dkdz = request.getParameter("dkdz");
//    String dkxz = request.getParameter("dkxz");
//    String dknz = request.getParameter("dknz");
//    String dkbz = request.getParameter("dkbz");
//    String dkbzxx = request.getParameter("dkbzxx");
//    String zjrxm = request.getParameter("zjrxm");
//
//    String isAdd = request.getParameter("isAdd");
//
//    Dk obj = (Dk)command;
//    if (!id.equals("")) {
//      obj.setId(Long.parseLong(id));
//    }
//    System.out.println("dklb="+dklb);
//    System.out.println("dkbm="+dk.getDkbm());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");
    
    try {
//      Dk aa = new Dk();
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      String tStr = ow.writeValueAsString(obj);
//      System.out.println(tStr);
      dkDao.saveOrUpdate(obj);

      writer.write("{success:true, msg:'地块信息保存成功!'}");
    } catch (IOException ex) {
      writer.write("{success:false, msg:'地块信息保存失败!'}");
      Logger.getLogger(DkController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * 删除商品people/remove_people.do
   */
  @RequestMapping(value = "/remove_dk", method = RequestMethod.POST)
  public void deletePeople(HttpServletRequest request, PrintWriter writer) {
//		
//		String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");
//		
//		if(peopleService.remove(ids)){
//			writer.write("{success:true,msg:'删除成功!'}");
//		}else{
//			writer.write("{success:false,msg:'删除失败!'}");
//		}
  }

}
