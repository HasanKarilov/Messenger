package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** ConsoleHelper – вспомогательный класс, для чтения или записи в консоль.
 * Created by hanaria on 4/21/17.
 */
public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        String line = "";
        while(true)
        {
            try
            {
                line = bis.readLine();
                break;
            }
            catch (IOException e)
            {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return line;
    }

    public static int readInt(){
        int num = 0;
        while (true)
        {
            try
            {
                num = Integer.parseInt(readString());
                break;
            }
            catch (NumberFormatException e){
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
        return num;
    }

}
