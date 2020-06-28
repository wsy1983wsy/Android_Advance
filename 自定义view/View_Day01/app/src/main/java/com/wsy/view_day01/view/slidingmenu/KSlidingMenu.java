package com.wsy.view_day01.view.slidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.wsy.view_day01.R;
import com.wsy.view_day01.util.SizeUtil;

public class KSlidingMenu extends HorizontalScrollView {

    private View mMenuView, mContentView;

    private int mMenuWidth;

    private Context mContext;

    private GestureDetector mGestureDetector; // 系统自带的手势处理类


    private boolean mMenuIsOpen = false;  // 当前是否打开

    public KSlidingMenu(Context context) {
        this(context, null);
    }

    public KSlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        mGestureDetector = new GestureDetector(mContext, new GestureDetectorListener());

        // 初始化自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        float rightMargin = array.getDimension(R.styleable.SlidingMenu_menuRightMargin, SizeUtil.dp2px(mContext, 50));
        mMenuWidth = (int) (SizeUtil.getScreenWidth(mContext) - rightMargin);

        array.recycle();
    }

    private class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("TAG", "velocityX -> " + velocityX);// 向右快速滑动会是正的  +   向左快速滑动 是  -
            // 如果菜单是打开的   向右向左快速滑动都会回调这个方法
            if (mMenuIsOpen) {
                if (velocityX < 0) {
                    toggleMenu();
                    return true;
                }
            } else {
                if (velocityX > 0) {
                    toggleMenu();
                    return true;
                }
            }
            return false;
        }
    }

    // 切换菜单
    private void toggleMenu() {
        if (mMenuIsOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    /*private class GestureDetectorListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            // 按下
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;// 单击
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;// 滚动
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // 长安
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // 快速滑动
            return false;
        }
    }*/


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 拦截处理事件
        if (mGestureDetector.onTouchEvent(ev)) {
            return mGestureDetector.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            // TODO 快速滑动处理
            // 怎样才算快速滑动   手指按下 和抬起  时间 距离  手势处理类
            case MotionEvent.ACTION_UP:
                int currentScrollX = getScrollX();
                // LogUtils.e("scrollX" , currentScrollX);
                if (currentScrollX < mMenuWidth / 2) {
                    // 打开菜单
                    openMenu();
                } else {
                    // 关闭菜单
                    closeMenu();
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int left, int t, int oldl, int oldt) {
        super.onScrollChanged(left, t, oldl, oldt);
        // 滚动的时候一直自动回调该方法  left  ScrollX
        //        LogUtils.e(l);  // start mMenuWidth - end 0

        // mMenuView.setTranslationX(-mMenuWidth); // nineoldandroids兼容低版本
        // ViewHelper.setTranslationX(mMenuView, l);// mMenuWidth - 0

        // 1.内容动画   只有一个缩放   1.0f - 0.7
        // 计算梯度值
        float scale = left * 1f / mMenuWidth;// scale 1f-0.0f
        float rightScale = 0.7f + 0.3f * scale;// 1.0 - 0.7
        // 设置缩放的中心点  兼容低版本动画 需要下一个动画的兼容包 nineoldandroid
        // Compat 大部分是兼容   LayoutInflaterCompat( 插件式换肤也是为了兼容 )
        mContentView.setPivotX(0);
        mContentView.setPivotY(mContentView.getHeight() / 2);
        mContentView.setScaleX(rightScale);
        mContentView.setScaleY(rightScale);

        // 2.菜单的动画    缩放  0.7 - 1.0    alpha  0.6 - 1.0f
        mMenuView.setTranslationX(left * 0.7f);
        float leftScale = 0.7f + 0.3f * (1 - scale);
        mMenuView.setScaleX(leftScale);
        mMenuView.setScaleY(leftScale);
        float leftAlpha = 0.6f + 0.4f * (1 - scale);
        mMenuView.setAlpha(leftAlpha);
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 这个方法代表整个布局加载完毕
        // 1.获取菜单和内容View
        // 这个获取的是 根布局  LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);

        // 2.给其指定宽度
        mMenuView = container.getChildAt(0);
        // 2.1 指定菜单的宽度 LayoutParams 是布局的一些属性

        mMenuView.getLayoutParams().width = mMenuWidth;

        mContentView = container.getChildAt(1);
        // 2.1 指定内容的宽度  指定宽高会重新摆放  onLayout() 方法
        mContentView.getLayoutParams().width = SizeUtil.getScreenWidth(mContext);

        // 3. 默认是关闭的  也就是说默认划过去
        // scrollTo(mMenuWidth,0);  靠折腾  靠蒙  靠猜
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 用来排放子布局的   等子View全部拜访完才能去滚动
        scrollTo(mMenuWidth, 0);
    }
}
