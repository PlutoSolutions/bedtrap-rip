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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class AccountCache
implements ISerializable<AccountCache> {
    private ByteTexture headTexture;
    public String username = "";
    public String uuid = "";

    public boolean makeHead(String string) {
        try {
            byte[] arrby = new byte[192];
            int[] arrn = new int[4];
            BufferedImage bufferedImage = string.equals("steve") ? ImageIO.read(Utils.mc.method_1478().method_14486(new class_2960("meteor-client", "textures/steve.png")).method_14482()) : ImageIO.read(new URL(string));
            int n = 0;
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    bufferedImage.getData().getPixel(i, j, arrn);
                    for (int k = 0; k < 3; ++k) {
                        arrby[n] = (byte)arrn[k];
                        ++n;
                        if (2 >= -1) continue;
                        return false;
                    }
                    if (2 > 0) continue;
                    return false;
                }
                if (0 < 3) continue;
                return false;
            }
            this.headTexture = new ByteTexture(8, 8, arrby, ByteTexture.Format.RGB, ByteTexture.Filter.Nearest, ByteTexture.Filter.Nearest);
            return true;
        }
        catch (Exception exception) {
            MeteorClient.LOG.error(String.valueOf(new StringBuilder().append("Failed to read skin url. (").append(string).append(")")));
            return false;
        }
    }

    @Override
    public AccountCache fromTag(class_2487 class_24872) {
        if (!class_24872.method_10545("username") || !class_24872.method_10545("uuid")) {
            throw new NbtException();
        }
        this.username = class_24872.method_10558("username");
        this.uuid = class_24872.method_10558("uuid");
        return this;
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("username", this.username);
        class_24872.method_10582("uuid", this.uuid);
        return class_24872;
    }

    public ByteTexture getHeadTexture() {
        return this.headTexture;
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }
}

