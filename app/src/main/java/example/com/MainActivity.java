package example.com;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  Button btnGuardar;
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
  }
}
