package jx.com.day7_listproduct.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtils {
    public static NetUtils instance;
    public static NetUtils getInstance(){
        if(instance == null){
            instance = new NetUtils();
        }
        return instance;
    }





    //请求网络
    public String getload(String myuri){
        String result = "";
        try {
            URL url = new URL(myuri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            int responseCode = connection.getResponseCode();
            if(responseCode == 200){
                result = strean(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //转换字符
    public String strean(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        for (String tmp = br.readLine();tmp != null; tmp = br.readLine()) {
            builder.append(tmp);
        }
        return builder.toString();
    }

}
