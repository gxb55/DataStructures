package com.demo;

/**
 * @ClassName text
 * @Author guoxiaobing
 * @Date 2020/6/10 10:28
 * @Version 1.0
 * @Description TODO
 */
public class text {
  public static void main(String[] args) {
    int a = 5;
    inner: if(a>2){
        System.out.println(11);
        break inner;
      }
  }
}
