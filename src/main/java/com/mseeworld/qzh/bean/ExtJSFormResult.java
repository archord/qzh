/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.bean;

/**
 *
 * @author xy
 */
public class ExtJSFormResult {

  private boolean success;
  private String msg;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String toString() {
    return "{success:" + this.success + ",msg:'"+getMsg()+"'}";
  }

  /**
   * @return the msg
   */
  public String getMsg() {
    return msg;
  }

  /**
   * @param msg the msg to set
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

}
