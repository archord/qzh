/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.model;

/**
 *
 * @author xy
 */
public class ExtJSFormResult {

  private boolean success;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String toString() {
    return "{success:" + this.success + "}";
  }
}
