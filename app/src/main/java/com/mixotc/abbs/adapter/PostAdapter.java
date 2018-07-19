package com.mixotc.abbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mixotc.abbs.CircleImageView;
import com.mixotc.abbs.R;
import com.mixotc.abbs.home.bean.PostSummaryBean;
import java.util.List;

/**
 * @author Sai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/05
 *    class note : Home页Top 3 Post ListView的适配器
 */
public class PostAdapter extends BaseAdapter {

    private static final int TEN_THOUSANDS = 10000;
    private static final int THOUSAND = 1000;
    private Context mContext;
    private List<PostSummaryBean> mPostList;

    public PostAdapter(Context context, List<PostSummaryBean> postList) {
        mContext = context;
        mPostList = postList;
    }

    @Override
    public int getCount() {
        return mPostList != null ? mPostList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mPostList != null ? mPostList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        PostSummaryBean postSummaryBean = mPostList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_post, null);
            viewHolder = new ViewHolder();
            viewHolder.mIvUserHead = convertView.findViewById(R.id.iv_home_post_item_user_head);
            viewHolder.mTvUserNickName = convertView.findViewById(R.id.tv_home_post_item_user_name);
            viewHolder.mTvPostTitle = convertView.findViewById(R.id.tv_home_post_item_title);
            viewHolder.mTvPostClassify = convertView.findViewById(R.id.tv_home_post_item_classify);
            viewHolder.mTvReadingNum = convertView.findViewById(R.id.tv_home_post_item_reading);
            viewHolder.mTvCommentNum = convertView.findViewById(R.id.tv_home_post_item_comment);
            viewHolder.mTvPostDate = convertView.findViewById(R.id.tv_home_post_item_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mIvUserHead.setImageResource(postSummaryBean.getUserHeadImage());
        viewHolder.mTvUserNickName.setText(postSummaryBean.getUserNickName());
        viewHolder.mTvPostTitle.setText(postSummaryBean.getPostTitle());
        viewHolder.mTvPostClassify.setText(postSummaryBean.getPostClassify());
        viewHolder.mTvReadingNum.setText(String.format("阅读:%s", convertNum(postSummaryBean.getReadNum())));
        viewHolder.mTvCommentNum.setText(String.format("评论:%s", convertNum(postSummaryBean.getCommentNum())));
        viewHolder.mTvPostDate.setText(postSummaryBean.getPostDate());
        return convertView;
    }

    private class ViewHolder {
        private CircleImageView mIvUserHead;
        private TextView mTvUserNickName;
        private TextView mTvPostTitle;
        private TextView mTvPostClassify;
        private TextView mTvReadingNum;
        private TextView mTvCommentNum;
        private TextView mTvPostDate;
    }

    /**
     * 转换过大的评论数和阅读数
     * @param num 数量
     * @return 转换后的数量
     */
    private String convertNum(String num) {
        double i = Integer.parseInt(num);
        if (i > TEN_THOUSANDS) {
            i = i - i % THOUSAND;
            i = i / TEN_THOUSANDS;
            return String.valueOf(i) + "w";
        } else {
            return num;
        }
    }
}
