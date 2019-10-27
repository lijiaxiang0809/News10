
package com.teach.news10.local_utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.teach.news10.Frame.Application10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


/**
 * Created by qiaoliang on 2017/4/8. 常用工具类，减少业务代码里的null判断，getContext, 强转等
 */

public class Lang {

    public static Application app() {
        return Application10.getApplication();
    }

    // -----------------------------------------------------
    // 判空系列
    // -----------------------------------------------------
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isEmpty(Map<?, ?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> c) {
        return !isEmpty(c);
    }

    public static <T> boolean isEmpty(T[] c) {
        return c == null || c.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] c) {
        return !isEmpty(c);
    }

    public static boolean isEmpty(String c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNotEmpty(String c) {
        return !isEmpty(c);
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return o != null;
    }

    // -----------------------------------------------------
    // 相等系列
    // -----------------------------------------------------
    public static <T> boolean isEquals(T o1, T o2) {
        if (o1 == null || o2 == null)
            return false;
        return o1.equals(o2);
    }

    public static boolean isEqualsIgnoreCase(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        } else {
            return s1.equalsIgnoreCase(s2);
        }
    }

    public static <T> boolean isNotEquals(T o1, T o2) {
        if (o1 == null || o2 == null)
            return true;
        return !o1.equals(o2);
    }

    public static boolean isNotEqualsIgnoreCase(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return true;
        } else {
            return !s1.equalsIgnoreCase(s2);
        }
    }

    // -----------------------------------------------------
    // 字符串系列
    // -----------------------------------------------------
    public static String snull(Object maybeNull, String replaceNull) {
        return maybeNull == null ? replaceNull : maybeNull.toString();
    }

    public static String snull(Object maybeNull) {
        return maybeNull == null ? "" : maybeNull.toString();
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    // -----------------------------------------------------
    // 集合数组长度系列
    // -----------------------------------------------------
    public static int count(Collection<?> c) {
        return c == null ? 0 : c.size();
    }

    public static int count(Map<?, ?> c) {
        return c == null ? 0 : c.size();
    }

    public static <T> int count(T[] c) {
        return c == null ? 0 : c.length;
    }

    public static <T> int count(String s) {
        return s == null ? 0 : s.length();
    }

    // -----------------------------------------------------
    // 资源系列
    // -----------------------------------------------------
    public static String rstring(int id) {
        return app().getResources().getString(id);
    }

    public static String rstring(int id, Object... formatArgs) {
        return app().getResources().getString(id, formatArgs);
    }

    public static int rcolor(int id) {
        return app().getResources().getColor(id);
    }

    public static int rcolor(String color, int defaultColor) {
        try {
            return Color.parseColor(color);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultColor;
        }
    }

    public static int rcolor(String color) {
        return rcolor(color, Color.WHITE);
    }

    public static float rdimen(int id) {
        return app().getResources().getDimension(id);
    }

    public static Drawable rdrawable(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return app().getResources().getDrawable(id, null);
        } else {
            return app().getResources().getDrawable(id);
        }
    }

    // -----------------------------------------------------
    // 类型转换系列
    // -----------------------------------------------------
    public static int toInt(String strInt) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int toInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static long toLong(String strInt) {
        try {
            return Long.parseLong(strInt);
        } catch (Exception e) {
            return 0;
        }
    }

    public static long toLong(String strInt, int defaultValue) {
        try {
            return Long.parseLong(strInt);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static double toDouble(String strInt) {
        try {
            return Double.parseDouble(strInt);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double toDouble(String strInt, int defaultValue) {
        try {
            return Double.parseDouble(strInt);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static float toFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float toFloat(String str, int defaultValue) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // -----------------------------------------------------
    // 日期转换系列
    // -----------------------------------------------------
    public static String toDate(String pattern, String seconds) {
        return toDate(pattern, toInt(seconds));
    }

    public static String toDate(String pattern, long seconds) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(new Date(seconds * 1000));
    }

    public static String toDate(String pattern, Date date) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * 尝试把str的时间格式转为format的格式，通过new Date(str)来转，只能尽量转，失败则返回原来字符串
     *
     * @param str    类似Tue May 31 17:46:55 +0800 2011的字符串
     * @param format
     */
    public static String tryToDate(String str, String format) {
        try {
            Date date = new Date(str);
            DateFormat df = new SimpleDateFormat(format);
            String s = df.format(date);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    // -----------------------------------------------------
    // Bundle取值系列
    // -----------------------------------------------------
    public static String rstring(Intent intent, String key, String defaultValue) {
        String s = (intent != null && intent.hasExtra(key)) ? intent.getStringExtra(key) : defaultValue;
        if (TextUtils.isEmpty(s)) return defaultValue;
        return s;
    }

    public static String rstring(Bundle intent, String key, String defaultValue) {
        String s = (intent != null && intent.containsKey(key)) ? intent.getString(key) : defaultValue;
        if (TextUtils.isEmpty(s)) return defaultValue;
        return s;
    }

    public static String rstring(Intent intent, String key) {
        return (intent != null && intent.hasExtra(key)) ? intent.getStringExtra(key) : "";
    }

    public static String rstring(Bundle intent, String key) {
        return (intent != null && intent.containsKey(key)) ? intent.getString(key) : "";
    }

    public static int rint(Intent intent, String key, int defaultValue) {
        return (intent != null && intent.hasExtra(key)) ? intent.getIntExtra(key, defaultValue)
                : defaultValue;
    }

    public static long rint(Intent intent, String key, long defaultValue) {
        return (intent != null && intent.hasExtra(key)) ? intent.getLongExtra(key, defaultValue)
                : defaultValue;
    }

    public static int rint(Bundle intent, String key, int defaultValue) {
        return (intent != null && intent.containsKey(key)) ? intent.getInt(key) : defaultValue;
    }

    public static int rint(Intent intent, String key) {
        return (intent != null && intent.hasExtra(key)) ? intent.getIntExtra(key, 0) : 0;
    }

    public static int rint(Bundle intent, String key) {
        return (intent != null && intent.containsKey(key)) ? intent.getInt(key) : 0;
    }

    public static boolean rbool(Intent intent, String key, boolean defaultValue) {
        return (intent != null && intent.hasExtra(key)) ? intent.getBooleanExtra(key, defaultValue)
                : defaultValue;
    }

    public static boolean rbool(Bundle intent, String key, boolean defaultValue) {
        return (intent != null && intent.containsKey(key)) ? intent.getBoolean(key) : defaultValue;
    }

    public static boolean rbool(Intent intent, String key) {
        return (intent != null && intent.hasExtra(key)) && intent.getBooleanExtra(key, false);
    }

    public static boolean rbool(Bundle intent, String key) {
        return (intent != null && intent.containsKey(key)) && intent.getBoolean(key);
    }

    public static <T> T rparsable(Intent intent, String key) {
        return (intent != null && intent.hasExtra(key)) ? (T) intent.getParcelableExtra(key) : null;
    }

    public static <T> T rparsable(Bundle intent, String key) {
        return (intent != null && intent.containsKey(key)) ? (T) intent.getParcelable(key) : null;
    }

    public static <T> T rserializable(Intent intent, String key) {
        return (intent != null && intent.hasExtra(key)) ? (T) intent.getSerializableExtra(key)
                : null;
    }

    public static <T> T rserializable(Bundle intent, String key) {
        return (intent != null && intent.containsKey(key)) ? (T) intent.getSerializable(key) : null;
    }

    public static boolean containsKey(Intent intent, String key) {
        return intent != null && intent.hasExtra(key);
    }

    public static boolean containsKey(Bundle b, String key) {
        return b != null && b.containsKey(key);
    }

    // -----------------------------------------------------
    // 集合系列
    // -----------------------------------------------------
    public static <T> ArrayList<T> newArrayList(T... t) {
        ArrayList<T> list = new ArrayList<T>();
        if (t == null || t.length == 0) {
            return list;
        } else {
            for (int i = 0; i < t.length; i++) {
                list.add(t[i]);
            }
        }
        return list;
    }

    public static <K, V> Map<K, V> newHashMap(Object... args) {
        Map<K, V> m = new HashMap<K, V>();

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i += 2) {
                int ki = i;
                int vi = i + 1;
                if (ki < args.length && vi < args.length) {
                    K k = (K) args[ki];
                    V v = (V) args[vi];
                    m.put(k, v);
                }
            }
        }
        return m;
    }

    public static <K> Set<K> newHashSet(K... args) {
        Set<K> set = new HashSet<>();

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                set.add(args[i]);
            }
        }
        return set;
    }

    public static <T> T lastElement(List<T> list) {
        if (list == null || list.size() == 0)
            return null;
        return list.get(list.size() - 1);
    }

    public static <T> T firstElement(List<T> list) {
        if (list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    public static <T> T elementAt(List<T> list, int index, T defaultValue) {
        if (list == null || list.size() == 0)
            return defaultValue;
        if (index < 0 || index >= list.size())
            return defaultValue;
        return list.get(index);
    }

    public static <T> T elementAt(List<T> list, int index) {
        if (list == null || list.size() == 0)
            return null;
        if (index < 0 || index >= list.size())
            return null;
        return list.get(index);
    }

    public interface OnWalk<T> {

        /**
         * @param index current index
         * @param t     current element
         * @param total list.size()
         */
        boolean process(int index, T t, int total);

    }

    public interface Func<IN, OUT> {
        OUT process(IN ele);
    }

    public static <OUT, IN> List<OUT> cast(List<IN> list, Func<IN, OUT> func) {
        if (Lang.isEmpty(list))
            return new ArrayList<>();
        List<OUT> res = new ArrayList<>();
        for (IN ele : list) {
            res.add(func.process(ele));
        }
        return res;
    }

    public static <T> void each(Collection<T> c, OnWalk<T> callback) {
        if (callback == null)
            return;
        if (Lang.isNotEmpty(c)) {
            int count = 0;
            for (T o : c) {
                callback.process(count, o, c.size());
                count++;
            }
        }
    }

    public static <K, V> void each(Map<K, V> c, OnWalk<Map.Entry<K, V>> callback) {
        if (callback == null)
            return;
        if (c == null || c.size() == 0)
            return;

        int count = 0;
        for (Map.Entry<K, V> entry : c.entrySet()) {
            callback.process(count, entry, c.size());
            count++;
        }
    }

    public static <T> Collection<T> combine(Collection<T> c1, Collection<T> c2) {
        if (c1 == null && c2 == null)
            return null;
        if (c1 == null)
            return c2;
        if (c2 == null)
            return c1;
        c1.addAll(c2);
        return c1;
    }

    public static <T> boolean contains(T[] array, T ele) {
        if (null == array || array.length == 0)
            return false;
        if (ele == null)
            return false;
        for (T e : array) {
            if (isEquals(e, ele))
                return true;
        }
        return false;
    }

    public static <T> boolean contains(List<T> array, T ele) {
        if (null == array || array.size() == 0)
            return false;
        if (ele == null)
            return false;
        return array.contains(ele);
    }

    public static <T> boolean contains(Set<T> array, T ele) {
        if (null == array || array.size() == 0)
            return false;
        if (ele == null)
            return false;
        return array.contains(ele);
    }

    public static <K, V> boolean containsKey(Map<K, V> map, K key) {
        if (null == map || map.size() == 0)
            return false;
        if (key == null)
            return false;
        return map.containsKey(key);
    }

    public static <K, V> boolean containsValue(Map<K, V> map, V value) {
        if (null == map || map.size() == 0)
            return false;
        if (value == null)
            return false;
        return map.containsValue(value);
    }

    // -----------------------------------------------------
    // View系列
    // -----------------------------------------------------
    public static <T extends View> T findViewById(View v, int id) {
        return v == null ? null : (T) v.findViewById(id);
    }

    public static <T extends View> T findViewById(Activity v, int id) {
        return v == null ? null : (T) v.findViewById(id);
    }

    public static void setText(TextView tv, String text) {
        if (tv != null) {
            if (!TextUtils.isEmpty(text)) {
                tv.setText(text);
                if (tv instanceof EditText) {
                    ((EditText) tv).setSelection(text.length());
                }
            } else {
                tv.setText("");
            }
        }
    }

    public static void setHtml(TextView tv, String html) {
        if (tv != null) {
            if (!TextUtils.isEmpty(html)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    tv.setText(Html.fromHtml(html));
                    // tv.setText(Html.fromHtml(html,
                    // Html.FROM_HTML_MODE_COMPACT));
                } else {
                    tv.setText(Html.fromHtml(html));
                }
            } else {
                tv.setText("");
            }
        }
    }

    public static void setBackground(View v, Drawable d) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(d);
        } else {
            v.setBackgroundDrawable(d);
        }
    }

    public static boolean isViewAttachedToWindow(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return v.isAttachedToWindow();
        } else {
            return true;
        }
    }

    // -----------------------------------------------------
    // 适配系列
    // -----------------------------------------------------
    private static int statusBarHeight = 0;

    private static int screenWidth = 0;

    private static int screenHeight = 0;

    private static void init(Context context) {
        statusBarHeight = getStatusBarHeight(context);
        screenWidth = getScreenWidth(
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        screenHeight = getScreenHeight(
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
    }

    private static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static int getScreenHeight(WindowManager manager) {
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    private static int getScreenWidth(WindowManager manager) {
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int screenWidth() {
        if (screenWidth == 0) {
            init(app());
        }
        return screenWidth;
    }

    public static int screenHeight() {
        if (screenWidth == 0) {
            init(app());
        }
        return screenHeight;
    }

    public static int statusBarHeight() {
        if (screenWidth == 0) {
            init(app());
        }
        return statusBarHeight;
    }

    public static int dip2px(float dpValue) {
        final float scale = app().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = app().getResources().getDisplayMetrics().density;
        return (int) ((pxValue / scale + 0.5f) - 15);
    }

    public static int sp2px(float value) {
        DisplayMetrics metrics = app().getResources().getDisplayMetrics();
        return (int) (value * metrics.scaledDensity);
    }

    public static int px2sp(float value) {
        DisplayMetrics metrics = app().getResources().getDisplayMetrics();
        return (int) (value / metrics.scaledDensity);
    }

    public static final int WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static final int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;

    public static ViewGroup.MarginLayoutParams layoutParam(int w, int h) {
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(w, h);
        return lp;
    }

    public static ViewGroup.MarginLayoutParams changeLayoutParams(View v, int w, int h) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        if (lp == null) {
            lp = layoutParam(w, h);
        } else {
            lp.width = w;
            lp.height = h;
        }
        return lp;
    }

    public static void ensureSizeRatioAccordingToRealWidth(View v, int standardW, int standardH,
                                                           int realW) {
        int rh = (int) (standardH * realW * 1.0 / standardW);
        changeLayoutParams(v, realW, rh);
    }

    public static void ensureSizeRatioAccordingToRealHeight(View v, int standardW, int standardH,
                                                            int realH) {
        int rw = (int) (standardW * realH * 1.0 / standardH);
        changeLayoutParams(v, rw, realH);
    }

    // -----------------------------------------------------
    // String创建系列
    // -----------------------------------------------------
    public static String fromStream(InputStream inputStream) {
        String jsonStr = "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean len = false;

        try {
            int len1;
            while ((len1 = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len1);
            }

            jsonStr = new String(out.toByteArray());
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return jsonStr;
    }

    public static <T> String fromList(List<T> list, String delemeter, boolean ignoreNull) {
        if (Lang.isEmpty(list)) {
            return "";
        } else {
            String res = "";

            for (int i = 0; i < list.size(); ++i) {
                if (!ignoreNull || list.get(i) != null) {
                    res = res + Lang.snull(list.get(i)) + (i == list.size() - 1 ? "" : delemeter);
                }
            }

            return res;
        }
    }

    public interface StringConverter<T> {
        String convert(T t);
    }

    public static <T> String fromList(List<T> list, String delemeter, boolean ignoreNull,
                                      StringConverter<T> converter) {
        if (Lang.isEmpty(list)) {
            return "";
        } else {
            String res = "";

            for (int i = 0; i < list.size(); ++i) {
                if (!ignoreNull || list.get(i) != null) {
                    res = res + Lang.snull(converter.convert(list.get(i)))
                            + (i == list.size() - 1 ? "" : delemeter);
                }
            }

            return res;
        }
    }

    public static <T> String fromArray(T[] list, String delemeter, boolean ignoreNull) {
        if (Lang.isEmpty(list)) {
            return "";
        } else {
            String res = "";

            for (int i = 0; i < list.length; ++i) {
                if (!ignoreNull || list[i] != null) {
                    res = res + Lang.snull(list[i]) + (i == list.length - 1 ? "" : delemeter);
                }
            }

            return res;
        }
    }

    public static <T> String fromSet(Set<T> set, String delemeter, boolean ignoreNull) {
        if (Lang.isEmpty(set)) {
            return "";
        } else {
            String res = "";
            int i = 0;
            Iterator var5 = set.iterator();

            while (true) {
                while (var5.hasNext()) {
                    Object str = var5.next();
                    if (ignoreNull && str == null) {
                        ++i;
                    } else {
                        res = res + Lang.snull(str) + (i == set.size() - 1 ? "" : delemeter);
                        ++i;
                    }
                }

                return res;
            }
        }
    }

    public static String[] split(String s, String delemeter) {
        return s == null ? null : s.split(delemeter);
    }

    public static List<String> split(String s, int elementLength) {
        ArrayList list = new ArrayList();
        if (s == null) {
            return list;
        } else {
            if (s.length() <= elementLength) {
                list.add(s);
            } else {
                int start = 0;

                while (start < s.length()) {
                    int end = start + elementLength;
                    if (end > s.length()) {
                        end = s.length();
                    }

                    String element = s.substring(start, end);
                    start = end;
                    list.add(element);
                }
            }

            return list;
        }
    }

    public static String trimStart(String s, String trim) {
        if (s == null || trim == null)
            return s;
        if (s.startsWith(trim)) {
            return trimStart(s.substring(trim.length()), trim);
        } else {
            return s;
        }
    }

    public static String trimEnd(String s, String trim) {
        if (s == null || trim == null)
            return s;
        if (s.endsWith(trim)) {
            return trimEnd(s.substring(0, s.length() - trim.length()), trim);
        } else {
            return s;
        }
    }

    public static String trim(String s, String trim) {
        s = trimStart(s, trim);
        return trimEnd(s, trim);
    }

    // -----------------------------------------------------
    // path及uri系列
    // -----------------------------------------------------
    public static String pathToUri(String localPath) {
        if (Lang.isEmpty(localPath))
            return "";
        if (!localPath.startsWith("/"))
            return localPath;
        return "file://" + localPath;
    }

    // -----------------------------------------------------
    // 异步系列
    // -----------------------------------------------------
    private static class SafeHandler<T> extends Handler {
        private WeakReference<T> mContainer;

        private SafeHandler(T container) {
            super(Looper.getMainLooper());
            mContainer = new WeakReference<T>(container);
        }

        public static <T> SafeHandler<T> bind(T t) {
            return new SafeHandler<>(t);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        public void postTask(final Runnable r, long delayMillis) {
            this.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mContainer != null && mContainer.get() != null) {
                        r.run();
                    }
                }
            }, delayMillis);
        }
    }

    public static void post(Activity a, Runnable task, long delayMillis) {
        SafeHandler.bind(a).postTask(task, delayMillis);
    }

    public static void post(Fragment a, Runnable task, long delayMillis) {
        SafeHandler.bind(a).postTask(task, delayMillis);
    }

    public static boolean isActivityStillRunning(Activity activity) {
        if (activity == null)
            return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !activity.isDestroyed() && !activity.isFinishing();
        } else {
            return !activity.isFinishing();
        }
    }

    /**
     * fragment退出调用onDestroy不是很及时 取消请求滞后 所以得加个判断
     */
    public static boolean isFragmentStillRunning(Fragment f) {
        return f != null && f.getActivity() != null && !f.isDetached();
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    public static void copyToClipboard(String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) app().getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @return
     */
    public static String pasteFromClipboard() {
        // 得到剪贴板管理器
        try {
            ClipboardManager cmb = (ClipboardManager) app().getSystemService(Context.CLIPBOARD_SERVICE);
            return cmb.getText().toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static boolean isMainProcess(Context c) {
        if (c == null) return false;
        ActivityManager am = ((ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE));
        if (am == null) return false;
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        String mainProcessName = c.getPackageName();
        int myPid = android.os.Process.myPid();
        try {
            for (ActivityManager.RunningAppProcessInfo info : processInfoList) {
                if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int rint(Integer integer) {
        return integer == null ? 0 : integer;
    }

    public static boolean isNotNullOrEmpty(Object... obj) {
        if (obj == null || obj.length == 0) return false;
        boolean isNotNullOrEmpty = true;
        for (Object o : obj) {
            if (o == null) {
                isNotNullOrEmpty = false;
                break;
            }
            if (o instanceof String) {
                if (TextUtils.isEmpty((String) o)) isNotNullOrEmpty = false;
                break;
            }
            if (o instanceof Collection) {
                if (((Collection) o).size() == 0) isNotNullOrEmpty = false;
                break;
            }
            if (o instanceof Map) {
                if (((Map) o).size() == 0) isNotNullOrEmpty = false;
                break;
            }

        }
        return isNotNullOrEmpty;
    }


    public static String getText(String text) {
        return getText(text, "");
    }

    public static String getText(String text, int exclude) {
        return getText(String.valueOf(exclude).trim().equals(text) ? "" : text, "");
    }

    public static int parseColor(String color) {
        try {
            return Color.parseColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getText(String text, String defaultValue) {
        return isEmpty(text) ? defaultValue : text;
    }

    public static float getScreenScale() {
        return (float) screenHeight / (float) screenHeight;
    }

    public static Bitmap setImgSize(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }
}
