package jx.com.day7_listproduct.model;

import jx.com.day7_listproduct.util.MyCallBack;

public interface NewModel {
    void requestData(String uri, Class clazz, MyCallBack callBack);
}
