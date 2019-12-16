package com.potevio.app6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * LiveData&ViewModel
 */
public class MainActivity extends AppCompatActivity {

    private MyViewModel viewModel;
    private TextView tv;
    private EditText et;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn = findViewById(R.id.btn);


        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et.getText().toString();
                viewModel.changeName(name);
            }
        });

    }
}
