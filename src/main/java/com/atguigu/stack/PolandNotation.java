package com.atguigu.stack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName PolandNotation @Author guoxiaobing @Date 2020/6/18 19:32 @Version 1.0 @Description
 * 逆波兰表达式 后缀表达式
 */

/**
 * 中缀表达式和后缀表达式的思路分析以及区别
 * 中缀表达式：
 *      1.两个栈，一个数栈s1，一个符号栈s2，s1直接入栈，
 *      2.s2如果是空的或者新加入的优先级比s2栈顶的运算符优先级高则直接入栈；
 *      3.如果新加入的运算符优先级小于s2栈顶的运算符的优先级，则从数栈中取出两个数先取出的是num1 后取出的是num2 让num2 运算符 num1 计算出结果
 *      4.将运算符全部出栈，然后如同第三步一样的计算最后得出结果。
 *  后缀表达式：
 *     1.两个栈s1 s2，s1数字栈直接入栈，s2结果栈
 *     2.s2符号栈入栈的情况，
 *      1.s2栈空直接入栈 （左括号直接入栈 优先级大于栈顶元素直接入栈
 *      2.如果符号的优先级小于栈顶的符号则将栈顶的符号出栈，放入到s2栈中去，一直循环此操作直到遇到符号优先级小于当前符号的
 *      3.如果是）右括号则将右括号抛弃并将s1中的符号放到s2中 直到遇到（左括号，并将左括号移除
 *      4.当表达式从左到右扫描完，将s1栈中的元素以此放入到s2中
 *      5.s2逆序输出到list中 最终将list中的元素进行后续表达式的计算
 *  扩展：
 *      1.多位数问题 多位数在扫描表达式的时候要判断下一个是否是数组如果是则进行拼接直到下一个元素不是数字，最终在栈中每一个元素都有可能是多位数
 *      2.小数问题  小数问题和多位数问题非常相似，就是在扫描到小数点的时候将他当做是数字进行拼接，直到下一个字母是一个运算符号，最终栈中的每一个元素都有可能是一个小数或者证书
 *        在运算的时候不能直接+ - 要用到BigDecimal 来进行加减乘除的运算
 */
public class PolandNotation {
  public static void main(String[] args) {
    /* 后缀表达式计算 1+（（2+3）*4）-5 转化为 ---》1, 2, 3, +, 4, *, +, 5,
    - stack stack 其实后一个stack可以直接用list因为他没有用到stack的属性即 先入后出，
    */
    String str = "10+((2+31)*4)-5.1";
    List<String> list = new ArrayList<>();
    StringBuilder sb;
    for (int i = 0; i < str.length(); i++) {
      if ((str.charAt(i) > 47 && str.charAt(i) < 58) || str.charAt(i)==46) { // 0是48 9是58说明是一个数字  小数点是46
        sb = new StringBuilder();
        while (i < str.length() && ((str.charAt(i) > 47 && str.charAt(i) < 58)||(str.charAt(i)==46))) {
          sb.append(str.charAt(i));
          i++;
        }
        i--;
        list.add(sb.toString());
      } else {
        list.add(String.valueOf(str.charAt(i)));
      }
    }
    System.out.println(list.toString());
    Stack<String> operStack = new Stack();
    Stack<String> resStack = new Stack<String>();
    for (String s : list) {
      if (isOper(s)) { // 如果是符号 1.如果operStack 是空的或者优先级大于栈顶的或者是（则直接入栈
        // 2.如果优先级小于栈顶的则将压入resStack，3.如果是）则将运算符加入到resStack中去指导遇到（ 然后将一对括号删除
        if (operStack.isEmpty() || "(".equals(s)) {
          operStack.push(s);
          continue;
        }
        if (")".equals(s)) {
          String oper = operStack.pop();
          while (!"(".equals(oper)) {
            resStack.push(oper);
            oper = operStack.pop();
          }
        } else if (priority(s) > priority(operStack.peek())) {
          operStack.push(s);
        } else {
          while (!operStack.isEmpty() && priority(s) <= priority(operStack.peek())) {
            resStack.push(operStack.pop());
          }
          operStack.push(s);
        }
      } else { // 数字
        resStack.push(s);
      }
    }
    while (!operStack.isEmpty()) {
      resStack.push(operStack.pop());
    }
    System.out.println(resStack.toString());
    String[] arr = new String[resStack.size()];
    String[] strings = resStack.toArray(arr);
    List<String> objects = Arrays.asList(strings);
    System.out.println(objects);
    calculate(str, objects);
    // infix();

  }

  public static int priority(String oper) {

    if ("*".equals(oper) || "/".equals(oper)) {
      return 1;
    } else if ("+".equals(oper) || "-".equals(oper)) {
      return 0;
    } else {
      return -1;
    }
  }

  private static void infix() {
    /**
     * 中缀表达式计算，表达式已给出来了 逆波兰表达式的转化 （30+4）*5-6 --》30 4 + 5 * 6 - 4*5-8+60+8/2 --》4 5 * 8 - 60 8 2 / +
     */
    // String suffix = "30 4 + 5 * 6 -";
    String suffix = "4 5 * 8 - 60 + 8 2 / +";
    List<String> list = getListString(suffix);
    calculate(suffix, list);
  }

  private static void calculate(String suffix, List<String> list) {
    Stack<String> numStack = new Stack();
    for (String str : list) {
      if (!isOper(str)) { // 数字
        numStack.push(str);
      } else {
        String num1 = numStack.pop();
        String num2 = numStack.pop();
        BigDecimal cal = cal(BigDecimal.valueOf(Double.parseDouble(num1)), BigDecimal.valueOf(Double.parseDouble(num2)), str);
        numStack.push(cal + "");
      }
    }
    System.out.println(suffix + "的运算结果是：" + numStack.pop());
  }

  public static List<String> getListString(String str) {
    return Arrays.asList(str.split(" "));
  }

  public static boolean isOper(String str) {
    if ("+".equals(str)
        || "-".equals(str)
        || "*".equals(str)
        || "/".equals(str)
        || "(".equals(str)
        || ")".equals(str)) {
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

    public static BigDecimal cal(BigDecimal num1, BigDecimal num2, String oper) {
        BigDecimal val = new BigDecimal(0);
        switch (oper) {
            case "+":
                val = num1.add(num2);
                break;
            case "-":
                val = num2.subtract(num1); // 注意顺序 后出来的 - 先出来的
                break;
            case "*":
                val = num1.multiply(num2);
                break;
            case "/":
                val = num2.divide(num1); // 跟上述的，减号一样
                break;
            default:
                break;
        }
        return val;
    }
}
