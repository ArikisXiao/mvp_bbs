package com.mixotc.abbs.dynamic.publish;

import android.content.Context;

import com.mixotc.abbs.db.bean.DynamicInfoBean;
import com.mixotc.abbs.db.provider.DynamicProvider;
import com.mixotc.abbs.model.PublishDynamicModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/17
 * describe : PublishDynamicModel的实现类
 * version : 1.0
 */
public class PublishDynamicModelImpl implements PublishDynamicModel {

    @Override
    public void publish(Context context, DynamicInfoBean dynamic) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String dateTime = formatter.format(curDate);
        dynamic.setDate(dateTime);
        DynamicProvider dynamicProvider = new DynamicProvider(context);
        dynamicProvider.insertContacts(dynamic);
    }
}
