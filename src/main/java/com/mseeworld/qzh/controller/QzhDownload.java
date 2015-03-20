/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.word.DocumentHandler;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author xy
 */
@Controller
@RequestMapping("/download")
public class QzhDownload {
  
  private DocumentHandler documentHandler;

  /**
   * 下载
   */
  @RequestMapping(value = "/qz", method = RequestMethod.GET)
  public String download(HttpServletRequest request, PrintWriter writer) {

    String qzbm = request.getParameter("qzbm");
    String storePath = request.getSession().getServletContext().getRealPath("/tmp");
    System.out.println("webRoot=" + storePath);
    System.out.println("qzbm=" + qzbm);
    String fileName = documentHandler.createDoc(qzbm, storePath);
    return "redirect:/tmp/" + fileName;
  }

  /**
   * @param documentHandler the documentHandler to set
   */
  @Resource
  public void setDocumentHandler(DocumentHandler documentHandler) {
    this.documentHandler = documentHandler;
  }
}
