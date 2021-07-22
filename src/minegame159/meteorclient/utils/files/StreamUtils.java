/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
    public static void copy(InputStream inputStream, OutputStream outputStream) {
        byte[] arrby = new byte[512];
        try {
            int n;
            while ((n = inputStream.read(arrby)) != -1) {
                outputStream.write(arrby, 0, n);
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void copy(InputStream inputStream, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            StreamUtils.copy(inputStream, fileOutputStream);
            inputStream.close();
            ((OutputStream)fileOutputStream).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void copy(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            StreamUtils.copy((InputStream)fileInputStream, fileOutputStream);
            ((InputStream)fileInputStream).close();
            ((OutputStream)fileOutputStream).close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}

