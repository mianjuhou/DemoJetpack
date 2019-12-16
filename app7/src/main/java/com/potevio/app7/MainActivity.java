package com.potevio.app7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * LiveData&ViewModel&Room
 */
public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel;
    private TextView tv;
    private EditText etId;
    private EditText etName;
    private EditText etAge;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);

        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                tv.setText(users.toString());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                int ageInt = Integer.parseInt(age);
                viewModel.addUser(name, ageInt);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                long idLong = Long.parseLong(id);
                viewModel.deleteUser(idLong);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                long idLong = Long.parseLong(id);
                String name = etName.getText().toString();
                String age = etAge.getText().toString();
                viewModel.updateUser(idLong, name, age);
            }
        });

    }
}
