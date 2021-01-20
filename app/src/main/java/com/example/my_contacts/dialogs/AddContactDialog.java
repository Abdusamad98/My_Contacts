package com.example.my_contacts.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.my_contacts.R;
import com.example.my_contacts.Settings.Settings;

public class AddContactDialog {
    private AlertDialog dialog;
    private TextInputEditText textName;
    private TextInputEditText textNumber;
    private Spinner spinner;
    private OnClickListener onClickListener;

    public  AddContactDialog(Context context){
        dialog =new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add,null,false);
        textName =view.findViewById(R.id.inputName);
        textNumber=view.findViewById(R.id.inputNumber);
        spinner=(Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(context,R.array.Images,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Add",(dialog,which)-> {
            String name =textName.getText().toString();
            String number ="+"+textNumber.getText().toString();
            if(onClickListener!=null) onClickListener.onClick(name,number,spinner.getSelectedItem().toString());
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel",(dialog,which)->{
        });
        dialog.setView(view);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public void show() {
        dialog.show();
    }

    public interface OnClickListener {
        void onClick(String name, String number,String imageName);
    }
}
