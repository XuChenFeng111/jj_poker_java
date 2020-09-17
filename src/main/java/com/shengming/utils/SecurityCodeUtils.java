package com.shengming.utils;

import java.io.*;
import java.net.*;
import java.security.Principal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author XuChenFeng
 * @Date 2020/8/14 9:15
 */

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SecurityCodeUtils {

    /*
     * webservice服务器定义
     */

    private String serviceURL = "http://yd10086.cn:8070/Service.asmx";

    private String corporateid = "201036";// 序列号
    private String userid = "201036";
    private String password = "IYSr2027";


//    private String securityCode = null;
  private String securityCode = "123456";

//    private String phoneNew = null;
  private String phoneNew = "13895777103";


    /**
     * 获取count个随机数
     *
     * @param count 随机数个数
     * @return
     */
    public String game(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num) + ""), "");
        }
        //session.put("securityCode", sb.toString());
        return sb.toString();
    }

    /**
     * 注册帐号短信验证码发送至手机
     *
     * @param phone
     * @param
     * @return
     */
    public String SendSecurityCode(String phone) {
        phoneNew = phone;
        securityCode = this.game(6);//取得随机数作为验证码
//        request.getSession().setAttribute("securityCode", securityCode);//验证码保存为session
        String content = "【叮咚mall】尊敬的客户，您好！您此次的验证码为：" + securityCode + "，请勿泄漏！";
//        String content = "【mall】验证码为：" + securityCode;
        String result = SendSmsNew(phone, content);
//        return result;
        return securityCode;
    }

    /**
     * 判断输入的验证码是否正确
     *
     * @return 返回值isok 0:成功;1:失败
     */
    public String checkSecurityCode(String code, String phoNo) {

        String isok = "1";
        try {
            if (securityCode != null) {
//                String securityCode=request.getSession().getAttribute("securityCode").toString();
                    if (securityCode.equals(code) && phoneNew.equals(phoNo)) {
                    isok = "0";
                }
            }

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        //isok="0";
        return isok;
    }

    /**
     * 方法名称：SendSmsNew
     * 功    能：发送短信
     * 返 回 值：唯一标识 rrid将返回系统生成的
     */
    public String SendSmsNew(String mobile, String content) {
        //String content="【有品优品】"+contentb+securityCode+contente;
        String subcode = "";
        String schtime = "";
        String result = "";
        String soapAction = "http://tempuri.org/SendSmsNew";
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        xml += "<soap:Body>";
        xml += "<SendSmsNew xmlns=\"http://tempuri.org/\">";
        xml += "<corporateid>" + corporateid + "</corporateid>";
        xml += "<userid>" + userid + "</userid>";
        xml += "<password>" + password + "</password>";
        xml += "<mobile>" + mobile + "</mobile>";
        xml += "<content>" + content + "</content>";
        xml += "<subcode>" + subcode + "</subcode>";
        xml += "<schtime>" + schtime + "</schtime>";
        xml += "</SendSmsNew>";
        xml += "</soap:Body>";
        xml += "</soap:Envelope>";

        URL url;
        try {
            url = new URL(serviceURL);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpconn = (HttpURLConnection) connection;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            System.out.print("+++++++++++++" + content);
            bout.write(xml.getBytes());
            byte[] b = bout.toByteArray();
            httpconn.setRequestProperty("Content-Length", String
                    .valueOf(b.length));
//            httpconn.setRequestProperty("Content-Type",
//                    "text/xml; charset=gb2312");
            httpconn.setRequestProperty("Content-Type",
                    "text/xml; charset=UTF-8");
            httpconn.setRequestProperty("SOAPAction", soapAction);
            httpconn.setRequestMethod("POST");
            httpconn.setDoInput(true);
            httpconn.setDoOutput(true);

            OutputStream out = httpconn.getOutputStream();
            out.write(b);
            out.close();

            InputStreamReader isr = new InputStreamReader(httpconn
                    .getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            while (null != (inputLine = in.readLine())) {
//                System.out.println(inputLine);
                Pattern pattern = Pattern.compile("<SendSmsNewResult>(.*)</SendSmsNewResult>");
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    result = matcher.group(1);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}

