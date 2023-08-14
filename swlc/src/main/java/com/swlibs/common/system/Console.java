package com.swlibs.common.system;

/**
 *
 * @author brzsmg
 */
public class Console {
    public static void write(String s)
    {
        System.out.print(s);
    }
    
    public static void writeln(String s)
    {
        System.out.println(s);
    }
    
    public static void setColor(ConsoleColor color){
        System.out.println(color);
    }
    
    public static void resetColor(){
        System.out.println(ConsoleColor.RESET);
    }
    
}
