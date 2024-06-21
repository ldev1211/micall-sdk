package com.vn.build.mitek.sdk.core;

import com.vn.build.mitek.sdk.listener.publisher.MiCallStateListener;
import com.vn.build.mitek.sdk.model.account.MiCallAccount;

public class MiCallFactory {
    static void init(String apiKey){
        MiCallSDK.init(apiKey);
    }
    static void register(MiCallAccount account){
        MiCallSDK.register(account);
    }

    static void addMiCallListener(MiCallStateListener listener){
        MiCallSDK.addMiCallListener(listener);
    }
}
