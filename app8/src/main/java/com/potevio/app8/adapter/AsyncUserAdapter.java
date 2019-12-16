package com.potevio.app8.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.potevio.app8.R;
import com.potevio.app8.User;

import java.util.List;

public class AsyncUserAdapter extends RecyclerView.Adapter<AsyncUserAdapter.UserViewHold> {
    private AsyncListDiffer<User> mDiffer;

    public AsyncUserAdapter() {
        mDiffer = new AsyncListDiffer<>(this, new UserItemCallback());
    }

    public void submitList(List<User> datas) {
        mDiffer.submitList(datas);
    }

    @NonNull
    @Override
    public UserViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHold(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHold holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    public User getItem(int position) {
        return mDiffer.getCurrentList().get(position);
    }

    public static class UserViewHold extends RecyclerView.ViewHolder {
        private final TextView tvId;
        private final TextView tvName;
        private final TextView tvAge;

        public UserViewHold(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        public void setData(User user) {
            tvId.setText(user.getId() + "");
            tvName.setText(user.getName());
            tvAge.setText(user.getAge() + "");
        }
    }

    public static class UserItemCallback extends DiffUtil.ItemCallback<User> {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            if (oldItem.getName().equals(newItem.getName())) {
                if (oldItem.getAge() == newItem.getAge()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
