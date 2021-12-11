package proyectos.create.ef_ejercicio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etnombre, etcorreo , ettelefono, etdni;
    Button btn_agregar, btn_editar, btn_eliminar,
            btnConsultar, btnConsultarUsuario;
    RecyclerView rvUsuario;

    DatabaseReference base_de_datos;

    List<Usuario> listausuarios = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializamos Firebase (DataBase)
        base_de_datos = FirebaseDatabase.getInstance().getReference();

        // Casteo de los objetos
        etnombre = findViewById(R.id.txt_nombre);
        etcorreo = findViewById(R.id.txt_correo);
        ettelefono = findViewById(R.id.txt_telefono);
        etdni = findViewById(R.id.txt_dni);

        //Botones
        btn_agregar = findViewById(R.id.btn_añadir);
        btnConsultar= findViewById(R.id.btn_consultar);
        btnConsultarUsuario = findViewById(R.id.btn_consultar_usuario);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarData();
            }
        });
    }

    public void agregarData(){
        listausuarios.clear();

        Usuario usuario = new Usuario(
                etnombre.getText().toString().trim(),
                ettelefono.getText().toString().trim(),
                etcorreo.getText().toString().trim(),
                etdni.getText().toString().trim()
        );

        base_de_datos.child("Usuarios").push().setValue(usuario, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this, "Usuario Añadido", Toast.LENGTH_SHORT).show();
            }
        });
        limpiarCampos();
    }

    public void limpiarCampos(){
        etnombre.setText("");
        etcorreo.setText("");
        ettelefono.setText("");
        etdni.setText("");
    }

    private void obtenerUsuario(){
        listausuarios.clear();
        String usuario = etnombre.getText().toString().trim().toLowerCase();

        Query consulta = base_de_datos.child("Usuarios").orderByChild("nombre").equalTo(usuario);

        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot objeto: snapshot.getChildren()){
                    listausuarios.add(objeto.getValue(Usuario.class));
                }
                adaptador = new AdaptadorUsuario(MainActivity.this, listausuarios);
                rvUsuario.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}