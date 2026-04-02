package com.example.orders_klimov;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.text.Regex;

public class MainActivity extends AppCompatActivity {

    EditText fio;
    EditText phone;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void AlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void AlertDialogConfirm() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Подтверждение заказа")
                .setMessage("Подтверждаете заказ?")
                .setCancelable(false)
                .setPositiveButton("Подтвердить",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog("Уведомление", "Заказ оформлен");
                                fio.setText("");
                                phone.setText("");
                                address.setText("");
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void OnArrange(View view) {
        fio = findViewById(R.id.fio);
        phone = findViewById(R.id.number);
        address = findViewById(R.id.address);

        if (fio.getText().length() == 0) AlertDialog("Уведомление", "Пожалуйста, укажите фамилию, имя и отчество.");
        else if (phone.getText().length() == 0 || !phone.getText().toString().matches("^8 \\(9\\d{2}\\)-\\d{3}-\\d{2}-\\d{2}$"))
            AlertDialog("Уведомление", "Пожалуйста, укажите номер телефона. Формат 8 (9xx)-xxx-xx-xx");
        else if (address.getText().length() == 0) AlertDialog("Уведомление", "Пожалуйста, укажите адрес доставки.");
        else  {
            AlertDialogConfirm();
        }
    }
}