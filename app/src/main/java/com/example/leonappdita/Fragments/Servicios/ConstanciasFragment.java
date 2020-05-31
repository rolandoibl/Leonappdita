package com.example.leonappdita.Fragments.Servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.ExpandableListView.AdaptadorELVServicios;
import com.example.leonappdita.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConstanciasFragment extends Fragment {

    private ExpandableListView elvConstancias;
    private AdaptadorELVServicios adaptadorElv;
    private ArrayList<String> categorias;
    private Map<String,ArrayList<String>> mapChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_servesc_constancias,container,false);
        elvConstancias = v.findViewById(R.id.elvListaConstancias);
        categorias = new ArrayList<>();
        mapChild = new HashMap<>();
        cargarLista();
        return v;
    }

    private void cargarLista(){
        ArrayList<String> listAsignAprov = new ArrayList<>();
        ArrayList<String> listComprInscrip = new ArrayList<>();
        ArrayList<String> listHistAcadem = new ArrayList<>();
        ArrayList<String> listVigenciaSeguro = new ArrayList<>();
        ArrayList<String> listAdeudoIdioma = new ArrayList<>();
        ArrayList<String> listTermEstudios = new ArrayList<>();
        ArrayList<String> listPasante = new ArrayList<>();

        categorias.add("Asignaturas aprobadas (sem. anterior)");
        categorias.add("Comprobante de inscripción");
        categorias.add("Historial académico");
        categorias.add("Vigencia seguro IMSS");
        categorias.add("Adeudo del idioma");
        categorias.add("Constancia estudios terminados");
        categorias.add("Carta de pasante");

        listAsignAprov.add("Asignaturas aprovadadas en ordinario con su calificación");
        listAsignAprov.add("Requiere haberse inscrito el semestre anterior");
        listAsignAprov.add("Entrega inmediata");

        listComprInscrip.add("Se indica que el alumno está inscrito y se especifican fechas");
        listComprInscrip.add("Requiere estar inscrito");
        listComprInscrip.add("Entrega inmediata");

        listHistAcadem.add("Relación no oficial de asignaturas inscritas y calificación");
        listHistAcadem.add("Entrega inmediata");

        listVigenciaSeguro.add("Datos del alumno y número de afiliación");
        listVigenciaSeguro.add("Requiere estar inscrito y estar afiliado al IMSS por parte de la UNAM");
        listVigenciaSeguro.add("Entrega al día siguiente");

        listAdeudoIdioma.add("Constancia de créditos y promedio que dice que debe cumplir con requisito del idioma");
        listAdeudoIdioma.add("Entrega en 3 días hábiles");

        listTermEstudios.add("Muestra acreditación de todas las materias del plan de estudios");
        listTermEstudios.add("Requiere tener el 100% de créditos");
        listTermEstudios.add("Entrega en 3 días hábiles");

        listPasante.add("Autorización para práctica profesional con promédio, créditos y duración inscrito");
        listPasante.add("Requiere promedio mínimo de 7 y 70% de avance de créditos o 100% de avance y no más de un año de haber acabado la carrera");
        listPasante.add("Entrega en 4 o 5 días hábiles");

        mapChild.put(categorias.get(0), listAsignAprov);
        mapChild.put(categorias.get(1), listComprInscrip);
        mapChild.put(categorias.get(2), listHistAcadem);
        mapChild.put(categorias.get(3), listVigenciaSeguro);
        mapChild.put(categorias.get(4), listAdeudoIdioma);
        mapChild.put(categorias.get(5), listTermEstudios);
        mapChild.put(categorias.get(6), listPasante);

        adaptadorElv = new AdaptadorELVServicios(categorias, mapChild, getActivity().getApplicationContext());
        elvConstancias.setAdapter(adaptadorElv);
    }
}
