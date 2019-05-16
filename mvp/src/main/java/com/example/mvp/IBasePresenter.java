package com.example.mvp;

/**
 * @author yangxu
 * @date 2019/4/13
 *
 * MVP Presenter 顶级接口
 */
public interface IBasePresenter<T extends IBaseView>{
    /**
     * view生命周期onCreate之后进行回调
     */
    void onViewCreate();
    /**
     * view生命周期Destroy进行回调
     */
    void onViewDestroy();
    /**
     * view和Presenter进行绑定
     */
    void attachView(T view);
    /**
     * view和Presenter解除绑定
     */
    void detachView();
    /**
     * 判断View和Presenter 是否绑定成功，返回false  View为null，不能进行回调
     */
    boolean isAttach();

}
