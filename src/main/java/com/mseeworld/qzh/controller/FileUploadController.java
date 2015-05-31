/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.controller;

import com.mseeworld.qzh.bean.ExtJSFormResult;
import com.mseeworld.qzh.bean.FileUpload;
import com.mseeworld.qzh.service.ModelParser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author xy
 */
@Controller
@RequestMapping("/uploadData")
public class FileUploadController {

  private ModelParser modelParser;

  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody
  String create(FileUpload uploadItem, BindingResult result, HttpServletRequest request) {

    String webRoot = request.getSession().getServletContext().getRealPath("/");
    System.out.println("webRoot=" + webRoot);

    ExtJSFormResult extjsFormResult = new ExtJSFormResult();
    if (result.hasErrors()) {
      for (ObjectError error : result.getAllErrors()) {
        System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
      }
      extjsFormResult.setSuccess(false);
      return extjsFormResult.toString();
    }
    System.err.println("-------------------------------------------");
    System.err.println("Test upload: " + uploadItem.getFile().getOriginalFilename());
    System.err.println("-------------------------------------------");
    extjsFormResult.setSuccess(true);

    String absPath = moveFile(uploadItem.getFile(), webRoot);
    if (!absPath.isEmpty()) {
      System.out.println("absPath="+absPath);
      modelParser.parseData(absPath);
    }

    return extjsFormResult.toString();
  }

  private String moveFile(CommonsMultipartFile file, String webRoot) {
    try {
      String webRootDir = "";
      File userDir = new File(webRoot, "/data");
      userDir.mkdirs();
      File myFile = new File(userDir, file.getOriginalFilename());
      file.transferTo(myFile);
      return myFile.getAbsolutePath();
    } catch (IOException ex) {
      Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalStateException ex) {
      Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "";
  }

  /**
   * @param modelParser the modelParser to set
   */
  @Resource
  public void setModelParser(ModelParser modelParser) {
    this.modelParser = modelParser;
  }
}
