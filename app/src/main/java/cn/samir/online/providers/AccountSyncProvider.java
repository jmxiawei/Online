//package cn.samir.online.providers;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
//import cn.samir.online.util.Constant;
//import cn.samir.online.util.LogUtil;
//
///**
// * Created by xiaw on 2017/5/23 0023.
// */
//
//public class AccountSyncProvider extends ContentProvider {
//
//
//    public static final String AUTHORITY = Constant.AUTHORITY;
//
//    @Override
//    public boolean onCreate() {
//        return false;
//    }
//
//
//    private void log(String msg) {
//        LogUtil.e("AccountSyncProvider " + msg);
//    }
//
//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//
//        log("query");
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//
//
//        log("getType");
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//
//        log("insert");
//        return null;
//    }
//
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//        log("delete");
//        return 0;
//    }
//
//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
//
//        log("update");
//        return 0;
//    }
//}
