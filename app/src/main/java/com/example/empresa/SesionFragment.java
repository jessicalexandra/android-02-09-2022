package com.example.empresa;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {

     EditText jetCorreo,jetClave;
     Button jbtingresar;
     TextView jtvRegistrar;
     RequestQueue rq;
     JsonRequest jrq;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion,container,false);
        rq= Volley.newRequestQueue(getContext());//requerimiento volley
        jetCorreo=vista.findViewById(R.id.etcorreo);
        jetClave=vista.findViewById(R.id.etclave);
        jbtingresar=vista.findViewById(R.id.btingresar);
        jtvRegistrar=vista.findViewById(R.id.tvregistrar);
        jbtingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Iniciar_Sesion();
            }
        });


                return vista;



    }
 private void Iniciar_Sesion(){
        String correo,clave;
        correo=jetCorreo.getText().toString();
        clave=jetClave.getText().toString();
        if (correo.isEmpty()|| clave.isEmpty())
        {
            Toast.makeText(getContext() ,"correo y clave son requeridos",Toast.LENGTH_LONG).show();
        }else
        {
            String url = "http://172.16.60.31:8080/WebServices/sesion.php?correo="+correo+"&clave="+clave;
            jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            rq.add(jrq);
        }
 }

    @Override
    public void onErrorResponse(VolleyError error) {
     Toast.makeText(getContext(),"Error en inicio de sesion",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Sesion iniciada",Toast.LENGTH_LONG).show();

    }
}