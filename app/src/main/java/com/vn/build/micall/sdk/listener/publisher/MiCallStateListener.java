package com.vn.build.micall.sdk.listener.publisher;

import com.vn.build.micall.sdk.listener.observe.MiCallObserve;
import com.vn.build.micall.sdk.model.CallStateEnum;
import com.vn.build.micall.sdk.model.RegistrationStateEnum;

public class MiCallStateListener implements MiCallObserve {
    @Override
    public void onRegistrationStateChanged(RegistrationStateEnum state, String message) {

    }

    @Override
    public void onCallStateChanged(CallStateEnum state, String message) {

    }
}
