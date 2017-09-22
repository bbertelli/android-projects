package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno.bertelli on 05/04/2017.
 */

public class User implements Parcelable {

    @SerializedName("name")
    public String name;

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatar;

    @SerializedName("company")
    public String company;

    @SerializedName("location")
    public String location;

    @SerializedName("public_repos")
    public String repositories;

    @SerializedName("followers")
    public String followers;

    @SerializedName("following")
    public String following;

    protected User(Parcel in) {
        name = in.readString();
        login = in.readString();
        avatar = in.readString();
        company = in.readString();
        location = in.readString();
        repositories = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(login);
        parcel.writeString(avatar);
        parcel.writeString(company);
        parcel.writeString(location);
        parcel.writeString(repositories);
        parcel.writeString(followers);
        parcel.writeString(following);
    }
}
