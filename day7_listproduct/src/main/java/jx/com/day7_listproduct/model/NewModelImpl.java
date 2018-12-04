package jx.com.day7_listproduct.model;

import android.os.AsyncTask;

import com.google.gson.Gson;

import jx.com.day7_listproduct.util.MyCallBack;
import jx.com.day7_listproduct.util.NetUtils;

public class NewModelImpl implements NewModel {
    private MyCallBack myCallBack;
    //Gson解析
    public <E> E getload(String myuri,Class clazz){
        E e = (E) new Gson().fromJson(NetUtils.getInstance().getload(myuri), clazz);
        return e;
    }
    @Override
    public void requestData(String uri, final Class clazz, final MyCallBack callBack) {
        myCallBack = callBack;
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                return getload(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                myCallBack.setData(o);
            }
        }.execute(uri);
    }
}
