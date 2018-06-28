package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private MyApplicationData appState;
    private EditText nameField, emailField;
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

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            pid=receivedPersonInfo.uid;
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        String name=nameField.getText().toString().trim();
        String email=emailField.getText().toString().trim();
        appState.firebaseReference.child(pid).child("email").setValue(email);
        appState.firebaseReference.child(pid).child("name").setValue(name);
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);


    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        nameField.setText(null);
        emailField.setText(null);
        appState.firebaseReference.child(pid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        }
}
