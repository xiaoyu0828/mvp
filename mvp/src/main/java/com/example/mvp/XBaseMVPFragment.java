package com.example.mvp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * @author yangxu
 * @date 2019/4/16
 */
public abstract class XBaseMVPFragment<P extends XBasePresenter> extends Fragment implements IBaseView{

    protected P mPresenter;
    private CreatePresenter mCreatePresenter;
    private Dialog mLoadingDialog;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutId(),container,false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPresenter();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
            mPresenter.onViewDestroy();
        }
    }

    private void initPresenter() {
        mCreatePresenter = getClass().getAnnotation(CreatePresenter.class);
        if (mCreatePresenter != null) {
            Class<P> pClass = (Class<P>) mCreatePresenter.value();
            try {
                mPresenter= pClass.newInstance();
                mPresenter.attachView(this);
                mPresenter.onViewCreate();
                onPresenterCreate();
            } catch (Exception e) {
                throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
            }
        }

    }
    public <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }
    protected abstract int getLayoutId();
    //初始化view
    protected abstract void initView();

    //初始化data
    protected abstract void initData();


    protected abstract void initEvent();

    /**
     * 回调此方法，presenter创建完毕
     */
    protected void onPresenterCreate() {

    }

    @Override
    public void showLoading(String message) {
        getLoadingDialog().show();
    }

    @Override
    public void showToast(String errorMsg) {
        Toast.makeText(getContext(),errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        getLoadingDialog().dismiss();
    }
    protected Dialog getLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(getContext());
        }
        return mLoadingDialog;
    }
}
