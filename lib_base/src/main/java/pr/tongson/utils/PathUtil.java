package pr.tongson.utils;

import android.os.Environment;
import android.util.Log;

/**
 * <b>Create Date:</b> 2018/9/12<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class PathUtil {
    private static final String TAG = "Tongson";

    static {
        Log.d(TAG, Environment.getDataDirectory().getAbsolutePath());
        Log.d(TAG, Environment.getDataDirectory().getPath());
        Log.d(TAG, Environment.getDataDirectory().getName());

        Log.d(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d(TAG, Environment.getExternalStorageDirectory().getPath());
        Log.d(TAG, Environment.getExternalStorageDirectory().getName());

        Log.d(TAG, Environment.getDownloadCacheDirectory().getAbsolutePath());
        Log.d(TAG, Environment.getDownloadCacheDirectory().getPath());
        Log.d(TAG, Environment.getDownloadCacheDirectory().getName());

        Log.d(TAG, Environment.getRootDirectory().getAbsolutePath());
        Log.d(TAG, Environment.getRootDirectory().getPath());
        Log.d(TAG, Environment.getRootDirectory().getName());

    }
}
