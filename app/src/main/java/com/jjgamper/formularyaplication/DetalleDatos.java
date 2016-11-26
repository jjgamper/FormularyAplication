package com.jjgamper.formularyaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DetalleDatos extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFechanacimien;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_datos);

        Bundle bundle = getIntent().getExtras();

        String nombre =bundle.getString("nombre");
        String fechanac =bundle.getString("fechnac");
        String telefono =bundle.getString("telefono");
        String email =bundle.getString("correo");
        String descripcion =bundle.getString("descripcion");


        tvNombre  = (TextView) findViewById(R.id.ddNombre);
        tvFechanacimien=(TextView) findViewById(R.id.tvddFecNac);
        tvTelefono =(TextView) findViewById(R.id.tvddTelefono);
        tvEmail = (TextView) findViewById(R.id.tvddEmail);
        tvDescrip = (TextView) findViewById(R.id.tvddDescripcion);

        tvNombre.setText(nombre);
        tvFechanacimien.setText(fechanac);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescrip.setText(descripcion);

        // Referencia Botón
        Button botonEditDatos = (Button) findViewById(R.id.btEditarDatos);
        botonEditDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editardatos();
            }
        });

    }
    public boolean onKeyDown(int keyCode, KeyEvent envent){
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            editardatos();
        }
        return super.onKeyDown(keyCode, envent);
    }

    private void editardatos(){
        Toast.makeText(this,"Enviando informacion para editar", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DetalleDatos.this,MainActivity.class);
        intent.putExtra("nombre2",tvNombre.getText().toString());
        intent.putExtra("telefono2",tvTelefono.getText().toString());
        intent.putExtra("fechnac2",tvFechanacimien.toString());
        intent.putExtra("correo2",tvEmail.getText().toString());
        intent.putExtra("descripcion2",tvDescrip.getText().toString());
        intent.putExtra("validar","true");
        startActivity(intent);
        finish();
    }
}

