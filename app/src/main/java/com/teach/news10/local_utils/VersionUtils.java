package com.teach.news10.local_utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.teach.news10.Frame.Application10;


/**
 * Created by 任小龙 on 2019/5/10.
 */
public class VersionUtils {
    public static int getVersionCode(){
        PackageInfo info = null;
        try {
            info = Application10.sApplication.getPackageManager().getPackageInfo(Application10.sApplication.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException pE) {
            pE.printStackTrace();
        }
        return info.versionCode;
    }
}
