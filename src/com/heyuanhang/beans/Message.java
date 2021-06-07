package com.heyuanhang.beans;

import java.io.Serializable;

/**
 * @Author 何远航
 * @Date: 2021/6/2 17:30
 * @Version 1.8
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;//发送方
    private String receiver;//接收方
    private String msgType;//消息类型
    private String time;//发送时间
    private String msg;//发送内容

    public Message() {
    }

    public Message(String sender, String receiver, String msgType, String time, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msgType = msgType;
        this.time = time;
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
