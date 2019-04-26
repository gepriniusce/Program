package pr.tongson.time.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import pr.tongson.base.ui.activity.BaseActivity;
import pr.tongson.time.R;
import pr.tongson.time.ui.fragment.ItemFragment;
import pr.tongson.time.ui.fragment.dummy.DummyContent;

public class MainActivity extends BaseActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commitFragment(R.id.cl_content, new ItemFragment());
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, EditActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, PlanListActivity.class));
                break;
            default:
                break;
        }

    }
}
