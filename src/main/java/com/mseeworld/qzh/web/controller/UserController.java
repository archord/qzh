package com.mseeworld.qzh.web.controller;

import com.mseeworld.qzh.model.AUser;
import com.mseeworld.qzh.service.UserService;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	///**缓存用的变量*/
  //private List<User> users;
  //private int len = 0;

  UserService userService;

  public UserService getUserService() {
    return userService;
  }

  @Resource
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * 添加用户
   */
  @RequestMapping(value = "/add_user", method = RequestMethod.POST)
  public void addUserr(HttpServletRequest request, PrintWriter writer) {
    //没用uid为空不能注入为User的对象，这里，我们自己创建吧
    AUser user = new AUser();
    user.setName(request.getParameter("name"));
    user.setPassword(request.getParameter("password"));
    user.setPhone(request.getParameter("phone"));
    user.setAddr(request.getParameter("addr"));
    user.setQq(request.getParameter("QQ"));
    user.setIp(request.getRemoteAddr());
    user.setRegDate(new Timestamp(System.currentTimeMillis()));
    try {
      if (null != userService.register(user)) {
	writer.write("{success:true, msg:'新增成功!'}");
      } else {
	writer.write("{success:false, msg:'新增失败!'}");
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

    user.setIp(request.getRemoteAddr());
    user.setRegDate(new Timestamp(System.currentTimeMillis()));

    if (userService.modify(user)) {
      writer.write("{success:true,msg:'修改成功!'}");
    } else {
      writer.write("{success:false,msg:'修改失败!'}");
    }
  }

  /**
   * 删除用户
   */
  @RequestMapping(value = "/remove_user", method = RequestMethod.POST)
  public void deleteUser(HttpServletRequest request, PrintWriter writer) {

    String[] ids = request.getParameter("ids").replaceAll("\"", "").split(",");

    if (userService.remove(ids)) {
      writer.write("{success:true,msg:'删除成功!'}");
    } else {
      writer.write("{success:false,msg:'删除失败!'}");
    }
  }

  /**
   * 分页显示所有用户信息
   */
  @RequestMapping(value = "/list_user", method = RequestMethod.GET)
  public void listUser(HttpServletRequest request, PrintWriter writer) {
    String startStr = request.getParameter("start");
    String limitStr = request.getParameter("limit");
    //if(this.len == 0){
    List<AUser> users = userService.getPersons(Integer.parseInt(startStr), Integer.parseInt(limitStr));
			//this.setLen(users.size());
    //}
    int len = users.size();
    String preStr = "{totalCount:" + userService.getCount() + ",rows:[";
    String cenStr = "";
    int i = 0;
    String douhao = ",";
    for (AUser user : users) {
      i++;
      cenStr += "{id:'" + user.getId() + "', "
	      + "name:'" + user.getName() + "',"
	      + "password:'" + user.getPassword() + "',"
	      + "sex:'" + user.getSex()+ "',"
	      + "phone:'" + user.getPhone() + "',"
	      + "QQ:'" + user.getQq() + "',"
	      + "email:'" + user.getEmail() + "',"
	      + "addr:'" + user.getAddr() + "',"
	      + "regDate:'" + user.getRegDate() + "',"
	      + "IP:'" + user.getIp() + "'"
	      + "}";
      if (i < len) {
	cenStr += douhao;
      }
    }
    String endStr = "]}";
    String resultStr = preStr + cenStr + endStr;
    writer.write(resultStr);
  }

  /**
   * 用户登陆./user/login.do
   */
  @RequestMapping("/login")
  public void userLogin(HttpServletRequest request, PrintWriter writer) throws Exception {

    //这里去找用户
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if (username != null && !username.equals("") || password != null && !password.equals("")) {
      try {
	if (username.equals("root")) {
	  AUser u = userService.login(username, password);
	  if (null != u) {
	    writer.write("{success:true}");
	  } else {
	    writer.write("{success:false}");
	  }
	} else {
	  writer.write("{success:false}");
	}
      } catch (IndexOutOfBoundsException e) {
	writer.write("{success:false}");
      }
    }
  }

}
