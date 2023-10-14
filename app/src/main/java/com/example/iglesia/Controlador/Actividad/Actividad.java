package com.example.iglesia.Controlador.Actividad;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.iglesia.Modelo.Actividad.ClaseActividad;
import com.example.iglesia.Modelo.Actividad.ModeloActividad;
import com.example.iglesia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Actividad extends AppCompatActivity {

    private static final String CHANNEL_ID = "actividad_channel";
    private static final int NOTIFICATION_ID = 1;

    EditText etIdActividad;
    EditText etNombreActividad;
    EditText etDPFechaActividad;
    EditText etTPHoraActividad;
    Button btnDPFecha;
    Button btnTPHora;
    Button btnAgregarActividad;
    Button btnEditarActividad;
    Button btnBuscarActividad;
    Button btnMostrarActividad;
    Button btnEliminarrActividad;
    Button btnLimpiarActividad;

    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private Date fechaActual;

    private ModeloActividad modeloActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);

        etIdActividad = findViewById(R.id.editTextIdActividad);
        etNombreActividad = findViewById(R.id.editTextNombreActividad);
        etDPFechaActividad = findViewById(R.id.editTextDPFechaActividad);
        etTPHoraActividad = findViewById(R.id.editTextTPHoraActividad);
        btnDPFecha = findViewById(R.id.btnDPFechaActividad);
        btnTPHora = findViewById(R.id.btnTPHoraActividad);
        btnAgregarActividad = findViewById(R.id.btnAddActividad);
        btnEditarActividad = findViewById(R.id.btnEditActividad);
        btnBuscarActividad = findViewById(R.id.btnBuscarActividad);
        btnEliminarrActividad = findViewById(R.id.btnDeleteActividad);
        btnMostrarActividad = findViewById(R.id.btnShowActividad);
        btnLimpiarActividad = findViewById(R.id.btnLimpiarActividad);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        fechaActual = Calendar.getInstance().getTime();

        // Crear instancia del modelo
        modeloActividad = new ModeloActividad(Actividad.this);

        // Botón Fecha Actividad
        btnDPFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int anio = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH);
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Actividad.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        String fecha = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        etDPFechaActividad.setText(fecha);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });

        // Botón Hora Actividad
        btnTPHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minuto = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Actividad.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        String hora = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
                        etTPHoraActividad.setText(hora);
                    }
                }, hora, minuto, true);
                timePickerDialog.show();
            }
        });

        // Actividad Agregar Actividad
        btnAgregarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(etIdActividad.getText().toString());
                    String nombre = etNombreActividad.getText().toString();
                    String fecha = etDPFechaActividad.getText().toString();
                    String hora = etTPHoraActividad.getText().toString();

                    modeloActividad.agregarActividad(id, nombre, fecha, hora);
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "La actividad se agregó correctamente", Toast.LENGTH_SHORT).show();
                    programarNotificacion(id, nombre, fecha, hora); // Programar notificación
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "El ID de la actividad debe ser un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Actividad Buscar Actividad
        btnBuscarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseActividad actividad = new ClaseActividad();
                String textId = etIdActividad.getText().toString();
                int id = Integer.valueOf(textId);
                modeloActividad.buscarActividad(actividad, id);
                etNombreActividad.setText(actividad.getNombre());
                etDPFechaActividad.setText(actividad.getFecha());
                etTPHoraActividad.setText(actividad.getHora());
            }
        });

        // Actividad Mostrar Actividades
        btnMostrarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarActividad = new Intent(getApplicationContext(), ActividadMostrar.class);
                startActivity(mostrarActividad);
            }
        });

        // Actividad Editar Actividad
        btnEditarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(etIdActividad.getText().toString());
                    String nombre = etNombreActividad.getText().toString();
                    String fecha = etDPFechaActividad.getText().toString();
                    String hora = etTPHoraActividad.getText().toString();

                    modeloActividad.editarActividad(id, nombre, fecha, hora);
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Los datos del actividad se actualizaron correctamente", Toast.LENGTH_SHORT).show();
                    programarNotificacion(id, nombre, fecha, hora); // Programar notificación
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "El ID del actividad debe ser un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Actividad Eliminar Actividad
        btnEliminarrActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id = Integer.parseInt(etIdActividad.getText().toString());
                    modeloActividad.eliminarActividad(id);
                    limpiarCampos();
                    Toast.makeText(getApplicationContext(), "Los datos del actividad se eliminaron correctamente", Toast.LENGTH_SHORT).show();
                    cancelarNotificacion(id); // Cancelar notificación si existe
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "El ID del actividad debe ser un número válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Actividad Limpiar Campos
        btnLimpiarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        etIdActividad.setText("");
        etNombreActividad.setText("");
        etDPFechaActividad.setText("");
        etTPHoraActividad.setText("");
    }

    private void programarNotificacion(int id, String nombre, String fecha, String hora) {
        try {
            Date fechaActividad = dateFormat.parse(fecha);
            Date horaActividad = timeFormat.parse(hora);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaActividad);
            calendar.set(Calendar.HOUR_OF_DAY, horaActividad.getHours());
            calendar.set(Calendar.MINUTE, horaActividad.getMinutes());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            long tiempoNotificacion = calendar.getTimeInMillis();

            if (tiempoNotificacion > System.currentTimeMillis()) {
                // Crear canal de notificación
                crearCanalNotificacion();

                // Configurar intent para la notificación
                Intent intent = new Intent(this, ActividadMostrar.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

                // Formatear la hora del actividad
                SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String horaFormateada = horaFormat.format(horaActividad);

                // Construir la notificación
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.actividades2)
                        .setContentTitle("Recordatorio de evento")
                        .setContentText("Evento: " + nombre)
                        .setContentText("Evento: " + nombre + "\nHora: " + horaFormateada)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

                // Mostrar la notificación
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void cancelarNotificacion(int id) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancel(id);
    }

    private void crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nombre = "Actividades";
            String descripcion = "Canal de notificaciones para actividades";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nombre, importancia);
            channel.setDescription(descripcion);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

} 