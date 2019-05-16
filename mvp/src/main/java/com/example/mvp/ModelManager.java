package com.example.mvp;


import java.util.HashMap;

/**
 * @author yangxu
 * @date 2019/4/13
 *
 * MVP  model 的管理类
 */
public class ModelManager {
    //用户缓存model 数据
    private static HashMap<Class<? extends IBaseModel>, IBaseModel> mCache = new HashMap<>();
    /**
     * 传递类引用字符串，反射获得该类对象
     *
     * @param token 类引用字符串
     */
    public static <T extends IBaseModel> T getModel(Class<T> token, Object tag) {

       T model = (T) mCache.get(token);
        try {
            if (model == null) {
                model = token.newInstance();
                mCache.put(token, model);
            }
            model.setTag(tag);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  model;
    }

    public static void cancel(Object tag){
       // RMHttpClient.get().cancel(tag);
    }
}
