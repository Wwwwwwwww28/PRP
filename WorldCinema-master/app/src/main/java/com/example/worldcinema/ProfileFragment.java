package com.example.worldcinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.worldcinema.Service.ApiHandler;
import com.example.worldcinema.Service.IApi;
import com.example.worldcinema.Service.Models.ProfileResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    IApi IApi = new ApiHandler().getService();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String token = "835381";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView discusion;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        discusion = v.findViewById(R.id.tvDiscus);
        discusion.setOnClickListener(view -> {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG);
            Intent inten = new Intent(getContext(), DiscussionActivity.class);
            startActivity(inten);
        });
        doProfile();
        return v;
    }

    public void doProfile() {
        AsyncTask.execute(()->{
            IApi.getUser(token).enqueue(new Callback<List<ProfileResponse>>() {
                @Override
                public void onResponse(Call<List<ProfileResponse>> call, Response<List<ProfileResponse>> response) {
                    if(response.isSuccessful()){
                        TextView tv = getView().findViewById(R.id.textName);
                        TextView tv1 = getView().findViewById(R.id.textEmail);
                        ImageView iv = getView().findViewById(R.id.profileImg);
                        tv.setText(response.body().get(0).getFirstname());
                        tv1.setText(response.body().get(0).getEmail());
                        Picasso.with(getContext())
                                .load("http://cinema.areas.su/up/images/" + response.body().get(0).getAvatar())
                                .into(iv);
                    }else if(response.code() == 201){
                        Toast.makeText(getContext(), "не совсем OK", Toast.LENGTH_LONG);

                    }
                    else{
                        Toast.makeText(getContext(), "Совсем не ок" + response.code(), Toast.LENGTH_LONG);
                    }
                }
                @Override
                public void onFailure(Call<List<ProfileResponse>> call, Throwable t) {
                    Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG);
                }
            });
        });
    }

}