package pr.tongson.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * <b>Create Date:</b> 2018/8/24<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class BaseActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int fullOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public int hindNavigationBarOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public int showNavigationBarOptions = View.SYSTEM_UI_FLAG_VISIBLE;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void initFullscreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(fullOptions);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(hindNavigationBarOptions);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void showNavigationBar() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(showNavigationBarOptions);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    public void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
