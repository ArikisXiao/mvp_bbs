package com.mixotc.abbs.dynamic;

import android.content.Context;
import com.mixotc.abbs.db.provider.DynamicProvider;
import com.mixotc.abbs.model.DynamicModel;
import com.mixotc.abbs.db.bean.DynamicInfoBean;
import java.util.List;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_HOT;
import static com.mixotc.abbs.dynamic.DynamicDetailsFragment.DYNAMIC_TYPE_NEW;

/**
 *    @author : xiaosai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/03
 *    class note :
 */
public class DynamicModelImpl implements DynamicModel {

    /** 根据用户ID加载关注的用户发布的动态 */
    @Override
    public void loadDynamicDataByID(Context context, long uid, LoadResultCallback<DynamicInfoBean> resultCallback) {
        DynamicProvider dynamicProvider = new DynamicProvider(context);
        List<DynamicInfoBean> resultList = dynamicProvider.getAllFriendDynamicById(uid);
        if (resultList != null) {
            resultCallback.onSucceed(resultList);
        } else {
            resultCallback.onFailed(1);
        }
        dynamicProvider.closeDb();
    }

    /** 根据Time倒序加载动态 */
    @Override
    public void loadDynamicDataByTimeDesc(Context context, LoadResultCallback<DynamicInfoBean> resultCallback) {
        DynamicProvider dynamicProvider = new DynamicProvider(context);
        List<DynamicInfoBean> resultList = dynamicProvider.getDynamicByType(DYNAMIC_TYPE_NEW);
        if (resultList != null) {
            resultCallback.onSucceed(resultList);
        } else {
            resultCallback.onFailed(2);
        }
        dynamicProvider.closeDb();
    }

    /** 根据热度倒序加载动态 */
    @Override
    public void loadDynamicDateByHotDesc(Context context, LoadResultCallback<DynamicInfoBean> resultCallback) {
        DynamicProvider dynamicProvider = new DynamicProvider(context);
        List<DynamicInfoBean> resultList = dynamicProvider.getDynamicByType(DYNAMIC_TYPE_HOT);
        if (resultList != null) {
            resultCallback.onSucceed(resultList);
        } else {
            resultCallback.onFailed(3);
        }
        dynamicProvider.closeDb();
    }


}
