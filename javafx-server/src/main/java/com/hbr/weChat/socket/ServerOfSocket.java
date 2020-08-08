package com.hbr.weChat.socket;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.UserService;
import com.hbr.weChat.socket.socketController.RegisterUserController;
import com.hbr.weChat.socket.socketController.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 循环监听，并且开启线程，将线程保存
 */
@Component()
@EnableAsync
public class ServerOfSocket {

    @Autowired
    UserService userService;

    @Autowired
    UserController userController;
    @Autowired
    RegisterUserController registerController;

    @Async
    public User loginServer() {
        ServerSocket serverSocket;
        User user = null;


        try {
            System.out.println("已开启 ： 16668");
            serverSocket = new ServerSocket(16668);

            while (true){
                Socket socket = serverSocket.accept();
                ServerSocketThread socketThread = new ServerSocketThread(socket);


                FutureTask<ResponseCode> ft = new FutureTask<>(socketThread);
                new Thread(ft).start();
                ResponseCode code = ft.get();
                user = code.getUser();

                if(code.getCode() == ResponseCode.LOGIN_CODE){
                    //将socketThread线程保存起来
                    ManageServerSocket.addServerSocketThread(user.getAccount(), socketThread);
                    userController.sendToClient(user);
                    /**
                     * 在此处开一个线程，让此socket保存通信
                     */
                    MessageThread messageThread = new MessageThread(socket);
                    messageThread.start();

                }else if (code.getCode() == ResponseCode.REGISTER_CODE){
                    ManageServerSocket.addRegisterSocket(socketThread);
                    registerController.sendToClient(user);
                    //保存
                    userService.addUser(user);

                }
            }

        } catch (IOException e) {
            System.out.println("Address already in use: JVM_Bind");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }

}
