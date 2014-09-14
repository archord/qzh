package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.FbfDao;
import com.mseeworld.qzh.model.Fbf;
import com.mseeworld.qzh.service.PeopleService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/people")
public class PeopleController {

  private PeopleService peopleService;
  private FbfDao fbfDao;

  /**
   * @param fbfDao the fbfDao to set
   */
  @Resource
  public void setFbfDao(FbfDao fbfDao) {
    this.fbfDao = fbfDao;
  }

  @Resource
  public void setPeopleService(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  /**
   * 分页查询商品信息./people/list_people.do
   */
  @RequestMapping(value = "/listall_people", method = RequestMethod.GET)
  public void listAllPeople(HttpServletRequest request, PrintWriter writer) {
    int n = 10;
    List<Fbf> fbfs = fbfDao.getFirstNOfAll(n);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(10);
    rstStr.append(",rows:[");
    int i = 0;
    for (Fbf fbf : fbfs) {
      try {
        String tStr = ow.writeValueAsString(fbf);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (i++ < fbfs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  /**
   * 分页查询商品信息./people/list_people.do
   */
  @RequestMapping(value = "/list_people", method = RequestMethod.GET)
  public void listPeople(HttpServletRequest request, PrintWriter writer) {
//		String startStr = request.getParameter("start");
//		String limitStr = request.getParameter("limit");
//		
//		List<People> peoples = peopleService.findAll(Integer.parseInt(startStr), Integer.parseInt(limitStr));
//		int len = peoples.size();
//		String preStr = "{totalCount:" + peopleService.count() + ",rows:[";
//		String cenStr = "";
//		int i=0;
//		String douhao = ",";
//		for(People people : peoples) {
//			i++;
//			cenStr += "{id:'"+people.getId()+"', " +
//					"name:'"+people.getName()+"'," +
//					"descr:'"+people.getDescr()+"'," +
//					"normaPrice:'"+people.getNormaPrice()+"'," +
//					"memberPrice:'"+people.getMemberPrice()+"'," +
//					"pdate:'"+people.getPdate()+"'," +
//					"photo:'"+people.getPhoto()+"'," +
//					"category:'"+people.getCategory().getText()+"'" +
//			"}";
//			if(i < len){
//				cenStr += douhao;
//			}
//		}
//		String endStr = "]}";
//		String resultStr = preStr + cenStr + endStr;
//		writer.write(resultStr);
  }

  /**
   * 新增产品people/add_people.do
   */
  @RequestMapping(value = "/add_people", method = RequestMethod.POST)
  public void addPeople(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
    String id = request.getParameter("id");
    String orgId = request.getParameter("orgId");
    String fbfbm = request.getParameter("fbfbm");
    String fbfmc = request.getParameter("fbfmc");
    String fbffzrxm = request.getParameter("fbffzrxm");
    String fzrzjlx = request.getParameter("fzrzjlx");
    String fzrzjhm = request.getParameter("fzrzjhm");
    String lxdh = request.getParameter("lxdh");
    String fbfdz = request.getParameter("fbfdz");
    String yzbm = request.getParameter("yzbm");
    String fbfdcy = request.getParameter("fbfdcy");
    String fbfdcrq = request.getParameter("fbfdcrq");
    String fbfdcjs = request.getParameter("fbfdcjs");

    String isAdd = request.getParameter("isAdd");
//    if (isAdd.equals("1")) {
    Fbf fbf = new Fbf();
    if (!id.equals("")) {
      fbf.setId(Long.parseLong(id));
    }
    fbf.setOrgId(Long.parseLong(orgId));
    fbf.setFbfbm(fbfbm);
    fbf.setFbfmc(fbfmc);
    fbf.setFbffzrxm(fbffzrxm);
    fbf.setFzrzjlx('1');
    fbf.setFzrzjhm(fzrzjhm);
    fbf.setLxdh(lxdh);
    fbf.setFbfdz(fbfdz);
    fbf.setYzbm(yzbm);
    fbf.setFbfdcy(fbfdcy);
    fbf.setFbfdcrq(new Date());
    fbf.setFbfdcjs(fbfdcjs);

//    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//    String tStr = ow.writeValueAsString(fbf);
//    System.out.println(tStr);
    fbfDao.saveOrUpdate(fbf);
//    }
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'发包方信息保存成功!'}");
  }

  /**
   * 删除商品people/remove_people.do
   */
  @RequestMapping(value = "/remove_people", method = RequestMethod.POST)
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

  /**
   * 修改商品信息people/update_people.do
   */
  @RequestMapping(value = "/update_people", method = RequestMethod.POST)
  public void updatePeople(@RequestParam MultipartFile photos, HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
//		String id = request.getParameter("id");
//		String name = request.getParameter("name");
//		String categoryId = request.getParameter("categoryId");
//		String memberPrice = request.getParameter("memberPrice");
//		String normaPrice = request.getParameter("normaPrice");
//		String descr = request.getParameter("descr");
//
//		People people = new People();
//		people.setId(Integer.parseInt(id));
//		if(null != name && !name.equals("")){
//			people.setName(name);
//		}
//		people.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100)/100.0);
//		people.setNormaPrice(Math.round(Float.parseFloat(normaPrice) * 100)/100.0);
//		people.setDescr(descr);
//		Category category = new Category();
//		category.setId(Integer.parseInt(categoryId));
//		people.setCategory(category);
//		people.setPdate(new Timestamp(System.currentTimeMillis()));
//		
//		
//		if(photos.isEmpty()){
//			//System.out.println("文件未上传");
//			//people.setPhoto("default.jpg");
//		}else{
//			people.setPhoto(photos.getOriginalFilename());
//			//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
//			String realPath = request.getSession().getServletContext().getRealPath("/images/people");
//			//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
//			FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
//		}
//		
//		/**解决乱码问题*/
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;  charset=UTF-8");
//		
//		if(peopleService.modify(people)){
//			writer.write("{success:true, msg:'商品修改成功!',photo:'"+photos.getOriginalFilename()+"'}");
//		}else{
//			writer.write("{success:false, msg:'商品修改失败!'}");
//		}
  }
}
