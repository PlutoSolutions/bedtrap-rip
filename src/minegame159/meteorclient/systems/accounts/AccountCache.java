/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.systems.accounts;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.NbtException;
import minegame159.meteorclient.utils.render.ByteTexture;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

public class AccountCache
implements ISerializable<AccountCache> {
    private /* synthetic */ ByteTexture headTexture;
    public /* synthetic */ String username;
    public /* synthetic */ String uuid;

    public boolean makeHead(String llllllllllllllllIllllIIlIIllIlII) {
        try {
            BufferedImage llllllllllllllllIllllIIlIIlllIlI;
            byte[] llllllllllllllllIllllIIlIIlllIIl = new byte[192];
            int[] llllllllllllllllIllllIIlIIlllIII = new int[4];
            if (llllllllllllllllIllllIIlIIllIlII.equals("steve")) {
                BufferedImage llllllllllllllllIllllIIlIIlllllI = ImageIO.read(Utils.mc.method_1478().method_14486(new class_2960("meteor-client", "textures/steve.png")).method_14482());
            } else {
                llllllllllllllllIllllIIlIIlllIlI = ImageIO.read(new URL(llllllllllllllllIllllIIlIIllIlII));
            }
            int llllllllllllllllIllllIIlIIllIlll = 0;
            for (int llllllllllllllllIllllIIlIIlllIll = 0; llllllllllllllllIllllIIlIIlllIll < 8; ++llllllllllllllllIllllIIlIIlllIll) {
                for (int llllllllllllllllIllllIIlIIllllII = 0; llllllllllllllllIllllIIlIIllllII < 8; ++llllllllllllllllIllllIIlIIllllII) {
                    llllllllllllllllIllllIIlIIlllIlI.getData().getPixel(llllllllllllllllIllllIIlIIlllIll, llllllllllllllllIllllIIlIIllllII, llllllllllllllllIllllIIlIIlllIII);
                    for (int llllllllllllllllIllllIIlIIllllIl = 0; llllllllllllllllIllllIIlIIllllIl < 3; ++llllllllllllllllIllllIIlIIllllIl) {
                        llllllllllllllllIllllIIlIIlllIIl[llllllllllllllllIllllIIlIIllIlll] = (byte)llllllllllllllllIllllIIlIIlllIII[llllllllllllllllIllllIIlIIllllIl];
                        ++llllllllllllllllIllllIIlIIllIlll;
                    }
                }
            }
            llllllllllllllllIllllIIlIIllIIll.headTexture = new ByteTexture(8, 8, llllllllllllllllIllllIIlIIlllIIl, ByteTexture.Format.RGB, ByteTexture.Filter.Nearest, ByteTexture.Filter.Nearest);
            return true;
        }
        catch (Exception llllllllllllllllIllllIIlIIllIllI) {
            MeteorClient.LOG.error(String.valueOf(new StringBuilder().append("Failed to read skin url. (").append(llllllllllllllllIllllIIlIIllIlII).append(")")));
            return false;
        }
    }

    @Override
    public AccountCache fromTag(class_2487 llllllllllllllllIllllIIlIIIlllll) {
        AccountCache llllllllllllllllIllllIIlIIlIIIII;
        if (!llllllllllllllllIllllIIlIIIlllll.method_10545("username") || !llllllllllllllllIllllIIlIIIlllll.method_10545("uuid")) {
            throw new NbtException();
        }
        llllllllllllllllIllllIIlIIlIIIII.username = llllllllllllllllIllllIIlIIIlllll.method_10558("username");
        llllllllllllllllIllllIIlIIlIIIII.uuid = llllllllllllllllIllllIIlIIIlllll.method_10558("uuid");
        return llllllllllllllllIllllIIlIIlIIIII;
    }

    @Override
    public class_2487 toTag() {
        AccountCache llllllllllllllllIllllIIlIIlIIllI;
        class_2487 llllllllllllllllIllllIIlIIlIIlll = new class_2487();
        llllllllllllllllIllllIIlIIlIIlll.method_10582("username", llllllllllllllllIllllIIlIIlIIllI.username);
        llllllllllllllllIllllIIlIIlIIlll.method_10582("uuid", llllllllllllllllIllllIIlIIlIIllI.uuid);
        return llllllllllllllllIllllIIlIIlIIlll;
    }

    public AccountCache() {
        AccountCache llllllllllllllllIllllIIlIlIIlIll;
        llllllllllllllllIllllIIlIlIIlIll.username = "";
        llllllllllllllllIllllIIlIlIIlIll.uuid = "";
    }

    public ByteTexture getHeadTexture() {
        AccountCache llllllllllllllllIllllIIlIlIIlIIl;
        return llllllllllllllllIllllIIlIlIIlIIl.headTexture;
    }
}

