package brett_lundy.com.ffbp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
    private DatabaseReference databaseReference;
    private ListView listView;
    private List<ListName> books_list;
    private LAdapter adapter;
    private SearchView searchView;
    private Button all;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //final String herp = "derp";

        // Initialize Firebase Auth and Database Reference
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        final FirebaseUser user = mFirebaseAuth.getCurrentUser();
        //mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser == null || !(user.isEmailVerified())) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mUserId = mFirebaseUser.getUid();

            books_list = new ArrayList<>();
            listView = (ListView) findViewById(R.id.list);
            searchView = (SearchView) findViewById(R.id.searchbook);
            all = (Button) findViewById(R.id.all);

            books_list = new ArrayList<>();
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait loading.....");
            progressDialog.show();

            databaseReference = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database);

            progressDialog.dismiss();

            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            progressDialog.dismiss();
                            books_list.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                ListName ln = snapshot.getValue(ListName.class);
                                //if (ln.getMUserId().equals(mUserId))
                                books_list.add(ln);
                            }

                            adapter = new LAdapter(MainActivity.this, R.layout.list_item, books_list);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            progressDialog.dismiss();
                        }
                    });
                }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {return false; }

                @Override
                public boolean onQueryTextChange(final String newText) {
                    //Query query = databaseReference.orderByChild("isbn").startAt(newText).endAt("~");
                    Query query = databaseReference.orderByChild("isbn").startAt(newText).endAt(newText);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            progressDialog.dismiss();
                            books_list.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                ListName ln = snapshot.getValue(ListName.class);
                                books_list.add(ln);
                            }
                            adapter = new LAdapter(MainActivity.this,R.layout.list_item, books_list);
                            listView.setAdapter(adapter);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            progressDialog.dismiss();
                        }
                    });
                    return false;
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    ListName listName = books_list.get(i);
                    DatabaseReference dR = FirebaseDatabase.getInstance().getReference(EditActivity.fb_database).child(listName.getKey());
                    showUpdateDeleteDialog(listName.getKey(),listName.getTitle(),listName.getIsbn(),listName.getPrice(), listName.getCond() ,listName.getImages(), listName.getMUserId(),i, listName.getMUserEmail());
                    //return true;
                }
            });
        }

    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_acc:
                //
                account();
                // Exit activity
               // finish();
                return true;
                //break;
            case R.id.action_logout:
                mFirebaseAuth.signOut();
                loadLogInView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //DISPLAY INPUT DIALOG
    private void account() {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    private void showUpdateDeleteDialog(final String mkey,final String title,final String isbn, String price, String cond,final String muri,final String _muserid, int position,final String _museremail) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.buyers_view, null);
            dialogBuilder.setView(dialogView);
            String dollarSign="$";
            String condition="Condition: ";

            final TextView txt_title = (TextView) dialogView.findViewById(R.id.title_view);
            txt_title.setText(title);
            final TextView txt_isbn = (TextView) dialogView.findViewById(R.id.isbn_view);
            txt_isbn.setText(isbn);
            final TextView txt_price = (TextView) dialogView.findViewById(R.id.price_view);
            txt_price.setText(dollarSign + price);
            final TextView spinnerGenre = (TextView) dialogView.findViewById(R.id.cond_view);
            spinnerGenre.setText(condition + cond);
            final ImageView imageView = (ImageView) dialogView.findViewById(R.id.image_buyer);
            Glide.with(MainActivity.this).load(books_list.get(position).getImages()).into(imageView);
            final Button buttonsend = (Button) dialogView.findViewById(R.id.buttonsend);

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(_museremail, title);
            }
        });

        dialogBuilder.setTitle("Book View");
        final AlertDialog b = dialogBuilder.create();
        b.show();
        }


    protected void sendEmail(String To, String Title) {
        Log.i("Send email", "");
        String[] TO = {To};
        String[] CC = {""};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, (Title + " Offer"));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    }