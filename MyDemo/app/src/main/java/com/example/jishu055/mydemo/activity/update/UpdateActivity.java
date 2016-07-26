package com.example.jishu055.mydemo.activity.update;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.jishu055.mydemo.R;
import com.example.jishu055.mydemo.activity.BaseActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateActivity extends BaseActivity {
    //线上
//    public static final String API_HOST = "http://portal.oshop.cjwsc.com/portal/";
    //test3
    public static final String API_HOST = "http://portal.java.test3.cjwsc.com/";
    //本地
//    public static final String API_HOST = "http://portal.java.cjwsc.com/portal/";
    //检测版本信息
    public static final String CHECK_VERSION = API_HOST + "checkVersion/check.action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    /**
     * 检查更新
     */
    public void checkVersion(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String urlPath = new String(CHECK_VERSION);
                    Map<String, String> params = new HashMap<>();
                    params.put("appType", "android");
                    params.put("appVersion", getAppVersionName(UpdateActivity.this));
                    params.put("signatrue", sign(params));

                    Collection<String> keyset = params.keySet();
                    List<String> list = new ArrayList<>(keyset);
                    StringBuilder param = new StringBuilder("");

                    for (String key : list) {
                        String value = params.get(key);
                        if (!TextUtils.isEmpty(value)) {
                            param.append(key).append("=").append(value).append("&");
                        }
                    }
                    param.deleteCharAt(param.length() - 1);
                    //建立连接
                    URL url = new URL(urlPath);
                    HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                    //设置参数
                    httpConn.setDoOutput(true);   //需要输出
                    httpConn.setDoInput(true);   //需要输入
                    httpConn.setUseCaches(false);  //不允许缓存
                    httpConn.setRequestMethod("POST");   //设置POST方式连接
                    //设置请求属性
                    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
                    httpConn.setRequestProperty("Charset", "UTF-8");
                    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
                    httpConn.connect();
                    //建立输入流，向指向的URL传入参数
                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                    dos.writeBytes(param.toString());
                    dos.flush();
                    dos.close();
                    //获得响应状态
                    int resultCode = httpConn.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == resultCode) {
                        StringBuffer sb = new StringBuffer();
                        String readLine = new String();
                        BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                        while ((readLine = responseReader.readLine()) != null) {
                            sb.append(readLine).append("\n");
                        }
                        responseReader.close();
                        Log.e("Test", sb.toString());
                        JSONObject jsonObject = new JSONObject(sb.toString());
                        Log.e("Test", jsonObject.getJSONObject("data").getJSONObject("info").getString("downloadUrl"));
                        final String downloadUrl = jsonObject.getJSONObject("data").getJSONObject("info").getString("downloadUrl");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startDownLoad(downloadUrl);
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }


    /**
     * 获取应用版本名
     *
     * @param context
     * @return
     */
    public String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    private final static String SIGNKEY = "40287ae447680a6b0147680a6b580000";

    /**
     * 签名
     *
     * @param params
     * @return
     */
    public static String sign(Map<String, String> params) {
        params.put("timestamp", "" + System.currentTimeMillis());
        params.put("apiKey", SIGNKEY);
        //对key进行字典升序排序
        Collection<String> keyset = params.keySet();
        List<String> list = new ArrayList<>(keyset);
        Collections.sort(list);

        StringBuilder sb = new StringBuilder("");
        for (String key : list) {
            String value = params.get(key);
            if (!TextUtils.isEmpty(value)) {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return md5(sb.toString());
    }

    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    public void startDownLoad(String downloadUrl) {
        new DownloadApkManager(this, downloadUrl,
                this.getResources().getString(R.string.app_name)).beginUpdate();//开启下载线程
    }
}

