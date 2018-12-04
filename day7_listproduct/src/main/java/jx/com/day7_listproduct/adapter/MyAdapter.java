package jx.com.day7_listproduct.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import jx.com.day7_listproduct.R;
import jx.com.day7_listproduct.bean.NewBean;

public class MyAdapter extends BaseAdapter {

    private List<NewBean.PostsBean> list;
    private Context mcontext;

    public MyAdapter(Context mcontext) {
        this.mcontext = mcontext;
        list = new ArrayList<>();
    }

    public void setMlist(List<NewBean.PostsBean> mlist) {
        list.clear();
        list.addAll(mlist);
        notifyDataSetChanged();
    }
    public void addMlist(List<NewBean.PostsBean> mlist) {
        list.addAll(mlist);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewBean.PostsBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item,parent,false);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.binddata(getItem(position));
        return convertView;
    }

    class ViewHolder{
        TextView excerpt,date;
        ImageView image;
        private ViewHolder(View con){
            excerpt = con.findViewById(R.id.text1);
            date = con.findViewById(R.id.text2);
            image = con.findViewById(R.id.image);
            con.setTag(this);
        }

        public void binddata(NewBean.PostsBean item) {
                excerpt.setText(item.getTitle());
                date.setText(item.getDate());
                ImageLoader.getInstance().displayImage(item.getCustom_fields().getThumb_c().get(0),image);
        }
    }

}
