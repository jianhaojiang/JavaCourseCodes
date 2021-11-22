package io.github.kimmking.gateway.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: jvm-week1
 * @description 引入线程
 * @author: jjh
 * @create: 2021-11-14 17:43
 **/
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> service(socket)).start();

        }
    }

    private static void service(Socket socket) {
        PrintWriter printWriter = null;
        try {
            String body = "Hello Netty2！";
            //个性化处理,取得header中的数据，如果包括jjh，设置返回
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            in.read(buf);
            String requsetString = new String(buf);
            if (requsetString.contains("jjh")){
                body = "Hello 蒋建毫2！";
            }
            printWriter = new PrintWriter(socket.getOutputStream(), false);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);

            printWriter.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
