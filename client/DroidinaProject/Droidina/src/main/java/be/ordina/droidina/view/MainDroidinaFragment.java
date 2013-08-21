package be.ordina.droidina.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.ordina.droidina.R;

/**
 * Created by ordinamobile on 27/06/13.
 */
public class MainDroidinaFragment extends Fragment {

    public static Fragment newInstance(){ //Bundle args){
        MainDroidinaFragment fragment = new MainDroidinaFragment();
        //Currently no arguments need to be set
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.main_droidina_fragment, container, false);

        return fragmentView;
    }
}
