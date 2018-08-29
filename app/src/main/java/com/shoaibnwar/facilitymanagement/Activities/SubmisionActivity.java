package com.shoaibnwar.facilitymanagement.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shoaibnwar.facilitymanagement.R;
import com.shoaibnwar.facilitymanagement.Utilities.Permissions;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SubmisionActivity extends AppCompatActivity {

    RelativeLayout rl_bt_upload_image;
    EditText et_detail_text;
    LinearLayout ll_iv_1, ll_iv_2, ll_iv_3;
    ImageView iv_img_1, iv_img_2, iv_img_3;
    TextView tv_name_1, tv_name_2, tv_name_3;
    ImageView iv_crose_1, iv_crose_2, iv_crose_3;
    RelativeLayout rl_submit;


    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String  userChoosenTask;
    static Uri imageUri = null;
    public String profileImagePath_1="", profileImagePath_2="",  profileImagePath_3="";
    public Uri image_uri_1 = null, image_uri_2 = null, image_uri_3 = null;
    int imageSelectionCounter = 1;
    int counter = 1;

    FirebaseStorage storage;
    StorageReference storageReference;
    String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summision);

        init();
        gettingIntentValues();
        onImageClickHandler();
        onCrossClickHandler();
        submitClickHandler();
    }

    private void init()
    {
        rl_bt_upload_image = (RelativeLayout) findViewById(R.id.rl_bt_upload_image);
        et_detail_text = (EditText) findViewById(R.id.et_detail_text);
        ll_iv_1 = (LinearLayout) findViewById(R.id.ll_iv_1);
        ll_iv_2 = (LinearLayout) findViewById(R.id.ll_iv_2);
        ll_iv_3 = (LinearLayout) findViewById(R.id.ll_iv_3);
        iv_img_1 = (ImageView) findViewById(R.id.iv_img_1);
        iv_img_2 = (ImageView) findViewById(R.id.iv_img_2);
        iv_img_3 = (ImageView) findViewById(R.id.iv_img_3);
        tv_name_1 = (TextView) findViewById(R.id.tv_name_1);
        tv_name_2 = (TextView) findViewById(R.id.tv_name_2);
        tv_name_3 = (TextView) findViewById(R.id.tv_name_3);
        iv_crose_1 = (ImageView) findViewById(R.id.iv_crose_1);
        iv_crose_2 = (ImageView) findViewById(R.id.iv_crose_2);
        iv_crose_3 = (ImageView) findViewById(R.id.iv_crose_3);

        rl_submit = (RelativeLayout) findViewById(R.id.rl_submit);

    }

    private void gettingIntentValues()
    {
        Intent i = getIntent();
        itemId = i.getExtras().getString("item_id");
    }

    private void onImageClickHandler()
    {
        rl_bt_upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    selectImage();
            }
        });

    }

    private void selectImage() {


        final CharSequence[] items = { "Take Photo", "Select Photo From Gallery",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(SubmisionActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    userChoosenTask ="Take Photo";
                    boolean isCameraPermissionActive = Permissions.checkCameraPermission(SubmisionActivity.this);
                    if (isCameraPermissionActive){
                        cameraIntent();
                    }


                } else if (items[item].equals("Select Photo From Gallery")) {
                    userChoosenTask ="Select Photo From Gallery";
                    boolean isGalleryPermissionActive = Permissions.checkGalleryPermission(SubmisionActivity.this);
                    if (isGalleryPermissionActive) {
                        galleryIntent();
                    }

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        if (imageSelectionCounter<4) {
            builder.show();
        }
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("TAg", "The Request code is: data " + data);
        Log.e("TAg", "The Request code is: " + requestCode);
        Log.e("tag", "capture image uri in onActivityResult : " + imageUri);

        if (requestCode == SELECT_FILE) {
            onSelectFromGalleryResult(data);
        } else if (requestCode == REQUEST_CAMERA) {
            onCaptureImageResult(data);

        }
    }//end of onActivity result

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {

                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (data!=null) {
            imageUri = data.getData();
            Log.e("TAG", "onSelectFromGalleryResult: license file");
            try {

                if (imageSelectionCounter==1) {
                    ll_iv_1.setVisibility(View.VISIBLE);
                    profileImagePath_1 = getRealPathFromURI(imageUri);
                    image_uri_1 = imageUri;
                    File f = new File(profileImagePath_1);
                    String imageName = f.getName();
                    if (imageName.length()>10){
                        imageName = String.format("%."+ 10 +"s", imageName);
                    }
                    Log.e("TAG", "the image uri is: " + profileImagePath_1);
                    Log.e("TAG", "the image name: " + imageName);
                    imageName = imageName+"...";
                    tv_name_1.setText(imageName);
                    Picasso.with(SubmisionActivity.this).load(profileImagePath_1).into(iv_img_1);
                }
                else if (imageSelectionCounter==2) {
                    ll_iv_2.setVisibility(View.VISIBLE);
                    profileImagePath_2 = getRealPathFromURI(imageUri);
                    image_uri_2 = imageUri;
                    File f = new File(profileImagePath_2);
                    String imageName = f.getName();
                    if (imageName.length()>10){
                        imageName = String.format("%."+ 10 +"s", imageName);
                    }
                    Log.e("TAG", "the image uri is: " + profileImagePath_2);
                    Log.e("TAG", "the image name: " + imageName);
                    imageName = imageName+"...";
                    tv_name_2.setText(imageName);
                    Picasso.with(SubmisionActivity.this).load(profileImagePath_2).into(iv_img_2);
                }
                else if (imageSelectionCounter==3) {
                    ll_iv_3.setVisibility(View.VISIBLE);
                    profileImagePath_3 = getRealPathFromURI(imageUri);
                    image_uri_3 = imageUri;
                    File f = new File(profileImagePath_3);
                    String imageName = f.getName();
                    if (imageName.length()>10){
                        imageName = String.format("%."+ 10 +"s", imageName);
                    }
                    Log.e("TAG", "the image uri is: " + profileImagePath_3);
                    Log.e("TAG", "the image name: " + imageName);
                    imageName = imageName+"...";
                    tv_name_3.setText(imageName);
                    Picasso.with(SubmisionActivity.this).load(profileImagePath_3).into(iv_img_3);
                }

                imageSelectionCounter = imageSelectionCounter+1;

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri contentURI)
    {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void onCaptureImageResult(Intent data) {

        Log.e("tag" , "capture image uri 123 data: "+ data);

        if(data!= null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageUri = getImageUri(SubmisionActivity.this, photo);
            // bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

            if (imageSelectionCounter==1) {
                ll_iv_1.setVisibility(View.VISIBLE);
                profileImagePath_1 = getRealPathFromURI(imageUri);
                image_uri_1 = imageUri;
                File f = new File(profileImagePath_1);
                String imageName = f.getName();
                if (imageName.length()>10){
                    imageName = String.format("%."+ 10 +"s", imageName);
                }
                Log.e("TAG", "the image uri is: " + profileImagePath_1);
                Log.e("TAG", "the image name: " + imageName);
                tv_name_1.setText(imageName);
                Picasso.with(SubmisionActivity.this).load(profileImagePath_1).into(iv_img_1);
            }
            else if (imageSelectionCounter==2) {
                ll_iv_2.setVisibility(View.VISIBLE);
                profileImagePath_2 = getRealPathFromURI(imageUri);
                image_uri_2 = imageUri;
                File f = new File(profileImagePath_2);
                String imageName = f.getName();
                if (imageName.length()>10){
                    imageName = String.format("%."+ 10 +"s", imageName);
                }
                Log.e("TAG", "the image uri is: " + profileImagePath_2);
                Log.e("TAG", "the image name: " + imageName);
                tv_name_2.setText(imageName);
                Picasso.with(SubmisionActivity.this).load(profileImagePath_2).into(iv_img_2);
            }
           else if (imageSelectionCounter==3) {
                ll_iv_3.setVisibility(View.VISIBLE);
                profileImagePath_3 = getRealPathFromURI(imageUri);
                File f = new File(profileImagePath_3);
                String imageName = f.getName();
                if (imageName.length()>10){
                    imageName = String.format("%."+ 10 +"s", imageName);
                }
                Log.e("TAG", "the image uri is: " + profileImagePath_3);
                Log.e("TAG", "the image name: " + imageName);
                tv_name_3.setText(imageName);
                Picasso.with(SubmisionActivity.this).load(profileImagePath_3).into(iv_img_3);
            }

            imageSelectionCounter = imageSelectionCounter+1;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Permissions.MY_PERMISSION_GALLERY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     if(userChoosenTask.equals("Select Photo From Gallery"))
                        galleryIntent();
                }
                break;
            case Permissions.MY_PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                break;
        }
        }
    }

    private void onCrossClickHandler()
    {
        iv_crose_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_name_1.setText("");
                imageSelectionCounter = imageSelectionCounter-1;
                ll_iv_1.setVisibility(View.GONE);
                profileImagePath_1 = "";
                image_uri_1 = null;
            }
        });

        iv_crose_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_name_2.setText("");
                imageSelectionCounter = imageSelectionCounter-1;
                ll_iv_2.setVisibility(View.GONE);
                profileImagePath_2 = "";
                image_uri_2 = null;
            }
        });
        iv_crose_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_name_3.setText("");
                imageSelectionCounter = imageSelectionCounter-1;
                ll_iv_3.setVisibility(View.GONE);
                profileImagePath_3 = "";
                image_uri_3 = null;
            }
        });
    }

    private void insertNewDataToFirebase(final String userID, final String username, final String userCompanyName, final String userAddress, final String detailText, final ArrayList<Uri> arrayList, final String itemId){

        long time= System.currentTimeMillis();

        String mTime = String.valueOf(time);
        counter = 1;
        for (int i=0; i<arrayList.size();i++){
            Log.e("TAg", "the image uri is 111222 " + arrayList.get(i));
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final StorageReference ref = storageReference.child("facilitymanagement/"+mTime);
        StorageMetadata metadata = new StorageMetadata.Builder()
                .setContentType("image/jpg")
                .setCustomMetadata("userid", userID)
                .build();

        ref.putFile(arrayList.get(i), metadata)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(SubmisionActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        Log.e("TAG", "the download uri of images are: " + downloadUri);
                        //uploading to database
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("FacilityDB/"+itemId);
                        Log.e("TAg", "the key for reference " + mDatabase.getKey());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userid", userID);
                        hashMap.put("sender_name", username);
                        hashMap.put("sender_address", userAddress);
                        hashMap.put("sender_company", userCompanyName);
                        hashMap.put("imageurl", downloadUri.toString());
                        hashMap.put("description", detailText);
                        //Adding values
                        mDatabase.child(userID).setValue(hashMap);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SubmisionActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded "+(int)progress+"%");
                    }
                });
        counter = counter+1;
    }
    }



    private void submitClickHandler()
    {
        rl_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String detailText = et_detail_text.getText().toString();
                if (detailText.length()==0){
                    et_detail_text.setError("Should not be empty");
                }else {
                    ArrayList<Uri> imagesList = new ArrayList<>();
                    if (profileImagePath_1.length() > 4) {
                        imagesList.add(image_uri_1);
                    }
                    if (profileImagePath_2.length() > 4) {
                        imagesList.add(image_uri_2);
                    }
                    if (profileImagePath_3.length() > 4) {
                        imagesList.add(image_uri_3);
                    }
                    Log.e("TAg", "the size of array is " + imagesList.size());
                    insertNewDataToFirebase("5","Shoaib Anwar","Arfa Software Technology Park, 8th Floor Room#9", "Idea Centricity",  detailText, imagesList, "item " + itemId);
                }
            }
        });
    }
}
