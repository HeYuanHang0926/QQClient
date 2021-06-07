package com.heyuanhang.client;

import com.heyuanhang.beans.User;
import com.heyuanhang.service.QQClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author 何远航
 * @Date: 2021/6/4 22:33
 * @Version 1.8
 */
public class QqLoginView extends JFrame implements ActionListener {
    public static void main(String[] args) {
        QqLoginView qqLoginView = new QqLoginView();
    }

    private QQClientService qqClientService = new QQClientService();
    //背面面板
    private JLabel jbl;
    //中间面板
    JTabbedPane jtp;//选项卡
    JPanel jp2, jp3, jp4;//面板
    JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;//标签
    JButton jp2_jb1;//按钮，清除号码使用
    JTextField jp2_jtf;//QQ号码输入框
    JPasswordField jp2_jpf;//密码输入框
    JCheckBox jp2_jcb1, jp2_jcb2;//单选框
    //南部面板
    JPanel jpd;
    JButton jpd_bt1, jpd_bt2, jpd_bt3;

    public QqLoginView() {
        //处理北面
        jbl = new JLabel(new ImageIcon("image/tou.gif"));
        //处理中部组件
        jp2 = new JPanel(new GridLayout(3, 3));
        jp2_jbl1 = new JLabel("QQ号码", JLabel.CENTER);
        jp2_jbl2 = new JLabel("QQ密码", JLabel.CENTER);
        jp2_jbl3 = new JLabel("忘记密码", JLabel.CENTER);
        //设置jp2_jbl3的前景色
        jp2_jbl3.setForeground(Color.blue);
        jp2_jbl4 = new JLabel("申请密码保护", JLabel.CENTER);
        jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
        //为清除按钮添加事件
        jp2_jb1.addActionListener(this);
        jp2_jtf = new JTextField();
        jp2_jpf = new JPasswordField();
        jp2_jcb1 = new JCheckBox("隐身登录");
        jp2_jcb2 = new JCheckBox("记住密码");
        //将各组件添加到Jpanl中即jp2中
        jp2.add(jp2_jbl1);
        jp2.add(jp2_jtf);
        jp2.add(jp2_jb1);
        jp2.add(jp2_jbl2);
        jp2.add(jp2_jpf);
        jp2.add(jp2_jbl3);
        jp2.add(jp2_jcb1);
        jp2.add(jp2_jcb2);
        jp2.add(jp2_jbl4);
        jp3 = new JPanel();
        jp4 = new JPanel();
        //添加到选项卡中
        jtp = new JTabbedPane();
        jtp.add("QQ号码登录", jp2);
        jtp.add("手机号码登录", jp3);
        jtp.add("电子邮件登录", jp4);
        //处理南面的三个按钮
        jpd = new JPanel();//流布局
        jpd_bt1 = new JButton(new ImageIcon("image/denglu.gif"));
        //为登录按钮添加点击事件
        jpd_bt1.addActionListener(this);
        jpd_bt2 = new JButton(new ImageIcon("image/quxiao.gif"));
        jpd_bt3 = new JButton(new ImageIcon("image/xiangdao.gif"));
        jpd.add(jpd_bt1);
        jpd.add(jpd_bt2);
        jpd.add(jpd_bt3);
        this.setSize(350, 240);
        this.setLocation(500, 300);
        this.add(jbl, "North");
        this.add(jtp, "Center");
        //把jpd放在南面
        this.add(jpd, "South");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //开始判断,当是点击登录按钮后
        if (e.getSource() == jpd_bt1) {
            User user = new User();
            user.setUserID(jp2_jtf.getText().trim());
            user.setPwd(new java.lang.String(jp2_jpf.getPassword()));
            //发送请求去服务器进行验证
            Integer b = qqClientService.LoginAuthorization(user.getUserID(), user.getPwd());
            if (b == 1) {
                //登录成功
                QqFriendsList qqFriendsList = new QqFriendsList(user.getUserID());
                //像服务器发送请求，获取所有在线的好友
                
                //为该窗口添加一个关闭退出事件
                qqFriendsList.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        //退出就告诉服务器
                        qqClientService.QqClientOffline(user.getUserID());
                        //同时告诉服务器，客户端退出了要进行相应的关闭
                        //qqClientService.logOut();
                    }
                });
                //关闭登录界面
                this.dispose();
            } else if (b == 2) {//用户名重复登录
                JOptionPane.showMessageDialog(this,"该用户已经登录，请忽重复登录！");
            } else {
                System.out.println("登录失败");
                JOptionPane.showMessageDialog(this, "用户名或密码错误");
            }
        }
        /**
         * 当点击清除按钮后就进行输入框内容的清空
         */
        if (e.getSource() == jp2_jb1) {
            jp2_jtf.setText("");
            jp2_jpf.setText("");
        }
    }
}
