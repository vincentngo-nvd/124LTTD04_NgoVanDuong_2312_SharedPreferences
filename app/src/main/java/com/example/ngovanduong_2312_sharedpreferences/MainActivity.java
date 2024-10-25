package com.example.ngovanduong_2312_sharedpreferences;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etStudentID;
    Button btnSave, btnShow;
    TextView tvDisplay;

    // Tên file SharedPreferences
    private static final String SHARED_PREF_NAME = "student_info";
    private static final String KEY_NAME = "name";
    private static final String KEY_STUDENT_ID = "student_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etStudentID = findViewById(R.id.etStudentID);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        tvDisplay = findViewById(R.id.tvDisplay);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }

    // Lưu dữ liệu vào SharedPreferences
    private void saveData() {
        String name = etName.getText().toString();
        String studentID = etStudentID.getText().toString();

        if (name.isEmpty() || studentID.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Nếu mã sinh viên tồn tại thì không cho lưu
        String existingName = sharedPreferences.getString(KEY_STUDENT_ID, null);
        if (existingName != null && existingName.equals(studentID)) {
            Toast.makeText(this, "Mã sinh viên đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu dữ liệu
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_STUDENT_ID, studentID);
        editor.apply();

        Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
    }

    private void showData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, "Không có dữ liệu");
        String studentID = sharedPreferences.getString(KEY_STUDENT_ID, "Không có dữ liệu");

        tvDisplay.setText("Họ tên: " + name + "\nMã sinh viên: " + studentID);
    }
}