package com.example.ling.beijingnews;

import android.app.Application;

import org.xutils.x;

/**
 * @author ling
 * @des
 * @updateAuthor
 * @updataDes
 */
public class BeiJingNewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
