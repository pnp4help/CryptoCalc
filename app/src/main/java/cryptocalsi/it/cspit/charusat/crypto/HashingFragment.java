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

public class HashingFragment extends Fragment {

    private String[] hashing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hashing_fragment,container,false);
        ListView listView=view.findViewById(R.id.hashing_listview);

        hashing = this.getContext().getResources().getStringArray(R.array.Hashing);
        getActionBar().setTitle("Hashing Algorithm");

        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,hashing);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_md5()).addToBackStack("Stack").commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_1()).addToBackStack("Stack").commit();
                        break;
                    case 2:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_224()).addToBackStack("Stack").commit();
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_256()).addToBackStack("Stack").commit();
                        break;
                    case 4:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_384()).addToBackStack("Stack").commit();
                        break;
                    case 5:
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new Hash_sha_512()).addToBackStack("Stack").commit();
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
