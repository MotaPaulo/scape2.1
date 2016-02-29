package com.scape.ufv.scape;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by souzadomingues on 01/10/2015.
 */
public class fragment_tab4 extends Fragment {

    private TextView emDesenvolvimento;

    public static fragment_tab4 newInstance(){
        fragment_tab4 fragment = new fragment_tab4();
        return fragment;
    }

    public fragment_tab4(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tab4, container, false);
        emDesenvolvimento = (TextView) rootView.findViewById(R.id.TVdesenvolvimento);
        return rootView;
    }

}
