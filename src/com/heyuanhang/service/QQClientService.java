package com.heyuanhang.service;

import com.heyuanhang.beans.Message;
import com.heyuanhang.beans.User;
import com.heyuanhang.clientInter.MessageInterface;
import com.heyuanhang.clientThread.ThreadClientSocked;
import com.heyuanhang.clientThread.ThreadHashMap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Author 何远航
 * @Date: 2021/6/2 18:53
 * @Version 1.8
 */
//这是QQ授权登录的服务层
public class QQClientService {
    private User user = new User();
    //-1表示用户名或密码错误
    //2表示用户名重复登录
    //1表示正常登录
    private Integer loup = -1;
    private Socket socket;

    public Integer LoginAuthorization(String userID, String pwd) {
        try {
            //与服务器构建连接
            socket = new Socket(InetAddress.getLocalHost(), 8888);
            user.setUserID(userID);
            user.setPwd(pwd);
            //构建对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将user写入soked通道
            oos.writeObject(user);
            //然后从soked中读入服务器发送过来的消息
            //创建输入流
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //读入消息对象
            Message msg = (Message) ois.readObject();
            //开始判断
            if (msg.getMsgType().equals(MessageInterface.SUCCESS)) {
                loup = 1;
                //登录成功
                //成功之后就将当前这个soked，创建在线程之中，使其与服务器保持通信
                ThreadClientSocked threadClientSocked = new ThreadClientSocked(socket);//开启线程
                threadClientSocked.start();
                //将线程装入到集合中
                ThreadHashMap.addThread(userID, threadClientSocked);
            } else if (msg.getMsgType().equals(MessageInterface.MSG_REPEAT_USER)) {//用户名已经登录
                    loup=2;
                    socket.close();
            } else {//失败了，没有该用户
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return loup;
    }

    //在线用户列表展示
    public void OnlineUserList() {
        Message message = new Message();
        //获取当前用户与服务器保持通信的socked,通过UserID获得
        // 先获取与socked相关联的线程
        ThreadClientSocked threadClientSocked = ThreadHashMap.getThread(user.getUserID());
        //在获取socked
        Socket socket = threadClientSocked.getSocket();
        //告诉服务器，我需要查询在线用户列表
        message.setMsgType(MessageInterface.MSG_ONLINE_SEND_OUT);
        message.setSender(user.getUserID());
        //获取输出流
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //退出体统
    public void logOut() {
        //告诉服务器，客户端要退出了
        Message message = new Message();
        message.setMsgType(MessageInterface.MSG_EXIT);
        message.setSender(user.getUserID());//发送者
        //先从map集合中获得关联socked的线程
        ThreadClientSocked threadClientSocked = ThreadHashMap.getThread(user.getUserID());
        Socket socket = threadClientSocked.getSocket();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(user.getUserID() + "请求退出!!!");
            //退出
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id      接收方
     * @param sendMsg 要发送的消息
     * @param userID  发送者
     */
    //进行私聊
    public void privateChat(String id, String sendMsg, String userID) {
        //先获取当前这个用户的socked
        ThreadClientSocked threadClientSocked = ThreadHashMap.getThread(userID);
        Socket socket = threadClientSocked.getSocket();
        //创建消息对象
        Message message = new Message();
        message.setSender(userID);
        message.setMsg(sendMsg);//发送的具体消息
        message.setReceiver(id);//接收方
        message.setMsgType(MessageInterface.MSG_PRIVATE_CHAT);//私聊请求
        //将消息写入到socked通道中
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //接收私聊消息
    public void getPrivateChat(String readMsg, String sender, String receiver) {
        //获得接收对象的socked
        ThreadClientSocked threadClientSocked = ThreadHashMap.getThread(receiver);
        Socket socket = threadClientSocked.getSocket();
        //暂时不处理回发消息
    }

    //告诉服务器，我（客户端）要下线了

    /**
     * @param userID：要下线的客户端
     */
    public void QqClientOffline(String userID){
        //从线程集合map中获取对象的socked
        ThreadClientSocked threadClientSocked = ThreadHashMap.getThread(userID);
        Socket socket = threadClientSocked.getSocket();
        Message message = new Message();
        message.setMsgType(MessageInterface.MSG_QQ_EXIT);//客户端退出指令
        message.setSender(userID);//发送者
        //获取对象输出流将消息发给服务器
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向服务器发送请求去获取好友列表
    /**
     *
     * @param sender:发送者
     */
    public void getFriendsList(String sender){

    }
}
