package com.potevio.app8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHold> {

    private List<User> datas = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHold(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHold holder, int position) {
        User data = datas.get(position);
        holder.setData(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDatas(List<User> datas) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MyDiffCallback(this.datas, datas), false);
        this.datas.clear();
        this.datas.addAll(datas);
        result.dispatchUpdatesTo(this);
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

    static class MyDiffCallback extends DiffUtil.Callback {
        private List<User> oldUser, newUser;
        public MyDiffCallback(List<User> oldUser, List<User> newUser) {
            this.oldUser = oldUser;
            this.newUser = newUser;
        }
        @Override
        public int getOldListSize() {
            return oldUser.size();
        }
        @Override
        public int getNewListSize() {
            return newUser.size();
        }
        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldUser.get(oldItemPosition).getId() == newUser.get(newItemPosition).getId();
        }
        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            if (oldUser.get(oldItemPosition).getName().equals(newUser.get(newItemPosition).getName())) {
                if (oldUser.get(oldItemPosition).getAge() == newUser.get(newItemPosition).getAge()) {
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
