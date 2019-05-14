package com.hengmeng.hmkuaiyi.pro.util;

import android.util.Log;

import com.hengmeng.hmkuaiyi.pro.constant.FilePath;

import java.util.Date;

public class LogWriter {
    private static Builder builder;

    public static Builder init(String msg){
        return builder = new Builder(msg);
    }

    public static class Builder{
        private String msg;

        private Builder(String msg){
            this.msg = msg;
        }

        public Builder write(String fileName){
            Date date = new Date();
            FileWriterX writer = new FileWriterX();
            writer.write(FilePath.LOG_FILDER_PATH + fileName,
                    date.toString() + ":" + msg + "\n");

            return builder;
        }

        public Builder e(String tag){
            Log.e(tag,msg);

            return builder;
        }

        public Builder d(String tag){
            Log.d(tag,msg);

            return builder;
        }

        public Builder i(String tag){
            Log.i(tag,msg);

            return builder;
        }

        public Builder w(String tag){
            Log.w(tag,msg);

            return builder;
        }

        public Builder v(String tag){
            Log.v(tag,msg);

            return builder;
        }
    }
}
