package com.example.gamestore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregarCarrito;
    private Button btnVaciarCarrito;
    private TextView txtContadorCarrito;
    private TextView txtCarritoVacio;
    private LinearLayout layoutCarrito;

    private int cantidadProductos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new androidx.core.view.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        btnAgregarCarrito = findViewById(R.id.btnAgregarCarrito);
        btnVaciarCarrito = findViewById(R.id.btnVaciarCarrito);
        txtContadorCarrito = findViewById(R.id.txtContadorCarrito);
        txtCarritoVacio = findViewById(R.id.txtCarritoVacio);
        layoutCarrito = findViewById(R.id.layoutCarrito);

        btnAgregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarProductoAlCarrito();
            }
        });
    }

    private void agregarProductoAlCarrito() {
        if (txtCarritoVacio.getParent() != null) {
            layoutCarrito.removeView(txtCarritoVacio);
        }

        TextView nuevoProducto = new TextView(this);
        nuevoProducto.setText("• Auriculares Redragon Zeus X RGB - $89.999");
        nuevoProducto.setTextSize(16);
        nuevoProducto.setTextColor(getResources().getColor(android.R.color.white));
        nuevoProducto.setPadding(0, 0, 0, 20);

        layoutCarrito.addView(nuevoProducto);

        cantidadProductos++;
        txtContadorCarrito.setText("Productos en carrito: " + cantidadProductos);

        Toast.makeText(this, "Producto agregado al carrito", Toast.LENGTH_SHORT).show();
    }
}