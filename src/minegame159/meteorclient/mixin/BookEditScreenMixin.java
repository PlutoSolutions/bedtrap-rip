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
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_473
 *  org.lwjgl.glfw.GLFW
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package minegame159.meteorclient.mixin;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2507;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_339;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_473;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_473.class})
public abstract class BookEditScreenMixin
extends class_437 {
    @Shadow
    @Final
    private List<String> field_17116;
    @Shadow
    private int field_2840;
    @Shadow
    private boolean field_2837;

    @Shadow
    protected abstract void method_2413();

    public BookEditScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo info) {
        this.method_25411((class_339)new class_4185(4, 4, 70, 16, (class_2561)new class_2585("Copy"), button -> {
            class_2499 listTag = new class_2499();
            this.field_17116.stream().map(class_2519::method_23256).forEach(listTag::add);
            class_2487 tag = new class_2487();
            tag.method_10566("pages", (class_2520)listTag);
            tag.method_10569("currentPage", this.field_2840);
            FastByteArrayOutputStream bytes = new FastByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream((OutputStream)bytes);
            try {
                class_2507.method_10628((class_2487)tag, (DataOutput)out);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                GLFW.glfwSetClipboardString((long)Utils.mc.method_22683().method_4490(), (CharSequence)Base64.getEncoder().encodeToString(bytes.array));
            }
            catch (OutOfMemoryError exception) {
                GLFW.glfwSetClipboardString((long)Utils.mc.method_22683().method_4490(), (CharSequence)exception.toString());
            }
        }));
        this.method_25411((class_339)new class_4185(4, 24, 70, 16, (class_2561)new class_2585("Paste"), button -> {
            byte[] bytes;
            String clipboard = GLFW.glfwGetClipboardString((long)Utils.mc.method_22683().method_4490());
            if (clipboard == null) {
                return;
            }
            try {
                bytes = Base64.getDecoder().decode(clipboard);
            }
            catch (IllegalArgumentException ignored) {
                return;
            }
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
            try {
                class_2487 tag = class_2507.method_10627((DataInput)in);
                class_2499 listTag = tag.method_10554("pages", 8).method_10612();
                this.field_17116.clear();
                for (int i = 0; i < listTag.size(); ++i) {
                    this.field_17116.add(listTag.method_10608(i));
                }
                if (this.field_17116.isEmpty()) {
                    this.field_17116.add("");
                }
                this.field_2840 = tag.method_10550("currentPage");
                this.field_2837 = true;
                this.method_2413();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}

