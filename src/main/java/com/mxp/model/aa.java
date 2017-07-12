package com.mxp.model;

class TestRegx {
    public static void main(String[] args){
        String str = "12123";
        String subStr = "123";
        char[]  start = test(str,subStr);
        System.out.println("第一个开始的位置在第 "+(start)+" 个字符");
    }
    public static char[]   test(String str,String subStr){
        char[] strChar = str.toCharArray();
        char[] subStrChar = subStr.toCharArray();
        Integer start = null;
        boolean flag = false;
        for(int i=0;i<strChar.length&&flag==false;i++){
            int mark = 0;
            for(int j=0;j<subStrChar.length;j++){
                if(i+mark<strChar.length&&strChar[i+mark]==subStrChar[j]){
                    mark++;
                }else{
                    break;
                }
                if(mark == subStrChar.length){
                    flag = true;
                    start = i;
                    break;
                }
            }
        }
        System.out.println(strChar);
        return start == null ?strChar:subStrChar;
    }
}