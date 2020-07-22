package com.demo.work;

/**
 * @ClassName IdCardTest
 * @Author guoxiaobing
 * @Date 2020/7/20 10:27
 * @Version 1.0
 * @Description 身份证号校验
 */
public class IdCardTest {
  public static void main(String[] args) {
    String a = "/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$/";
      boolean matches = "411328199408201318".matches(a);
      System.out.println(matches);
   /* * 身份证18位编码规则：dddddd yyyymmdd xxx y
    * dddddd：6位地区编码
    * yyyymmdd: 出生年(四位年)月日，如：19910215
    * xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
    * y: 校验码，该位数值可通过前17位计算获得*/
  }
}
