/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2507
 */
package minegame159.meteorclient.systems;

import java.io.File;
import java.io.IOException;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.utils.files.StreamUtils;
import minegame159.meteorclient.utils.misc.ISerializable;
import net.minecraft.class_2487;
import net.minecraft.class_2507;

public abstract class System<T>
implements ISerializable<T> {
    private File file;

    public void save() {
        this.save(null);
    }

    @Override
    public class_2487 toTag() {
        return null;
    }

    public void load(File file) {
        File file2 = this.getFile();
        if (file2 == null) {
            return;
        }
        try {
            if (file != null) {
                file2 = new File(file, file2.getName());
            }
            if (file2.exists()) {
                this.fromTag(class_2507.method_10633((File)file2));
            }
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void init() {
    }

    @Override
    public T fromTag(class_2487 class_24872) {
        return null;
    }

    public void load() {
        this.load(null);
    }

    public File getFile() {
        return this.file;
    }

    public void save(File file) {
        File file2 = this.getFile();
        if (file2 == null) {
            return;
        }
        class_2487 class_24872 = this.toTag();
        if (class_24872 == null) {
            return;
        }
        try {
            File file3 = File.createTempFile("meteor-client", file2.getName());
            class_2507.method_10630((class_2487)class_24872, (File)file3);
            if (file != null) {
                file2 = new File(file, file2.getName());
            }
            file2.getParentFile().mkdirs();
            StreamUtils.copy(file3, file2);
            file3.delete();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public System(String string) {
        if (string != null) {
            this.file = new File(MeteorClient.FOLDER, String.valueOf(new StringBuilder().append(string).append(".nbt")));
        }
    }
}

