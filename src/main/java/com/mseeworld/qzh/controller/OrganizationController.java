package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.OrganizationDAO;
import com.mseeworld.qzh.model.AOrganization;
import com.mseeworld.qzh.model.Category;
import com.mseeworld.qzh.model.Product;
import com.mseeworld.qzh.service.OrganizationService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("org")
public class OrganizationController {

  private OrganizationService orgService;
  private OrganizationDAO orgDao;

  /**
   * @param orgDao the orgDao to set
   */
  @Resource
  public void setOrgDao(OrganizationDAO orgDao) {
    this.orgDao = orgDao;
  }

  /**
   * @param orgService the orgService to set
   */
  @Resource
  public void setOrgService(OrganizationService orgService) {
    this.orgService = orgService;
  }

  /**
   * @param orgService the orgService to set
   */
  @RequestMapping(value = "/list_org_tree", method = RequestMethod.GET)
  public void listProduct(HttpServletRequest request, PrintWriter writer) {
    writer.write(orgService.getOrgTree(0));
  }

  @RequestMapping(value = "/listall_org", method = RequestMethod.GET)
  public void listAll(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    int total = orgDao.count().intValue();
    List<AOrganization> orgs = orgDao.getFirstNOfAll(istart, isize);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (AOrganization org : orgs) {
      try {
        String tStr = ow.writeValueAsString(org);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < orgs.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  /**
   * 新增product/add_product.do
   */
  @RequestMapping(value = "/save_org", method = RequestMethod.POST)
  public void add(@ModelAttribute("a_organization") AOrganization obj, HttpServletResponse response, PrintWriter writer) throws IOException {

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String tStr = ow.writeValueAsString(obj);
    System.out.println(tStr);
    obj.setIsDeleted(false);
    orgDao.saveOrUpdate(obj);

    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

    writer.write("{success:true, msg:'组织机构信息保存成功!'}");
  }

  /**
   * 删除product/remove_product.do
   */
  @RequestMapping(value = "/remove_org", method = RequestMethod.POST)
  public void deleteProduct(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");
    orgDao.deleteOrgById(ids);
    writer.write("{success:true, msg:'删除组织成功!'}");
  }

  /**
   * 修改信息product/update_product.do
   */
  @RequestMapping(value = "/update_product", method = RequestMethod.POST)
  public void updateProduct(@RequestParam MultipartFile photos, HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String categoryId = request.getParameter("categoryId");
    String memberPrice = request.getParameter("memberPrice");
    String normaPrice = request.getParameter("normaPrice");
    String descr = request.getParameter("descr");

    Product product = new Product();
    product.setId(Integer.parseInt(id));
    if (null != name && !name.equals("")) {
      product.setName(name);
    }
    product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100) / 100.0);
    product.setNormaPrice(Math.round(Float.parseFloat(normaPrice) * 100) / 100.0);
    product.setDescr(descr);
    Category category = new Category();
    category.setId(Integer.parseInt(categoryId));
    product.setCategory(category);
    product.setPdate(new Timestamp(System.currentTimeMillis()));


    if (photos.isEmpty()) {
      //System.out.println("文件未上传");
      //product.setPhoto("default.jpg");
    } else {
      product.setPhoto(photos.getOriginalFilename());
      //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
      String realPath = request.getSession().getServletContext().getRealPath("/images/product");
      //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
      FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
    }

    /**
     * 解决乱码问题
     */
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

//    if (productService.modify(product)) {
//      writer.write("{success:true, msg:'修改成功!',photo:'" + photos.getOriginalFilename() + "'}");
//    } else {
//      writer.write("{success:false, msg:'修改失败!'}");
//    }
  }
}
