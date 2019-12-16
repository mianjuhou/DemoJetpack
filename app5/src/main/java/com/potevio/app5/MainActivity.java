package com.potevio.app5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Room&LiveData
 */
public class MainActivity extends AppCompatActivity {

    private Button btndd;
    private Button btnDelete;
    private Button btnUpdate;
    private UserDao userDao;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        tv = findViewById(R.id.tv);

        userDao = AppDataBase.getInstance(this).userDao();
        userDao.findAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                tv.setText(users.toString());
            }
        });

        btndd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.insertAll(new User("fda", 20));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(1);
                userDao.delete(user);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(2);
                user.setName("lkh");
                user.setAge(25);
                userDao.update(user);
            }
        });
    }
}
