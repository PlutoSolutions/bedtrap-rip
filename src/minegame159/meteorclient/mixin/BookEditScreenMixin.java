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
import java.util.Objects;
import java.util.stream.Stream;
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

    public BookEditScreenMixin(class_2561 class_25612) {
        super(class_25612);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInit(CallbackInfo callbackInfo) {
        this.method_25411((class_339)new class_4185(4, 4, 70, 16, (class_2561)new class_2585("Copy"), this::lambda$onInit$0));
        this.method_25411((class_339)new class_4185(4, 24, 70, 16, (class_2561)new class_2585("Paste"), this::lambda$onInit$1));
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void lambda$onInit$1(class_4185 class_41852) {
        byte[] arrby;
        String string = GLFW.glfwGetClipboardString((long)Utils.mc.method_22683().method_4490());
        if (string == null) {
            return;
        }
        try {
            arrby = Base64.getDecoder().decode(string);
        }
        catch (IllegalArgumentException exception) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(arrby));
        {
            class_2487 class_24872 = class_2507.method_10627((DataInput)dataInputStream);
            class_2499 class_24992 = class_24872.method_10554("pages", 8).method_10612();
            this.field_17116.clear();
            for (int i = 0; i < class_24992.size(); ++i) {
                this.field_17116.add(class_24992.method_10608(i));
            }
            if (this.field_17116.isEmpty()) {
                this.field_17116.add("");
            }
            this.field_2840 = class_24872.method_10550("currentPage");
            this.field_2837 = true;
            this.method_2413();
            return;
        }
    }

    private void lambda$onInit$0(class_4185 class_41852) {
        class_2499 class_24992 = new class_2499();
        Stream<class_2519> stream = this.field_17116.stream().map(class_2519::method_23256);
        Objects.requireNonNull(class_24992);
        stream.forEach(class_24992::add);
        class_2487 class_24872 = new class_2487();
        class_24872.method_10566("pages", (class_2520)class_24992);
        class_24872.method_10569("currentPage", this.field_2840);
        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream((OutputStream)fastByteArrayOutputStream);
        try {
            class_2507.method_10628((class_2487)class_24872, (DataOutput)dataOutputStream);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        try {
            GLFW.glfwSetClipboardString((long)Utils.mc.method_22683().method_4490(), (CharSequence)Base64.getEncoder().encodeToString(fastByteArrayOutputStream.array));
        }
        catch (OutOfMemoryError outOfMemoryError) {
            GLFW.glfwSetClipboardString((long)Utils.mc.method_22683().method_4490(), (CharSequence)outOfMemoryError.toString());
        }
    }
}

