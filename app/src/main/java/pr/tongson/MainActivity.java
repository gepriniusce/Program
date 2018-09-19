package pr.tongson;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mContentView;

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
    }

   
}
