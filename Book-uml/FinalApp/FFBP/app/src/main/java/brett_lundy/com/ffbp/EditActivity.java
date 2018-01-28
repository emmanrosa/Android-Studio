package brett_lundy.com.ffbp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
/*
    This activity was created by Emmanuel Rosario with the help of a lot youtube tutorials and
    Brett's Firebase user id.
 */
public class EditActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 0;
    private EditText txt_title;
    private EditText txt_isbn;
    private EditText txt_price;
    private Button imageBtn;
    private Button btnProcess;
    private String title;
    private String isbn;
    private String price;
    private String sspinner;
    private String key;
    private Spinner mspinner;
    private ImageView imageView;
    private Uri actualUri;
    private StorageReference mStorageRef;
    private DatabaseReference ref;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUserEmail;
    private DatabaseReference mDatabase;
    private String mUserId;
    private List<ListName> books_list;
    private LAdapter adapter;
    Bitmap bm;
    public static final String fb_storage = "image/";
    public static final String fb_database = "Books";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        ref = FirebaseDatabase.getInstance().getReference(fb_database);

        //get the current user id
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId = mFirebaseUser.getUid();


        imageView = (ImageView) findViewById(R.id.image_view);
        txt_title = (EditText) findViewById(R.id.title_edit);
        txt_isbn = (EditText) findViewById(R.id.ISBN_edit);
        txt_price = (EditText) findViewById(R.id.book_price_edit);
        mspinner = (Spinner) findViewById(R.id.spinner_condition);
        imageBtn = (Button) findViewById(R.id.select_image);
        btnProcess = (Button)findViewById(R.id.button_process);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengettitle();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    public void openImageSelector(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode,resultCode, resultData);
        if ((requestCode == PICK_IMAGE_REQUEST) &&
                (resultCode == Activity.RESULT_OK) &&
                (resultData != null) &&
                (resultData.getData() != null)) {
                actualUri = resultData.getData();
                try {
                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(),actualUri);
                    imageView.setImageBitmap(bm);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public String getImageExt (Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    //save all the user inputs
    private boolean savebook() {
        boolean isAllOk = true;
        //set the user data
        title = txt_title.getText().toString();
        isbn = txt_isbn.getText().toString();
        price = txt_price.getText().toString();
        sspinner = mspinner.getSelectedItem().toString();

        //checks for user inputs are valid
        if (!checkIfValueSet(txt_title, "Title")) {
            isAllOk = false;
        }if (isbn.length() != 13)  {
            isAllOk = false;
            Toast.makeText(getApplicationContext(), "ISBN", Toast.LENGTH_SHORT).show();
        }if (!checkIfValueSet(txt_price, "Price")) {
            isAllOk = false;
        }if (actualUri == null) {
            isAllOk = false;
            imageBtn.setError("Missing image");
        }if (!isAllOk) {
            return false;
        }else {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Uploading");
            dialog.show();
            mUserId = mFirebaseUser.getUid();
            mUserEmail = mFirebaseUser.getEmail();

            StorageReference sf = mStorageRef.child(fb_storage + System.currentTimeMillis() + "." +
                    getImageExt(actualUri));
            sf.putFile(actualUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "uploaded",Toast.LENGTH_SHORT).show();
                    //set data
                    String upload_id = ref.push().getKey();
                    final FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    ListName s = new ListName(upload_id,title, isbn, price,sspinner,
                            taskSnapshot.getDownloadUrl().toString(), mUserId, mUserEmail);
                    //save data
                    ref.child(upload_id).setValue(s);
                }
            })
             .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                     double progress = (100 * taskSnapshot.getBytesTransferred()) /
                             taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded" + (int) progress + "0");
                 }
             });
            dialog.dismiss();
            return true;
        }
    }

    //checks for all user inputs are valid and returns a boolean if is valid or not
    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing book " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                // Save data to firebase
                if (!savebook()) {
                    // saying to onOptionsItemSelected that user clicked button
                    return true;
                }
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean opengettitle(){
        boolean isAllOk = true;
        TextRecognizer textRecognizer;
        textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (bm == null) {
            imageBtn.setError("Missing image");
            isAllOk= false;
        }else {
            if (!textRecognizer.isOperational()) {
                Log.e("ERROR", "Detector dependencies are not yet available");
            } else {
                Frame frame = new Frame.Builder().setBitmap(bm).build();
                SparseArray<TextBlock> items = textRecognizer.detect(frame);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); ++i) {
                    TextBlock item = items.valueAt(i);
                    stringBuilder.append(item.getValue());
                    //stringBuilder.append("\n");
                }
                txt_title.setText(stringBuilder.toString());
                isAllOk = true;
            }
        }
        return isAllOk;
    }

}