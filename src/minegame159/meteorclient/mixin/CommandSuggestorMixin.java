/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.suggestion.Suggestions
 *  net.minecraft.class_2172
 *  net.minecraft.class_310
 *  net.minecraft.class_342
 *  net.minecraft.class_4717
 *  net.minecraft.class_4717$class_464
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package minegame159.meteorclient.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.concurrent.CompletableFuture;
import minegame159.meteorclient.systems.commands.Commands;
import minegame159.meteorclient.systems.config.Config;
import net.minecraft.class_2172;
import net.minecraft.class_310;
import net.minecraft.class_342;
import net.minecraft.class_4717;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={class_4717.class})
public abstract class CommandSuggestorMixin {
    @Shadow
    private ParseResults<class_2172> field_21610;
    @Shadow
    @Final
    private class_342 field_21599;
    @Shadow
    @Final
    private class_310 field_21597;
    @Shadow
    private boolean field_21614;
    @Shadow
    private CompletableFuture<Suggestions> field_21611;
    @Shadow
    private class_4717.class_464 field_21612;

    @Shadow
    protected abstract void method_23937();

    @Inject(method={"refresh"}, at={@At(value="INVOKE", target="Lcom/mojang/brigadier/StringReader;canRead()Z", remap=false)}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD)
    public void onRefresh(CallbackInfo ci, String string, StringReader reader) {
        String prefix = Config.get().prefix;
        int length = prefix.length();
        if (reader.canRead(length) && reader.getString().startsWith(prefix, reader.getCursor())) {
            int cursor;
            reader.setCursor(reader.getCursor() + length);
            assert (this.field_21597.field_1724 != null);
            CommandDispatcher<class_2172> commandDispatcher = Commands.get().getDispatcher();
            if (this.field_21610 == null) {
                this.field_21610 = commandDispatcher.parse(reader, (Object)Commands.get().getCommandSource());
            }
            if (!((cursor = this.field_21599.method_1881()) < 1 || this.field_21612 != null && this.field_21614)) {
                this.field_21611 = commandDispatcher.getCompletionSuggestions(this.field_21610, cursor);
                this.field_21611.thenRun(() -> {
                    if (this.field_21611.isDone()) {
                        this.method_23937();
                    }
                });
            }
            ci.cancel();
        }
    }
}

