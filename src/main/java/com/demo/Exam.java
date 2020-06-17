package com.demo;

/**
 * @ClassName Exam
 * @Author guoxiaobing
 * @Date 2020/6/17 13:38
 * @Version 1.0
 * @Description TODO
 */
public class Exam {
    static int s;
    int i;
    int j;
    {
        int i=1;
        i++;
        j++;
        s++;
    }
    public void  add(int j){
        i++;
        j++;
        s++;
    }

    public static void main(String[] args) {
        Exam e1= new Exam();
        Exam e2= new Exam();
        System.out.println(e1.s);
        e1.add(10);
        e1.add(20);
        e2.add(10);
        System.out.println(e1.i+","+e1.j+","+e1.s);
      //  2  4  2

        System.out.println(e2.i+","+e2.j+","+e2.s);
      //  1 2 3

    }
}
