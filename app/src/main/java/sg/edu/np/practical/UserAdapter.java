package sg.edu.np.practical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<User> userList;
    private RecyclerViewClickListener clickListener;


    public UserAdapter(ArrayList<User> users, RecyclerViewClickListener clickListener) {
        this.userList = users;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;

        if (viewType == 0) {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item,
                    parent,
                    false);
            return new UserViewHolder(item);
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.big_image,
                    parent,
                    false);
            return new BigImageViewHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            User user = userList.get(position);
            ((UserViewHolder)holder).name.setText(user.getName());
            ((UserViewHolder)holder).description.setText(user.getDescription());
            ((UserViewHolder)holder).image.setImageResource(android.R.drawable.sym_def_app_icon);
        } else {
            User user = userList.get(position);
            ((BigImageViewHolder)holder).name.setText(user.getName());
            ((BigImageViewHolder)holder).description.setText(user.getDescription());
            ((BigImageViewHolder)holder).image.setImageResource(android.R.drawable.sym_def_app_icon);
            ((BigImageViewHolder)holder).image.setImageResource(android.R.drawable.sym_def_app_icon);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String username = userList.get(position).getName();
        if (username.endsWith("7")) {
            return 1;
        }
        return 0;
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        TextView description;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public class BigImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        TextView description;
        ImageView bigImage;

        public BigImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView7);
            name = itemView.findViewById(R.id.name2);
            description = itemView.findViewById(R.id.description2);
            itemView.setOnClickListener(this);
            bigImage = itemView.findViewById(R.id.imageView3);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

}
