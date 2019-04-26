package pr.tongson.time.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import pr.tongson.base.ui.activity.BaseActivity;
import pr.tongson.lib.greendao.entity.Plan;
import pr.tongson.time.R;
import pr.tongson.time.ui.fragment.PlanListFragment;

public class PlanListActivity extends BaseActivity implements PlanListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        commitFragment(R.id.cl_content, new PlanListFragment());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onListFragmentInteraction(Plan item, TextView view) {
        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop, null, false);
        if (mPopupWindow == null) {
            // 创建PopupWindow对象，其中：
            // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
            // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
            mPopupWindow = new PopupWindow(this);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setContentView(contentView);
            // 设置PopupWindow的背景
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置PopupWindow是否能响应外部点击事件
            mPopupWindow.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            mPopupWindow.setTouchable(true);
            // 设置PopupWindow是否获取焦点
            mPopupWindow.setFocusable(true);

            mPopupWindow.setAnimationStyle(R.style.AnimTranslate);
        }

        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        } else {
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int mShowMorePopupWindowWidth = -contentView.getMeasuredWidth();
            int mShowMorePopupWindowHeight = -contentView.getMeasuredHeight();
            mPopupWindow.showAsDropDown(view, mShowMorePopupWindowWidth, mShowMorePopupWindowHeight);
        }
    }

    PopupWindow mPopupWindow;
}
