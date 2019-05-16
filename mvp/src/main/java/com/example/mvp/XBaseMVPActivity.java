package com.example.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * @author yangxu
 * @date 2019/4/11
 * 增加init方法
 */
public abstract class XBaseMVPActivity<P extends XBasePresenter> extends AppCompatActivity implements IBaseView,View.OnClickListener {

    private static final int CLICK_THRESHOLD = 200;//毫秒
    protected P mPresenter;
    private CreatePresenter mCreatePresenter;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XActivityManager.get().addActivity(this);
        initStatusBar();
        initPresenter();
        initView();
        initData();
        initEvent();
    }

    protected void initStatusBar(){
        if (getStatusBarImmerse()){
            XStatusBarUtil.setRootViewFitsSystemWindows(this,true);
            XStatusBarUtil.setTranslucentStatus(this);
        }
    }

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    private void initPresenter() {
        mCreatePresenter = this.getClass().getAnnotation(CreatePresenter.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XActivityManager.get().removeActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onViewDestroy();
        }
    }

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
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        getLoadingDialog().dismiss();
    }
    protected Dialog getLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(this);
        }
        return mLoadingDialog;
    }

    /**
     *
     * 沉浸式状态栏 默认返回false  不进行沉浸式状态栏，否则沉浸式状态栏
     */
    protected boolean getStatusBarImmerse(){
        return false;
    }

    @Override
    public void onClick(View v) {
        if (ClickUtils.isDoubleClick(getClickThreshold())){
            return;
        }
        onViewClick(v);
    }
    protected  int getClickThreshold(){
        return CLICK_THRESHOLD;
    }

    protected void onViewClick(View view){

    }


}
