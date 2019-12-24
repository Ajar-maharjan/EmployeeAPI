package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.model.Employee;
import com.e.classworkvoli.model.EmployeeCUD;
import com.e.classworkvoli.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDelete extends AppCompatActivity {

    EditText etEmpNumber, etEmpName, etEmpSalary, etEmppAge;
    Button btnUpdate, btnSearchForEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        etEmpNumber = findViewById(R.id.etEmpNumber);
        etEmpName = findViewById(R.id.etEmpName);
        etEmpSalary = findViewById(R.id.etEmpSalary);
        etEmppAge = findViewById(R.id.etEmppAge);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSearchForEdit = findViewById(R.id.btnSearchForEdit);
        btnDelete = findViewById(R.id.btnDelete);

        btnSearchForEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });
    }

    public void loadData() {
        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);
        Call<Employee> employeeCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNumber.getText().toString()));

        employeeCall.enqueue(new Callback<Employee>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {

                etEmpName.setText(response.body().getEmployee_name());
                etEmpSalary.setText(response.body().getEmployee_salary());
                etEmppAge.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Errors" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateEmployee() {
        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);
        EmployeeCUD employeeCUD = new EmployeeCUD(
                etEmpName.getText().toString(),
                Float.parseFloat(etEmpSalary.getText().toString()),
                Integer.parseInt(etEmppAge.getText().toString())
        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmpNumber.getText().toString()), employeeCUD);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Updated successfully!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Errors" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteEmployee() {
        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmpNumber.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
