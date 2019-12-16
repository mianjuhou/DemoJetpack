package com.potevio.demojetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 单独使用LiveData
 */
public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;
    private Button btn2;
    private MutableLiveData<Integer> liveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        liveData = new MutableLiveData<>();
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer num) {
                tv.setText(num + "");
                System.out.println("" + num);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.postValue(10);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.start(MainActivity.this);
            }
        });
    }
}
