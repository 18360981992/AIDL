package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.bean.IMyAidlInterface;
import com.example.bean.User;


public class MyService extends Service {

    private MyIbinder myIbinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if(myIbinder == null){
            myIbinder = new MyIbinder();
        }
        return myIbinder;
    }

    class MyIbinder extends IMyAidlInterface.Stub{

        @Override
        public int getNume() throws RemoteException {
            return 10;
        }

        @Override
        public User getUser() throws RemoteException {
            return new User("张三", 25);
        }
    }

}
