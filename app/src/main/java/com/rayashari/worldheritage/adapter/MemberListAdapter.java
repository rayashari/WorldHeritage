package com.rayashari.worldheritage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rayashari.worldheritage.*;
import com.rayashari.worldheritage.model.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by docotel on 3/7/16.
 */
public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>{

    private List<Member> memberList;
    private OnClickListener listener;

    public MemberListAdapter(OnClickListener listener) {
        memberList = new ArrayList<>();
        this.listener = listener;
    }

    private void add(Member item) {
        memberList.add(item);
        notifyItemInserted(memberList.size() - 1);
    }

    public void addAll(List<Member> memberList) {
        for (Member member : memberList) {
            add(member);
        }
    }

    public void remove(Member item) {
        int position = memberList.indexOf(item);
        if (position > -1) {
            memberList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Member getItem(int position){
        return memberList.get(position);
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_member, parent, false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(final MemberViewHolder holder, final int position) {
        final Member member = memberList.get(position);

        holder.memberThumb.setImageResource(member.getThumb());
        holder.memberName.setText(member.getName());
        holder.memberLocation.setText(member.getLocation());

        holder.memberView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        public final View memberView;
        public final ImageView memberThumb;
        public final TextView memberName;
        public final TextView memberLocation;
        public MemberViewHolder(final View itemView) {
            super(itemView);
            memberView = itemView;
            memberThumb = (ImageView) itemView.findViewById(R.id.thumb);
            memberName = (TextView) itemView.findViewById(R.id.name);
            memberLocation = (TextView) itemView.findViewById(R.id.location);




        }
    }

    public static interface OnClickListener {
        void onClick(View v, int position);
    }
}
