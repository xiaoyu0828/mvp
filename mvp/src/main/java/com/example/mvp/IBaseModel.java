package com.example.mvp;

/**
 * @author yangxu
 * @date 2019/4/13
 * MVP model 顶级接口
 */
public interface IBaseModel {
    //设置当前请求的tag
    void setTag(Object tag);
    //获取当前tag
    Object getTag();
}
