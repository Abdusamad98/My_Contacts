package com.example.my_contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.my_contacts.Settings.Settings;
import com.example.my_contacts.adapters.ContactAdapter;
import com.example.my_contacts.dialogs.AddContactDialog;
import com.example.my_contacts.models.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ContactAdapter adapter;
    private  ArrayList<String> rasmlar;
    private ArrayList<ContactData> data;
    private ListView list;
    public Settings saver;
    private HashMap<String,Integer> hashMap;
    private Intent call;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         saver = new Settings(getApplicationContext());
         loadResources();
         loadData();
         adapter = new ContactAdapter(data);
         list = findViewById(R.id.list);
         list.setAdapter(adapter);

     //    list.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(MainActivity.this, "Salom", Toast.LENGTH_SHORT).show());


            adapter.setDeleteListener(posittion -> {

             //   Toast.makeText(this,"HALLO"+posittion,Toast.LENGTH_SHORT).show();
                AlertDialog dialog = new AlertDialog.Builder(this)
                     .setMessage("Are you sure you want to delete this Contact?")
                     .setTitle("Delete this contact!")
                     .setPositiveButton("Delete", (dialog1, which) -> {
                         data.remove(posittion);
                         adapter.notifyDataSetChanged();
                     })
                     .setNegativeButton("Cancel", null).show();
         });
         adapter.setItemListener(posittion -> {
             fhoneCall(   data.get(posittion).getNumber());
         });


     }
     public void fhoneCall(final String phoneNumber){
         startActivity(new Intent(Intent.ACTION_CALL,Uri.fromParts("tel",phoneNumber,null)));
     }

    public  void loadResources(){
        rasmlar=new ArrayList<>();
        rasmlar.addAll(Arrays.asList(this.getResources().getStringArray(R.array.Images)));
        hashMap=new HashMap<String, Integer>();
        hashMap.put(rasmlar.get(0),R.drawable.sss);
        hashMap.put(rasmlar.get(1),R.drawable.manzara);
        hashMap.put(rasmlar.get(2),R.drawable.enrique);
        hashMap.put(rasmlar.get(3),R.drawable.naqsh);
        hashMap.put(rasmlar.get(4),R.drawable.tabiat);
        hashMap.put(rasmlar.get(5),R.drawable.eminem);
        hashMap.put(rasmlar.get(6),R.drawable.tohir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_add,menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add){
            AddContactDialog dialog= new AddContactDialog(this);
            dialog.setOnClickListener((name,number,image)->{
                boolean t=false;
                for(int i=0;i<data.size();i++)
                if(number.equals(data.get(i).getNumber())){t=true;}
                if(number.equals("+")&&name.equals("")){

                    AlertDialog dialogss = new AlertDialog.Builder(this)
                            .setMessage("Empty contact?")
                            .setTitle("Error!")
                            .setPositiveButton("Ok", (dialog3, which) -> {
                                dialog3.cancel();
                            }).show();
                }
                else
                if(t==true){
                    AlertDialog dialogs = new AlertDialog.Builder(this)
                            .setMessage("This contact is already exist?")
                            .setTitle("Error!")
                            .setPositiveButton("Ok", (dialog2, which) -> {
                                dialog2.cancel();
                            }).show();
                }
                else {
                    data.add(new ContactData(hashMap.get(image),name,number));
                    adapter.notifyDataSetChanged();}
            });
            dialog.show();
        }
    return true;
     }


    public void saveData(){
         saver.setSavedState(true);
         saver.setPosition(data.size());
        for (int i=0;i<data.size();i++){
           saver.setContact(i,data.get(i).getImageUrl(),data.get(i).getName(),data.get(i).getNumber());
        }

    }


    public void loadSavedData(){
                 if(saver.hasSavedState()==true){
            int k=saver.getPosition();
            for (int i=0;i<k;i++){
            data.add(new ContactData(saver.getImage(i),saver.getSavedName(i),saver.getSavedNumber(i)));
       }}
    }

    @Override
    protected void onStop() {
         saveData();
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void loadData() {
        data = new ArrayList<>();
        loadSavedData();
    }
}





