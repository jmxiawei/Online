//package cn.samir.online.services;
//
//import android.accounts.Account;
//import android.app.Service;
//import android.content.AbstractThreadedSyncAdapter;
//import android.content.ContentProviderClient;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SyncResult;
//import android.os.Bundle;
//import android.os.IBinder;
//
//import cn.samir.online.util.LogUtil;
//
//public class AccountSyncService extends Service {
//
//    // Object to use as a thread-safe lock
//    private static final Object sSyncAdapterLock = new Object();
//
//    /*
//     * Instantiate the sync adapter object.
//     */
//    @Override
//    public void onCreate() {
//        /*
//         * Create the sync adapter as a singleton.
//         * Set the sync adapter as syncable
//         * Disallow parallel syncs
//         */
//        synchronized (sSyncAdapterLock) {
//            if (accountSyncAdapter == null) {
//                accountSyncAdapter = new AccountSyncAdapter(getApplicationContext(), true);
//            }
//        }
//    }
//
//    private AccountSyncAdapter accountSyncAdapter;
//
//    private static void log(String msg) {
//        LogUtil.e("Authenticator " + msg);
//    }
//
//    public AccountSyncService() {
//        LogUtil.e("AccountSyncService");
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return accountSyncAdapter.getSyncAdapterBinder();
//    }
//
//    public static final class AccountSyncAdapter extends AbstractThreadedSyncAdapter {
//
//        public AccountSyncAdapter(Context context, boolean autoInitialize) {
//            super(context, autoInitialize);
//        }
//
//        @Override
//        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
//            log("onPerformSync=" + account + ",authority=" + authority);
//        }
//
//    }
//}
