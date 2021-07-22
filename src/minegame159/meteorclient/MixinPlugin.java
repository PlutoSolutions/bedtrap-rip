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
import java.util.ListIterator;
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
    private String getFovDesc;
    private String gameRenderer;
    private boolean isResourceLoaderPresent = false;

    public void postApply(String string, ClassNode classNode, String string2, IMixinInfo iMixinInfo) {
    }

    public void preApply(String string, ClassNode classNode, String string2, IMixinInfo iMixinInfo) {
        if (string.equals(this.gameRenderer)) {
            MethodNode methodNode = this.getMethod(classNode, this.getFovDesc);
            if (methodNode == null) {
                throw new RuntimeException("[Meteor Client] Could not find method GameRenderer.getFov()");
            }
            AbstractInsnNode abstractInsnNode = null;
            AbstractInsnNode abstractInsnNode2 = null;
            VarInsnNode varInsnNode = null;
            for (AbstractInsnNode abstractInsnNode3 : methodNode.instructions) {
                if (abstractInsnNode3.getOpcode() == 175 && abstractInsnNode instanceof VarInsnNode && abstractInsnNode.getOpcode() == 24 && abstractInsnNode2 != null) {
                    varInsnNode = (VarInsnNode)abstractInsnNode;
                    break;
                }
                abstractInsnNode = abstractInsnNode3;
                if (!(abstractInsnNode3 instanceof LabelNode)) continue;
                abstractInsnNode2 = abstractInsnNode;
            }
            if (varInsnNode == null) {
                throw new RuntimeException("[Meteor Client] Could not find injection point for GameRenderer.getFov()");
            }
            ListIterator listIterator = new InsnList();
            listIterator.add((AbstractInsnNode)new FieldInsnNode(178, "minegame159/meteorclient/MeteorClient", "EVENT_BUS", "Lmeteordevelopment/orbit/IEventBus;"));
            listIterator.add((AbstractInsnNode)new VarInsnNode(24, varInsnNode.var));
            listIterator.add((AbstractInsnNode)new MethodInsnNode(184, "minegame159/meteorclient/events/render/GetFovEvent", "get", "(D)Lminegame159/meteorclient/events/render/GetFovEvent;"));
            listIterator.add((AbstractInsnNode)new MethodInsnNode(185, "meteordevelopment/orbit/IEventBus", "post", "(Ljava/lang/Object;)Ljava/lang/Object;"));
            listIterator.add((AbstractInsnNode)new TypeInsnNode(192, "minegame159/meteorclient/events/render/GetFovEvent"));
            listIterator.add((AbstractInsnNode)new FieldInsnNode(180, "minegame159/meteorclient/events/render/GetFovEvent", "fov", "D"));
            methodNode.instructions.insert((AbstractInsnNode)varInsnNode, (InsnList)listIterator);
            methodNode.instructions.remove((AbstractInsnNode)varInsnNode);
        }
    }

    public String getRefMapperConfig() {
        return null;
    }

    private MethodNode getMethod(ClassNode classNode, String string) {
        for (MethodNode methodNode : classNode.methods) {
            if (!methodNode.desc.equals(string)) continue;
            return methodNode;
        }
        return null;
    }

    public List<String> getMixins() {
        return null;
    }

    public boolean shouldApplyMixin(String string, String string2) {
        if (string2.endsWith("NamespaceResourceManagerMixin") || string2.endsWith("ReloadableResourceManagerImplMixin")) {
            return !this.isResourceLoaderPresent;
        }
        return true;
    }

    public void acceptTargets(Set<String> set, Set<String> set2) {
    }

    public void onLoad(String string) {
        for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
            if (!modContainer.getMetadata().getId().startsWith("fabric-resource-loader")) continue;
            this.isResourceLoaderPresent = true;
            break;
        }
        this.gameRenderer = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_757");
        this.getFovDesc = String.valueOf(new StringBuilder().append("(L").append(FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_4184").replace('.', '/')).append(";FZ)D"));
    }
}

