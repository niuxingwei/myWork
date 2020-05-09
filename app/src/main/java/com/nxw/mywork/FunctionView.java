package com.nxw.mywork;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

import static com.nxw.mywork.GetData.getChineseName;
import static com.nxw.mywork.GetData.getTel;


public class FunctionView extends AppCompatActivity {
    int length = 15;//设置通讯录初始人员信息
    private String dataName[] = new String[length];//设置初始化姓名数组长度
    private String dataPh[] = new String[length];//设置初始化手机号数组长度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_function_view);
        List<Map<String, String>> list = initData(dataName, dataPh);//初始化数据;
//        ArrayAdapter<Map<String, String>> adapter = new ArrayAdapter<>(
//                FunctionView.this, R.layout.support_simple_spinner_dropdown_item, list);
        ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(adapter);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                FunctionView.this, R.layout.support_simple_spinner_dropdown_item, dataPh);
        listView.setAdapter(adapter);
//        //添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FunctionView.this, "第" + position + "条记录", Toast.LENGTH_SHORT).show();
            }
        });
        // 添加长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FunctionView.this);
                builder.setTitle("请选择拨打方式");
                builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = dataPh[position];
                        dial(phone);
                    }
                });
                builder.setNeutralButton("Dial", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phone = dataPh[position];
                        call(phone);
                    }
                });
                builder.show();
                return false;
            }
        });
//        listView.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                return false;
//
//
//
//        };
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Dial:

                        Toast.makeText(FunctionView.this, "You are using Dial", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Call:
//                        call();
                        Toast.makeText(FunctionView.this, "You are using Call", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        Button dial = findViewById(R.id.Dial);
        dial.setOnClickListener(myListener);
        Button call = findViewById(R.id.Call);
        call.setOnClickListener(myListener);

    }

    private void call(String data) {//实现call功能
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, Integer.parseInt("001"));
        } else {
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + data)));
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, String data) {
        switch (requestCode) {//判断是否需已获得权限
            case 001:
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    call(data);
                } else {
                    Toast.makeText(getBaseContext(), "You Need Allow The Permission To Run This App", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void dial(String data) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + data));//intent实现拨号功能
        try {//调试时用于捕获异常
            startActivity(intent);
        } catch (Exception e) {//输出异常
            System.err.println("测试出错" + e);
        }
    }

    public List initData(String name[], String tel[]) {//初始化信息
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            name[i] = getChineseName();
            tel[i] = getTel();
            map.put(name[i], tel[i]);
            list.add(map);
        }

        return list;
    }
}
