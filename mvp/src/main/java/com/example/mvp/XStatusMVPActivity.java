package com.example.mvp;

/**
 * @author yangxu
 * @date 2019/4/11
 * 沉浸式状态栏继承
 */
public abstract class XStatusMVPActivity<P extends XBasePresenter> extends XBaseMVPActivity<P> {

    @Override
    protected boolean getStatusBarImmerse() {
        return true;
    }
}
