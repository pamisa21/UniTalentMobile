package com.example.unitalentv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFragment extends Fragment {

    SearchView searchView;
    ListView listView;

    String[] nameList = {"RYELL GWENN ANDRADE", "ROSETTE AYUNAR", "Sarah Jane Anez", "JUBEN DOMLI BALANDRA",
            "Klent Denzel Baylosis", "CEAZAR NATHANAEL BIGORNIA", "CHRISTAN CABASA", "Gabriel Catane",
            "Dave Edward Chavez", "ERNEST JOHN CORITICO", "Jhomar Dagoc", "MALACHE DAUGAN", "JERICO DELUTE",
            "Ruel Miguel Diaz", "Kenny Walter Duran", "GEN FRANCY EVANGELISTA", "SHEM KOBE JAYMA", "ELISHAPHAT PAUL JUAN",
            "PATREECE MONIQUE JUAN", "KARL KINJI LANDICHO", "Ervin John Libardos", "STARRY MARIZ LOVIDO",
            "ADRIANE CLAUDE NINO LOYLOY", "Regeryl Lumbayan", "Myca Mirar", "JIANNE MERIJO MENGOTE",
            "Simon Alexander Mulligan", "Ritchel Naquinez", "GRACE CLAIRE PATAC", "LYCA JANE PAVO",
            "MARK JUSTIN PENKIAN",  "Hannah Grace Questadio", "JAY RANES",  "DEXTHER REMADABIA",
            "LAILE PEEJAY SARMIENTO",  "Evangeline Silagan", "Tissa Mari Silverio"," NIEVEN CHARLES TAGACTAC",  "Beverlyn Zapanta"};

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        // Find views by their IDs
        searchView = rootView.findViewById(R.id.search_bar);
        listView = rootView.findViewById(R.id.list_item);

        // Convert array to ArrayList for easier manipulation
        arrayList = new ArrayList<>(Arrays.asList(nameList));


        arrayAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, arrayList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.dark_grey));
                return textView;
            }
        };


        // Set the adapter to the ListView
        listView.setAdapter(arrayAdapter);

        // Rest of your code...
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "You clicked " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        // Search View set Filter
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return rootView;
    }
}
