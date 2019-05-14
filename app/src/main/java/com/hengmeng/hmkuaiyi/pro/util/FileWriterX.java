package com.hengmeng.hmkuaiyi.pro.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterX {
    private final String TAG = "FileWriterX";

    private OnWritingListener writingListener;

    private MyHandler myHandler;

    @SuppressLint("HandlerLeak")
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0: // onWritingStart
                    if (writingListener != null){
                        writingListener.onWritingStart();
                    }
                    break;

                case 1: // onWritingEnd
                    if (writingListener != null){
                        writingListener.onWritingEnd();
                    }
                    break;

                case 2: // onWritingError
                    if (writingListener != null){
                        writingListener.onWritingError();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public FileWriterX(){
        myHandler = new MyHandler();
    }

    public void write(final String path, final String text){
        // 截取目录path，创建目录
        String folderPath = path.substring(0,path.lastIndexOf("/")+1);
        File folder = new File(folderPath);
        if (! folder.exists()){
            boolean success = folder.mkdir();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();

                File file = new File(path);
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file,true);

                    // onWritingStart
                    msg.what = 0;
                    myHandler.sendMessage(msg);

                    writer.write(text);

                    // onWritingEnd
                    msg.what = 0;
                    myHandler.sendMessage(msg);
                } catch (FileNotFoundException e) {
                    // onWritingError
                    msg.what = 2;
                    myHandler.sendMessage(msg);

                    Log.e(TAG,"File Not Found!");
                    e.printStackTrace();
                } catch (IOException e) {
                    // onWritingError
                    msg.what = 2;
                    myHandler.sendMessage(msg);

                    Log.e(TAG,"Writing File IOException!");
                    e.printStackTrace();
                }finally {
                    if (writer != null){
                        try {
                            writer.close();
                        } catch (IOException e) {
                            Log.e("TAG","Close Stream IOException!");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void setOnWritingListener(OnWritingListener writingListener){
        this.writingListener = writingListener;
    }

    public interface OnWritingListener{
        void onWritingStart();

//        // 速度kb/s,每次写入的内容，已经写入的内容
//        void onWriting(long speed,String writtenTextAtTime,String writtenText);

        void onWritingEnd();

        void onWritingError();
    }
}
