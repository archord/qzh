package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.CbfDao;
import com.mseeworld.qzh.model.Cbf;
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
@RequestMapping("/dk")
public class DkController {
 
  private CbfDao cbfDao;

  /**
   * @param cbfDao the cbfDao to set
   */
  @Resource
  public void setCbfDao(CbfDao cbfDao) {
    this.cbfDao = cbfDao;
  }

  @RequestMapping(value = "/listall_dk", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {
    int n = 10;
    List<Cbf> cbfs = cbfDao.getFirstNOfAll(n);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(10);
    rstStr.append(",rows:[");
    int i = 0;
    for (Cbf cbf : cbfs) {
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
  public void addPeople(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
    String id = request.getParameter("id");
    String orgId = request.getParameter("orgId");
    
    String cbfbm = request.getParameter("cbfbm");
    String cbflx = request.getParameter("cbflx");
    String cbfmc = request.getParameter("cbfmc");
    String cbfzjlx = request.getParameter("cbfzjlx");
    String cbfzjhm = request.getParameter("cbfzjhm");
    String cbfdz = request.getParameter("cbfdz");
    String yzbm = request.getParameter("yzbm");
    String lxdh = request.getParameter("lxdh");
    String cbfcysl = request.getParameter("cbfcysl");
    String cbfdcrq = request.getParameter("cbfdcrq");
    String cbfdcy = request.getParameter("cbfdcy");
    String cbfdcjs = request.getParameter("cbfdcjs");
    String gsjs = request.getParameter("gsjs");
    String gsjsr = request.getParameter("gsjsr");
    String gsshrq = request.getParameter("gsshrq");
    String gsshr = request.getParameter("gsshr");

    String isAdd = request.getParameter("isAdd");

    Cbf cbf = new Cbf();
    if (!id.equals("")) {
      cbf.setId(Long.parseLong(id));
    }
    cbf.setOrgId(Long.parseLong(orgId));
    cbf.setCbfbm(cbfbm);
    cbf.setCbflx('1');
    cbf.setCbfmc(cbfmc);
    cbf.setCbfzjlx('1');
    cbf.setCbfzjhm(cbfzjhm);
    cbf.setCbfdz(cbfdz);
    cbf.setYzbm(yzbm);
    cbf.setLxdh(lxdh);
    if(!cbfcysl.isEmpty())
    cbf.setCbfcysl(Integer.parseInt(cbfcysl));
    cbf.setCbfdcrq(new Date());
    cbf.setCbfdcy(cbfdcy);
    cbf.setCbfdcjs(cbfdcjs);
    cbf.setGsjs(gsjs);
    cbf.setGsjsr(gsjsr);
    cbf.setGsshrq(new Date());
    cbf.setGsshr(gsshr);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(cbf);
    System.out.println(tStr);
    cbfDao.saveOrUpdate(cbf);
    
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'发包方信息保存成功!'}");
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
