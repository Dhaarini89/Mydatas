package tinker.apps.myyoutube;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.Collections;

public class Myadapter extends BaseAdapter {
    ArrayList youtubelist = new ArrayList<>();
     boolean isEnable=false;
     Context c;
     ArrayList<Integer> selectList= new ArrayList<>();
    View v;
    public Myadapter(Context context, ArrayList arrayList) {
        youtubelist = arrayList;
        c=context;
    }


    @Override
    public int getCount() {
        return youtubelist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        v = convertView;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_layout, null);
        String Url = "https://img.youtube.com/vi/";
        String jpg = "hqdefault.jpg";
        String textA = youtubelist.get(position).toString().trim();
        String finalUrlA = Url + textA + "/" + jpg;
        LinearLayout linearLayout =(LinearLayout) v.findViewById(R.id.layoutinner);
        ImageButton imageView = (ImageButton) v.findViewById(R.id.button);
        ImageView check=(ImageView) v.findViewById(R.id.check_box);
        if (!TextUtils.isEmpty(textA)) {
            Glide.with(imageView.getContext())
                    .load(finalUrlA)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                imageView.setBackground(resource);
                            }
                        }
                    });
        } else {
            imageView.setBackgroundColor(Color.BLACK);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEnable)
                {
                    // when action mode is enable
                    // call method

                    ClickItem(check,linearLayout,position);
                }
                else {
                    Intent I = new Intent(view.getContext(), Player.class);
                    I.putExtra("link", textA);
                    view.getContext().startActivity(I);
                }
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //  int column= (getAdapterPosition()*4);
                if(!isEnable) {
                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                            MenuInflater menuInflater = actionMode.getMenuInflater();
                            menuInflater.inflate(R.menu.delete, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                            isEnable=true;
                            ClickItem(check,linearLayout,position);
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            switch (id) {
                                case R.id.action_delete:
                                    DB deleteDB=new DB(v.getContext());
                                    //    ArrayList<Integer> new;
                                   // Collections.sort(selectList);
                                   // Collections.reverse(selectList);
                                    for(Integer ivalue:selectList)
                                    {
                                        int data=ivalue;
                                        youtubelist.remove(data);
                                       int abc=deleteDB.deleteProduct(ivalue);
                                    //    for(int i=ivalue;i<(deleteDB.totalcount()+1);i++)
                                        //{
                                          //  int updatecheck = deleteDB.updateno(i);
                                        //}

                                    }

                                    actionMode.finish();
                                    break;


                            }
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode actionMode) {
                            isEnable=false;
                            selectList.clear();
                            notifyDataSetChanged();

                        }
                    };
                    ((AppCompatActivity) view.getContext()).startActionMode(callback);
                }
                else
                {
                   ClickItem(check,linearLayout,position);
                }
                return true;
            }
        });
        return v;

    }

    private void ClickItem(ImageView drawcheck, LinearLayout layout,Integer columnvalue) {
         Integer s = (Integer) columnvalue;
        // get selected item value
        //    String s=arrayList.get(holder.getAdapterPosition());
        // check condition
        if (drawcheck.getVisibility() == View.INVISIBLE) {
            drawcheck.setVisibility(View.VISIBLE);
            // set background color
            layout.setBackgroundColor(Color.LTGRAY);
            // add value in select array list
            selectList.add(s);
        } else {
            // when item selected
            // hide check box image
            drawcheck.setVisibility(View.INVISIBLE);
            // set background color
            layout.setBackgroundColor(Color.TRANSPARENT);
            // remove value from select arrayList
             selectList.remove(s);

        }
    }

}
