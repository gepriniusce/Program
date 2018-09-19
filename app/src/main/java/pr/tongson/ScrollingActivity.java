package pr.tongson;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import pr.tongson.base.BaseActivity;

/**
 * https://developer.android.google.cn/training/system-ui/
 */
@SuppressLint("InlinedApi")
public class ScrollingActivity extends BaseActivity implements View.OnClickListener {
    View topView;
    View bottomView;
    Animation mHiddenTopAction;
    Animation mShowTopAction;
    Animation mHiddenBottomAction;
    Animation mShowBottomAction;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initAnim();

        initFullscreen();

        initContainer();
        hideContainer();
        setListener();
    }

    private void initAnim() {
        mShowTopAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenTopAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
        mShowTopAction.setDuration(500);
        mHiddenTopAction.setDuration(800);

        mShowBottomAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenBottomAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mShowBottomAction.setDuration(500);
        mHiddenBottomAction.setDuration(300);
    }

    private void setListener() {
        findViewById(R.id.tv_content).setOnClickListener(this);
    }


    private void initContainer() {
        topView = findViewById(R.id.container);
        bottomView = findViewById(R.id.bottom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    /*
View.SYSTEM_UI_FLAG_LOW_PROFILE：使状态栏和导航栏上的一些图标变暗不可见，以降低用户注意力的分散。单独设置该标志时，点击状态栏和导航栏即可使其重新恢复，并且清除这个标志。
View.SYSTEM_UI_FLAG_FULLSCREEN：隐藏非必要的UI，比如状态栏。隐藏状态栏后用户下滑即重新显示，当沉浸模式同时开启时，应用的可绘制区域扩大，下滑显示状态栏时，状态栏以半透明方式显示在app的上方。
View.SYSTEM_UI_FLAG_HIDE_NAVIGATION：隐藏底部的导航栏。正常情况下用户点击屏幕后导航栏即可重新出现，在沉浸模式下，用户上滑才会显示出来。
View.SYSTEM_UI_FLAG_IMMERSIVE：沉浸模式必须与以上至少一种标志合用才能生效，当用户把状态栏或者导航栏滑出来后即退出沉浸模式，该标志自动清除掉。
View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY：粘性沉浸模式与普通沉浸模式的区别在于两点，一是滑出来的状态栏或导航栏呈半透明状态。二是滑出来的状态栏或者导航栏短时间后会自动隐藏，该标志也不会自动清除。
*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_content:
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    if (actionBar.isShowing()) {
                        hideContainer();
                    } else {
                        showContainer();
                    }
                }
                break;
            default:
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showContainer() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
        topView.setVisibility(View.VISIBLE);
        topView.startAnimation(mShowTopAction);
        bottomView.setVisibility(View.VISIBLE);
        bottomView.startAnimation(mShowBottomAction);
        showNavigationBar();
        //showSystemUI();
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void hideContainer() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        topView.startAnimation(mHiddenTopAction);
        topView.setVisibility(View.GONE);
        bottomView.startAnimation(mHiddenBottomAction);
        bottomView.setVisibility(View.GONE);
        bottomView.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                initFullscreen();
            }
        }, 300);
    }


}