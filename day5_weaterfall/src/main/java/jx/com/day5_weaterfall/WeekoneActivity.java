package jx.com.day5_weaterfall;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import jx.com.day5_weaterfall.bean.DataBean;
import jx.com.day5_weaterfall.dao.DataDao;
import jx.com.day5_weaterfall.view.HeadView;
import jx.com.day5_weaterfall.view.HeadlineView;
import jx.com.day5_weaterfall.view.LabelView;

public class WeekoneActivity extends AppCompatActivity {

    private HeadView headView;
    private LabelView labelView,remen;
    private String[] contents = new String[]{"美 女", "女 神", "热 舞", "丰 满", "性 感", "知 性"};
    private DataDao dao;
    private ImageView laji;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekone);
        dao = new DataDao(this);
        headView = findViewById(R.id.headview);
        labelView = findViewById(R.id.labelview);
        laji = findViewById(R.id.laji);
        remen = findViewById(R.id.remen);
        final List<DataBean> select = dao.select();
        for (int i = 0; i < select.size(); i++) {
            TextView textView = new TextView(WeekoneActivity.this);
            textView.setText(select.get(i).getName());
            textView.setTextSize(18);
            textView.setPadding(15,15,15,15);
            textView.setTextColor(Color.RED);
            textView.setBackgroundResource(R.drawable.textstyle);
            labelView.addView(textView);
            final int index = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dao.del(select.get(index).getRandomnum());
                    labelView.removeView(v);
                }
            });
        }
        headView.setOnButtonClick(new HeadView.OnButtonClick() {
            @Override
            public void setButtonClick(String str) {
                String randomnum = UUID.randomUUID().toString();
                TextView textView = new TextView(WeekoneActivity.this);
                textView.setText(str);
                textView.setTag(randomnum);
                textView.setTextSize(18);
                textView.setPadding(15,15,15,15);
                textView.setTextColor(Color.RED);
                textView.setBackgroundResource(R.drawable.textstyle);
                labelView.addView(textView);
                dao.add(str,randomnum);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String randomnum = String.valueOf(v.getTag());
                        dao.del(randomnum);
                        labelView.removeView(v);
                    }
                });
            }
        });
        laji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WeekoneActivity.this);
                builder.setTitle("确定删除吗?");
                builder.setPositiveButton("狠心删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.delall();
                        labelView.removeAllViews();
                    }
                });
                builder.setNegativeButton("不小心点错了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
        for (int i = 0; i < contents.length; i++) {
            TextView textView = new TextView(WeekoneActivity.this);
            textView.setText(contents[i]);
            textView.setTextSize(18);
            textView.setPadding(15,15,15,15);
            textView.setTextColor(Color.RED);
            textView.setBackgroundResource(R.drawable.textstyle);
            remen.addView(textView);
        }
    }
}
