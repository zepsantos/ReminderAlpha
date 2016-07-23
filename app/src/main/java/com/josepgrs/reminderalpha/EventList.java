package com.josepgrs.reminderalpha;

import com.google.firebase.database.IgnoreExtraProperties;


// [START blog_user_class]
@IgnoreExtraProperties
public class EventList {


    public String group;

    public EventList() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public EventList(String group) {


        this.group = group;
    }

}
// [END blog_user_class]

