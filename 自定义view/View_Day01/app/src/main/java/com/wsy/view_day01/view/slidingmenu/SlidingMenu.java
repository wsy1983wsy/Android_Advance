package com.wsy.view_day01.view.slidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.wsy.view_day01.R;
import com.wsy.view_day01.util.SizeUtil;

public class SlidingMenu extends HorizontalScrollView {
    private ViewGroup menuContainer;
    private ViewGroup contentContainer;
    private int mMenuWidth;
    GestureDetector gestureDetector;
    boolean menuIsOpened = false;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);
        float rightMargin = array.getDimension(R.styleable.SlidingMenu_menuRightMargin, SizeUtil.dp2px(context, 50));
        mMenuWidth = (int) (SizeUtil.getScreenWidth(context) - rightMargin);
        array.recycle();
        gestureDetector = new GestureDetector(context, gestureListener);
    }

    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // Bug  判断左右还是上下   只有左右快速滑动才切换
            if (Math.abs(velocityY) > Math.abs(velocityX)) {
                // 代表上下快速划  这个时候不做处理
                return super.onFling(e1, e2, velocityX, velocityX);
            }
            //只关注快速滑动，只要快速滑动就会回调
            //
            if (menuIsOpened) {
                if (velocityX < 0) {
                    closeMenu();
                    return true;
                }
            } else {
                if (velocityX > 0) {
                    openMenu();
                    return true;
                }
            }

            return super.onFling(e1, e2, velocityX, velocityX);
        }
    };
    //1.宽度不对（乱套了），指定宽高

    /**
     * xml文件解析完毕之后才会执行本方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //指定宽高
        ViewGroup container = (ViewGroup) getChildAt(0);
        if (container.getChildCount() > 2) {
            throw new RuntimeException("最多三个子View");
        }
        //1. 内容页的宽高
        menuContainer = (ViewGroup) container.getChildAt(0);
        ViewGroup.LayoutParams menuLayoutParams = menuContainer.getLayoutParams();
        menuLayoutParams.width = mMenuWidth;
        menuContainer.setLayoutParams(menuLayoutParams);
        //2. 指定菜单页的宽高
        contentContainer = (ViewGroup) container.getChildAt(1);
        ViewGroup.LayoutParams contentLayoutParams = contentContainer.getLayoutParams();
        contentLayoutParams.width = SizeUtil.getScreenWidth(getContext());
        contentContainer.setLayoutParams(contentLayoutParams);
        //默认关闭,不起作用
        //scrollTo(mMenuWidth, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(mMenuWidth, 0);
    }

    private boolean isIntercept;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        isIntercept = false;
        if (menuIsOpened) {
            float pointX = ev.getX();
            if (pointX > mMenuWidth) {
                closeMenu();
                isIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isIntercept) {//如果拦截了事件，则不做下面的操作
            return true;
        }
        if (gestureDetector.onTouchEvent(ev)) {//快速滑动触发，下面就不再执行了
            return true;
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            //手指抬起
            int scrollX = getScrollX();
            //向左移动超过了菜单的1/2，则认为需要关闭
            if (scrollX > (mMenuWidth / 2)) {
                //关闭
                closeMenu();
            } else {
                //打开
                openMenu();
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 关闭菜单，滚动到menuWidth位置
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        menuIsOpened = false;
    }

    /**
     * 打开菜单，滚动到0
     */
    private void openMenu() {
        smoothScrollTo(0, 0);
        menuIsOpened = true;
    }

    //4. 处理有点的缩放，左边的缩放和透明度，需要不断的获取当前滚动的位置


    @Override
    protected void onScrollChanged(int left, int t, int oldl, int oldt) {
        super.onScrollChanged(left, t, oldl, oldt);
        //算一个梯度值
        float scale = 1f * left / mMenuWidth;
        //右边的缩放，最小时0.7，最大时1.0f
        float rightScale = 0.7f + 0.3f * scale;
        //设置中心点
        contentContainer.setPivotX(0);
        contentContainer.setPivotY(contentContainer.getHeight() / 2);
        contentContainer.setScaleX(rightScale);
        contentContainer.setScaleY(rightScale);

        //菜单的缩放和透明度
        //透明是由半透明到完全透明 0.7 到 1.0
        float leftAlpha = 0.5f + (1 - scale) * 0.5f;
        menuContainer.setAlpha(leftAlpha);
        //缩放是由0.7到1.0
        float leftScale = 0.7f + (1 - scale) * 0.3f;
        menuContainer.setTranslationX(left * leftScale);
        menuContainer.setScaleX(leftAlpha);
        menuContainer.setScaleY(leftAlpha);
    }
}
