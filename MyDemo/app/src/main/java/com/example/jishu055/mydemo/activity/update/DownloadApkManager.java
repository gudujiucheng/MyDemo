package com.example.jishu055.mydemo.activity.update;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


import com.example.jishu055.mydemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * apk文件下载管理器
 * Created by cjw-D08 on 2016/6/11.
 */
public class DownloadApkManager {

    //下载中
    private final int DOWNLOADING = 1;

    //下载完成
    private final int DOWNLOAD_FINISH = 2;

    //内存空间不足
    private final int STORAGE_SPACE_NOT_ENOUGH = 3;

    private final int NOTIFICATION_ID = 10010;//通知id

    private NotificationCompat.Builder mBuilder;
    private NotificationManager mManager;

    private String mDownloadUrl = "";//下载路径
    private String mApkName = "";
    private String mSavePath = "";//文件存储路径
    private int mProgress;//下载进度
    private boolean mCancelUpdate = false;//是否取消更新
    private Context mContext;

    private UpdateProgressDialog mProgressDialog;//进度弹窗

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case DOWNLOAD_FINISH://下载完成
                        // 通知栏进度条完成
                        mBuilder.setContentTitle("下载完成").setProgress(0, 0, false).setOngoing(false);
                        mManager.cancel(NOTIFICATION_ID);
                        //安装文件
                        installApk();
                        break;

                    case DOWNLOADING://下载中
                        //设置弹窗的进度条
                        mProgressDialog.setProgress(mProgress);
                        mProgressDialog.setProgressText(msg.obj.toString());

                        // 设置通知栏进度条进度
                        if (mProgress % 5 == 0) {
                            mBuilder.setProgress(100, mProgress, false);
                            mManager.notify(NOTIFICATION_ID, mBuilder.build());
                        }
                        break;

                    case STORAGE_SPACE_NOT_ENOUGH://存储空间不足
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        Toast.makeText(mContext, "SD 卡存储空间不足", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    /**
     * @param context
     * @param url     apk下载链接
     * @param name    app名称
     */
    public DownloadApkManager(Context context, String url, String name) {
        mContext = context;
        mApkName = name;
        mDownloadUrl = url;
        mManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 显示弹窗并开始下载
     */
    public void beginUpdate() {
        showDownloadDialog();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {

        mProgressDialog = new UpdateProgressDialog(mContext);
        mProgressDialog.setNegativeButton(new UpdateProgressDialog.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //清除指定通知
                mManager.cancel(NOTIFICATION_ID);
                dialog.dismiss();
                // 设置取消状态
                mCancelUpdate = true;
            }
        });
        mProgressDialog.show();

        // 下载文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {

        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("开始下载")
                .setOngoing(true).setContentTitle("正在下载...")
                .setProgress(100, 0, false);
        mManager.notify(NOTIFICATION_ID, mBuilder.build());

        // 启动新线程下载软件
        new downloadApkThread().start();
    }

    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    URL url = new URL(mDownloadUrl);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    long length = conn.getContentLength();
                    long freeSize = getSDFreeSize();

                    if (freeSize < length) {
                        // SD 卡存储空间不足
                        Message.obtain(mHandler, STORAGE_SPACE_NOT_ENOUGH).sendToTarget();
                        conn.disconnect();
                        return;
                    }

                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mApkName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置（当前进度除以总长度）
                        mProgress = (int) (((float) count / length) * 100);
                        // 更新进度
                        Message.obtain(mHandler, DOWNLOADING, mProgress + "%").sendToTarget();
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!mCancelUpdate);    // 点击取消就停止下载.

                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 取消下载对话框显示
            mProgressDialog.dismiss();
        }
    }

    /**
     * 获取 SD 卡的剩余空间
     */
    private long getSDFreeSize() {

        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());

        long blockSize = statFs.getBlockSize();
        long freeBlocks = statFs.getAvailableBlocks();

        return blockSize * freeBlocks;    //	单位 KB
    }

    /**
     * 安装 APK 文件
     */
    private void installApk() {

        File apkfile = new File(mSavePath, mApkName);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

}
