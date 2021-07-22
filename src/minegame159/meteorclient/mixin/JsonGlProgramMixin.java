/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_280
 *  net.minecraft.class_2960
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package minegame159.meteorclient.mixin;

import minegame159.meteorclient.utils.render.Outlines;
import net.minecraft.class_280;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={class_280.class})
public class JsonGlProgramMixin {
    @ModifyVariable(method={"<init>"}, at=@At(value="STORE"))
    private class_2960 onInitNewIdentifierModifyVariable(class_2960 identifier) {
        if (Outlines.loadingOutlineShader && identifier.method_12832().equals("shaders/program/my_entity_outline.json")) {
            return new class_2960("meteor-client", identifier.method_12832());
        }
        return identifier;
    }

    @ModifyVariable(method={"getShader"}, at=@At(value="STORE"))
    private static class_2960 onGetShaderNewIdentifierModifyVariable(class_2960 identifier) {
        if (Outlines.loadingOutlineShader && identifier.method_12832().equals("shaders/program/my_entity_sobel.fsh")) {
            return new class_2960("meteor-client", identifier.method_12832());
        }
        return identifier;
    }
}

