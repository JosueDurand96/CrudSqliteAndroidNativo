package example.com;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  Button btnGuardar,btnBuscar,btnBorrar,btnActualizar;
  EditText etId,etNombres,etTelefono;
  TextView textView4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    etId=(EditText)findViewById(R.id.etId);
    etNombres=(EditText)findViewById(R.id.etNombres);
    etTelefono=(EditText)findViewById(R.id.etTelefono);
    btnGuardar=(Button)findViewById(R.id.btnGuardar);
    btnBuscar=(Button)findViewById(R.id.btnBuscar);
    btnActualizar=(Button)findViewById(R.id.btnActualizar);
    btnBorrar=(Button)findViewById(R.id.btnBorrar);
    textView4=(TextView)findViewById(R.id.textView4);

    final PersonaBD personaBD = new PersonaBD(getApplicationContext());
    btnGuardar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SQLiteDatabase db = personaBD.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(PersonaBD.DatosTabla.COLUMNA_ID,etId.getText().toString());
        valores.put(PersonaBD.DatosTabla.COLUMNA_NOMBRES,etNombres.getText().toString());
        valores.put(PersonaBD.DatosTabla.COLUMNA_TELEFONOS,etTelefono.getText().toString());

        Long IdGuardado = db.insert(PersonaBD.DatosTabla.NOMBRE_TABLA,PersonaBD.DatosTabla.COLUMNA_ID,valores);
        Toast.makeText(MainActivity.this, "SE REGISTRO "+IdGuardado, Toast.LENGTH_SHORT).show();
        textView4.setText(IdGuardado.toString());
      }
    });

    btnBorrar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SQLiteDatabase db = personaBD.getWritableDatabase();
        String selection = PersonaBD.DatosTabla.COLUMNA_ID+"=?";
        String[] argsel={etId.getText().toString()};

        db.delete(PersonaBD.DatosTabla.NOMBRE_TABLA,selection,argsel);
      }
    });

    btnBuscar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SQLiteDatabase db = personaBD.getReadableDatabase();
        String[] argsel = {etId.getText().toString()};
        String[] projection ={PersonaBD.DatosTabla.COLUMNA_NOMBRES,PersonaBD.DatosTabla.COLUMNA_TELEFONOS};
        Cursor c = db.query(PersonaBD.DatosTabla.NOMBRE_TABLA,projection,PersonaBD.DatosTabla.COLUMNA_ID+"=?",argsel,null,null,null);

        c.moveToFirst();
        etNombres.setText(c.getString(0));
        etTelefono.setText(c.getString(1));


      }
    });
    btnActualizar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SQLiteDatabase db = personaBD.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(PersonaBD.DatosTabla.COLUMNA_NOMBRES,etNombres.getText().toString());
        valores.put(PersonaBD.DatosTabla.COLUMNA_TELEFONOS,etTelefono.getText().toString());
        String[] argsel={etId.getText().toString()};
        String Selection = PersonaBD.DatosTabla.COLUMNA_ID+"=?";

        int count = db.update(PersonaBD.DatosTabla.NOMBRE_TABLA,valores,Selection,argsel);
      }
    });

  }
}
