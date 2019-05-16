package com.example.mvp;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author yangxu
 * @date 2019/4/13
 * 动态创建presenter
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends IBasePresenter> value();
}
