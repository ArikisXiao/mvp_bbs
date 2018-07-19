package com.mixotc.abbs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mixotc.abbs.R;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import java.util.List;

/**
 * @author Sai
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {

    private static final int TEN_THOUSANDS = 10000;
    private static final int THOUSAND = 1000;
    private Context mContext;
    private List<NewsSummaryBean> mTopNewsList;
    private List<MyHolder> mMyHolderList;
    private OnRecyclerItemClickListener mItemClickListener;

    /**
     * 构造函数
     * @param context context
     * @param newsList news list
     */
    public NewsAdapter(Context context, List<NewsSummaryBean> newsList) {
        mContext = context;
        mTopNewsList = newsList;
    }

    /**
     * 用于创建ViewHolder,使用LayoutInflater指定每个Item的XML
     * @param viewGroup ?
     * @param viewType viewType
     * @return MyHolder(view)
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View recyclerView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_home_news, viewGroup, false);
        return new MyHolder(recyclerView);
    }

    /**
     * 设置每个Item
     * @param myHolder myHolder
     * @param position Item序号
     */
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int position) {
        final NewsSummaryBean newsSummaryBean = mTopNewsList.get(position);
        myHolder.mTvTitle.setText(newsSummaryBean.getNewsTitle());
        myHolder.mTvReadingNum.setText(String.format("阅读:%s", convertNum(newsSummaryBean.getReadingNum())));
        myHolder.mTvCommentNum.setText(String.format("评论:%s", convertNum(newsSummaryBean.getNewsCommentNum())));
        myHolder.mIvNewsItemIcon.setImageResource(newsSummaryBean.getNewsImage());
        //为每个Item设置点击事件监听
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(myHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTopNewsList != null ? mTopNewsList.size() : 0;
    }

    /**
     * RecyclerView的点击事件回调接口
     */
    public interface OnRecyclerItemClickListener {
        /**
         * 当点击事件发生后，处理点击事件的接口
         * @param position 被点击的item的序号
         */
        void onItemClick(int position);
    }

    /**
     * 外部调用方法
     * @param listener item监听
     */
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 加快内存回收，在Fragment的onDestroy()中使用
     */
    public void onDestroy() {
        mTopNewsList.clear();
        mTopNewsList = null;
        if (mMyHolderList != null) {
            for (int i = 0, len = mMyHolderList.size(); i < len; i++) {
                mMyHolderList.get(i).itemView.setOnClickListener(null);
            }
            mMyHolderList.clear();
        }
        mMyHolderList = null;
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

    /**
     * 初始化每个NewsSummary的控件
     */
    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvReadingNum;
        private TextView mTvCommentNum;
        private ImageView mIvNewsItemIcon;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_home_news_item_title);
            mTvReadingNum = itemView.findViewById(R.id.tv_home_news_item_reading);
            mTvCommentNum = itemView.findViewById(R.id.tv_home_news_item_comment);
            mIvNewsItemIcon = itemView.findViewById(R.id.iv_home_news_item_image);
        }
    }
}
