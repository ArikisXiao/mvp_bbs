package com.mixotc.abbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mixotc.abbs.CircleImageView;
import com.mixotc.abbs.R;
import com.mixotc.abbs.home.bean.QaSummaryBean;

import java.util.List;

/**
 *    @author Sai
 *    e-mail : xiaosai@mixotc.com
 *    time   : 2018/07/05
 *    class note : Home页Latest 3 QAListView的适配器
 */
public class QuestionAdapter extends BaseAdapter {

    private static final int THOUSAND = 1000;
    private static final int HUNDRED = 100;
    private static final String STATE_FINISHED = "已采纳";
    private static final String STATE_UN_FINISH = "待回答";
    private Context mContext;
    private List<QaSummaryBean> mQaList;

    public QuestionAdapter(Context context, List<QaSummaryBean> qaList) {
        mContext = context;
        mQaList = qaList;
    }

    @Override
    public int getCount() {
        return mQaList != null ? mQaList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mQaList != null ? mQaList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final QaSummaryBean qaSummaryBean = mQaList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_qa, null);
            viewHolder = new ViewHolder();
            viewHolder.mCircleIvUserHeadIcon = convertView.findViewById(R.id.iv_home_qa_item_user_head);
            viewHolder.mTvUserNickName = convertView.findViewById(R.id.tv_home_qa_item_user_name);
            viewHolder.mTvQaDate = convertView.findViewById(R.id.tv_home_qa_item_date);
            viewHolder.mTvQaState = convertView.findViewById(R.id.tv_home_qa_item_state);
            viewHolder.mTvQaCredit = convertView.findViewById(R.id.tv_home_qa_item_credit);
            viewHolder.mTvQaTitle = convertView.findViewById(R.id.tv_home_qa_item_title);
            viewHolder.mTvQaClassify = convertView.findViewById(R.id.tv_home_qa_item_classify);
            viewHolder.mTvAnswerNum = convertView.findViewById(R.id.tv_home_qa_item_answer_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.mCircleIvUserHeadIcon.setImageResource(qaSummaryBean.getUserHeadImage());
        viewHolder.mTvUserNickName.setText(qaSummaryBean.getUserNickName());
        viewHolder.mTvQaDate.setText(qaSummaryBean.getQuestionDate());
        if (qaSummaryBean.isAnswerState()) {
            viewHolder.mTvQaState.setText(STATE_FINISHED);
        } else {
            viewHolder.mTvQaState.setText(STATE_UN_FINISH);
        }
        viewHolder.mTvQaCredit.setText(String.format("[%s积分]", qaSummaryBean.getQuestionCredit()));
        viewHolder.mTvQaTitle.setText(qaSummaryBean.getQuestionTitle());
        viewHolder.mTvQaClassify.setText(qaSummaryBean.getQuestionClassify());
        viewHolder.mTvAnswerNum.setText(String.format("回答:%s", convertNum(qaSummaryBean.getAnswerNum())));
        return convertView;
    }

    private class ViewHolder {
        private CircleImageView mCircleIvUserHeadIcon;
        private TextView mTvUserNickName;
        private TextView mTvQaDate;
        private TextView mTvQaState;
        private TextView mTvQaCredit;
        private TextView mTvQaTitle;
        private TextView mTvQaClassify;
        private TextView mTvAnswerNum;
    }

    /**
     * 转换过大的评论数和阅读数
     * @param num 数量
     * @return 转换后的数量
     */
    private String convertNum(String num) {
        double i = Integer.parseInt(num);
        if (i > THOUSAND) {
            i = i - i % HUNDRED;
            i = i / THOUSAND;
            return String.valueOf(i) + "k";
        } else {
            return num;
        }
    }
}
