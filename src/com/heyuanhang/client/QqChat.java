package com.heyuanhang.client;

/**
 * @Author 何远航
 * @Date: 2021/6/5 8:37
 * @Version 1.8
 * <p>
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个
 */

import com.heyuanhang.beans.Message;
import com.heyuanhang.service.QQClientService;

import javax.swing.*;
import java.awt.event.*;

public class QqChat extends JFrame implements ActionListener {
    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    String ownerId;
    String friendId;
    private QQClientService qqClientService = new QQClientService();

    public QqChat() {
    }

    public static QqChat getQqChat() {
        return new QqChat();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //QqChat qqChat = new QqChat("1", "100");
    }

    public QqChat(String ownerId, String friend) {
        this.ownerId = ownerId;
        this.friendId = friend;
        jta = new JTextArea();
        //为该输入框添加事件
        jtf = new JTextField(15);
        jb = new JButton("发送");
        //为发送按钮添加点击事件
        jb.addActionListener(this);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);
        this.add(jta, "Center");
        this.add(jp, "South");
        this.setTitle(ownerId + " 正在和 " + friend + " 聊天");
        this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
        this.setSize(500, 400);
        this.setLocation(600, 300);
        this.setVisible(true);
    }

    //写一个方法，让它显示消息
    public void showMessage(Message m) {
        String info = "\n" + m.getSender() + ": " + m.getMsg();
        jta.append(info);
    }

    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if (arg0.getSource() == jb) {
            //如果用户点击了，发送按钮
            //获取到输入框中的内容，即就是要发送的消息
            String msg = jtf.getText();
            Message message = new Message();
            message.setSender(ownerId);
            message.setReceiver(friendId);
            message.setMsg(msg);
            jtf.setText("");
            //开始向服务器发送消息
            /***
             * friendId:接收者
             * msg：要发送的消息
             * ownerId：发送者
             */
            qqClientService.privateChat(friendId, msg, ownerId);
            //把发送的内容添加到聊天区域中
            this.jta.append("\n我: " + msg);
        }

    }
}

