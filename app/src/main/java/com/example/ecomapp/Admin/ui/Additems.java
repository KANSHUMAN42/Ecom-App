package com.example.ecomapp.Admin.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Additems#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Additems extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Additems() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Additems.
     */
    // TODO: Rename and change types and number of parameters
    public static Additems newInstance(String param1, String param2) {
        Additems fragment = new Additems();
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

    ImageView imgaddimg;
    EditText itemsubcatogrytxt, pricetxt, itemcnttxt,itemdiscrpt,itemcatogry;
    Button adddetailsbtn;
     String catogryname,subcatogry,priceofitem,livestock,itemdiscrption,scurdate,scurtime,productrandomkey,downloadImgUrl;
    private static final int picnum = 1;
    private Uri returnUri;
    private StorageReference ProductImageRef;
    private DatabaseReference productref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_additems, container, false);
        ProductImageRef= FirebaseStorage.getInstance().getReference().child("product Images");
        productref=FirebaseDatabase.getInstance().getReference().child("Products");
        itemcatogry = root.findViewById(R.id.item_catogry);
        itemcatogry.setText(mParam1);
        imgaddimg = root.findViewById(R.id.img_admin_addpic);
        itemsubcatogrytxt = root.findViewById(R.id.item_subcatogry);
        pricetxt = root.findViewById(R.id.item_price);
        itemcnttxt = root.findViewById(R.id.item_count);
        adddetailsbtn = root.findViewById(R.id.item_addbtn);
        itemdiscrpt=root.findViewById(R.id.item_discription);
        imgaddimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Opengallary();

            }
        });
        adddetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validateproductdata();
            }
        });

        return root;
    }

    private void Opengallary() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       // Intent i = new Intent();
       // i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, picnum);
//
//        startActivityForResult(i, picnum);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
       // super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode ==picnum ) {
                Toast.makeText(getActivity(),"clicked ",Toast.LENGTH_SHORT).show();
                returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgaddimg.setImageBitmap(bitmapImage);
            }
        }

    }
    private void  Validateproductdata(){

                String catogryname=itemcatogry.getText().toString();
                String subcatogry=itemsubcatogrytxt.getText().toString();
                String priceofitem=pricetxt.getText().toString();
                String  livestock=itemcnttxt.getText().toString();
                String itemdiscrption=itemdiscrpt.getText().toString();
                if(subcatogry==null || priceofitem==null||livestock==null ||itemdiscrption==null){
                    Toast.makeText(getActivity(),"No column should remain empty",Toast.LENGTH_SHORT).show();
                }else{
                    Storepoductinfo();
                }


    }

    private void Storepoductinfo() {
        Calendar calender= Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("DD mmm,yyyy");
       scurdate=currentDate.format(calender.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        scurtime=currentTime.format(calender.getTime());
        productrandomkey=scurdate+scurtime;
        final StorageReference filePath=ProductImageRef.child(returnUri.getLastPathSegment()+productrandomkey+".jpg");
        final UploadTask uploadTask=filePath.putFile(returnUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String msg=e.toString();
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getActivity(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            throw task.getException();
                        }
                        downloadImgUrl=filePath.getDownloadUrl().toString();
                     return  filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"product Image saved to data base",Toast.LENGTH_SHORT).show();
                            SaveproductinfoDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveproductinfoDatabase() {
        String catogryname=itemcatogry.getText().toString();
        String subcatogry=itemsubcatogrytxt.getText().toString();
        String priceofitem=pricetxt.getText().toString();
        String  livestock=itemcnttxt.getText().toString();
        String itemdiscrption=itemdiscrpt.getText().toString();

        HashMap<String,Object>productmap=new HashMap<>();
        productmap.put("pid",productrandomkey);
        productmap.put("date",scurdate);
        productmap.put("time",scurtime);
        productmap.put("catogry",catogryname);
        productmap.put("subcatogry",subcatogry);
        productmap.put("price",priceofitem);
        productmap.put("livestock",livestock);
        productmap.put("discrpt",itemdiscrption);
        productmap.put("image",downloadImgUrl);
        productref.child(productrandomkey).updateChildren(productmap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            final NavController navController = Navigation.findNavController(getActivity(), R.id.admin_nav_host_fragment);
                            navController.navigate(R.id.admin_nav_home);
                            Toast.makeText(getActivity(),"Uploaded to db",Toast.LENGTH_SHORT).show();

                        }else{
                            String msg=task.getException().toString();
                            Toast.makeText(getActivity(),"Failed in db upload",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}