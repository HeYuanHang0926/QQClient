package com.heyuanhang.client;

import com.heyuanhang.beans.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author 何远航
 * @Date: 2021/6/2 21:59
 * @Version 1.8
 */
public class MyClient {
   public static void main(String[] args) throws IOException {
       Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
       User user = new User();
       user.setPwd("123");
       user.setUserID("100");
       ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
       oos.writeObject(user);


   }
}
