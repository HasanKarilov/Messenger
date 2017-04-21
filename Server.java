package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Server - основной класс сервера
 * Сервер должен поддерживать множество соединений с разными клиентами одновременно.
 * Это можно реализовать с помощью следующего алгоритма:
 * - Сервер создает серверное сокетное соединение.
 * - В цикле ожидает, когда какой-то клиент подключится к сокету.
 * - Создает новый поток обработчик Handler, в котором будет происходить обмен сообщениями с клиентом.
 * - Ожидает следующее соединение.
 */
public class Server
{
    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Enter port for chat's server please");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            ConsoleHelper.writeMessage("Server is running");
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage(e.getMessage());
        }

    }
}
