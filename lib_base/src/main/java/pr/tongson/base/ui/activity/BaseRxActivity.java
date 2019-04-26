//package pr.tongson.base.ui.activity;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//
//import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
//
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
///**
// * <b>Create Date:</b> 2019-04-26<br>
// * <b>Email:</b> 289286298@qq.com<br>
// * <b>Description:</b>  <br>
// *
// * @author tongson
// */
//public class BaseRxActivity extends RxAppCompatActivity {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    public void commitFragment(int idRes, Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(idRes, fragment);
//        ft.commitAllowingStateLoss();a
//    }
//
//}
