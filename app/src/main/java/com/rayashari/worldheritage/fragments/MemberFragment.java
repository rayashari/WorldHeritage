package com.rayashari.worldheritage.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayashari.worldheritage.R;
import com.rayashari.worldheritage.adapter.MemberListAdapter;

/**
 * Created by docotel on 3/7/16.
 */
public class MemberFragment extends Fragment {

    private RecyclerView listMember;
    private LinearLayoutManager linearLayoutManager;
    private MemberListAdapter memberListAdapter;

    protected Context context;

    public static MemberFragment newInstance(){
        return new MemberFragment();
    }

//    int thumb[] = {R.drawable.ayana_shahab, R.drawable.beby_chaseara_anadila};
//
//    String name[] = {"Ayana Shahab", "Beby Chaesara Anadila"};
//
//    double lat[] = {37.6329946,37.73284};
//
//    double lng[] = {-122.4938344,-122.503065};
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_member, container, false);

        listMember = (RecyclerView) rootView.findViewById(R.id.listMember);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        linearLayoutManager = new LinearLayoutManager(context);
//        memberListAdapter = new MemberListAdapter(new OnClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, MemberDetail.class);
//                intent.putExtra(MemberDetail.EXTRA_NAME, name[position]);
//                intent.putExtra(MemberDetail.EXTRA_TITLE, name[position]);
//                intent.putExtra(MemberDetail.EXTRA_LAT, lat[position]);
//                intent.putExtra(MemberDetail.EXTRA_LNG, lng[position]);
//                intent.putExtra(MemberDetail.EXTRA_PICTURE, thumb[position]);
//
//                context.startActivity(intent);
//
//                //Toast.makeText(MemberFragment.this.getContext(), position  + "", Toast.LENGTH_SHORT).show();
//            }
//        });

        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);

        loadData();
    }

    private void loadData(){
//        List<Member> memberList = new ArrayList<>();
//        Member member;
//
//
//
//        for(int i=0; i < thumb.length; i++){
//            member = new Member();
//
//            member.setId(i+1);
//            member.setName(name[i]);
//            member.setlat(lat[i]);
//            member.setLang(lng[i]);
//            member.setThumb(thumb[i]);
//
//            memberList.add(member);
//        }
//
//        memberListAdapter.addAll(memberList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}