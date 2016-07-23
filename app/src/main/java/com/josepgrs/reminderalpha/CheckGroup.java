package com.josepgrs.reminderalpha;

/**
 * Created by zepsantos on 08/06/16.
 */

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class CheckGroup {
    public String group;

    public CheckGroup() {

    }

    public CheckGroup(String group) {
        this.group = group;
    }
}
