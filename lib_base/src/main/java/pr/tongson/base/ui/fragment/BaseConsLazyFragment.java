package pr.tongson.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pr.tongson.base.ui.fragment.i.IFragmentVisibleCallbak;

/**
 * <b>Create Date:</b> 2019/3/8<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseConsLazyFragment extends BaseConsFragment implements IFragmentVisibleCallbak {
    private Object lock;
    //分别表示当前Fragment是否可见,是否已准备(表示已经走过onCreateView方法)以及是否数据已加载
    //这里需要设置为true，因为在不使用ViewPager的情况下，会出问题，其次setUserVisibleHint的调用比onViewCreated早
    protected boolean isVisible = true;
    protected boolean isPrepared = false;
    protected boolean isLoaded = false;
    protected boolean isInitViewed = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lock = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            inflateView(inflater, container);
            //            if (!isInitViewed) {
            //                initView();
            //                isInitViewed = true;
            //            }
        }
        ViewGroup group = (ViewGroup) rootView.getParent();
        if (group != null) {
            group.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //只要走过这个方法就认为已经加载了根View了
        isPrepared = true;
//        L.i("Tongson " + getType());
        visLoad();
    }

    /**
     *
     */
    private void visLoad() {
        synchronized (lock) {
            //如果可见说明这个Fragment初次加载就是可见，应立即初始化布局
            if (isVisible) {
                //只有没有加载才去初始化View
                if (!isInitViewed) {
//                    L.i("Tongson --initView--" + getType());
                    initView();
                    isInitViewed = true;
                }
                //只有没有加载才去绑定数据
                if (!isLoaded) {
//                    L.i("Tongson --loadData--" + getType());
                    loadData();
                    isLoaded = true;
                }
            }
            lock.notify();
        }
    }

    @Override
    public void onFragmentVisible() {
//        L.i("Tongson " + getType());
    }

    @Override
    public void onFragmentInvisible() {
//        L.i("Tongson " + getType());
    }

    @Override
    public void onLoadedData() {
//        L.i("Tongson " + getType());
        if (!isPrepared) {
            return;
        }
        visLoad();
    }

    /**
     * 不提供覆写，需监听可见性的子类可覆写{@link #onFragmentVisible()}和{@link #onFragmentInvisible()}方法
     *
     * @param isVisibleToUser 当前Fragment的可见性，onCreateView之前调用
     */
    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        L.i("Tongson from:" + getType() + ",isVisibleToUser:" + isVisibleToUser);
        isVisible = isVisibleToUser;
        if (getUserVisibleHint()) {
            onLoadedData();
            onFragmentVisible();
        } else {
            onFragmentInvisible();
        }
    }

    protected String getType() {
        return "base";
    }

}
