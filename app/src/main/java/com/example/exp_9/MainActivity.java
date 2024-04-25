package com.example.exp_9;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "example.text";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
    }

    public void Save(View view) {
        String text = editText.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            editText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();
        }

        catch (FileNotFoundException e) {throw new RuntimeException(e);}
        catch (IOException e) {throw new RuntimeException(e);}

        finally {
            if (fos != null) {
                try {fos.close();}
                catch (IOException e) {throw new RuntimeException(e);}
            }
        }
    }

    public void Load(View view) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {sb.append(text).append("\n");}
            editText.setText(sb.toString());
        }

        catch (FileNotFoundException e) {throw new RuntimeException(e);}
        catch (IOException e) {throw new RuntimeException(e);}

        finally {
            if (fis != null) {
                try {fis.close();}
                catch (IOException e) {throw new RuntimeException(e);}
            }
        }
    }
}