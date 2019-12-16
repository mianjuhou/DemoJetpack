package com.potevio.app10.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;

import android.os.Bundle;

import com.potevio.app10.R;
import com.potevio.app10.data.db.User;
import com.potevio.app10.databinding.ActivityMainBinding;

/**
 * LiveData + ViewModel + Room + RecyclerView + Paging + Databinding
 * 网络: Retrofit + RxJava + OkHttp
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserAdapter adapter;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new UserAdapter();
        binding.rcv.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getUsers().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });

        binding.btnAdd.setOnClickListener(v -> {
            String name = binding.etName.getText().toString();
            String age = binding.etAge.getText().toString();
            int ageInt = Integer.parseInt(age);
            viewModel.addUser(name, ageInt);
        });
        binding.btnDelete.setOnClickListener(v -> {
            String id = binding.etId.getText().toString();
            long idLong = Long.parseLong(id);
            viewModel.deleteUser(idLong);
        });
        binding.btnUpdate.setOnClickListener(v -> {
            String id = binding.etId.getText().toString();
            long idLong = Long.parseLong(id);
            String name = binding.etName.getText().toString();
            String age = binding.etAge.getText().toString();
            viewModel.updateUser(idLong, name, age);
        });
    }
}
