package pr.tongson.lib.greendao.entity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

import pr.tongson.lib.greendao.R;

/**
 * <b>Create Date:</b> 2019/2/10<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */

@Entity(
        //设置表名
        nameInDb = "T_PLAN_A_TABLE",
        //是否需要greenDao创建表，默认为true
        createInDb = true,
        //是否生成所有参数构造器
        generateConstructors = true,
        //如果没有提供get、set方法，是否生成，默认为true
        generateGettersSetters = true)
public class Plan {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String detial;
    /**
     * 0、1、2、3
     */
    private Integer typeA;
    /**
     * 0、1、2、3
     */
    private Integer typeB;
    /**
     * 是否重复
     */
    private boolean repeat;
    /**
     * 如果是重复
     * 哪天是重复的
     * 周 1\2\3\4\5\6\7
     */
    private String repeatDay;
    /**
     * 如果重复
     * 指定时间HHmm
     */
    private String time;
    /**
     * 如果不重复
     * 指定日期yyyyMMddHHmmss
     */
    private String date;

    /**
     * 0：没达成，空着
     * 1：达到目标
     */
    private int status;

    /**
     * 额外
     */
    private String extra;

    @Generated(hash = 758456488)
    public Plan(Long id, String title, String detial, Integer typeA, Integer typeB, boolean repeat, String repeatDay, String time, String date, int status, String extra) {
        this.id = id;
        this.title = title;
        this.detial = detial;
        this.typeA = typeA;
        this.typeB = typeB;
        this.repeat = repeat;
        this.repeatDay = repeatDay;
        this.time = time;
        this.date = date;
        this.status = status;
        this.extra = extra;
    }

    @Generated(hash = 592612124)
    public Plan() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetial() {
        return this.detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public Integer getTypeA() {
        return this.typeA;
    }

    public void setTypeA(Integer typeA) {
        this.typeA = typeA;
    }

    public Integer getTypeB() {
        return this.typeB;
    }

    public void setTypeB(Integer typeB) {
        this.typeB = typeB;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public String getRepeatDay() {
        return this.repeatDay;
    }

    public void setRepeatDay(String repeatDay) {
        this.repeatDay = repeatDay;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getTypeAString(Context context) {
        if (getTypeA() == null) {
            return "";
        }
        switch (getTypeA()) {
            case 1:
                return context.getString(R.string.plan_type_a_1);
            case 2:
                return context.getString(R.string.plan_type_a_2);
            case 3:
                return context.getString(R.string.plan_type_a_3);
            case 4:
                return context.getString(R.string.plan_type_a_4);
            default:
                return "";
        }
    }

    public String getTypeBString(Context context) {
        if (getTypeB() == null) {
            return "";
        }
        switch (getTypeB()) {
            case 1:
                return context.getString(R.string.plan_type_b_1);
            case 2:
                return context.getString(R.string.plan_type_b_2);
            case 3:
                return context.getString(R.string.plan_type_b_3);
            case 4:
                return context.getString(R.string.plan_type_b_4);
            default:
                return "";
        }
    }

    public StringBuilder getDateTime() {
        StringBuilder sb = new StringBuilder();
        if (getRepeat()) {
            sb.append(getRepeatDay());
            sb.append("\t");
            StringBuilder str = new StringBuilder(getTime());
            sb.append(str.insert(2, ":"));
            return sb;
        } else {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateCache = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                Date date = dateCache.parse(getDate());
                sb.append(getDateFormat(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return sb;
        }
    }

    @Override
    public String toString() {
        return "Plan{" + "id=" + id + ", title='" + title + '\'' + ", detial='" + detial + '\'' + ", typeA=" + typeA + ", typeB=" + typeB + ", repeat=" + repeat + ", repeatDay='" + repeatDay + '\'' + ", time='" + time + '\'' + ", date='" + date + '\'' + ", status=" + status + ", extra='" + extra + '\'' + '}';
    }

    public String getTimeFormat(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeShow = new SimpleDateFormat("HH:mm");
        return timeShow.format(date);
    }

    public String getDateFormat(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateShow = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateShow.format(date);
    }
}
