package com.heyuanhang.clientThread;

import java.util.HashMap;

/**
 * @Author 何远航
 * @Date: 2021/6/4 10:41
 * @Version 1.8
 */
//管理线程
public class ThreadHashMap {
    private static HashMap<String, ThreadClientSocked> map = new HashMap<String, ThreadClientSocked>();

    public static void addThread(String userID, ThreadClientSocked threadClientSocked) {
        map.put(userID, threadClientSocked);
    }

    //返回线程
    public static ThreadClientSocked getThread(String userID) {
        return map.get(userID);
    }

}
