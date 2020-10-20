package com.example.ecomapp.Admin.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.ecomapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_nav_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_nav_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public admin_nav_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment admin_nav_home.
     */
    // TODO: Rename and change types and number of parameters
    public static admin_nav_home newInstance(String param1, String param2) {
        admin_nav_home fragment = new admin_nav_home();
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
     ImageView btn_beauty,btn_menfashion,btn_womenfashion,btn_furniture,btn_mobile,btn_gym,btn_food,btn_medicine,btn_books,
          btn_toys, btn_television,btn_utensils;
    Button newcatogry;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_admin_nav_home, container, false);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.admin_nav_host_fragment);
        btn_beauty = v.findViewById(R.id.img1);
        btn_menfashion = v.findViewById(R.id.img2);
        btn_womenfashion = v.findViewById(R.id.img3);
        btn_furniture = v.findViewById(R.id.img4);
        btn_books = v.findViewById(R.id.img5);
        btn_toys = v.findViewById(R.id.img6);
        btn_television = v.findViewById(R.id.img7);
        btn_gym = v.findViewById(R.id.img8);
        btn_mobile = v.findViewById(R.id.img9);
        btn_utensils = v.findViewById(R.id.img10);
        btn_food = v.findViewById(R.id.img11);
        btn_medicine = v.findViewById(R.id.img12);
        newcatogry=v.findViewById(R.id.item_newcatogry);
        btn_beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Fragment f=Additems.newInstance("Beauty","Beauty");
                navController.navigate(R.id.additems,f.getArguments());
            }
       });
        btn_menfashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Menwear","men");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_womenfashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Womenwear","women");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Furniture","furniture");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Books","books");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_toys.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Toys","toys");
                navController.navigate(R.id.additems,f.getArguments());
            }
        }));
        btn_television.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Television","television");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Gym","gym");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Mobile","mobile");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_utensils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("utensils","utensils");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Food","food");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        btn_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("Medicine","medicine");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });
        newcatogry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment f=Additems.newInstance("new Catogry","default catogry");
                navController.navigate(R.id.additems,f.getArguments());
            }
        });

        return v;
    }






}