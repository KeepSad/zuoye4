package com.example.asus.seekbardiy;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.asus.seekbardiy.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AdViewSwitcher extends ViewSwitcher {

    private int mCutItem;
    private int loopTime;//循环时间
    private MyHandler myHandler;
    private ArrayList<String> arrayListAd;

    public AdViewSwitcher(Context context) {
        this(context, null);
    }

    public AdViewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData();
        initAnimation();
    }

    /**
     * 初始化一些变量
     */
    private void initData() {
        arrayListAd = new ArrayList<>();
        myHandler = new MyHandler(this);
    }

    /**
     * 给viewSwitch添加显示的view，可以自由设置，外部调用
     *
     * @param layoutId 自定义view的布局id
     */
    public void addView(final int layoutId) {
        setFactory(new ViewFactory() {
            @Override
            public View makeView() {
                return LayoutInflater.from(getContext()).inflate(layoutId, null);
            }
        });
    }

    /**
     * 初始化进入和出去动画
     */
    private void initAnimation() {
        setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_in));
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.translate_out));
    }



    /**
     * 设置数据源并展示view，外部调用
     *
     * @param mList
     * @param time
     */
    public void upDataListAndView(ArrayList<String> mList, int time) {
        mCutItem = 0;
        loopTime = time;
        if (mList == null)
            return;

        arrayListAd.clear();
        arrayListAd.addAll(mList);
        updataView(mList.get(0), getCurrentView(), mCutItem);
    }

    /**
     * 在当前view上设置数据
     *
     * @param text
     * @param view
     */
    private void updataView(String text, View view, final int mCutItem) {
        TextView textView = (TextView) view.findViewById(R.id.tv_text);
        textView.setText(text);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onClickItemListener) {
                    onClickItemListener.onClick(mCutItem);
                }
            }
        });
    }
    /**
     * 展示下一条广告
     */
    public void showNextView() {
        if(arrayListAd == null)
            return;

        if ( arrayListAd.size() <= 1)
            return;

        mCutItem = (mCutItem == (arrayListAd.size() - 1)) ? 0 : mCutItem + 1;
        updataView(arrayListAd.get(mCutItem), getNextView(), mCutItem);
        showNext();
    }

    /**
     * 启动轮播
     */
    public void startLooping() {
        if (null == arrayListAd || arrayListAd.size() < 2) {
            return;
        }
        myHandler.removeMessages(0);
        myHandler.sendEmptyMessageDelayed(0, loopTime);
    }

    /**
     * 停止轮播
     */
    public void stopLooping() {
        myHandler.removeMessages(0);
    }



    /**
     * @description 主线程Handler
     * @note 因为存在定时任务，并且TextSwitcherView持有Activity的引用
     * 所以这里采用弱引用，主要针对内存回收的时候Activity泄露
     **/
    private static class MyHandler extends Handler {

        private WeakReference<AdViewSwitcher> mRef;

        public MyHandler(AdViewSwitcher view) {
            mRef = new WeakReference<AdViewSwitcher>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AdViewSwitcher mView = this.mRef.get();
            mView.showNextView();//展示下一条广告，会调用shownext方法展示下一条广告
            mView.startLooping();//启动轮播，间隔后展示下一条
        }
    }


    public interface OnClickItemListener {
        void onClick(int position);
    }
    OnClickItemListener onClickItemListener;
    public void setOnClickListener(OnClickItemListener onClickListener) {
        this.onClickItemListener = onClickListener;
    }

}
