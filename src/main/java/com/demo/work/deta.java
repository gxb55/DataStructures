package com.demo.work;

import cn.hutool.core.date.DateTime;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName deta
 * @Author guoxiaobing
 * @Date 2020/7/13 14:42
 * @Version 1.0
 * @Description TODO
 */
public class deta {
  public static void main(String[] args) throws ParseException {
      //Date date = new Date();
      Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse("Fri Jul 17 00:00:00 CST 2020");

      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date truncate = DateUtils.truncate(new DateTime(), Calendar.DATE);
      System.out.println(dateFormat.format(truncate));
  }
}
