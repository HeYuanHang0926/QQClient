package com.heyuanhang.client;

import com.heyuanhang.service.QQClientService;
import com.heyuanhang.utils.Utility;

/**
 * @Author 何远航
 * @Date: 2021/6/2 18:40
 * @Version 1.8
 */
//这是QQ的客户端
public class QQClient {
    private boolean loup = true;
    private QQClientService qqClientService = new QQClientService();

    public static void main(String[] args) {
        new QQClient();
    }

    public QQClient() {
        String key1,key;
        while (loup) {
            System.out.println("=========QQ登录===========");
            System.out.println("\t\t1.登录系统");
            System.out.println("\t\t9.退出系统");
            System.out.print("请输入你的选择:");
             key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("请输入QQ号:");
                    String userID = Utility.readString(7);
                    System.out.print("请输入密码:");
                    String pwd = Utility.readString(3);
                    //去服务器端进行验证，成功了之后才允许登录
                    if (qqClientService.LoginAuthorization(userID, pwd) == 1) {
                        System.out.println("========欢迎用户" + userID + "登录成功二级菜单");
                        System.out.println("\t\t1.获取在线用户列表");
                        System.out.println("\t\t2.私聊");
                        System.out.println("\t\t9.退出系统");
                        System.out.print("\n请输入选择:");
                         key1 = Utility.readString(1);
                        switch (key1) {
                            case "1":
                                //  System.out.println("获取在线用户列表");
                                //调用方法进行在线用户列表展示
                                qqClientService.OnlineUserList();
                                break;
                            case "2":
                                System.out.println("进行私聊");
                                System.out.print("请输入对方用户名:");
                                String id = Utility.readString(7);
                                System.out.println("请输如要说的话:");
                                String readString = Utility.readString(8888);
                                //调用一个方法进行消息的发送
                                 qqClientService.privateChat(id,readString,userID);
                                break;
                            case "9":
                                qqClientService.logOut();
                                loup = false;
                                break;
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    loup = false;
                    break;
            }

        }
    }
}
