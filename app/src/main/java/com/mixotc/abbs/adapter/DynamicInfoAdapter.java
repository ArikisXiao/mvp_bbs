package com.mixotc.abbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mixotc.abbs.R;
import com.mixotc.abbs.db.bean.DynamicInfoBean;
import java.util.List;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/12
 * class note : 动态信息的数据适配器
 */
public class DynamicInfoAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private List<DynamicInfoBean> mDynamicInfoBeanList;
    private ClickCallBack mClickCallBack;

    public DynamicInfoAdapter(Context context, List<DynamicInfoBean> resultList, ClickCallBack clickCallBack) {
        mContext = context;
        mDynamicInfoBeanList = resultList;
        mClickCallBack = clickCallBack;
    }

    @Override
    public int getCount() {
        return mDynamicInfoBeanList != null ? mDynamicInfoBeanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDynamicInfoBeanList != null ? mDynamicInfoBeanList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        DynamicInfoBean dynamicInfoBean = mDynamicInfoBeanList.get(position);
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(mContext).inflate(R.layout.item_dynamic_info, null);
            viewHolder = new ViewHolder();
            viewHolder.mInfo = convertView.findViewById(R.id.tv_dynamic_item_info);
            viewHolder.mUserNickName = convertView.findViewById(R.id.tv_dynamic_user_nickname);
            viewHolder.mUserHead = convertView.findViewById(R.id.iv_dynamic_user_head);
            viewHolder.mDate = convertView.findViewById(R.id.tv_dynamic_date);
            viewHolder.mAppreciate = convertView.findViewById(R.id.iv_dynamic_item_appreciate);
            viewHolder.mAppreciateStr = convertView.findViewById(R.id.tv_dynamic_item_appreciate_num);
            viewHolder.mComment = convertView.findViewById(R.id.tv_dynamic_item_comment_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mUserHead.setImageResource(dynamicInfoBean.getUserHead());
        viewHolder.mUserNickName.setText(dynamicInfoBean.getUserNickName());
        viewHolder.mDate.setText(dynamicInfoBean.getDate());
        viewHolder.mInfo.setText(dynamicInfoBean.getDynamicInfo());
        if (dynamicInfoBean.getAppreciateNum() == 0) {
            viewHolder.mAppreciateStr.setText("");
        } else if (dynamicInfoBean.getAppreciateNum() == 1 && dynamicInfoBean.getIsAppreciate()) {
            viewHolder.mAppreciate.setImageResource(R.drawable.ic_like_fill);
            viewHolder.mAppreciateStr.setText("我觉得很赞");
        } else if (dynamicInfoBean.getIsAppreciate()) {
            viewHolder.mAppreciate.setImageResource(R.drawable.ic_like_fill);
            viewHolder.mAppreciateStr.setText(String.format("我和%s人觉得很赞",
                    String.valueOf(dynamicInfoBean.getAppreciateNum() - 1)));
        } else {
            viewHolder.mAppreciate.setImageResource(R.drawable.ic_like);
            viewHolder.mAppreciateStr.setText(String.format("%s人觉得很赞",
                    String.valueOf(dynamicInfoBean.getAppreciateNum())));
        }
        viewHolder.mComment.setText(String.valueOf(dynamicInfoBean.getCommentNum()));
        return convertView;
    }

    @Override
    public void onClick(View view) {
        mClickCallBack.click(view);
    }

    public interface ClickCallBack {
        void click(View view);
    }

    private class ViewHolder {
        private TextView mUserNickName;
        private ImageView mUserHead;
        private TextView mDate;
        private TextView mInfo;
        private ImageView mAppreciate;
        private TextView mAppreciateStr;
        private TextView mComment;
    }
}
