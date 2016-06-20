package com.rayashari.worldheritage.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by docotel on 3/7/16.
 */
public class Member implements Parcelable {

    private int id;
    private String name;
    private String location;
    private String desc;
    private double lat;
    private double lang;
    private int thumb;

    public Member() {
    }

    protected Member(Parcel in) {
        id = in.readInt();
        name = in.readString();
        lat = in.readDouble();
        lang = in.readDouble();
        thumb = in.readInt();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getlat() {
        return lat;
    }

    public void setlat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lat;
    }

    public void setLang(double lat) {
        this.lat = lat;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(desc);
        dest.writeDouble(lat);
        dest.writeDouble(lang);
        dest.writeInt(thumb);
    }
}
