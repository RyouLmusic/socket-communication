package com.hbr.weChat.config;

import com.hbr.weChat.JavafxDemo0Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

public class WindowParam {

    @Autowired
    JavafxDemo0Application application;
    /**
     * 登录页面的长宽
     *  prefHeight="480.0" prefWidth="450.0"
     */
    public static final double Login_Width = 450.0;
    public static final double Login_Height = 480.0;
    /**
     * 忘记密码页面的长宽
     */
    public static final double Forget_Width = 700.0;
    public static final double Forget_Height = 450.0;
    /**
     * 注册页面的长宽
     * 530.0" prefWidth="840.0
     */
    public static final double Register_Width = 840.0;
    public static final double Register_Height = 530.0;

    /**
     * 主窗口页面
     * prefHeight="700.0" prefWidth="1400.0"
     */
    public static final double Main_Window_Width = 1400.0;
    public static final double Main_Window_Height =700.0;

    /**
     * home页面
     * prefHeight="700.0" prefWidth="600.0"
     */
    public static final double Home_Page_Width = 600.0;
    public static final double Home_Page_Height = 700.0;

    /**
     * 修改个人信息的页面
     * prefHeight="800.0" prefWidth="450.0"
     */
    public static final double Alter_Person_Width = 450.0;
    public static final double Alter_Person_height = 800.0;

    public static void reSize(Stage stage, Double height, Double width){
        stage.setHeight(height);
        stage.setWidth(width);
    }
}
