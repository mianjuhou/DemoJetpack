package com.potevio.app02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * LiveData + ViewModel + Room + RecyclerView + Paging
 * 使用Paging默认也要使用AsyncListDiffer
 */
public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private EditText etId;
    private EditText etName;
    private EditText etAge;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnUpdate;
    private RecyclerView rcv;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcv);
        etId = findViewById(R.id.et_id);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        adapter = new UserAdapter();
        rcv.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getUsers().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
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
