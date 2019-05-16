package com.example.mvp;


/**
 * @author yangxu
 * @date 2019/4/13
 *
 * 所有Model的基类
 */
public class XBaseModel implements IBaseModel {

    private Object mTag;

    @Override
    public void setTag(Object tag) {
        this.mTag=tag;
    }

    @Override
    public Object getTag() {
        return mTag;
    }
}
