package com.example.frag1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.broadcast.MyAIDLInterface;

public class MainActivity extends AppCompatActivity {


    private MyAIDLInterface myAIDLInterface;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
                myAIDLInterface = MyAIDLInterface.Stub.asInterface(service);
                try{
                    int result = myAIDLInterface.plus(50,50);
                    String upperStr = myAIDLInterface.toUpperCase("come from ClientTest");
                    Log.d("TAG","result from clients is " + result);
                    Log.d("TAG","upperStr from clients is " + upperStr);
                }catch (RemoteException e){
                    e.printStackTrace();
                }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    FragmentManager manager;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fragment不需要去清单中声明，直接new
        SoundFragment sf = new SoundFragment();

        //拿到Fragment的manager对象
        FragmentManager manager = getFragmentManager();


        //事物（防止花屏）
        FragmentTransaction transaction = manager.beginTransaction();

        //表示使用SoundFragment去替换之前的Fragment
        transaction.replace(R.id.container,sf);

        //提交事物
        transaction.commit();


    }





    public void onClick(View view){
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.Btn_sd:
                SoundFragment sf = new SoundFragment();
                transaction.replace(R.id.container,sf);
                break;
            case  R.id.Btn_dp:
                DisplayFragment df = new DisplayFragment();
                transaction.replace(R.id.container,df);
                break;
            case  R.id.Btn_stg:
                StorgeFragment stg = new StorgeFragment();
                transaction.replace(R.id.container,stg);
                break;
            case R.id.Btn_bind:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.broadcast","com.example.broadcast.MyService"));

                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
        }
        transaction.commit();



    }



//    public void sound(View view){
//
//        //在右侧声明一个fragment
//        //fragment不需要去清单文件中声明，直接new
//        SoundFragment sf = new SoundFragment();
//
//
//        //拿到fragment的manager对象
//        manager = getFragmentManager();
//
//
//        //事物（防止花屏）
//        transaction = manager.beginTransaction();
//
//
//        //表示使用SoundFragment去替换之前的fragment
//        transaction.replace(R.id.container,sf);
//
//
//        //提交事物
//        transaction.commit();
//    }
//
//
//    public void storge(View view){
//        StorgeFragment stf = new StorgeFragment();
//
//        transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.container,stf);
//        transaction.commit();
//    }
//
//
//
//    public void display(View view){
//        DisplayFragment df = new  DisplayFragment();
//        manager = getFragmentManager();
//        transaction = manager.beginTransaction();
//        transaction.replace(R.id.container,df);
//        transaction.commit();
//    }



}
