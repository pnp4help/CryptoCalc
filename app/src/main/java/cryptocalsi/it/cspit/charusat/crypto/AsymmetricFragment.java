package cryptocalsi.it.cspit.charusat.crypto;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Objects;

public class AsymmetricFragment extends Fragment {
    private String[] asymmetric;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.asymmetric_fragment,container,false);
        ListView listView=view.findViewById(R.id.asymmetric_listview);

        getActionBar().setTitle("Asymmetric Algorithms");
        asymmetric=this.getContext().getResources().getStringArray(R.array.Asymmetric);

        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,asymmetric);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Asym_Diffie_Hellman_Fragment()).addToBackStack("Stack").commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Asym_RSA_Fragment()).addToBackStack("Stack").commit();
                        break;

                }
            }
        });

        return view;

    }

    private ActionBar getActionBar() {
        return ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
    }
}