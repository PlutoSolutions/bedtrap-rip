/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
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
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
    private final PointerBuffer filters = BufferUtils.createPointerBuffer((int)1);
    private static final SimpleCommandExceptionType OOPS;
    private static final SimpleCommandExceptionType MAP_NOT_FOUND;

    static {
        MAP_NOT_FOUND = new SimpleCommandExceptionType((Message)new class_2585("You must be holding a filled map."));
        OOPS = new SimpleCommandExceptionType((Message)new class_2585("Something went wrong."));
    }

    @Override
    public void build(LiteralArgumentBuilder<class_2172> literalArgumentBuilder) {
        literalArgumentBuilder.executes(this::lambda$build$0);
    }

    private int lambda$build$0(CommandContext commandContext) throws CommandSyntaxException {
        class_1799 class_17992 = this.getMap();
        if (class_17992 == null) {
            throw MAP_NOT_FOUND.create();
        }
        class_22 class_222 = class_1806.method_7997((class_1799)class_17992, (class_1937)SaveMapCommand.mc.field_1687);
        if (class_222 == null) {
            throw MAP_NOT_FOUND.create();
        }
        String string = TinyFileDialogs.tinyfd_saveFileDialog((CharSequence)"Save image", null, (PointerBuffer)this.filters, null);
        if (string == null) {
            throw OOPS.create();
        }
        if (!string.endsWith(".png")) {
            string = String.valueOf(new StringBuilder().append(string).append(".png"));
        }
        class_1011 class_10112 = new class_1011(128, 128, true);
        for (int i = 0; i < 128; ++i) {
            for (int j = 0; j < 128; ++j) {
                int n = class_222.field_122[i + j * 128] & 0xFF;
                if (n / 4 == 0) {
                    class_10112.method_4305(i, j, 0);
                    continue;
                }
                class_10112.method_4305(i, j, class_3620.field_16006[n / 4].method_15820(n & 3));
                if (!false) continue;
                return 0;
            }
            if (true) continue;
            return 0;
        }
        try {
            class_10112.method_4325(new File(string));
            class_10112.close();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return 1;
    }

    public SaveMapCommand() {
        super("save-map", "Saves a map to an image.", "sm");
        ByteBuffer byteBuffer = MemoryUtil.memASCII((CharSequence)"*.png");
        this.filters.put(byteBuffer);
        this.filters.rewind();
    }

    private class_1799 getMap() {
        class_1799 class_17992 = SaveMapCommand.mc.field_1724.method_6047();
        if (class_17992.method_7909() == class_1802.field_8204) {
            return class_17992;
        }
        class_17992 = SaveMapCommand.mc.field_1724.method_6079();
        if (class_17992.method_7909() == class_1802.field_8204) {
            return class_17992;
        }
        return null;
    }
}

