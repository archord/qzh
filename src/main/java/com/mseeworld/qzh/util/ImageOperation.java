/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mseeworld.qzh.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
public class ImageOperation {

    public static String getImageBinary(String _urlStr) {
        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        BufferedImage bi;

        try {
            URL url = new URL(_urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            bi = ImageIO.read(inputStream);//new BufferedImage(607, 330, BufferedImage.TYPE_INT_RGB);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getGeoServerGetMap() {

        //return sb.toString();
        return "http://localhost:8081/geoserver/cite/wms?service=WMS&version=1.1.0&request=GetMap&layers=cite:tuocun1&styles=&bbox=437533.1319999993,4535782.89,439375.4415249899,4536783.197&width=607&height=330&srs=EPSG:2351&format=image/jpeg";
    }

    public static String getGeoServerGetMap(String _host, String _layers, String _bbox, String _width, String _height) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://" + _host + ":8081/geoserver/wms?");//wms路径  
        sb.append("request=GetMap"); //geoserver的操作  
        sb.append("&service=WMS"); //服务的类型  
        sb.append("&version=1.1.0"); //版本  
        sb.append("&layers=" + _layers); //图层的名称  
        sb.append("&srs=EPSG:2351"); //图源的类型  
        sb.append("&bbox=" + _bbox); //图层的范围  
        sb.append("&width=" + _width);
        sb.append("&height=" + _height);
        sb.append("&format=image/png");//图片的格式  
        return sb.toString();
//        return "http://localhost:8081/geoserver/cite/wms?service=WMS&version=1.1.0&request=GetMap&layers=cite:tuocun1&styles=&bbox=437533.1319999993,4535782.89,439375.4415249899,4536783.197&width=607&height=330&srs=EPSG:2351&format=image/jpeg";
    }

}