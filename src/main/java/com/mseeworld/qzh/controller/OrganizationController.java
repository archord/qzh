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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("organization")
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

  /**
   * 新增product/add_product.do
   */
  @RequestMapping(value = "/addOrg", method = RequestMethod.POST)
  public void addProduct(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {

    String orgId = request.getParameter("orgId");
    String orgLevel = request.getParameter("orgLevel");
    String orgCoding = request.getParameter("orgCoding");
    String orgName = request.getParameter("orgName");
    String orgMember = request.getParameter("orgMember");
    String fbfName = request.getParameter("fbfName");
    String fbfLegalPerson = request.getParameter("fbfLegalPerson");
    String fbfPhone = request.getParameter("fbfPhone");
    String fbfAddress = request.getParameter("fbfAddress");
    String authOrgName = request.getParameter("authOrgName");
    String authPeople = request.getParameter("authPeople");
    String authPhone = request.getParameter("authPhone");
    String parentId = request.getParameter("parentId");

    AOrganization org = new AOrganization();
    org.setOrgLevel(Short.parseShort(orgLevel));
    org.setOrgCoding(orgCoding);
    org.setOrgName(orgName);
    org.setOrgMember(orgMember);
    org.setFbfName(fbfName);
    org.setFbfLegalPerson(fbfLegalPerson);
    org.setFbfAddress(fbfAddress);
    org.setFbfPhone(fbfPhone);
    org.setAuthOrgName(authOrgName);
    org.setAuthPeople(authPeople);
    org.setAuthPhone(authPhone);
    org.setParentId(Long.parseLong(parentId));
    org.setIsDeleted(false);

    orgDao.saveOrUpdate(org);

//		Product product = new Product();
//		product.setName(name);
//		product.setMemberPrice(Math.round(Float.parseFloat(memberPrice) * 100)/100.0);
//		product.setNormaPrice(Math.round(Float.parseFloat(normaPrice) * 100)/100.0);
//		product.setDescr(descr);
//		Category category = new Category();
//		category.setId(Integer.parseInt(categoryId));
//		product.setCategory(category);
//		product.setPdate(new Timestamp(System.currentTimeMillis()));
//		
//		
//		if(photos.isEmpty()){
//			System.out.println("文件未上传");
//			product.setPhoto("default.jpg");
//		}else{
//			product.setPhoto(photos.getOriginalFilename());
//			//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
//			String realPath = request.getSession().getServletContext().getRealPath("/images/product");
//			//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
//			FileUtils.copyInputStreamToFile(photos.getInputStream(), new File(realPath, photos.getOriginalFilename()));
//		}

    /**
     * 解决乱码问题
     */
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;  charset=UTF-8");

//		if(null != productService.add(product)){
//			writer.write("{success:true, msg:'新增成功!'}");
//		}else{
//			writer.write("{success:false, msg:'新增失败!'}");
//		}
    writer.write("{success:true, msg:'新增成功!'}");
  }

  /**
   * 删除product/remove_product.do
   */
  @RequestMapping(value = "/remove_org", method = RequestMethod.POST)
  public void deleteProduct(HttpServletRequest request, PrintWriter writer) {

    String id = request.getParameter("id");
    orgDao.deleteOrgById(Long.parseLong(id));
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
