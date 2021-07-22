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
    private /* synthetic */ File file;

    public void save() {
        System llllllllllllllllIllIlIllIIIllIll;
        llllllllllllllllIllIlIllIIIllIll.save(null);
    }

    @Override
    public class_2487 toTag() {
        return null;
    }

    public void load(File llllllllllllllllIllIlIllIIIlIIll) {
        System llllllllllllllllIllIlIllIIIlIlII;
        File llllllllllllllllIllIlIllIIIlIIlI = llllllllllllllllIllIlIllIIIlIlII.getFile();
        if (llllllllllllllllIllIlIllIIIlIIlI == null) {
            return;
        }
        try {
            if (llllllllllllllllIllIlIllIIIlIIll != null) {
                llllllllllllllllIllIlIllIIIlIIlI = new File(llllllllllllllllIllIlIllIIIlIIll, llllllllllllllllIllIlIllIIIlIIlI.getName());
            }
            if (llllllllllllllllIllIlIllIIIlIIlI.exists()) {
                llllllllllllllllIllIlIllIIIlIlII.fromTag(class_2507.method_10633((File)llllllllllllllllIllIlIllIIIlIIlI));
            }
        }
        catch (IOException llllllllllllllllIllIlIllIIIlIlIl) {
            llllllllllllllllIllIlIllIIIlIlIl.printStackTrace();
        }
    }

    public void init() {
    }

    @Override
    public T fromTag(class_2487 llllllllllllllllIllIlIllIIIIIlIl) {
        return null;
    }

    public void load() {
        System llllllllllllllllIllIlIllIIIIllII;
        llllllllllllllllIllIlIllIIIIllII.load(null);
    }

    public File getFile() {
        System llllllllllllllllIllIlIllIIIIlIII;
        return llllllllllllllllIllIlIllIIIIlIII.file;
    }

    public void save(File llllllllllllllllIllIlIllIIlIIIII) {
        System llllllllllllllllIllIlIllIIlIIIIl;
        File llllllllllllllllIllIlIllIIlIIIll = llllllllllllllllIllIlIllIIlIIIIl.getFile();
        if (llllllllllllllllIllIlIllIIlIIIll == null) {
            return;
        }
        class_2487 llllllllllllllllIllIlIllIIlIIIlI = llllllllllllllllIllIlIllIIlIIIIl.toTag();
        if (llllllllllllllllIllIlIllIIlIIIlI == null) {
            return;
        }
        try {
            File llllllllllllllllIllIlIllIIlIIlll = File.createTempFile("meteor-client", llllllllllllllllIllIlIllIIlIIIll.getName());
            class_2507.method_10630((class_2487)llllllllllllllllIllIlIllIIlIIIlI, (File)llllllllllllllllIllIlIllIIlIIlll);
            if (llllllllllllllllIllIlIllIIlIIIII != null) {
                llllllllllllllllIllIlIllIIlIIIll = new File(llllllllllllllllIllIlIllIIlIIIII, llllllllllllllllIllIlIllIIlIIIll.getName());
            }
            llllllllllllllllIllIlIllIIlIIIll.getParentFile().mkdirs();
            StreamUtils.copy(llllllllllllllllIllIlIllIIlIIlll, llllllllllllllllIllIlIllIIlIIIll);
            llllllllllllllllIllIlIllIIlIIlll.delete();
        }
        catch (IOException llllllllllllllllIllIlIllIIlIIllI) {
            llllllllllllllllIllIlIllIIlIIllI.printStackTrace();
        }
    }

    public System(String llllllllllllllllIllIlIllIIlIlllI) {
        System llllllllllllllllIllIlIllIIllIIIl;
        if (llllllllllllllllIllIlIllIIlIlllI != null) {
            llllllllllllllllIllIlIllIIllIIIl.file = new File(MeteorClient.FOLDER, String.valueOf(new StringBuilder().append(llllllllllllllllIllIlIllIIlIlllI).append(".nbt")));
        }
    }
}

