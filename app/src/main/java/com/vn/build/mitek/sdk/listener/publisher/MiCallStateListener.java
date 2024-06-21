package com.vn.build.mitek.sdk.listener.publisher;

import com.vn.build.mitek.sdk.listener.observe.MiCallObserve;
import com.vn.build.mitek.sdk.model.CallStateEnum;
import com.vn.build.mitek.sdk.model.RegistrationStateEnum;

public class MiCallStateListener implements MiCallObserve {
    @Override
    public void onRegistrationStateChanged(RegistrationStateEnum state, String message) {

    }

    @Override
    public void onCallStateChanged(CallStateEnum state, String message) {

    }
}
