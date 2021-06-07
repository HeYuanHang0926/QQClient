package com.heyuanhang.clientThread;

import com.heyuanhang.beans.Message;
import com.heyuanhang.client.QqChat;
import com.heyuanhang.clientInter.MessageInterface;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @Author 何远航
 * @Date: 2021/6/2 19:12
 * @Version 1.8
 */
public class ThreadClientSocked extends Thread {
    //构建socked
    private Socket socket;

    //通过构造器注入socked
    public ThreadClientSocked(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //创建输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //读入消息对象
                Message msg = (Message) ois.readObject();
                //这儿开始读
                if (msg.getMsgType().equals(MessageInterface.GET_ONLINE_USERS)) {
                    String userList = msg.getMsg();
                    String[] userLists = userList.split(" ");
                    System.out.println("=========用户在线列表======");
                    for (String s : userLists) {
                        System.out.println(s);
                    }
                } else if (msg.getMsgType().equals(MessageInterface.MSG_PRIVATE_CHAT_SUCCESS)) {
                    //解析消息
                    String msg1 = msg.getMsg();
                    String receiver = msg.getReceiver();
                    String sender = msg.getSender();
                    System.out.println(sender+" 对 "+receiver+"说:"+msg1);
                    QqChat qqChat = AdministrationQqChatHashMap.getQqChat(receiver + " " + sender);
                    qqChat.showMessage(msg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获得socked
    public Socket getSocket() {
        return socket;
    }
}
