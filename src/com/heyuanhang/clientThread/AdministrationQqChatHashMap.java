package com.heyuanhang.clientThread;

import com.heyuanhang.client.QqChat;

import java.util.HashMap;

/**
 * @Author 何远航
 * @Date: 2021/6/6 19:28
 * @Version 1.8
 * 这个类是管理聊天界面的
 */
public class AdministrationQqChatHashMap {
    private  static HashMap<String, QqChat>  map = new HashMap<>();

    //装入聊天界面
    public static void addQqChat(String myIdAndFriendId, QqChat qqChat) {
        map.put(myIdAndFriendId, qqChat);
    }

    //获取QqChat
    public static QqChat getQqChat(String myIdAndFriendId) {
        return map.get(myIdAndFriendId);
    }

    //移除聊天界面
    public static void removeQqChat(String myIdAndFriendId) {
        map.remove(myIdAndFriendId);
    }
}
