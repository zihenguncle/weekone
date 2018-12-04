package jx.com.day7_listproduct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jx.com.day7_listproduct.adapter.MyAdapter;
import jx.com.day7_listproduct.bean.NewBean;
import jx.com.day7_listproduct.presenter.NewPersenterImpl;
import jx.com.day7_listproduct.view.IView;
import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements IView{


    private XListView xListView;
    private int page;
    private NewPersenterImpl mNewPersenter;
    private MyAdapter adapter;

    private String str = "http://i.jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=1&custom_fields=thumb_c,views&dev=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = findViewById(R.id.main_xlistview);
        page = 1;
        /*xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);*/
        mNewPersenter = new NewPersenterImpl(MainActivity.this);

        adapter = new MyAdapter(MainActivity.this);
        xListView.setAdapter(adapter);
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                page=1;
                info();//xListView.setPullRefreshEnable(false);
            }

            @Override
            public void onLoadMore() {
               info();
            }
        });
        info();
    }

    public void info(){
        mNewPersenter.startRequest(str,NewBean.class);
    }


    @Override
    public void showRqquestData(Object data) {
        NewBean newBean = (NewBean) data;

        if(page == 1){
            adapter.setMlist(newBean.getPosts());
        }else {
            adapter.addMlist(newBean.getPosts());
        }
        page++;
        xListView.setPullRefreshEnable(false);
        xListView.setPullLoadEnable(false);
        /*if(newBean.getCount()>=COUNT){
            xListView.setPullLoadEnable(false);
        }*/
        //Toast.makeText(MainActivity.this,((NewBean) data).getStatus(),Toast.LENGTH_SHORT).show();
    }
}
