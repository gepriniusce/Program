package pr.tongson.time.ui.fragment;

import android.content.Context;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pr.tongson.lib.greendao.entity.Plan;
import pr.tongson.time.R;
import pr.tongson.time.ui.fragment.PlanListFragment.OnListFragmentInteractionListener;
import pr.tongson.time.ui.fragment.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlanRecyclerViewAdapter extends RecyclerView.Adapter<MyPlanRecyclerViewAdapter.ViewHolder> {

    private List<Plan> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyPlanRecyclerViewAdapter(List<Plan> list, OnListFragmentInteractionListener listener) {
        mValues = list;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_plan, parent, false);
        TextView tvoperation = (TextView) view.findViewById(R.id.tv_operation);
        TextView tvtime = (TextView) view.findViewById(R.id.tv_time);
        TextView tvcontent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvtitle = (TextView) view.findViewById(R.id.tv_title);
        Guideline gl = (Guideline) view.findViewById(R.id.gl);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.aTypeTV.setText(holder.mItem.getTypeAString(holder.mContext));
        holder.bTypeTV.setText(holder.mItem.getTypeBString(holder.mContext));
        holder.mTitleTV.setText(holder.mItem.getTitle());

        holder.mContentTV.setText(holder.mItem.getDetial());

        holder.mTimeTV.setText(holder.mItem.getDateTime());

        holder.mOperationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem,holder.mOperationTV);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentTV;
        public final TextView mTimeTV;
        public final TextView mTitleTV;
        public final TextView mOperationTV;
        public final TextView bTypeTV;
        public final TextView aTypeTV;
        public Plan mItem;
        public Context mContext;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContext = view.getContext();
            aTypeTV = (TextView) view.findViewById(R.id.tv_type_a);
            bTypeTV = (TextView) view.findViewById(R.id.tv_type_b);
            mContentTV = (TextView) view.findViewById(R.id.tv_content);
            mTimeTV = (TextView) view.findViewById(R.id.tv_time);
            mTitleTV = (TextView) view.findViewById(R.id.tv_title);
            mOperationTV = (TextView) view.findViewById(R.id.tv_operation);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTimeTV.getText() + "'";
        }
    }
}
