/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.util;

import java.beans.PropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;

/**
 *
 * @author xy
 */
public class NumberFormatUtil {

  public static void registerDoubleFormat(WebDataBinder binder) {
    binder.registerCustomEditor(Long.TYPE, new CustomerLongEditor());
  }

  private static class CustomerLongEditor extends PropertyEditorSupport {

    public String getAsText() {
      Long d = (Long) getValue();
      return d.toString();
    }

    public void setAsText(String str) {
      if (str == "" || str == null) {
        setValue(0l);
      } else {
        setValue(Long.parseLong(str));
      }
    }
  }
}
