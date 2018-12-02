package jx.com.day5_weaterfall.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import jx.com.day5_weaterfall.bean.DataBean;
import jx.com.day5_weaterfall.sql.MySqliteHelper;

public class DataDao {

    private SQLiteDatabase base;
    public DataDao(Context context){
        MySqliteHelper mySqliteHelper = new MySqliteHelper(context);
        base = mySqliteHelper.getReadableDatabase();
    }

    //添加
    public void add(String name,String randomnum){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("randomnum",randomnum);
        base.insert("data",null,values);
    }

    //查询
    public List<DataBean> select(){
        List<DataBean> list= new ArrayList<>();
        Cursor query = base.query("data", null, null, null, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex("name"));
            String randomnum = query.getString(query.getColumnIndex("randomnum"));
            DataBean dataBean = new DataBean(name, randomnum);
            list.add(dataBean);
        }
        return list;
    }

    //条件删除
    public void del(String randomnum){
        base.delete("data","randomnum = ?",new String[]{randomnum});
    }

    //删除所有
    public void delall(){
        base.delete("data",null,null);
    }


}
