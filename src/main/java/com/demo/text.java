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


      if(a>4){
          System.out.println(555);
      }else if (a>3){
          System.out.println(333);
      }else{
          System.out.println(222);
      }
  }
}
