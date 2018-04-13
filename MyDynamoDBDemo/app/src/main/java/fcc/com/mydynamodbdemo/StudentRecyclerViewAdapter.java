package fcc.com.mydynamodbdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fcc.com.mydynamodbdemo.model.Student;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Student> mStudents;

    public StudentRecyclerViewAdapter(ArrayList<Student> students)
    {
        this.mStudents = students;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mNumView.setText(mStudents.get(position).getNumber());
        holder.mNameView.setText(mStudents.get(position).getName());
        holder.mMemoView.setText(mStudents.get(position).getMemo());
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mNumView;
        public final TextView mNameView;
        public final TextView mMemoView;

        public ViewHolder(View view) {
            super(view);
            mNumView = (TextView) view.findViewById(R.id.text_number);
            mNameView = (TextView) view.findViewById(R.id.text_name);
            mMemoView = (TextView)view.findViewById(R.id.text_status);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
