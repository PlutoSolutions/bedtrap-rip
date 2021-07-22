/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.ModContainer
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.TypeInsnNode
 *  org.objectweb.asm.tree.VarInsnNode
 *  org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
 *  org.spongepowered.asm.mixin.extensibility.IMixinInfo
 */
package minegame159.meteorclient;

import java.util.List;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class MixinPlugin
implements IMixinConfigPlugin {
    private /* synthetic */ String getFovDesc;
    private /* synthetic */ String gameRenderer;
    private /* synthetic */ boolean isResourceLoaderPresent;

    public void postApply(String lllllllllllllllllIIlIIIIlIllllll, ClassNode lllllllllllllllllIIlIIIIlIlllllI, String lllllllllllllllllIIlIIIIlIllllIl, IMixinInfo lllllllllllllllllIIlIIIIlIllllII) {
    }

    public void preApply(String lllllllllllllllllIIlIIIIllIIlIII, ClassNode lllllllllllllllllIIlIIIIllIIIlll, String lllllllllllllllllIIlIIIIllIIlIll, IMixinInfo lllllllllllllllllIIlIIIIllIIlIlI) {
        MixinPlugin lllllllllllllllllIIlIIIIllIIlIIl;
        if (lllllllllllllllllIIlIIIIllIIlIII.equals(lllllllllllllllllIIlIIIIllIIlIIl.gameRenderer)) {
            MethodNode lllllllllllllllllIIlIIIIllIlIIll = lllllllllllllllllIIlIIIIllIIlIIl.getMethod(lllllllllllllllllIIlIIIIllIIIlll, lllllllllllllllllIIlIIIIllIIlIIl.getFovDesc);
            if (lllllllllllllllllIIlIIIIllIlIIll == null) {
                throw new RuntimeException("[Meteor Client] Could not find method GameRenderer.getFov()");
            }
            AbstractInsnNode lllllllllllllllllIIlIIIIllIlIIlI = null;
            AbstractInsnNode lllllllllllllllllIIlIIIIllIlIIIl = null;
            VarInsnNode lllllllllllllllllIIlIIIIllIlIIII = null;
            for (AbstractInsnNode lllllllllllllllllIIlIIIIllIlIlII : lllllllllllllllllIIlIIIIllIlIIll.instructions) {
                if (lllllllllllllllllIIlIIIIllIlIlII.getOpcode() == 175 && lllllllllllllllllIIlIIIIllIlIIlI instanceof VarInsnNode && lllllllllllllllllIIlIIIIllIlIIlI.getOpcode() == 24 && lllllllllllllllllIIlIIIIllIlIIIl != null) {
                    lllllllllllllllllIIlIIIIllIlIIII = (VarInsnNode)lllllllllllllllllIIlIIIIllIlIIlI;
                    break;
                }
                lllllllllllllllllIIlIIIIllIlIIlI = lllllllllllllllllIIlIIIIllIlIlII;
                if (!(lllllllllllllllllIIlIIIIllIlIlII instanceof LabelNode)) continue;
                lllllllllllllllllIIlIIIIllIlIIIl = lllllllllllllllllIIlIIIIllIlIIlI;
            }
            if (lllllllllllllllllIIlIIIIllIlIIII == null) {
                throw new RuntimeException("[Meteor Client] Could not find injection point for GameRenderer.getFov()");
            }
            InsnList lllllllllllllllllIIlIIIIllIIllll = new InsnList();
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new FieldInsnNode(178, "minegame159/meteorclient/MeteorClient", "EVENT_BUS", "Lmeteordevelopment/orbit/IEventBus;"));
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new VarInsnNode(24, lllllllllllllllllIIlIIIIllIlIIII.var));
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new MethodInsnNode(184, "minegame159/meteorclient/events/render/GetFovEvent", "get", "(D)Lminegame159/meteorclient/events/render/GetFovEvent;"));
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new MethodInsnNode(185, "meteordevelopment/orbit/IEventBus", "post", "(Ljava/lang/Object;)Ljava/lang/Object;"));
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new TypeInsnNode(192, "minegame159/meteorclient/events/render/GetFovEvent"));
            lllllllllllllllllIIlIIIIllIIllll.add((AbstractInsnNode)new FieldInsnNode(180, "minegame159/meteorclient/events/render/GetFovEvent", "fov", "D"));
            lllllllllllllllllIIlIIIIllIlIIll.instructions.insert((AbstractInsnNode)lllllllllllllllllIIlIIIIllIlIIII, lllllllllllllllllIIlIIIIllIIllll);
            lllllllllllllllllIIlIIIIllIlIIll.instructions.remove((AbstractInsnNode)lllllllllllllllllIIlIIIIllIlIIII);
        }
    }

    public MixinPlugin() {
        MixinPlugin lllllllllllllllllIIlIIIIllllIIll;
        lllllllllllllllllIIlIIIIllllIIll.isResourceLoaderPresent = false;
    }

    public String getRefMapperConfig() {
        return null;
    }

    private MethodNode getMethod(ClassNode lllllllllllllllllIIlIIIIlIllIlIl, String lllllllllllllllllIIlIIIIlIllIIlI) {
        for (MethodNode lllllllllllllllllIIlIIIIlIllIlll : lllllllllllllllllIIlIIIIlIllIlIl.methods) {
            if (!lllllllllllllllllIIlIIIIlIllIlll.desc.equals(lllllllllllllllllIIlIIIIlIllIIlI)) continue;
            return lllllllllllllllllIIlIIIIlIllIlll;
        }
        return null;
    }

    public List<String> getMixins() {
        return null;
    }

    public boolean shouldApplyMixin(String lllllllllllllllllIIlIIIIlllIIlIl, String lllllllllllllllllIIlIIIIlllIIIlI) {
        if (lllllllllllllllllIIlIIIIlllIIIlI.endsWith("NamespaceResourceManagerMixin") || lllllllllllllllllIIlIIIIlllIIIlI.endsWith("ReloadableResourceManagerImplMixin")) {
            MixinPlugin lllllllllllllllllIIlIIIIlllIIllI;
            return !lllllllllllllllllIIlIIIIlllIIllI.isResourceLoaderPresent;
        }
        return true;
    }

    public void acceptTargets(Set<String> lllllllllllllllllIIlIIIIlllIIIII, Set<String> lllllllllllllllllIIlIIIIllIlllll) {
    }

    public void onLoad(String lllllllllllllllllIIlIIIIlllIllIl) {
        for (ModContainer lllllllllllllllllIIlIIIIlllIllll : FabricLoader.getInstance().getAllMods()) {
            if (!lllllllllllllllllIIlIIIIlllIllll.getMetadata().getId().startsWith("fabric-resource-loader")) continue;
            lllllllllllllllllIIlIIIIlllIllII.isResourceLoaderPresent = true;
            break;
        }
        lllllllllllllllllIIlIIIIlllIllII.gameRenderer = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_757");
        lllllllllllllllllIIlIIIIlllIllII.getFovDesc = String.valueOf(new StringBuilder().append("(L").append(FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_4184").replace('.', '/')).append(";FZ)D"));
    }
}

