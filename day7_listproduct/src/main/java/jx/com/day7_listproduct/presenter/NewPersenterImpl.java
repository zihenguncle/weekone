package jx.com.day7_listproduct.presenter;

import jx.com.day7_listproduct.model.NewModelImpl;
import jx.com.day7_listproduct.util.MyCallBack;
import jx.com.day7_listproduct.view.IView;

public class NewPersenterImpl implements NewPersenter {

    private NewModelImpl newModel;
    private IView miView;

    public NewPersenterImpl(IView iView){
        miView = iView;
        newModel = new NewModelImpl();
    }


    @Override
    public void startRequest(String uri, Class clazz) {
        newModel.requestData(uri, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                miView.showRqquestData(data);
            }
        });
    }
    public void ondetach(){
        if (miView!=null){
            miView=null;
        }
        if(newModel!=null){
            newModel=null;
        }
    }
}
