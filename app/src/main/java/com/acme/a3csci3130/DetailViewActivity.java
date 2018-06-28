package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailViewActivity extends Activity {

    private MyApplicationData appState;
    private EditText nameField, emailField,addressFile,numberFile;
    Spinner proSp,priSp;
    private TextView pritext,protext;

    private  String pid;
    Contact receivedPersonInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        appState = ((MyApplicationData) getApplicationContext());

        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        numberFile=(EditText) findViewById(R.id.number);
        addressFile=(EditText) findViewById(R.id.address_editText);
        priSp=(Spinner) findViewById(R.id.pri_sppnner);
        proSp=(Spinner) findViewById(R.id.pro_spinner);
        protext=(TextView) findViewById(R.id.protext);
        pritext=(TextView) findViewById(R.id.pri_text);
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            pid=receivedPersonInfo.uid;
            addressFile.setText(receivedPersonInfo.address);
            numberFile.setText(receivedPersonInfo.number);
            pritext.setText(receivedPersonInfo.primary);
            protext.setText(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        String name=nameField.getText().toString().trim();
        String email=emailField.getText().toString().trim();
        String number=numberFile.getText().toString().trim();
        String addr=addressFile.getText().toString().trim();
        String pro=proSp.getSelectedItem().toString();
        String pri=priSp.getSelectedItem().toString();
        if(formatting(name,number,addr)) {
            appState.firebaseReference.child(pid).child("email").setValue(email);
            appState.firebaseReference.child(pid).child("name").setValue(name);
            appState.firebaseReference.child(pid).child("number").setValue(number);
            appState.firebaseReference.child(pid).child("address").setValue(addr);
            appState.firebaseReference.child(pid).child("primary").setValue(pri);
            appState.firebaseReference.child(pid).child("province").setValue(pro);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"formatting issue!",Toast.LENGTH_LONG).show();

        }


    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        nameField.setText(null);
        emailField.setText(null);
        numberFile.setText(null);
        addressFile.setText(null);
        appState.firebaseReference.child(pid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        }
    public boolean formatting(String name,String number,String addr)
    {
        if (name.length()<2||name.length()>50)
        {
            return false;
        }
        if (number.length()!=9)
        {
            return false;
        }
        if (addr.length()>50)
        {
            return false;
        }
        return true;
    }

}
