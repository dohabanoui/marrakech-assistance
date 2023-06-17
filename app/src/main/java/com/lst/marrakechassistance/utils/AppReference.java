package com.lst.marrakechassistance.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppReference {

        private static final String PREF_FILE_NAME = "MyPrefs";
        private static final String KEY_IP_ADDRESS = "ip_address";
        private SharedPreferences sharedPreferences;

        public AppReference(Context context) {
            sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        }

        public void saveIpAddress(String ipAddress) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_IP_ADDRESS, ipAddress);
            editor.apply();
        }

        public String getIpAddress() {
            return sharedPreferences.getString(KEY_IP_ADDRESS, "");
        }

}
