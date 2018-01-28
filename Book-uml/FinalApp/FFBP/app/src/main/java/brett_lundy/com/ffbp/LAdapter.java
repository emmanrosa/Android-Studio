package brett_lundy.com.ffbp;

/**
 * Created by emmanuel on 9/30/17.
 */

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LAdapter extends ArrayAdapter<ListName>{
    private Activity context;
    private int resource;
    private List<ListName> list_books;
   // private String mUserId;


    public LAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ListName> ls) {
        super(context, resource, ls);
        this.context = context;
        this.resource = resource;
        list_books = ls;
       // mUserId = _mUserId;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(resource,null);
        String dollarSign="$";
        String condition="Condition: ";

            // title
            TextView TextView1 = (TextView) v.findViewById(R.id.bookname1);
            TextView1.setText(list_books.get(position).getTitle());

            // isbn
            TextView TextView2 = (TextView) v.findViewById(R.id.isbn1);
            TextView2.setText(list_books.get(position).getIsbn());

            // price
            TextView TextView3 = (TextView) v.findViewById(R.id.price1);
            TextView3.setText(dollarSign + list_books.get(position).getPrice());

            // condition
            TextView TextView4 = (TextView) v.findViewById(R.id.condd);
            TextView4.setText(condition + list_books.get(position).getCond());

            // title
            ImageView imageView = (ImageView) v.findViewById(R.id.image_list1);
            Glide.with(context).load(list_books.get(position).getImages()).into(imageView);
            //imageView.setImageBitmap(currentlist.getImages(b));
        //}
        return v;
    }
}
