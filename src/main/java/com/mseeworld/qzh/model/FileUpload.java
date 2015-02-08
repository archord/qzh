/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author xy
 */
public class FileUpload {

  private CommonsMultipartFile file;

  public CommonsMultipartFile getFile() {
    return file;
  }

  public void setFile(CommonsMultipartFile file) {
    this.file = file;
  }
}
