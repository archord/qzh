/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mseeworld.qzh.util;

import java.util.Collection;
import java.util.Iterator;
 
import org.apache.commons.lang3.ArrayUtils;
 
/**
 * Debug函数库
 * @author      ZhangLiKun
 * @mail        likun_zhang@yeah.net
 * @date        2013-5-8
 */
public class Debug {
 
    /**
     * 打印函数
     * @param objects
     * @date    2013-5-8
     */
    public static final void printf(String msg ,Object ...objects) {
        if(ArrayUtils.isEmpty(objects)) {
            System.out.println(msg);
            return ;
        }
        for(int i = 0 ,len = objects.length ; i < len ; i ++) {
            Object obj = objects[i] ;
            msg = msg.replaceFirst("\\{\\}", obj == null ? "" : obj.toString()) ;
        }
        System.out.println(msg);
    }
     
    /**
     * 打印函数
     * @param obj
     * @date    2013-5-8
     */
    public static final void printf(Object obj) {
        if(obj != null){
            System.out.println(obj.toString());
        }
    }  
     
    /**
     * 打印集合
     * @param list
     * @param ts
     * @date    2013-5-8
     */
    public static final <T> void printf(Collection<T> list ,ToString<T> ts) {
        if(list == null || list.isEmpty()) return ;
        Iterator<T> iter = list.iterator() ;
        while(iter.hasNext()) {
            T t = iter.next() ;
//          if(t == null)continue ;
            if(ts == null) {
                System.out.println(t.toString());
            } else {
                String msg = ts.toString(t) ;
//              if(msg != null) {
                    System.out.println(msg);
//              }
            }
        }
    }
     
}
