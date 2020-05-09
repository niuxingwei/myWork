package com.nxw.mywork;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG_EXIT = "退出";


    @Override
    protected void onCreate(Bundle savedInstanceState) {//重写Creat()方法
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//与相对应的布局页面关联

        View.OnClickListener choiceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.enter:
                        startActivity(new Intent(MainActivity.this, FunctionView.class));
                        Toast.makeText(MainActivity.this, "即将前往功能界面", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.quit:
                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.TAG_EXIT, true);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };
        Button enter = findViewById(R.id.enter);
        enter.setOnClickListener(choiceListener);
        Button quit = findViewById(R.id.quit);
        quit.setOnClickListener(choiceListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {//设置退出函数
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }
}
