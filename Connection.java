package com.javarush.task.task30.task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/** Connection – класс соединения между клиентом и сервером.
 * Created by hanaria on 4/21/17.
 * Клиент и сервер будут общаться через сокетное соединение. Одна сторона будет
 * записывать данные в сокет, а другая читать. Их общение представляет собой обмен
 * сообщениями Message. Класс Connection будет выполнять роль обертки над классом
 * java.net.Socket, которая должна будет уметь сериализовать и десериализовать объекты
 * типа Message в сокет. Методы этого класса должны быть готовы к вызову из разных потоков.
 */
public class Connection implements Closeable
{
    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    /**
     * Создать объект класса ObjectOutputStream нужно до того, как будет создаваться объект
     * класса ObjectInputStream, иначе может возникнуть взаимная блокировка потоков,
     * которые хотят установить соединение через класс Connection.
     */
    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    /**Он должен записывать (сериализовать) сообщение message в ObjectOutputStream.
     * Этот метод будет вызываться из нескольких потоков.
     * Позаботься, чтобы запись в объект ObjectOutputStream была возможна только одним потоком в определенный момент
     * времени, остальные желающие ждали завершения записи. При этом другие методы класса Connection не должны быть заблокированы.
     */
    public void send(Message message) throws IOException{
        synchronized (out)
        {
            out.writeObject(message);
        }
    }

    /**Он должен читать (десериализовать) данные из ObjectInputStream. Сделай так, чтобы операция чтения
     * не могла быть одновременно вызвана несколькими потоками, при этом вызов других методы класса Connection не блокировать.
     */
    public Message receive() throws IOException, ClassNotFoundException
    {
        synchronized (in)
        {
            return (Message) in.readObject();
        }
    }

    /**
     * Возвращает удаленный адресс сокетного соедение
     */
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }

    @Override
    public void close() throws IOException
    {
        socket.close();
        out.close();
        in.close();
    }

    /**
     * Закрывает все ресурсы класса
     */


}
