/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.io.FastByteArrayOutputStream
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2507
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_339
 *  net.minecraft.class_3872
 *  net.minecraft.class_3872$class_3931
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  org.lwjgl.glfw.GLFW
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2507;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_3872;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_3872.class})
public class BookScreenMixin
extends class_437 {
    @Shadow
    private class_3872.class_3931 field_17418;
    @Shadow
    private int field_17119;

    public BookScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo info) {
        this.method_25411((class_339)new class_4185(4, 4, 70, 16, (class_2561)new class_2585("Copy"), button -> {
            class_2499 listTag = new class_2499();
            for (int i = 0; i < this.field_17418.method_17560(); ++i) {
                listTag.add((Object)class_2519.method_23256((String)this.field_17418.method_17563(i).getString()));
            }
            class_2487 tag = new class_2487();
            tag.method_10566("pages", (class_2520)listTag);
            tag.method_10569("currentPage", this.field_17119);
            FastByteArrayOutputStream bytes = new FastByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream((OutputStream)bytes);
            try {
                class_2507.method_10628((class_2487)tag, (DataOutput)out);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            GLFW.glfwSetClipboardString((long)Utils.mc.method_22683().method_4490(), (CharSequence)Base64.getEncoder().encodeToString(bytes.array));
        }));
    }
}

