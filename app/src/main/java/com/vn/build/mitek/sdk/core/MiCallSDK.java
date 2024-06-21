package com.vn.build.mitek.sdk.core;

import com.vn.build.mitek.sdk.listener.publisher.MiCallStateListener;
import com.vn.build.mitek.sdk.model.RegistrationStateEnum;
import com.vn.build.mitek.sdk.model.account.MiCallAccount;
import com.vn.build.mitek.sdk.util.MiCallLog;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.Call;
import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.pjsip_transport_type_e;

import java.util.ArrayList;
import java.util.List;

public class MiCallSDK {
    private static String apiKey;
    private static boolean isAvailable = false;
    private static Endpoint ep = new Endpoint();
    static private List<MiCallStateListener> observe;
    static void init(String apiKey){
        MiCallSDK.apiKey = apiKey;
        observe = new ArrayList<>();
        try {
            ep.libCreate();
            EpConfig epConfig = new EpConfig();
            epConfig.getLogConfig().setLevel(4);
            epConfig.getLogConfig().setConsoleLevel(4);
            epConfig.getUaConfig().setUserAgent("MiCall SDK");
            ep.libInit(epConfig);
            TransportConfig transportConfig = new TransportConfig();
            transportConfig.setPort(6000);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TCP,transportConfig);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP,transportConfig);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TLS,transportConfig);
        } catch (Exception e) {
            MiCallLog.logE(e.getMessage());
        }
    }
    static void addMiCallListener(MiCallStateListener listener){
        if(observe==null) observe = new ArrayList<>();
        observe.add(listener);
    }
    public static void observingRegState(OnRegStateParam prm){
        String message;
        RegistrationStateEnum stateEnum;
        if (prm.getExpiration() == 0L) {
            message = "Un-registration: ";
            if (prm.getCode() == 200) {
                message += "Successful";
                stateEnum = RegistrationStateEnum.UNREGISTERED;
            } else {
                message += "Failed";
                stateEnum = RegistrationStateEnum.UNREGISTERED_FAILED;
            }
        } else {
            message = "Registration: ";
            if (prm.getCode() == 200) {
                message += "Successful";
                stateEnum = RegistrationStateEnum.REGISTERED;
            } else {
                message += "Failed";
                stateEnum = RegistrationStateEnum.REGISTER_FAILED;
            }
        }
        message += "(" + prm.getReason() + ")";
        for(MiCallStateListener ob : MiCallSDK.observe){
            ob.onRegistrationStateChanged(stateEnum,message);
        }
    }
    static void register(MiCallAccount acc){
        Account account = new Account();
        AccountConfig acf = new AccountConfig();
        String accountId = "sip:"+acc.getUsername()+"@"+acc.getDomain();
        String registrar = "sip:"+ acc.getDomain();
        String proxy = "sip:"+ acc.getProxy()+":"+ acc.getPort();
        String username = acc.getUsername();
        String password = acc.getPassword();
        acf.setIdUri(accountId);
        acf.getRegConfig().setRegistrarUri(registrar);
        acf.getSipConfig().getProxies().clear();
        acf.getSipConfig().getProxies().add(proxy);
        acf.getSipConfig().getAuthCreds().clear();
        acf.getSipConfig().getAuthCreds().add(new AuthCredInfo(
                "Digest", "*", username, 0,
                password
        ));
        acf.getNatConfig().setIceEnabled(true);
        try {
            account.modify(acf);
        } catch (Exception e) {
            MiCallLog.logE(e.getMessage());
        }
    }
}
