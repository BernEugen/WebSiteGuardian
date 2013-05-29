package com.berneugen.WebSiteGuardian.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.berneugen.WebSiteGuardian.R;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 29.05.13
 * Time: 22:58
 */
public class AllStatusFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.allstatus, container, false);
    }
}
