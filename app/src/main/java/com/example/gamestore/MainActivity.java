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
    private TextView txtTotalEstimado;
    private LinearLayout layoutCarrito;

    private int cantidadProductos = 0;
    private final int precioProducto = 89999;

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
        txtTotalEstimado = findViewById(R.id.txtTotalEstimado);
        layoutCarrito = findViewById(R.id.layoutCarrito);

        btnAgregarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarProductoAlCarrito();
            }
        });

        btnVaciarCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaciarCarrito();
            }
        });
    }

    private void agregarProductoAlCarrito() {
        if (txtCarritoVacio.getParent() != null) {
            layoutCarrito.removeView(txtCarritoVacio);
        }

        TextView nuevoProducto = new TextView(this);
        nuevoProducto.setText(getString(R.string.producto_carrito));
        nuevoProducto.setTextSize(16);
        nuevoProducto.setTextColor(getResources().getColor(android.R.color.white));
        nuevoProducto.setPadding(0, 0, 0, 20);

        layoutCarrito.addView(nuevoProducto);

        cantidadProductos++;
        txtContadorCarrito.setText(getString(R.string.productos_en_carrito_base) + cantidadProductos);

        int total = cantidadProductos * precioProducto;
        txtTotalEstimado.setText(getString(R.string.total_estimado_base) + total);

        Toast.makeText(this, getString(R.string.mensaje_producto_agregado), Toast.LENGTH_SHORT).show();
    }

    private void vaciarCarrito() {
        layoutCarrito.removeAllViews();
        layoutCarrito.addView(txtCarritoVacio);

        cantidadProductos = 0;
        txtContadorCarrito.setText(getString(R.string.productos_en_carrito));
        txtTotalEstimado.setText(getString(R.string.total_estimado));

        Toast.makeText(this, getString(R.string.mensaje_carrito_vaciado), Toast.LENGTH_SHORT).show();
    }
}