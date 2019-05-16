package com.example.mvp;



/**
 * @author yangxu
 * @date 2019/4/13
 * <p>
 * 所有persenter的基类
 */
public class XBasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    public V mView;
    private boolean isAttach;

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void attachView(V view) {
        mView = view;
        isAttach = true;
    }

    @Override
    public boolean isAttach() {
        return isAttach;
    }

    @Override
    public void detachView() {
        isAttach = false;
        mView = null;
        ModelManager.cancel(this);
    }

}
