package com.lst.marrakechassistance.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lst.marrakechassistance.Activity.ResultRestaurantsActivity;
import com.lst.marrakechassistance.Activity.ResultsHotelsActivity;
import com.lst.marrakechassistance.Adapter.CommonQueryAdapter;
import com.lst.marrakechassistance.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SearchRestaurantsFragment extends Fragment {



    public SearchRestaurantsFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    EditText search;
    SpeechRecognizer mSpeechRecognizer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search_restaurants, container, false);
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        Intent mSpeechRecognizerIntent_EN = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent_EN.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent_EN.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"en-US");

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches.size() > 0) {
                    String query = matches.get(0);
                    search.setText(query);
                }
            }
            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        String[] commonQueries = getResources().getStringArray(R.array.hotels_common_queries);
        List<String> queryList = new ArrayList<>();
        Collections.addAll(queryList, commonQueries);

        // populate the recyclerView with the data from the resources files
        recyclerView = view.findViewById(R.id.recyclerViewCommonQueries);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_drawable));
        recyclerView.addItemDecoration(itemDecoration);
        CommonQueryAdapter adapter = new CommonQueryAdapter(queryList);
        recyclerView.setAdapter(adapter);

        // add the listener to the search EditText
        search = view.findViewById(R.id.editTextQuery);
        search.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                    || keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                // Retrieve the entered query
                String query = search.getText().toString().trim();

                // Start the ResultsActivity with the selected category and query
                Intent intent = new Intent(getContext(), ResultRestaurantsActivity.class);
                intent.putExtra("category", "hotels");
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                checkPermissions();
            }
            // implement the vocal search feature
            floatingActionButton = view.findViewById(R.id.restfloatingActionButton);
            floatingActionButton.setOnTouchListener((view1, motionEvent) -> {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    floatingActionButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorAccent));
                    search.setText("");
                    search.setHint("Listening ...");
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent_EN);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    floatingActionButton.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.colorPrimary));
                    mSpeechRecognizer.stopListening();
                }
                return false;
            });

            return false;
        });
        return view;
    }

    private void checkPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, 1);
    }

}