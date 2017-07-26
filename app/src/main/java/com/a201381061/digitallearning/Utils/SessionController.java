package com.a201381061.digitallearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;

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

    // NIM
    private static final String KEY_NIM = "nim";

    // Fakultas UserModel
    private static final String KEY_FAKULTAS = "fakultas";

    //Jurusan UserModel
    private static final String KEY_JURUSAN = "jurusan";

    //Semester UserModel
    private static final String KEY_SEMESTER = "semester";

    //Angkatan UserModel
    private static final String KEY_ANGKATAN = "angkatan";

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
    public void createLoginSession(String name, String nim, String jurusan, String fakultas, int angkatan, int semester) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing nim in pref
        editor.putString(KEY_NIM, nim);

        // Storing kampus in pref
        editor.putString(KEY_JURUSAN, jurusan);

        // Storing fakultas in pref
        editor.putString(KEY_FAKULTAS, fakultas);

        // Storing angkatan in pref
        editor.putInt(KEY_ANGKATAN, angkatan);

        // Storing semester in pref
        editor.putInt(KEY_SEMESTER, semester);

        // commit changes
        editor.commit();
    }

    public boolean firstTimeOpen() {
        return pref.getBoolean(FIRST_TIME, true);
    }

    public void openedForFirstTime() {
        editor.putBoolean(FIRST_TIME, false);
        editor.commit();
    }

    public String getNama() {
        return pref.getString(KEY_NAME, null);
    }

    public String getNIM() {
        return pref.getString(KEY_NIM, null);
    }

    public String getFakultas() {
        return pref.getString(KEY_FAKULTAS, null);
    }

    public String getJurusan() {
        return pref.getString(KEY_JURUSAN, null);
    }

    public String getSemester() {
        return pref.getString(KEY_SEMESTER, null);
    }

    public String getAngkatan() {
        return pref.getString(KEY_ANGKATAN, null);
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.putBoolean(FIRST_TIME, false);
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
