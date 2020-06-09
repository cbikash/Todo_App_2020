package np.com.cbikas.todoapp2020.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.com.cbikas.todoapp2020.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Don extends Fragment {

    public Don() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_don, container, false);
    }
}
