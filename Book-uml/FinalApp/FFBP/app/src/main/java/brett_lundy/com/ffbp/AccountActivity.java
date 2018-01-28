package brett_lundy.com.ffbp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private DatabaseReference databaseReference;
    private ListView listView;
    private List<ListName> books_list;
    private LAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mUserId = mFirebaseUser.getUid();

            books_list = new ArrayList<>();
            listView = (ListView) findViewById(R.id.list1);

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait loading.....");
            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    books_list.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ListName ln = snapshot.getValue(ListName.class);
                        if (ln.getMUserId().equals(mUserId))
                            books_list.add(ln);
                    }

                    adapter = new LAdapter(AccountActivity.this, R.layout.list_item, books_list);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });

            //add
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AccountActivity.this, EditActivity.class);
                    startActivity(intent);
                }
            });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                ListName listName = books_list.get(i);
                DatabaseReference dR = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database).child(listName.getKey());
                showUpdateDeleteDialog(listName.getKey(),listName.getTitle(),listName.getIsbn(),listName.getPrice(), listName.getCond() ,listName.getImages(), listName.getMUserId(),i, listName.getMUserEmail());
                return true;
        }
    });

        }
    }

    private void showUpdateDeleteDialog(final String mkey,final String title,final String isbn, String price, String cond,final String muri,final String _muserid, int position,final String _museremail) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.user_list_item, null);
        dialogBuilder.setView(dialogView);

        final TextView txt_title = (TextView) dialogView.findViewById(R.id.title_update);
        txt_title.setText(title);
        final TextView txt_isbn = (TextView) dialogView.findViewById(R.id.ISBN_update);
        txt_isbn.setText(isbn);
        final EditText txt_price = (EditText) dialogView.findViewById(R.id.book_price_update);
        txt_price.setText(price);
        final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinner_condition_update);
        spinnerGenre.setPrompt(cond);
        final ImageView imageView = (ImageView) dialogView.findViewById(R.id.image_view_update);
        Glide.with(AccountActivity.this).load(books_list.get(position).getImages()).into(imageView);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdate);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Edit");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price  = txt_price.getText().toString().trim();
                String cond = spinnerGenre.getSelectedItem().toString();
                if (!TextUtils.isEmpty(price)) {
                    updatebook(mkey,title,isbn,price,cond,muri,_muserid, _museremail);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booksremove(mkey);
                books_list.clear();
                adapter.notifyDataSetChanged();
                b.dismiss();
            }
        });
    }

    // update node function
    private boolean updatebook(String mkey, String mtitle, String misbn, String mprice, String _cond, String muri, String _muserid, String _museremail) {
        //getting the specified node reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database).child(mkey);
        //updating book
        ListName ln = new ListName(mkey,mtitle,misbn,mprice,_cond,muri,_muserid, _museremail);
        dR.setValue(ln);
        Toast.makeText(getApplicationContext(), "Book Updated", Toast.LENGTH_LONG).show();
        books_list.clear();
        adapter.notifyDataSetChanged();
        return true;
    }

    //delete node function
    public boolean booksremove(String id){
        //getting rhe specified node reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database).child(id);
       //removing node
        dR.removeValue();
        return true;
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                // back home
                home();
                return true;
           case R.id.logout:
               mFirebaseAuth.signOut();
               loadLogInView();
               break;
        }
        return super.onOptionsItemSelected(item);
    }
    //DISPLAY INPUT DIALOG
    private void home() {
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
