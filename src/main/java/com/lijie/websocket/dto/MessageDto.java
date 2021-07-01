package com.lijie.websocket.dto;

/**
 * 功能描述：
 *
 * @author: lijie
 * @date: 2021/7/1 9:40
 * @version: V1.0
 */
public class MessageDto {
    private String clientId;
    private String message;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "dto{" +
                "clientId='" + clientId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
