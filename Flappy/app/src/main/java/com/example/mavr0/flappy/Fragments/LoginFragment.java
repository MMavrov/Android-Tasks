package com.example.mavr0.flappy.Fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mavr0.flappy.R;

public class LoginFragment extends Fragment {
    private enum Country {
        Bulgaria,
        Russia,
        China
    }

    private View.OnClickListener playButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity().getApplicationContext(),
                    ((EditText)getView().findViewById(R.id.username)).getText(),
                    Toast.LENGTH_SHORT).show();


            Toast.makeText(getActivity().getApplicationContext(),
                    ((EditText)getView().findViewById(R.id.email)).getText(),
                    Toast.LENGTH_SHORT).show();

//            Toast.makeText(getActivity().getApplicationContext(),
//                    Country.values()[(((RadioGroup) getActivity().findViewById(R.id.country)).getCheckedRadioButtonId())].toString(),
//                    Toast.LENGTH_SHORT).show();
        }
    };

    private String username;
    private String e_mail;
    private Country country;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View loginFragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        Button playButton = (Button)loginFragmentView.findViewById(R.id.play_button);
        playButton.setOnClickListener(playButtonOnClickListener);

        return loginFragmentView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
