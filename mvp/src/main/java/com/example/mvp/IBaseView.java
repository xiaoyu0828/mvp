package com.example.mvp;

/**
 * @author yangxu
 * @date 2019/4/13
 *
 * MVP View 顶级接口
 */
public interface IBaseView {

    /**
     * 显示loading加载弹窗，一般用于提交数据的时候展示
     * @param message loading 显示的信息
     */
    void showLoading(String message);
    /**
     * toast提示信息
     * @param errorMsg 信息内容
     */
    void showToast(String errorMsg);

    /**
     * 隐藏加载弹窗，当数据提交成功或者数据提交失败的时候。
     */
    void hideLoading();
}
