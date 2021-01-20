package com.example.my_contacts.Settings;

import android.content.Context;
import android.content.SharedPreferences;


public class Settings {
    private SharedPreferences pref;

    public Settings(Context context){
        this.pref=context.getSharedPreferences("MyData",Context.MODE_PRIVATE);
    }
    public  void setContact(int position,int image,String name,String number){
        pref.edit().putInt("IMAGE"+position,image).apply();
        pref.edit().putString("NAME"+position,name).apply();
        pref.edit().putString("NUMBER"+position,number).apply();
    }

    public String getSavedName(int position){
        return pref.getString("NAME"+position,"");
    }

    public String getSavedNumber(int position){
        return pref.getString("NUMBER"+position,"");
    }

    public void setPosition(int position){
        pref.edit().putInt("POSITION",position).apply();
    }
    public int getPosition(){
        return pref.getInt("POSITION",0);
    }

    public int getImage(int position){
        return pref.getInt("IMAGE"+position,0);
    }


    public void setSavedState(boolean b) {
        pref.edit().putBoolean("SAVED_STATE", b).apply();
    }
    public boolean hasSavedState() {
        return pref.getBoolean("SAVED_STATE", false);
    }

}
