package com.vn.build.micall.sdk.core;

import com.vn.build.micall.sdk.listener.publisher.MiCallStateListener;
import com.vn.build.micall.sdk.model.account.MiCallAccount;

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
