package pr.tongson.time.ui.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pr.tongson.base.ui.activity.BaseActivity;
import pr.tongson.lib.greendao.control.PlanDbCotroller;
import pr.tongson.lib.greendao.entity.Plan;
import pr.tongson.time.R;

public class EditActivity extends BaseActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = "EditActivity";
    private EditText title;
    private EditText detail;
    private TextView tvdate;
    private TextView tvsubmit;
    private TimePickerDialog mTimePickerDialog;
    private DatePickerDialog mDatePickerDialog;

    private Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int dayOfMonth;
    private int hourOfDay;
    private int minute;
    private TextView tvtime;

    private Plan mPlanA;
    private android.widget.RadioButton rbtypea1;
    private android.widget.RadioButton rbtypea2;
    private android.widget.RadioButton rbtypea3;
    private android.widget.RadioButton rbtypea4;
    private android.widget.RadioGroup rgtypea;
    private android.widget.RadioButton rbtypeb1;
    private android.widget.RadioButton rbtypeb2;
    private android.widget.RadioButton rbtypeb3;
    private android.widget.RadioButton rbtypeb4;
    private android.widget.RadioGroup rgtypeb;
    private android.widget.CheckBox cbrepeat;
    private android.widget.CheckBox cbday1;
    private android.widget.CheckBox cbday2;
    private android.widget.CheckBox cbday3;
    private android.widget.CheckBox cbday4;
    private android.widget.CheckBox cbday5;
    private android.widget.CheckBox cbday6;
    private android.widget.CheckBox cbday7;
    private android.widget.LinearLayout llrepeatday;
    StringBuilder mStringBuilder = new StringBuilder("1234567");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.llrepeatday = (LinearLayout) findViewById(R.id.ll_repeat_day);
        this.cbday7 = (CheckBox) findViewById(R.id.cb_day_7);
        this.cbday6 = (CheckBox) findViewById(R.id.cb_day_6);
        this.cbday5 = (CheckBox) findViewById(R.id.cb_day_5);
        this.cbday4 = (CheckBox) findViewById(R.id.cb_day_4);
        this.cbday3 = (CheckBox) findViewById(R.id.cb_day_3);
        this.cbday2 = (CheckBox) findViewById(R.id.cb_day_2);
        this.cbday1 = (CheckBox) findViewById(R.id.cb_day_1);
        this.cbrepeat = (CheckBox) findViewById(R.id.cb_repeat);
        this.rgtypeb = (RadioGroup) findViewById(R.id.rg_type_b);
        this.rbtypeb4 = (RadioButton) findViewById(R.id.rb_type_b_4);
        this.rbtypeb3 = (RadioButton) findViewById(R.id.rb_type_b_3);
        this.rbtypeb2 = (RadioButton) findViewById(R.id.rb_type_b_2);
        this.rbtypeb1 = (RadioButton) findViewById(R.id.rb_type_b_1);
        this.rgtypea = (RadioGroup) findViewById(R.id.rg_type_a);
        this.rbtypea4 = (RadioButton) findViewById(R.id.rb_type_a_4);
        this.rbtypea3 = (RadioButton) findViewById(R.id.rb_type_a_3);
        this.rbtypea2 = (RadioButton) findViewById(R.id.rb_type_a_2);
        this.rbtypea1 = (RadioButton) findViewById(R.id.rb_type_a_1);
        this.tvtime = (TextView) findViewById(R.id.tv_title);
        this.tvsubmit = (TextView) findViewById(R.id.tv_submit);
        this.tvdate = (TextView) findViewById(R.id.tv_date);
        this.detail = (EditText) findViewById(R.id.detail);
        this.title = (EditText) findViewById(R.id.title);

        //设置默认值
        mPlanA = new Plan();
        mPlanA.setRepeat(true);
        mPlanA.setRepeatDay(mStringBuilder.toString());
        mPlanA.setStatus(0);

        setListener();

        tvsubmit.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        mTimePickerDialog = new TimePickerDialog(this, this, minute, seconds, true);
        mDatePickerDialog = new DatePickerDialog(this, this, year, monthOfYear, dayOfMonth);
    }

    private void setListener() {
        this.tvdate.setOnClickListener(this);
        this.tvtime.setOnClickListener(this);

        cbday1.setOnCheckedChangeListener(this);
        cbday2.setOnCheckedChangeListener(this);
        cbday3.setOnCheckedChangeListener(this);
        cbday4.setOnCheckedChangeListener(this);
        cbday5.setOnCheckedChangeListener(this);
        cbday6.setOnCheckedChangeListener(this);
        cbday7.setOnCheckedChangeListener(this);
        cbrepeat.setOnCheckedChangeListener(repeatCheck);
        this.rgtypea.setOnCheckedChangeListener(aCheck);
        this.rgtypeb.setOnCheckedChangeListener(bCheck);
    }

    private CompoundButton.OnCheckedChangeListener repeatCheck = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                mPlanA.setRepeat(true);
                llrepeatday.setVisibility(View.VISIBLE);
                tvdate.setVisibility(View.GONE);
            } else {
                mPlanA.setRepeat(false);
                tvdate.setVisibility(View.VISIBLE);
                llrepeatday.setVisibility(View.GONE);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener aCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = group.getCheckedRadioButtonId();
            Log.i(TAG, "id:" + id + "===?===" + "checkedId:" + checkedId);
            switch (checkedId) {
                case R.id.rb_type_a_1:
                    mPlanA.setTypeA(1);
                    break;
                case R.id.rb_type_a_2:
                    mPlanA.setTypeA(2);
                    break;
                case R.id.rb_type_a_3:
                    mPlanA.setTypeA(3);
                    break;
                case R.id.rb_type_a_4:
                    mPlanA.setTypeA(4);
                    break;
                default:
                    break;
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener bCheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id = group.getCheckedRadioButtonId();
            Log.i(TAG, "id:" + id + "===?===" + "checkedId:" + checkedId);
            switch (checkedId) {
                case R.id.rb_type_b_1:
                    mPlanA.setTypeB(1);
                    break;
                case R.id.rb_type_b_2:
                    mPlanA.setTypeB(2);
                    break;
                case R.id.rb_type_b_3:
                    mPlanA.setTypeB(3);
                    break;
                case R.id.rb_type_b_4:
                    mPlanA.setTypeB(4);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
                mTimePickerDialog.show();
                break;
            case R.id.tv_date:
                mDatePickerDialog.show();
                break;
            case R.id.tv_submit:
                mPlanA.setTitle(title.getText().toString());
                mPlanA.setDetial(detail.getText().toString());
                if (new PlanDbCotroller(this).save(mPlanA)) {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        setDate();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        setDate();
    }

    private void setDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateCache = new SimpleDateFormat("yyyyMMddHHmmss");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeCache = new SimpleDateFormat("HHmm");
        Date date = new Date(calendar.getTimeInMillis());
        mPlanA.setTime(timeCache.format(date));
        tvtime.setText(mPlanA.getTimeFormat(date));
        mPlanA.setDate(dateCache.format(date));
        tvdate.setText(mPlanA.getDateFormat(date));
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_day_1:
                if (isChecked) {
                    mStringBuilder.append(1);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("1"));
                }
                break;
            case R.id.cb_day_2:
                if (isChecked) {
                    mStringBuilder.append(2);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("2"));
                }
                break;
            case R.id.cb_day_3:
                if (isChecked) {
                    mStringBuilder.append(3);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("3"));
                }
                break;
            case R.id.cb_day_4:
                if (isChecked) {
                    mStringBuilder.append(4);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("4"));
                }
                break;
            case R.id.cb_day_5:
                if (isChecked) {
                    mStringBuilder.append(5);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("5"));
                }
                break;
            case R.id.cb_day_6:
                if (isChecked) {
                    mStringBuilder.append(6);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("6"));
                }
                break;
            case R.id.cb_day_7:
                if (isChecked) {
                    mStringBuilder.append(7);
                } else {
                    mStringBuilder.deleteCharAt(mStringBuilder.indexOf("7"));
                }
                break;
            default:
                break;
        }
        mPlanA.setRepeatDay(mStringBuilder.toString());
    }
}
