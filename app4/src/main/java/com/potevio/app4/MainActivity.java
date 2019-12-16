package com.potevio.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * Room的单独使用
 */
public class MainActivity extends AppCompatActivity {

    private Button btndd;
    private Button btnDelete;
    private Button btnUpdate;
    private Button btnQuery;
    private Button btnQueryOne;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnQuery = findViewById(R.id.btn_query);
        btnQueryOne = findViewById(R.id.btn_query_one);

        userDao = AppDataBase.getInstance(this).userDao();

        btndd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.insertAll(new User("fda", 20));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userDao.findById(1);
                userDao.delete(user);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userDao.findById(1);
                user.setName("lkh");
                userDao.update(user);
            }
        });
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = userDao.findAll();
                System.out.println(users);
            }
        });
        btnQueryOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = userDao.findById(1);
                System.out.println(user);
            }
        });
    }
}
