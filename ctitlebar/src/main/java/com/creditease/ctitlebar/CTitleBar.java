package com.creditease.ctitlebar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.creditease.ctitlebar.support.StringUtil;
import com.creditease.ctitlebar.support.UIStatusBarUtil;

/**
 * Created by zhxh on 2019/07/31
 */
public class CTitleBar extends LinearLayout {
    private View llRoot; // 整个title布局
    private View statusBarInsert; // 状态栏
    private RelativeLayout titleLayout; // 整个title布局

    private TextView centerTitleTV; // title

    public CTitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public CTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.title_bar_layout, this);
        llRoot = findViewById(R.id.llRoot);
        statusBarInsert = findViewById(R.id.statusBarInsert);
        titleLayout = findViewById(R.id.titleLayout);
        centerTitleTV = findViewById(R.id.centerTitleTV);

        // 根据属性值设置TitleBar
        if (attrs != null) {
            setViewByAttrs(context, attrs);
        }
    }


    /**
     * 方法描述：根据属性值设置TitleBar
     *
     * @author renmeng
     * @time 2016/8/26 14:15.
     */
    private void setViewByAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CTitleBar);
        // title
        String tmp = a.getString(R.styleable.CTitleBar_CTitlebarTitle);
        if (!StringUtil.isEmpty(tmp)) {
            setTitleTextView(tmp);
        }


        boolean isFullScreen = a.getBoolean(R.styleable.CTitleBar_CTitlebarFullScreen, false);
        setStatusBar(context, isFullScreen);
    }

    /**
     * 方法描述：titleBar是否透明可见
     *
     * @author zhxh
     * @time 2017/07/30
     */
    public void setStatusBar(Context context, boolean isFullScreen) {
        if (isFullScreen) {
            if (context instanceof Activity) {
                UIStatusBarUtil.translucent((Activity) context);
                UIStatusBarUtil.setStatusBarLightMode((Activity) context);
            }
            statusBarInsert.setVisibility(VISIBLE);
            UIStatusBarUtil.setStatusBarPaddingAndHeightInsertView(statusBarInsert);
        } else {
            statusBarInsert.setVisibility(GONE);
        }
    }

    /**
     * 方法描述：获取到titleBar
     *
     * @author zhxh
     * @time 2017/08/05
     */
    public View getStatusBar() {
        return statusBarInsert;
    }

    public void setTitleTextView(String title) {
        centerTitleTV.setText(title);
        centerTitleTV.setVisibility(View.VISIBLE);
    }
}
