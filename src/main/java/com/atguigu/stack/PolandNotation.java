package com.atguigu.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PolandNotation
 * @Author guoxiaobing
 * @Date 2020/6/18 19:32
 * @Version 1.0
 * @Description 逆波兰表达式 后缀表达式
 */
public class PolandNotation {
  public static void main(String[] args) {
      /**
       * 逆波兰表达式的转化
       * （30+4）*5-6 --》30 4 + 5 * 6 -
       * 4*5-8+60+8/2 --》4 5 * 8 - 60 8 2 / +
       */
      //String suffix = "30 4 + 5 * 6 -";
      String suffix = "4 5 * 8 - 60 + 8 2 / +";
      List<String> list = getListString(suffix);
      calculate(suffix, list);
  }

    private static void calculate(String suffix, List<String> list) {
        Stack<String> numStack = new Stack();
        for (String str:list){
            if(!isOper(str)){//数字
                numStack.push(str);
            }else{
                String num1 = numStack.pop();
                String num2 = numStack.pop();
                int cal = cal(Integer.parseInt(num1), Integer.parseInt(num2), str);
                numStack.push(cal+"");
            }
        }
        System.out.println(suffix + "的运算结果是："+numStack.pop());
    }

    public static List<String> getListString(String str){
      return  Arrays.asList(str.split(" "));
  }
  public static boolean isOper (String str){
      if("+".equals(str)||"-".equals(str)||"*".equals(str)||"/".equals(str)){
          return true;
      }
      return false;
  }
    public static int cal(int num1, int num2, String oper) {
        int val = 0;
        switch (oper) {
            case "+":
                val = num1 + num2;
                break;
            case "-":
                val = num2 - num1; // 注意顺序 后出来的 - 先出来的
                break;
            case "*":
                val = num1 * num2;
                break;
            case "/":
                val = num2 / num1; // 跟上述的，减号一样
                break;
            default:
                break;
        }
        return val;
    }
}
