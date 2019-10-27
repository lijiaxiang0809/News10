package com.teach.news10.local_utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 任小龙 on 2019/5/18.
 */
public class HtmlUtil {
    public static String htmlScaleImage(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%;  width:auto ; height:auto !important;}</style>" +
                "</head>";
        String htmltext = "<html>" + head + "<body>" + bodyHTML + "</body></html>";
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        try {
            @SuppressLint("WrongConstant") TelephonyManager tel = (TelephonyManager) context.getSystemService("phone");
            return tel.getDeviceId() == null ? "" : tel.getDeviceId();
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static String replaceAdsUrlParams(Context context, String url) {
        if (url.contains("__TT__")) {
            url = url.replace("__TT__", String.valueOf(System.currentTimeMillis()));
        }

        if (url.contains("__IMEI__")) {
            url = url.replace("__IMEI__", getIMEI(context));
        }

        if (url.contains("__NET__")) {
            url = url.replace("__NET__", getNetWorkStatus(context));
        }

        if (url.contains("__MN__")) {
            url = url.replace("__MN__", Build.MODEL);
        }

        if (url.contains("__ANDROIDID__")) {
            url = url.replace("__ANDROIDID__", getAndroidID(context));
        }

        if (url.contains("__MNC__")) {
            url = url.replace("__MNC__", getMNC(context));
        }

        if (url.contains("__BN__")) {
            url = url.replace("__BN__", Build.BRAND);
        }

        return url;
    }

    public static String getMNC(Context context) {
        try {
            @SuppressLint("WrongConstant") TelephonyManager tel = (TelephonyManager) context.getSystemService("phone");
            String operator = tel.getSimOperator();
            if (operator != null) {
                if (!operator.equals("46000") && !operator.equals("46002") && !operator.equals("46007")) {
                    if (operator.equals("46001")) {
                        return "01";
                    } else {
                        return operator.equals("46003") ? "03" : "08";
                    }
                } else {
                    return "00";
                }
            } else {
                return "08";
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            return "08";
        }
    }

    public static String getAndroidID(Context context) {
        String androidId = "";

        try {
            androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return androidId;
    }

    public static String getNetWorkStatus(Context context) {
        try {
            if (context == null) {
                return "";
            } else {
                @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(0);
                NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(1);
                if (wifiNetInfo != null && wifiNetInfo.isConnected()) {
                    return "wifi";
                } else {
                    return mobNetInfo != null && mobNetInfo.isConnected() ? getNetworkTypeForAds(context) + "G" : "unknown";
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return "";
        }
    }

    public static int getNetworkTypeForAds(Context context) {
        if (context == null) {
            return 0;
        } else {
            @SuppressLint("WrongConstant") ConnectivityManager connManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connManager.getNetworkInfo(0);
            String strSubTypeName = networkInfo.getSubtypeName();
            NetworkInfo.State state = networkInfo.getState();
            if (state != NetworkInfo.State.CONNECTED && state != NetworkInfo.State.CONNECTING) {
                return 0;
            } else {
                switch (networkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 2;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 3;
                    case 13:
                        return 4;
                    default:
                        return !strSubTypeName.equalsIgnoreCase("TD-SCDMA") && !strSubTypeName.equalsIgnoreCase("WCDMA") && !strSubTypeName.equalsIgnoreCase("CDMA2000") ? 2 : 3;
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    public static Map<String, String> getAdsParams(Context context, int appPlatform) {
        Map<String, String> params = new HashMap();
        if (context == null) {
            return params;
        } else {
            params.put("net", getNetWorkStatus(context));
            PackageManager pm = context.getPackageManager();

            try {
                PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
                if (info != null) {
                    params.put("apvc", info.versionName);
                } else {
                    params.put("apvc", "");
                }
            } catch (PackageManager.NameNotFoundException var6) {
                params.put("apvc", "");
            }

            params.put("trace", "0");
            params.put("mn", Build.MODEL);
            params.put("os", "android");
            params.put("osv", Build.VERSION.SDK);
            params.put("bn", Build.MANUFACTURER);
            params.put("lt", "1");

            try {
                @SuppressLint("WrongConstant") TelephonyManager tel = (TelephonyManager) context.getSystemService("phone");
                params.put("imei", tel.getDeviceId());
                String operator = tel.getSimOperator();
                if (operator != null) {
                    if (!operator.equals("46000") && !operator.equals("46002") && !operator.equals("46007")) {
                        if (operator.equals("46001")) {
                            params.put("mnc", "01");
                        } else if (operator.equals("46003")) {
                            params.put("mnc", "03");
                        } else {
                            params.put("mnc", "08");
                        }
                    } else {
                        params.put("mnc", "00");
                    }
                } else {
                    params.put("mnc", "08");
                }
            } catch (Exception var7) {
                var7.printStackTrace();
                params.put("imei", "");
                params.put("mnc", "08");
            }

            @SuppressLint("WrongConstant") WindowManager manager = (WindowManager) context.getSystemService("window");
            Point point = new Point();
            if (Build.VERSION.SDK_INT >= 17) {
                manager.getDefaultDisplay().getRealSize(point);
                params.put("rs", point.x + "*" + point.y);
            } else {
                manager.getDefaultDisplay().getSize(point);
                params.put("rs", point.x + "*" + (point.y + getStatusBarHeight(context)));
            }

            params.put("dpr", String.valueOf(context.getResources().getDisplayMetrics().density));
            params.put("pl", Locale.getDefault().getLanguage());
            params.put("channel", "");
            params.put("tab", "");
            params.put("platform", String.valueOf(appPlatform));
            params.put("gps", "");
            params.put("androidid", getAndroidID(context));
            return params;
        }
    }

    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        } else {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            int height;
            if (resourceId > 0) {
                height = resources.getDimensionPixelSize(resourceId);
            } else {
                height = (int) Math.ceil((double) (25.0F * resources.getDisplayMetrics().density));
            }

            return height;
        }
    }
}
