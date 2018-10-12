package com.example.android.tutor;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;

import co.chatsdk.core.error.ChatSDKException;
import co.chatsdk.core.session.ChatSDK;
import co.chatsdk.core.session.Configuration;
import co.chatsdk.core.session.InterfaceManager;
import co.chatsdk.firebase.FirebaseNetworkAdapter;
import co.chatsdk.firebase.file_storage.FirebaseFileStorageModule;
import co.chatsdk.firebase.push.FirebasePushModule;
import co.chatsdk.ui.manager.BaseInterfaceAdapter;

public class MainActivity extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();

        Configuration.Builder config = new Configuration.Builder(context);
        config.firebaseRootPath("tutor_test");
        config.googleMaps("AIzaSyB3EjPdzBYu-Eay83_qwEsTvH6S6Gwcgmo");
        config.firebaseCloudMessagingServerKey("AAAA-bpjCl8:APA91bEwR9q637PGnJ1t8NMPms0-D6u8qROvMiAA3iy6fJ29KIstFWSevyBXV85pEHctLoBRADR_haOwA4vcHbyWRPTOQtMHZ8F13J0FJXwxSL-5x0hQunsXtwtRzf2hW4zSyeGmiwK5");
        config.publicRoomCreationEnabled(true);
        config.pushNotificationSound("default");
        config.setClientPushEnabled(false);
        config.pushNotificationsForPublicChatRoomsEnabled(true);

        try {
            ChatSDK.initialize(config.build(), new BaseInterfaceAdapter(context), new FirebaseNetworkAdapter());
        }
        catch (ChatSDKException e) {

        }


        FirebaseFileStorageModule.activate();
        FirebasePushModule.activateForFirebase();
//        FirebaseUIModule.activate(context, EmailAuthProvider.PROVIDER_ID, PhoneAuthProvider.PROVIDER_ID);

    }

    @Override
    protected void attachBaseContext (Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
