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

public class OtherFragment extends Fragment {

    String[] other;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.other_fragment,container,false);
        ListView listView=view.findViewById(R.id.other_listview);

        getActionBar().setTitle("Other");
        other=this.getContext().getResources().getStringArray(R.array.Other);

        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,other);
        listView.setAdapter(stringArrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_gcd_euclidian_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_modulus_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_prime_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_multiplicative_inverse_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 4:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_miller_rabin_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 5:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_chinese_remainder_fragment()).addToBackStack("Stack").commit();
                        break;
                    case 6:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Oth_square_multiply_fragment()).addToBackStack("Stack").commit();
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