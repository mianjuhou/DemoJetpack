package com.potevio.app10.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.potevio.app10.data.db.User;
import com.potevio.app10.databinding.ItemUserBinding;

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHold> {

    public static final DiffUtil.ItemCallback<User> mDiffItemItemCallback = new UserItemCallback();

    protected UserAdapter() {
        super(mDiffItemItemCallback);
    }

    @NonNull
    @Override
    public UserViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding =
                ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHold(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHold holder, int position) {
        holder.setData(getItem(position));
    }

    public static class UserViewHold extends RecyclerView.ViewHolder {
        public ItemUserBinding itemBinding;

        public UserViewHold(ItemUserBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void setData(User user) {
            if (user != null) {
                itemBinding.tvId.setText(user.getId() + "");
                itemBinding.tvName.setText(user.getName());
                itemBinding.tvAge.setText(user.getAge() + "");
            } else {
                itemBinding.tvId.setText("默认");
                itemBinding.tvName.setText("默认");
                itemBinding.tvAge.setText("默认");
            }
        }
    }

    public static class UserItemCallback extends DiffUtil.ItemCallback<User> {

        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getName().equals(newItem.getName()) ? oldItem.getAge() == newItem.getAge() : false;
        }
    }
}
