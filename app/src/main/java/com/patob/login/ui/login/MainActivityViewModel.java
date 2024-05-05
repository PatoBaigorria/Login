package com.patob.login.ui.login;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.patob.login.Modelo.Usuario;
import com.patob.login.Request.ApiClient;
import com.patob.login.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMail;
    private MutableLiveData<String> mPass;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String>getMMail(){
        if(mMail==null){
            mMail=new MutableLiveData<>();
        }
        return mMail;
    }

    public LiveData<String>getMPass(){
        if(mPass==null){
            mPass=new MutableLiveData<>();
        }
        return mPass;
    }

    public void acceso(String mail, String password){
        Usuario usuario = ApiClient.login(getApplication().getApplicationContext(), mail, password);
        if (usuario != null) {
            Intent intent = new Intent(getApplication(), RegistroActivity.class);
            intent.putExtra("usuario", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Email o contraseña incorrecta", Toast.LENGTH_LONG).show();

        }
    }

    public void registro(){
        Intent intent = new Intent(getApplication(), RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("usuario", false);
        getApplication().startActivity(intent);
    }

}
