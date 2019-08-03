package com.avril.model;

import lombok.Data;

@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String time;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}