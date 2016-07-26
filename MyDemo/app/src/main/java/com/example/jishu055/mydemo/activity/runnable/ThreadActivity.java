package com.example.jishu055.mydemo.activity.runnable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

public class ThreadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
    }

    public void runNow(View view) {

        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();

    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (ThreadActivity.class) {
                Log.e("Test", "enter thread1...");
                try {
                    ThreadActivity.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("Test", "thread1 is going on ....");
                Log.e("Test", "thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (ThreadActivity.class) {
                ThreadActivity.class.notify();
                try {
                    Thread.sleep(3000);
                    Log.e("Test", "thread2 sleep 3秒完毕...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("Test", "enter thread2....");
                Log.e("Test", "thread2 is going on....");
                Log.e("Test", "thread2 is over!!!");

            }
        }
    }

}
