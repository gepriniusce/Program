package pr.tongson.lib.greendao.control;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.List;

import pr.tongson.lib.greendao.dao.PlanDao;
import pr.tongson.lib.greendao.entity.Plan;
import pr.tongson.lib.greendao.manager.Manager;

/**
 * <b>Create Date:</b> 2019/2/12<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class PlanDbCotroller {
    private PlanDao mPlanDao;

    public PlanDbCotroller(Context context) {
        if (context instanceof Activity) {
            context = context.getApplicationContext();
        }
        mPlanDao = Manager.getInstance(context).getSession().getPlanDao();
    }

    public boolean save(Plan plan) {
        long size = mPlanDao.insert(plan);
        return size > 0;
    }

    public List<Plan> getPlanList() {
        List<Plan> list = mPlanDao.queryBuilder().orderDesc(PlanDao.Properties.Id).list();
        return list;
    }

}
