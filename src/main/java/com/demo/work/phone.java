package com.demo.work;

/**
 * @ClassName phone
 * @Author guoxiaobing
 * @Date 2020/7/15 10:42
 * @Version 1.0
 * @Description TODO
 */
public class phone {
  public static void main(String[] args) {
      String phone ="18595917065";
      String s = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1********$2");
      System.out.println(s);
//String id="411328199408201214";
String id="411328199408201214";
      String s1 = id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
      System.out.println(s1);
  }
}
