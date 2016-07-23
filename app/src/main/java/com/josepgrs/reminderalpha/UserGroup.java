package com.josepgrs.reminderalpha;


import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class UserGroup {


    public String group;

    public UserGroup() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserGroup(String group) {


        this.group = group;
    }

}
// [END blog_user_class]