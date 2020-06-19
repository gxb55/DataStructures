package com.atguigu.stack;

/**
 * @ClassName Calculator @Author guoxiaobing @Date 2020/6/18 16:06 @Version 1.0 @Description
 * 栈来实现计算器功能
 */
public class Calculator {
  public static void main(String[] args) {
    // infixCalculator();+(8*9-10)
    String str = "100+20*(6-4)+(8*9-10)";
    ArrayStack2 operStack = new ArrayStack2(str.length());
    ArrayStack2 numStack = new ArrayStack2(str.length());
    char[] chars = str.toCharArray();
    int num1, num2;
    for (int i = 0; i < chars.length; i++) {
      String keepNum = "";
      if (operStack.isOper(chars[i])) {
        if (operStack.isEmpty()) {
          operStack.push(chars[i]);
        } else {
          int pop = operStack.peek();

          if (chars[i] == ')') { // 计算到了符合的另一端则将（ +）全部出栈 然后找两个数字进行计算
            // operStack.pop(); // 把右括号出栈 右括号都没有入栈
            while (operStack.peek() != '(') {
              num1 = numStack.pop();
              num2 = numStack.pop();
              numStack.push(numStack.cal(num1, num2, operStack.pop()));
            }
            operStack.pop(); // 把左括号出栈
            continue;
          }

          if (operStack.priority(pop) < operStack.priority(chars[i]) || pop == '(') { // 如果新加入的符号优先级大于栈中的符号优先级则直接假如
            operStack.push(chars[i]);
          } else {
            numStack.push(numStack.cal(numStack.pop(), numStack.pop(), operStack.pop()));
            operStack.push(chars[i]);
          }
        }
      } else {
        // numStack.push(chars[i]-48);//遇见数字就入数栈是有问题的比如 70会被分成 7 0两个
        if (i == str.length() - 1) { // 最后一位了就不用再往后看了，直接放入栈中就可以了
          numStack.push(chars[i] - 48);
        } else {
          while (!numStack.isOper(chars[i])) { // 是数字的话
            keepNum += chars[i];
            i++;
          }
          i--;
          numStack.push(Integer.valueOf(keepNum));
        }
      }
    }

    while (!operStack.isEmpty()) {
      numStack.push(numStack.cal(numStack.pop(), numStack.pop(), operStack.pop()));
    }
    System.out.println(str + "的运算结果是：" + numStack.pop());
  }

  private static void infixCalculator() {
    // 中缀表达式不包含括号
    // 根据笔记的来写 两个栈一个存放数字一个存放符号，放入符号的时候判断如果符号的优先级比前面一个的优先级大则直接放入，如果优先级等于栈中符号的优先级则取出两个数来做运算
    String str = "100+20*6-4";
    ArrayStack2 operStack = new ArrayStack2(str.length());
    ArrayStack2 numStack = new ArrayStack2(str.length());
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      String keepNum = "";
      if (operStack.isOper(chars[i])) {
        if (operStack.isEmpty()) {
          operStack.push(chars[i]);
        } else {
          int pop = operStack.peek();
          if (operStack.priority(pop)
              < operStack.priority(chars[i])) { // 如果新加入的符号优先级大于栈中的符号优先级则直接假如
            operStack.push(chars[i]);
          } else {
            numStack.push(numStack.cal(numStack.pop(), numStack.pop(), operStack.pop()));
            operStack.push(chars[i]);
          }
        }
      } else {
        // numStack.push(chars[i]-48);//遇见数字就入数栈是有问题的比如 70会被分成 7 0两个
        if (i == str.length() - 1) { // 最后一位了就不用再往后看了，直接放入栈中就可以了
          numStack.push(chars[i] - 48);
        } else {
          while (!numStack.isOper(chars[i])) { // 是数字的话
            keepNum += chars[i];
            i++;
          }
          i--;
          numStack.push(Integer.valueOf(keepNum));
        }
      }
    }
    while (!operStack.isEmpty()) {
      numStack.push(numStack.cal(numStack.pop(), numStack.pop(), operStack.pop()));
    }
    System.out.println(str + "的运算结果是：" + numStack.pop());
  }
}

class ArrayStack2 {
  private int maxSize;
  private int[] stack;
  private int top = -1; // 栈鼎 默认 -1

  public ArrayStack2(int maxSize) {
    this.maxSize = maxSize;
    stack = new int[maxSize];
  }

  public boolean isFull() {
    return top == maxSize - 1;
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public void push(int val) {
    if (isFull()) {
      return;
    }
    top++;
    stack[top] = val;
  }
  // 查看栈顶元素并删除
  public int pop() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    int val = stack[top];
    top--;
    return val;
  }
  // 查看栈顶元素不删除
  public int peek() {
    if (isEmpty()) {
      throw new RuntimeException("栈空");
    }
    int val = stack[top];
    return val;
  }

  public void loop() {
    if (isEmpty()) {
      return;
    }
    for (int i = top; i > -1; i--) {
      System.out.println(stack[i]);
    }
  }
  // 判断运算符优先级
  public int priority(int oper) {
    if (oper == '(' || oper == ')') {
      return 2;
    } else if (oper == '*' || oper == '/') {
      return 1;
    } else if (oper == '+' || oper == '-') {
      return 0;
    } else {
      return -1;
    }
  }
  // 判断是运算符还是数字
  public boolean isOper(char val) {
    return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
  }
  // 计算的方法
  public int cal(int num1, int num2, int oper) {
    int val = 0;
    switch (oper) {
      case '+':
        val = num1 + num2;
        break;
      case '-':
        val = num2 - num1; // 注意顺序 后出来的 - 先出来的
        break;
      case '*':
        val = num1 * num2;
        break;
      case '/':
        val = num2 / num1; // 跟上述的，减号一样
        break;
      default:
        break;
    }
    return val;
  }
}
