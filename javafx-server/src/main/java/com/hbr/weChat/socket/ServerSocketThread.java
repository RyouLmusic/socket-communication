package com.hbr.weChat.socket;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.Message;
import com.hbr.weChat.model.User;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * 登录线程
 */
public class ServerSocketThread  implements Callable<ResponseCode> {

    private Socket socket;

    public ServerSocketThread(Socket socket){

        this.socket = socket;
    }


    @Override
    public ResponseCode call() {
        return loginServer();
    }
    public ResponseCode loginServer(){
        ResponseCode code = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            code = (ResponseCode) ois.readObject();
            socket.isInputShutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }


    public Socket getSocket(){
        return socket;
    }
}


//try {
//        //1.创建ServerSocket对象，绑定监听端口
//        ServerSocket serverSocket = new ServerSocket(8888);
//        //2.通过accept()方法监听客户端的请求
//        System.out.println("---客户端已经开启,等待连接---");
//        Socket socket = serverSocket.accept();
//        //3.连接建立，通过输入流读取客户端发送的请求信息
//        InputStream is = socket.getInputStream();//获取字节流
//        InputStreamReader isr = new InputStreamReader(is);//转为字符流
//        BufferedReader br = new BufferedReader(isr); //增加缓冲流
//
//
//        str = br.readLine();
//        //循环读取
//        while(str != null) {
//        System.out.println("接收到客户端的数据" + str);
//        str = br.readLine();
//        }
//        socket.isInputShutdown();
//        //4.响应客户端的信息--向客户端发送信息
//        OutputStream os = socket.getOutputStream();//输出流
//        PrintWriter pw = new PrintWriter(os); //转换成打印流
//        pw.write("欢迎");
//        pw.flush();
//        //5.关闭相关资源
//        pw.close();
//        os.close();
//        br.close();
//        isr.close();
//        is.close();
//        socket.close();
//        serverSocket.close();
//        } catch (IOException e) {
//
//        e.printStackTrace();
//        }