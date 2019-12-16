package com.potevio.app3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * ViewModel单独使用
 * 对onSaveInstanceState功能进行扩充
 */
public class MainActivity extends AppCompatActivity {

    private Button btn;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    private MyViewModel viewModel;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        btn.setOnClickListener(v -> {
            MainActivity.start(MainActivity.this);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String name = et.getText().toString();
        viewModel.setName(name);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewModel.getName() != null) {
            et.setText(viewModel.getName() + "ViewModel添加");
        }
    }
}
