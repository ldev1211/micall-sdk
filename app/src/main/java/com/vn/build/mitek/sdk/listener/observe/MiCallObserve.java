package com.vn.build.mitek.sdk.listener.observe;

import com.vn.build.mitek.sdk.model.CallStateEnum;
import com.vn.build.mitek.sdk.model.RegistrationStateEnum;

public interface MiCallObserve {
    void onRegistrationStateChanged(RegistrationStateEnum state, String message);
    void onCallStateChanged(CallStateEnum state, String message);
}
