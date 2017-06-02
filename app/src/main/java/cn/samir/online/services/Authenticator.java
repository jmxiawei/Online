//package cn.samir.online.services;
//
//import android.accounts.AbstractAccountAuthenticator;
//import android.accounts.Account;
//import android.accounts.AccountAuthenticatorResponse;
//import android.accounts.NetworkErrorException;
//import android.content.Context;
//import android.os.Bundle;
//
//import cn.samir.online.util.LogUtil;
//
///**
// * Created by xiaw on 2017/5/23 0023.
// */
//
//public class Authenticator extends AbstractAccountAuthenticator {
//
//    public Authenticator(Context context) {
//        super(context);
//    }
//    private void log(String msg) {
//        LogUtil.e("Authenticator " + msg);
//    }
//    @Override
//    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
//        log("editProperties");
//        return null;
//    }
//
//    @Override
//    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
//        log("addAccount");
//        return null;
//    }
//
//    @Override
//    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
//
//        log("confirmCredentials");
//        return null;
//    }
//
//    @Override
//    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
//        log("getAuthToken");
//        return null;
//    }
//
//    @Override
//    public String getAuthTokenLabel(String authTokenType) {
//
//        log("getAuthTokenLabel");
//        return null;
//    }
//
//    @Override
//    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
//
//        log("updateCredentials");
//        return null;
//    }
//
//    @Override
//    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
//
//        log("hasFeatures");
//        return null;
//    }
//}
