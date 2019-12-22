package com.e.classworkvoli.Recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.classworkvoli.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{

    public EmployeeAdapter(Context context, List<EmployeeView> employeeViewList) {
        this.context = context;
        this.employeeViewList = employeeViewList;
    }

    Context context;
    List<EmployeeView> employeeViewList;

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_employee,parent,false);
        return new EmployeeViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeView employeeView = employeeViewList.get(position);
        holder.tvEmployeeId.setText(employeeView.getEmployeeId()+"");
        holder.tvEmployeeName.setText(employeeView.getEmployeeName());
        holder.tvEmployeeSalary.setText(employeeView.getEmployeeSalary());
        holder.tvEmployeeAge.setText(employeeView.getEmployeeAge()+"");

    }

    @Override
    public int getItemCount() {
        return employeeViewList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder{
        TextView tvEmployeeId,tvEmployeeName,tvEmployeeSalary,tvEmployeeAge;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmployeeId = itemView.findViewById(R.id.tvEmployeeId);
            tvEmployeeName = itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeeSalary = itemView.findViewById(R.id.tvEmployeeSalary);
            tvEmployeeAge = itemView.findViewById(R.id.tvEmployeeAge);
        }
    }
}
