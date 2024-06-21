package com.vn.build.micall.sdk.listener.observe;

import com.vn.build.micall.sdk.model.CallStateEnum;
import com.vn.build.micall.sdk.model.RegistrationStateEnum;

public interface MiCallObserve {
    void onRegistrationStateChanged(RegistrationStateEnum state, String message);
    void onCallStateChanged(CallStateEnum state, String message);
}
