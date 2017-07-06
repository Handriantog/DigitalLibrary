package com.a201381061.digitallearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by UserModel on 6/29/2017.
 */

public class SessionController {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "DigLibPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // UserModel name
    private static final String KEY_NAME = "nama";

    // Email address
    private static final String KEY_EMAIL = "email";

    // Fakultas UserModel
    private static final String KEY_FAKULTAS = "fakultas";

    //Kampus UserModel
    private static final String KEY_KAMPUS = "kampus";

    //First time open the app
    private static final String FIRST_TIME = "firstTime";

    // Constructor
    public SessionController(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String name, String email, String kampus, String fakultas) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // Storing kampus in pref
        editor.putString(KEY_KAMPUS, kampus);

        // Storing fakultas in pref
        editor.putString(KEY_FAKULTAS, fakultas);

        // commit changes
        editor.commit();
    }

    public boolean firstTimeOpen(){
        return pref.getBoolean(FIRST_TIME,true);
    }

    public void openedForFirstTime(){
        editor.putBoolean(FIRST_TIME,false);
        editor.commit();
    }

    public String getNama() {
        return pref.getString(KEY_NAME, null);
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public String getFakultas() {
        return pref.getString(KEY_FAKULTAS, null);
    }

    public String getKampus() {
        return pref.getString(KEY_KAMPUS, null);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
