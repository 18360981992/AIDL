package com.example.aidlclientdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bean.IMyAidlInterface;
import com.example.bean.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IMyAidlInterface iMyAidlInterface;
    private Button btn;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Log.d("jba", "onCreate...");

        bindservice();

        //unbindService();
    }

    // 解绑
    private void unbindService() {
        unbindService(con);
    }

    // 绑定服务
    private void bindservice() {
        Intent intent = new Intent();
        intent.setAction("com.example.aidldemo.service");
        //不加这句话的话 android 5.0以上会报：Service Intent must be explitict
        intent.setPackage("com.example.aidldemo");
        bindService(intent, con, BIND_AUTO_CREATE);
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                try {
                    int nume = iMyAidlInterface.getNume();
                    Log.d("jba", "onclick nume = " + nume);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                try {
                    User user = iMyAidlInterface.getUser();
                    Log.d("jba", "onclick user = " + user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    private ServiceConnection con = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("jba", "服务绑定成功...");
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("jba", "服务绑定失败...");
        }
    };
}
