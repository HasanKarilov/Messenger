package com.javarush.task.task30.task3008;

import java.io.Serializable;

/** Message – класс, отвечающий за пересылаемые сообщения.
 * Created by hanaria on 4/21/17.
 */
public class Message implements Serializable{
    private final MessageType type; // Тип сообщения
    private final String data; // Данные сообщения

    public Message(MessageType type) {
        this(type, null);
    }

    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
