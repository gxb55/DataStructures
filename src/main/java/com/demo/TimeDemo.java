package com.demo;

import cn.hutool.Hutool;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ClassName TimeDemo
 * @Author guoxiaobing
 * @Date 2020/6/22 14:17
 * @Version 1.0
 * @Description 时间相关api
 */
public class TimeDemo {
  public static void main(String[] args) {
    //
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Calendar calendar = Calendar.getInstance();
      System.out.println(simpleDateFormat.format(calendar.getTime()));
      calendar.add(Calendar.DATE, 1);

      System.out.println(simpleDateFormat.format(calendar.getTime()));
  }
}
