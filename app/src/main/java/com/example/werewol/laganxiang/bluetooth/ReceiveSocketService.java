package com.example.werewol.laganxiang.bluetooth;

import android.os.Handler;
import android.os.Message;

import com.example.werewol.laganxiang.application.LaGanXiangApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Luhao on 2016/9/28.
 * 接收消息的服务
 */
public class ReceiveSocketService {

    public static void receiveMessage(Handler handler) {
        if (LaGanXiangApplication.bluetoothSocket == null || handler == null) return;
        try {
            InputStream inputStream = LaGanXiangApplication.bluetoothSocket.getInputStream();
            // 从客户端获取信息
            BufferedReader bff = new BufferedReader(new InputStreamReader(inputStream));
            String json;

            // 无线循环来接收数据
            while (true) {
                // 创建一个128字节的缓冲
                byte[] buffer = new byte[128];
                // 每次读取128字节，并保存其读取的角标
                int count = inputStream.read(buffer);
                // 创建Message类，向handler发送数据
                Message msg = Message.obtain();
                // 发送一个String的数据，让他向上转型为obj类型
                msg.obj = new String(buffer, 0, count, "utf-8");
                // 发送数据
                handler.sendMessage(msg);
            }

//            while (true) {
//                while ((json = bff.readLine()) != null) {
//                    Message message = new Message();
//                    message.obj = json;
//                    message.what = 1;
//                    handler.sendMessage(message);
//                    //说明接下来会接收到一个文件流
//                    if ("file".equals(json)) {
//                        FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/test.gif");
//                        int length;
//                        int fileSzie = 0;
//                        byte[] b = new byte[1024];
//                        // 2、把socket输入流写到文件输出流中去
//                        while ((length = inputStream.read(b)) != -1) {
//                            fos.write(b, 0, length);
//                            fileSzie += length;
//                            System.out.println("当前大小：" + fileSzie);
//                            //这里通过先前传递过来的文件大小作为参照，因为该文件流不能自主停止，所以通过判断文件大小来跳出循环
//                        }
//                        fos.close();
//                        message.obj = "文件:保存成功";
//                        message.what = 2;
//                        handler.sendMessage(message);
//                    }
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
