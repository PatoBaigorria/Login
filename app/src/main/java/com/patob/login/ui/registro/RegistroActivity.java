package com.patob.login.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.patob.login.Modelo.Usuario;
import com.patob.login.R;
import com.patob.login.databinding.ActivityMainBinding;
import com.patob.login.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private RegistroActivityViewModel vm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = binding.etDni.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String email = binding.etEmail.getText().toString();
                String password = binding.etPass.getText().toString();

                vm.guardar(dni, nombre, apellido, email, password);
            }
        });

        vm.getmUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni());
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etEmail.setText(usuario.getMail());
                binding.etPass.setText(usuario.getPassword());
            }
        });
        Intent intent = getIntent();
        vm.cargarDatos(intent);
    }
}