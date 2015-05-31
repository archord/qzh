package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.dao.UserDAO;
import com.mseeworld.qzh.bean.AUser;
import com.mseeworld.qzh.model.AUser2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

  private UserDAO userDao;

  @Resource
  public void setUserDao(UserDAO userDao) {
    this.userDao = userDao;
  }

  /**
   * 添加用户
   */
  @RequestMapping(value = "/save_user", method = RequestMethod.POST)
  public void addUserr(HttpServletRequest request, PrintWriter writer) {
    //没用uid为空不能注入为User的对象，这里，我们自己创建吧
    AUser user = new AUser();
    user.setName(request.getParameter("name").trim());
    user.setPassword(request.getParameter("password").trim());
    user.setOrgId(Integer.parseInt(request.getParameter("orgId")));
    String sid = request.getParameter("id");
    String isAdd = request.getParameter("isAdd");
    if (sid != null) {
      sid = sid.trim();
      if (!sid.isEmpty()) {
        user.setId(Integer.parseInt(sid));
      }
    }
//    user.setRegDate(new Timestamp(System.currentTimeMillis()));

    try {
      if (isAdd.equals("1")) {
        if (!userDao.exist(user)) {
          userDao.save(user);
          writer.write("{success:true, msg:'新增成功!'}");
        } else {
          writer.write("{success:false, msg:'用户名已存在!'}");
        }
      } else {
        userDao.update(user);
      }
    } catch (Exception e) {
      writer.write("{success:false, msg:'保存失败!'}");
    }
  }

  /**
   * 修改用户
   */
  @RequestMapping(value = "/update_user", method = RequestMethod.POST)
  public void updateUser(HttpServletRequest request, AUser user, PrintWriter writer) {

//    user.setIp(request.getRemoteAddr());
//    user.setRegDate(new Timestamp(System.currentTimeMillis()));
//
//    if (userService.modify(user)) {
//      writer.write("{success:true,msg:'修改成功!'}");
//    } else {
//      writer.write("{success:false,msg:'修改失败!'}");
//    }
  }

  /**
   * 删除用户
   */
  @RequestMapping(value = "/remove_user", method = RequestMethod.POST)
  public void deleteUser(HttpServletRequest request, PrintWriter writer) {

    String ids = request.getParameter("ids");

    userDao.deleteByIds(ids);
    writer.write("{success:true,msg:'删除成功!'}");
  }

  /**
   * 分页显示所有用户信息
   */
  @RequestMapping(value = "/listall_user", method = RequestMethod.GET)
  public void listUser(HttpServletRequest request, PrintWriter writer) {

    String page = request.getParameter("page");
    String start = request.getParameter("start");
    String psize = request.getParameter("limit");
    int ipage = Integer.parseInt(page);
    int istart = Integer.parseInt(start);
    int isize = Integer.parseInt(psize);
    
    String porgId = request.getParameter("orgId");
    System.out.println("porgId="+porgId);
    int iorgId = 0;
    if(porgId!=null && !porgId.isEmpty()){
      iorgId = Integer.parseInt(porgId);
    }

    int total = userDao.count().intValue();
    List<AUser2> users = userDao.getFirstNOfAll2(istart, isize,iorgId);

    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    StringBuilder rstStr = new StringBuilder("");
    rstStr.append("{totalCount:");
    rstStr.append(total);
    rstStr.append(",rows:[");
    int i = 0;
    for (AUser2 user : users) {
      try {
        String tStr = ow.writeValueAsString(user);
        rstStr.append(tStr);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      if (++i < users.size()) {
        rstStr.append(",");
      }
    }
    rstStr.append("]}");
    writer.write(rstStr.toString());
  }

  /**
   * 用户登陆./user/login.do
   */
  @RequestMapping("/login")
  public void userLogin(HttpServletRequest request, PrintWriter writer) throws Exception {

    //这里去找用户
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && !username.trim().equals("") || password != null && !password.trim().equals("")) {
      try {
        AUser user = new AUser();
        user.setName(username);
        user.setPassword(password);

        if (userDao.checkUser(user)) {
          writer.write("{success:true}");
        } else {
          writer.write("{success:false}");
        }
      } catch (IndexOutOfBoundsException e) {
        writer.write("{success:false}");
      }
    }
  }

}
