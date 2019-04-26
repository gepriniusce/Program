package pr.tongson.base.ui.fragment;

import android.app.Activity;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <b>Create Date:</b> 2018/12/25<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseConsFragment extends Fragment {
    protected View rootView;

    protected void inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(getLayoutId(), container, false);
        rootView = view;
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void loadData();

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        if (rootView == null) {
            return null;
        }
        return rootView.findViewById(id);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
