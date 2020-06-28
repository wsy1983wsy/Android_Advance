package com.wsy.view_day01.view.slidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.wsy.view_day01.R;
import com.wsy.view_day01.util.SizeUtil;

public class SlidingMenu extends HorizontalScrollView {
    private ViewGroup menuContainer;
    private ViewGroup contentContainer;
    private int mMenuWidth;

    private Context mContext;

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
    }
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
}
