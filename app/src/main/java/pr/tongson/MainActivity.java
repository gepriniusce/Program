package pr.tongson;

import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mContentView;
/*
通过Environment获取的

Environment.getDataDirectory().getPath() :                                      获得根目录/data (内部存储路径)
Environment.getDownloadCacheDirectory().getPath()  :               获得缓存目录/cache
Environment.getExternalStorageDirectory().getPath():                  获得SD卡目录/mnt/sdcard（获取的是手机外置sd卡的路径）
Environment.getRootDirectory().getPath() :                                     获得系统目录/system

通过Context获取的


Context.getDatabasePath()                                                      返回通过Context.openOrCreateDatabase 创建的数据库文件
Context.getCacheDir().getPath() :                                            用于获取APP的cache目录 /data/data/<application package>/cache目录
Context.getExternalCacheDir().getPath()  :                           用于获取APP的在SD卡中的cache目录/mnt/sdcard/Android/data/<application package>/cache
Context.getFilesDir().getPath()  :                                             用于获取APP的files目录 /data/data/<application package>/files
Context.getObbDir().getPath():                                                用于获取APP SDK中的obb目录 /mnt/sdcard/Android/obb/<application package>
Context.getPackageName() :                                                  用于获取APP的所在包目录
Context.getPackageCodePath()  :                                          来获得当前应用程序对应的 apk 文件的路径
Context.getPackageResourcePath() :                                   获取该程序的安装包路径
--------------------- 
作者：stevenzqzq 
来源：CSDN 
原文：https://blog.csdn.net/qq_26296197/article/details/51909423?utm_source=copy 
版权声明：本文为博主原创文章，转载请附上博文链接！


*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentView = findViewById(R.id.content);
        //        mContentView.setSystemUiVisibility(
        //                View.SYSTEM_UI_FLAG_LOW_PROFILE |
        //                        View.SYSTEM_UI_FLAG_FULLSCREEN |
        //                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
        //                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
        //                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
        //                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);


        TextView textView=findViewById(R.id.tv);
        StringBuilder stringBuilder=new StringBuilder();

        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsoluteFile());
        stringBuilder.append("\n");
        stringBuilder.append(Environment.getRootDirectory().getAbsoluteFile());
        stringBuilder.append("\n");
        stringBuilder.append(Environment.getDataDirectory().getAbsoluteFile());
          stringBuilder.append("\n");
        stringBuilder.append(getFilesDir().getAbsoluteFile());
        
        
        textView.setText(stringBuilder);


        
    }

   
}
