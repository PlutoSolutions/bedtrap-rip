/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  net.minecraft.class_1011
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1806
 *  net.minecraft.class_1937
 *  net.minecraft.class_2172
 *  net.minecraft.class_22
 *  net.minecraft.class_2585
 *  net.minecraft.class_3620
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.PointerBuffer
 *  org.lwjgl.system.MemoryUtil
 *  org.lwjgl.util.tinyfd.TinyFileDialogs
 */
package minegame159.meteorclient.systems.commands.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import minegame159.meteorclient.systems.commands.Command;
import net.minecraft.class_1011;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1806;
import net.minecraft.class_1937;
import net.minecraft.class_2172;
import net.minecraft.class_22;
import net.minecraft.class_2585;
import net.minecraft.class_3620;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

public class SaveMapCommand
extends Command {
    private final /* synthetic */ PointerBuffer filters;
    private static final /* synthetic */ SimpleCommandExceptionType OOPS;
    private static final /* synthetic */ SimpleCommandExceptionType MAP_NOT_FOUND;

    static {
        MAP_NOT_FOUND = new SimpleCommandExceptionType((Message)new class_2585("You must be holding a filled map."));
        OOPS = new SimpleCommandExceptionType((Message)new class_2585("Something went wrong."));
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> llllllllllllllllIllIlIlIIllIlIIl) {
        SaveMapCommand llllllllllllllllIllIlIlIIllIlIlI;
        llllllllllllllllIllIlIlIIllIlIIl.executes(llllllllllllllllIllIlIlIIlIlIlll -> {
            SaveMapCommand llllllllllllllllIllIlIlIIlIllIII;
            class_1799 llllllllllllllllIllIlIlIIlIlIllI = llllllllllllllllIllIlIlIIlIllIII.getMap();
            if (llllllllllllllllIllIlIlIIlIlIllI == null) {
                throw MAP_NOT_FOUND.create();
            }
            class_22 llllllllllllllllIllIlIlIIlIlIlIl = class_1806.method_7997((class_1799)llllllllllllllllIllIlIlIIlIlIllI, (class_1937)SaveMapCommand.mc.field_1687);
            if (llllllllllllllllIllIlIlIIlIlIlIl == null) {
                throw MAP_NOT_FOUND.create();
            }
            String llllllllllllllllIllIlIlIIlIlIlII = TinyFileDialogs.tinyfd_saveFileDialog((CharSequence)"Save image", null, (PointerBuffer)llllllllllllllllIllIlIlIIlIllIII.filters, null);
            if (llllllllllllllllIllIlIlIIlIlIlII == null) {
                throw OOPS.create();
            }
            if (!llllllllllllllllIllIlIlIIlIlIlII.endsWith(".png")) {
                llllllllllllllllIllIlIlIIlIlIlII = String.valueOf(new StringBuilder().append(llllllllllllllllIllIlIlIIlIlIlII).append(".png"));
            }
            class_1011 llllllllllllllllIllIlIlIIlIlIIll = new class_1011(128, 128, true);
            for (int llllllllllllllllIllIlIlIIlIllIlI = 0; llllllllllllllllIllIlIlIIlIllIlI < 128; ++llllllllllllllllIllIlIlIIlIllIlI) {
                for (int llllllllllllllllIllIlIlIIlIllIll = 0; llllllllllllllllIllIlIlIIlIllIll < 128; ++llllllllllllllllIllIlIlIIlIllIll) {
                    int llllllllllllllllIllIlIlIIlIlllII = llllllllllllllllIllIlIlIIlIlIlIl.field_122[llllllllllllllllIllIlIlIIlIllIlI + llllllllllllllllIllIlIlIIlIllIll * 128] & 0xFF;
                    if (llllllllllllllllIllIlIlIIlIlllII / 4 == 0) {
                        llllllllllllllllIllIlIlIIlIlIIll.method_4305(llllllllllllllllIllIlIlIIlIllIlI, llllllllllllllllIllIlIlIIlIllIll, 0);
                        continue;
                    }
                    llllllllllllllllIllIlIlIIlIlIIll.method_4305(llllllllllllllllIllIlIlIIlIllIlI, llllllllllllllllIllIlIlIIlIllIll, class_3620.field_16006[llllllllllllllllIllIlIlIIlIlllII / 4].method_15820(llllllllllllllllIllIlIlIIlIlllII & 3));
                }
            }
            try {
                llllllllllllllllIllIlIlIIlIlIIll.method_4325(new File(llllllllllllllllIllIlIlIIlIlIlII));
                llllllllllllllllIllIlIlIIlIlIIll.close();
            }
            catch (IOException llllllllllllllllIllIlIlIIlIllIIl) {
                llllllllllllllllIllIlIlIIlIllIIl.printStackTrace();
            }
            return 1;
        });
    }

    public SaveMapCommand() {
        super("save-map", "Saves a map to an image.", "sm");
        SaveMapCommand llllllllllllllllIllIlIlIIlllIIII;
        llllllllllllllllIllIlIlIIlllIIII.filters = BufferUtils.createPointerBuffer((int)1);
        ByteBuffer llllllllllllllllIllIlIlIIlllIIIl = MemoryUtil.memASCII((CharSequence)"*.png");
        llllllllllllllllIllIlIlIIlllIIII.filters.put(llllllllllllllllIllIlIlIIlllIIIl);
        llllllllllllllllIllIlIlIIlllIIII.filters.rewind();
    }

    private class_1799 getMap() {
        class_1799 llllllllllllllllIllIlIlIIllIIllI = SaveMapCommand.mc.field_1724.method_6047();
        if (llllllllllllllllIllIlIlIIllIIllI.method_7909() == class_1802.field_8204) {
            return llllllllllllllllIllIlIlIIllIIllI;
        }
        llllllllllllllllIllIlIlIIllIIllI = SaveMapCommand.mc.field_1724.method_6079();
        if (llllllllllllllllIllIlIlIIllIIllI.method_7909() == class_1802.field_8204) {
            return llllllllllllllllIllIlIlIIllIIllI;
        }
        return null;
    }
}

