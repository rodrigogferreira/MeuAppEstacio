package com.example.ferreira.meuappestacio.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.diogosoares.estacioapp.R;
import com.diogosoares.estacioapp.models.User;
import com.diogosoares.estacioapp.utils.AppDatabase;
import com.diogosoares.estacioapp.utils.DbOperations;
import com.example.ferreira.meuappestacio.R;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); }

    private void startProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando");
        progressDialog.show();}

    private void stopProgressDialog() {
        progressDialog.dismiss();
    }

    public void login(View view) {
        Intent intent = new Intent(this, ListaTarefasActivity.class);
        startActivity(intent);


        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        String user =  username.getText().toString();
        String pass =  password.getText().toString();

        DbOperations dbOperations = new DbOperations(this);
        dbOperations.queryAuthenticatedUser(user, pass, new DbOperations.DBOperationsCallBack() {
            @Override
            public void userSaverd(Boolean success) {}

            @Override
            public void userExists(final Boolean exists) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(exists) {
                            Toast.makeText(MainActivity.this, "Usuario logado!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario não logado!!!", Toast.LENGTH_SHORT).show(); } }}); }

            @Override
            public void userUpdated(Boolean success) {}

            @Override
            public void userDeleted(Boolean success) {}}); }

    public void cadastrar(View view) {
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent); }}
