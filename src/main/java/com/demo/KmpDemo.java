package com.demo;

/**
 * @ClassName KmpDemo @Author guoxiaobing @Date 2020/6/19 9:59 @Version 1.0 @Description KMP计算的问题
 * 字符串比较求子串的位置
 */
public class KmpDemo {
  public static void main(String[] args) {
    String t = "abaabcac";
    String s = "abcabaabcacaabcacb";
    // 暴力匹配法 效率比较低
    int j = t.length()-1;
    int p =0;
    for (int i = 0; i < s.length(); i++) {
      int k = i;
      while (true) {
        if (i<s.length()&&p<t.length()) {
          if (s.charAt(i) == t.charAt(p)) {
            i++;
            p++;
          } else {
            break;
          }
        }else{
            break;
        }
      }
      if (j == (i - k-1)) { // 说明串完全匹配了,当字符串匹配后就会将 i++ 用于下次比较但是最后一次也会++就造成了多加了一次
        System.out.println("在字符串 s的" + (k + 1) + "位置到" + (k + j + 1) + "的位置完全匹配");//使用的是下标记数的所有都会加一下标是2就是字符串的第三个
        break;
      } else {
        i = k;
        p=0;
      }
    }
  }
}
