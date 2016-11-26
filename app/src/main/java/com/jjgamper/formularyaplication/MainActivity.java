package com.jjgamper.formularyaplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilTelefono;
    private TextInputLayout tilCorreo;
    private TextInputLayout tilDescrip;
    private EditText etnombre;
    private EditText ettelefono;
    private EditText etcorreo;
    private EditText etdescripcion;
    private String fechnac;
    private DatePicker dpfnac;


    //Variables para recibir info
    private EditText tvNombre;
    private DatePicker tvFechanacimien;
    private EditText tvTelefono;
    private EditText tvEmail;
    private EditText tvDescrip;

    //private String CampoFecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtener intancia de los controles
        // Referencias TILs
        tilNombre     = (TextInputLayout) findViewById(R.id.til_nombre);
        tilTelefono   = (TextInputLayout) findViewById(R.id.til_telefono);
        tilCorreo     = (TextInputLayout) findViewById(R.id.til_email);
        tilDescrip    = (TextInputLayout) findViewById(R.id.til_descrip);

        // Referencias ETs
        etnombre      = (EditText) findViewById(R.id.etNomCompleto);
        ettelefono    = (EditText) findViewById(R.id.etTelefono);
        etcorreo      = (EditText) findViewById(R.id.etEmail);
        etdescripcion = (EditText) findViewById(R.id.etDescripcion);
        dpfnac        = (DatePicker)  findViewById(R.id.dpFecNac);


        //verificar si presiono editar
        if (getIntent().getStringExtra("validar") != null ) {
            recibirdatos();
        }

        etnombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ettelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilTelefono.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etcorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                esCorreoValido(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Referencia Botón
        Button botonSiguiente = (Button) findViewById(R.id.btSiguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });
    }

    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }

    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            tilTelefono.setError("Teléfono inválido");
            return false;
        } else {
            tilTelefono.setError(null);
        }

        return true;
    }

    private boolean esCorreoValido(String correo) {
        /*if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            tilCorreo.setError("Correo electrónico inválido");
            return false;
        } else {
            tilCorreo.setError(null);
        }
*/
        return true;
    }

    private void validarDatos() {
        String nombre       = tilNombre.getEditText().getText().toString();
        String telefono     = tilTelefono.getEditText().getText().toString();
        String correo       = tilCorreo.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esTelefonoValido(telefono);
        boolean c = esCorreoValido(correo);

        if (a && b && c) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Datos Correctos", Toast.LENGTH_LONG).show();
            enviarDatos();
        }

    }

    private void enviarDatos(){

        dpfnac        = (DatePicker)  findViewById(R.id.dpFecNac);
        fechnac   = Integer.toString(dpfnac.getYear()) + "/" +Integer.toString((dpfnac.getMonth()+1)) + "/" + Integer.toString(dpfnac.getDayOfMonth());
        Intent intent =new Intent(MainActivity.this,DetalleDatos.class);
        intent.putExtra("nombre",etnombre.getText().toString());
        intent.putExtra("telefono",ettelefono.getText().toString());
        intent.putExtra("fechnac",fechnac.toString());
        Toast.makeText(this, fechnac.toString(), Toast.LENGTH_LONG).show();
        intent.putExtra("correo",etcorreo.getText().toString());
        intent.putExtra("descripcion",etdescripcion.getText().toString());
        startActivity(intent);
        finish();
    }
    private void recibirdatos(){

        Bundle bundle = this.getIntent().getExtras();
        String nombre2 =bundle.getString("nombre2");
        String fechanac2 =bundle.getString("fechnac2");
        String telefono2 =bundle.getString("telefono2");
        String email2 =bundle.getString("correo2");
        String descripcion2 =bundle.getString("descripcion2");

        tvNombre  = (EditText) findViewById(R.id.etNomCompleto);
        tvFechanacimien=(DatePicker)  findViewById(R.id.dpFecNac);

        tvTelefono =(EditText) findViewById(R.id.etTelefono);
        tvEmail = (EditText) findViewById(R.id.etEmail);
        tvDescrip = (EditText) findViewById(R.id.etDescripcion);

        int year   = dpfnac.getYear();
        int mes    = dpfnac.getMonth();
        int dia    = dpfnac.getDayOfMonth();


        tvNombre.setText(nombre2);
        tvFechanacimien.updateDate(year,mes,dia);
        tvTelefono.setText(telefono2);
        tvEmail.setText(email2);
        tvDescrip.setText(descripcion2);


    }

}

