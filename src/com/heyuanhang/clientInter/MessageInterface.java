package com.heyuanhang.clientInter;

/**
 * @Author 何远航
 * @Date: 2021/6/2 19:01
 * @Version 1.8
 */
public interface MessageInterface {
    String SUCCESS = "1";
    String FAIL = "2";
    String MSG_ONLINE_SEND_OUT = "3";//发送在线用户列表请求
    String GET_ONLINE_USERS = "4";//得到用户列表
    String MSG_EXIT = "5";//退出系统指令
    String MSG_PRIVATE_CHAT = "6";//私聊请求
    String MSG_PRIVATE_CHAT_SUCCESS = "7";//私聊成功
    String MSG_REPEAT_USER = "8";//标志用户名重复
    String MSG_QQ_EXIT = "9";//QQ退出
}
