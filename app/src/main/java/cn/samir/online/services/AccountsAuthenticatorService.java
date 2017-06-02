//package cn.samir.online.services;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//
//public class AccountsAuthenticatorService extends Service {
//
//
//    private Authenticator authenticator;
//
//    public AccountsAuthenticatorService() {
//
//    }
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        authenticator = new Authenticator(this);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return authenticator.getIBinder();
//    }
//}
