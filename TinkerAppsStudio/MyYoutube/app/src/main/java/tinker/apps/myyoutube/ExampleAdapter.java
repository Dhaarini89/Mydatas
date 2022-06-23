package tinker.apps.myyoutube;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{
  private Context context;
    boolean isEnable=false;
    boolean isEnableB=false;
    boolean isEnableC=false;
    boolean isEnableD=false;
    ArrayList<Integer> selectList=new ArrayList<>();

    private ArrayList<Exampleclass> mexample;
    public ExampleAdapter(ArrayList<Exampleclass> exampleclassArrayList)
    {
        mexample = exampleclassArrayList;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder
    {
        public ImageButton imageView,imagebuttonB,imagebuttonC,imagebuttonD;
        private ImageView checkBox,checkBoxB,checkBoxC,checkBoxD;
        public TextView textView;
        public LinearLayout layout,layoutinner,layoutabc,layoutdef,layoutghi,layoutjkl;

        public ExampleViewHolder(@NonNull View itemview)
        {
            super(itemview);

            imageView = itemview.findViewById(R.id.button);
            imagebuttonB = itemview.findViewById(R.id.button2);
            imagebuttonC = itemview.findViewById(R.id.button3);
            imagebuttonD = itemview.findViewById(R.id.button4);
            checkBox=itemview.findViewById(R.id.check_box);
            checkBoxB=itemview.findViewById(R.id.check_boxB);
            checkBoxC=itemview.findViewById(R.id.checkC);
            checkBoxD=itemview.findViewById(R.id.checkboxD);
            layout=itemview.findViewById(R.id.abc);
            layoutinner=itemview.findViewById(R.id.abcinner);
            layoutdef=itemview.findViewById(R.id.definner);
            layoutghi=itemview.findViewById(R.id.ghiinner);
            layoutjkl=itemview.findViewById(R.id.jklinner);



        }
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        ExampleViewHolder exampleViewHolder = new ExampleViewHolder(view);

        return exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Exampleclass currentItem = mexample.get(position);
        String Url = "https://img.youtube.com/vi/";
        String jpg = "hqdefault.jpg";
        String textA = currentItem.getmText();
        String textB = currentItem.getTextB();
        String textC = currentItem.getTextC();
        String textD = currentItem.getTextD();
        String finalUrlA = Url + textA + "/" + jpg;
        String finalUrlB = Url + textB + "/" + jpg;
        String finalUrlC = Url + textC + "/" + jpg;
        String finalUrlD = Url + textD + "/" + jpg;

        if (!TextUtils.isEmpty(textA)) {
            // holder.imageView.setBackgroundResource(currentItem.getmImageResource());
            Glide.with(holder.imageView.getContext())
                    .load(finalUrlA)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                holder.imageView.setBackground(resource);
                            }
                        }
                    });
        } else {
            holder.imageView.setBackgroundColor(Color.BLACK);
        }
        if (!TextUtils.isEmpty(textB.trim())) {
            Glide.with(holder.imagebuttonB.getContext())
                    .load(finalUrlB)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                holder.imagebuttonB.setBackground(resource);
                            }
                        }
                    });
        } else {
            holder.imagebuttonB.setBackgroundColor(Color.BLACK);
        }
        if (!TextUtils.isEmpty(textC.trim())) {
            Glide.with(holder.imagebuttonC.getContext())
                    .load(finalUrlC)
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                holder.imagebuttonC.setBackground(resource);
                            }
                        }
                    });
        } else {
            holder.imagebuttonC.setBackgroundColor(Color.BLACK);
        }
        if (!TextUtils.isEmpty(textD.trim())) {
            Glide.with(holder.imagebuttonD.getContext())
                .load(finalUrlD)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            holder.imagebuttonD.setBackground(resource);
                        }
                    }
                });
        }
          else
        {
            holder.imagebuttonD.setBackgroundColor(Color.BLACK);
        }
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int column= (holder.getAdapterPosition()*4);
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
                            ClickItem(holder.checkBox,holder.layoutinner,column);
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            switch (id) {
                                case R.id.action_delete:
                                    DB deleteDB=new DB(holder.imageView.getContext());
                                    int abc=deleteDB.deleteProduct(column);
                                    for(int i=column;i<(deleteDB.totalcount()+1);i++) {
                                        int updatecheck = deleteDB.updateno(i);
                                    }
                                    break;


                            }
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode actionMode) {
                            isEnable=false;
                            notifyDataSetChanged();
                            selectList.clear();
                        }
                    };
                    ((AppCompatActivity) view.getContext()).startActionMode(callback);
                }
                else
                {
                    ClickItem(holder.checkBox,holder.layoutinner,column);
                }

                    return true;
                }

        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.layout.setBackgroundColor(Color.TRANSPARENT);
                Intent I=new Intent(view.getContext(),Player.class);
                I.putExtra("link",textA);
                view.getContext().startActivity(I);
            }
        });
        holder.imagebuttonB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int column = ((holder.getAdapterPosition() * 4)+1);
                if(!isEnableB)
                {
                ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater menuInflater = actionMode.getMenuInflater();
                        menuInflater.inflate(R.menu.delete, menu);
                          return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        isEnableB = true;
                        ClickItem(holder.checkBoxB,holder.layoutdef,column);
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.action_delete:
                                DB deleteDB = new DB(holder.imagebuttonB.getContext());
                                int abc = deleteDB.deleteProduct(column);
                                for (int i = column; i < (deleteDB.totalcount() + 1); i++) {
                                    int updatecheck = deleteDB.updateno(i);
                                }
                                actionMode.finish();
                                break;

                        }
                        return true;
                    }
                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        isEnableB=false;
                        notifyDataSetChanged();
                        selectList.clear();

                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            }
             else
            {
                ClickItem(holder.checkBoxB,holder.layoutdef,column);
            }
                return true;
            }

        });

        holder.imagebuttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(view.getContext(),Player.class);
                I.putExtra("link",textB);
                view.getContext().startActivity(I);
            }
        });
        holder.imagebuttonC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int column = ((holder.getAdapterPosition() * 4)+2);
                if(!isEnableC)
                 {
                  ActionMode.Callback callback = new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater menuInflater = actionMode.getMenuInflater();
                        menuInflater.inflate(R.menu.delete, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        isEnableC = true;
                        ClickItem(holder.checkBoxC,holder.layoutghi,column);
                        return true;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.action_delete:
                                DB deleteDB = new DB(holder.imagebuttonC.getContext());
                                int abc = deleteDB.deleteProduct(column);
                                for (int i = column; i < (deleteDB.totalcount() + 1); i++) {
                                    int updatecheck = deleteDB.updateno(i);
                                }
                                actionMode.finish();
                                break;


                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        isEnableC=false;
                        notifyDataSetChanged();
                        selectList.clear();

                    }
                };
                ((AppCompatActivity) view.getContext()).startActionMode(callback);
            }
                else
                {
                    ClickItem(holder.checkBoxC,holder.layoutghi,column);
                }

                return true;

            }

        });


        holder.imagebuttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(view.getContext(),Player.class);
                I.putExtra("link",textC);
                view.getContext().startActivity(I);
            }
        });
        holder.imagebuttonD.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int column = ((holder.getAdapterPosition() * 4)+3);
                if(!isEnableD) {
                   ActionMode.Callback callback = new ActionMode.Callback() {
                       @Override
                       public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                           MenuInflater menuInflater = actionMode.getMenuInflater();
                           menuInflater.inflate(R.menu.delete, menu);
                           return true;
                       }

                       @Override
                       public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                           isEnableD = true;
                           ClickItem(holder.checkBoxD,holder.layoutjkl,column);
                           return true;
                       }

                       @Override
                       public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                           int id = menuItem.getItemId();
                           switch (id) {
                               case R.id.action_delete:
                                   DB deleteDB = new DB(holder.imagebuttonD.getContext());
                                   for(Integer c:selectList)
                                  {
                                      int col=c;
                                      int abc = deleteDB.deleteProduct(col);
                                      for (int i = col; i < (deleteDB.totalcount() + 1); i++) {
                                          int updatecheck = deleteDB.updateno(i);
                                      }                                  }
                               //    int column=3;


                                 //  }

                                   actionMode.finish();
                                   break;

                           }
                           return true;
                       }

                       @Override
                       public void onDestroyActionMode(ActionMode actionMode) {
                           isEnableD=false;
                           notifyDataSetChanged();
                           selectList.clear();

                       }
                   };
                   ((AppCompatActivity) view.getContext()).startActionMode(callback);
               }
               else
               {
                ClickItem(holder.checkBoxD,holder.layoutjkl,column);
               }

                return true;
            }

        });

        holder.imagebuttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I=new Intent(view.getContext(),Player.class);
                I.putExtra("link",textD);
                view.getContext().startActivity(I);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mexample.size();
    }

    public void removeItem(int position) {
        mexample.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(0,mexample.size());
    }

    private void ClickItem(ImageView drawcheck,LinearLayout layout,int columnvalue) {
        Integer s=(Integer) columnvalue;
        // get selected item value
        //    String s=arrayList.get(holder.getAdapterPosition());
        // check condition
        if(drawcheck.getVisibility()==View.INVISIBLE)
        {
            drawcheck.setVisibility(View.VISIBLE);
            // set background color
            layout.setBackgroundColor(Color.LTGRAY);
            // add value in select array list
            selectList.add(s);
        }
        else
        {
            // when item selected
            // hide check box image
            drawcheck.setVisibility(View.INVISIBLE);
            // set background color
            layout.setBackgroundColor(Color.TRANSPARENT);
            // remove value from select arrayList
            selectList.remove(s);

        }
        // set text on view model
        // mainViewModel.setText(String.valueOf(selectList.size()));
        // mainViewModel.setText(s);
    }
}
