package pr.tongson.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author tongson
 */
public class MyService extends Service {


    private static final String TAG = "Tongson";

    public MyService() {
        Log.i(TAG, "MyService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //                AlertDialog dialog = new AlertDialog.
                //                        Builder(getApplicationContext()).
                //                        setTitle("title").
                //                        setMessage("这是一个由service弹出的对话框").
                //                        setCancelable(false).
                //                        setPositiveButton("button confirm", new DialogInterface.OnClickListener() {
                //                            @Override
                //                            public void onClick(DialogInterface dialog, int id) {
                //                            }
                //                        }).create();
                //                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                //                }
                //                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
                //                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                //                dialog.show();

                //    android.view.WindowManager$BadTokenException: Unable to add window android.view.ViewRootImpl$W@db3ea7e -- permission denied for window type 2003
                //                Dialog dialog = new Dialog(getApplicationContext());
                //                dialog.setContentView(R.layout.layout_dialog);
                //                TextView tipsTv = dialog.findViewById(R.id.tv_tips);
                //                tipsTv.setText("试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。试一下。");
                //                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                //                }
                //                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
                //                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                //                dialog.show();

                //                final String[] items = new String[]{"北京", "上海", "广州", "深圳"};
                //                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //                builder.setIcon(R.mipmap.ic_launcher).setTitle("提示").setItems(items, new DialogInterface.OnClickListener() {
                //
                //                    @Override
                //                    public void onClick(DialogInterface dialog, int which) {
                //                        // TODO Auto-generated method stub
                //                        Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                //                    }
                //                });
                //                AlertDialog dialog = builder.create();
                //                dialog.show();
            }
        }, 3 * 1000);


        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                Log.i(TAG, "Handler postDelayed Runnable run");
        //
        //                if (mActivityRunnable != null) {
        //                    mActivityRunnable.run();
        //                }
        //            }
        //        }, 3 * 1000);
    }

    private DialogBinder mDialogBinder;
    private Runnable mActivityRunnable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        mDialogBinder = new DialogBinder();
        return mDialogBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    public class DialogBinder extends Binder {
        public void showDialog(Runnable runnable) {
            Log.i(TAG, "showDialog");
            mActivityRunnable = runnable;
            Log.i(TAG, "mActivityRunnable:" + mActivityRunnable);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Handler postDelayed Runnable run");

                    if (mActivityRunnable != null) {
                        mActivityRunnable.run();
                    }
                }
            }, 3 * 1000);
        }
    }
}
