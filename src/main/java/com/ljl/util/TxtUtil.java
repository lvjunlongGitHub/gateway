package com.ljl.util;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author lvjunlong
 * @date 2019/4/13 下午1:22
 */
@ResponseBody
public class TxtUtil {

    /**
     * 导出txt文件
     * @param response re
     * @param fileName 文件名
     * @param content 导出内容
     */
    public static void exportToTxt(HttpServletResponse response, String fileName, String content) {
        response.setContentType("text/plain");// 一下两行关键的设置
        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(fileName, "utf-8") + ".txt");
        } catch (UnsupportedEncodingException e) {
            //SystemLogger.error(e, "encoder export fileName has Exception! ");
        }
        BufferedOutputStream buff = null;
        ServletOutputStream outSTr = null;
        try {
            outSTr = response.getOutputStream();
            buff = new BufferedOutputStream(outSTr);
            buff.write(content.getBytes(StandardCharsets.UTF_8));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //SystemLogger.error(e, "exportToTxt异常");
        } finally {
            try {
                if (null != buff) {
                    buff.close();
                }
                if (null != outSTr) {
                    outSTr.close();
                }
            } catch (Exception e) {
                //SystemLogger.error(e, "exportToTxt关闭流异常");
            }
        }
    }
}
