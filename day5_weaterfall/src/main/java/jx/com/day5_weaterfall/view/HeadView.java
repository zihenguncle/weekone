package jx.com.day5_weaterfall.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import jx.com.day5_weaterfall.R;

public class HeadView extends LinearLayout {
    private Context mcontext;
    public HeadView(Context context) {
        super(context);
        init();
    }

    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        init();
    }
    private void init(){
        View view = inflate(mcontext, R.layout.head,null);
        ImageView image = view.findViewById(R.id.head_image);
        final EditText text = view.findViewById(R.id.head_edit);
        text.setEms(20);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = text.getText().toString();
                if(!s.equals("")){
                    buttonClick.setButtonClick(s);
                }else {
                    Toast.makeText(mcontext,"输入不可为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        addView(view);
    }

    OnButtonClick buttonClick;
    public void setOnButtonClick(OnButtonClick onButtonClick){
        buttonClick = onButtonClick;
    }

    public interface OnButtonClick{
        void setButtonClick(String str);
    }
}
