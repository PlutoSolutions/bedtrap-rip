/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2248
 *  net.minecraft.class_2378
 *  net.minecraft.class_2487
 *  net.minecraft.class_2960
 */
package minegame159.meteorclient.settings;

import java.util.function.Consumer;
import java.util.function.Predicate;
import minegame159.meteorclient.settings.IVisible;
import minegame159.meteorclient.settings.Setting;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2487;
import net.minecraft.class_2960;

public class BlockSetting
extends Setting<class_2248> {
    public final /* synthetic */ Predicate<class_2248> filter;

    @Override
    protected class_2248 parseImpl(String lllllllllllllllllIIIIlIllIlIIllI) {
        return (class_2248)BlockSetting.parseId(class_2378.field_11146, lllllllllllllllllIIIIlIllIlIIllI);
    }

    @Override
    public Iterable<class_2960> getIdentifierSuggestions() {
        return class_2378.field_11146.method_10235();
    }

    @Override
    protected boolean isValueValid(class_2248 lllllllllllllllllIIIIlIllIIlllll) {
        BlockSetting lllllllllllllllllIIIIlIllIlIIIII;
        return lllllllllllllllllIIIIlIllIlIIIII.filter == null || lllllllllllllllllIIIIlIllIlIIIII.filter.test(lllllllllllllllllIIIIlIllIIlllll);
    }

    @Override
    public class_2487 toTag() {
        BlockSetting lllllllllllllllllIIIIlIllIIllIIl;
        class_2487 lllllllllllllllllIIIIlIllIIllIlI = new class_2487();
        lllllllllllllllllIIIIlIllIIllIlI.method_10582("value", class_2378.field_11146.method_10221((Object)((class_2248)lllllllllllllllllIIIIlIllIIllIIl.get())).toString());
        return lllllllllllllllllIIIIlIllIIllIlI;
    }

    @Override
    public class_2248 fromTag(class_2487 lllllllllllllllllIIIIlIllIIIllll) {
        BlockSetting lllllllllllllllllIIIIlIllIIlIIII;
        lllllllllllllllllIIIIlIllIIlIIII.value = class_2378.field_11146.method_10223(new class_2960(lllllllllllllllllIIIIlIllIIIllll.method_10558("value")));
        if (lllllllllllllllllIIIIlIllIIlIIII.filter != null && !lllllllllllllllllIIIIlIllIIlIIII.filter.test((class_2248)lllllllllllllllllIIIIlIllIIlIIII.value)) {
            for (class_2248 lllllllllllllllllIIIIlIllIIlIIll : class_2378.field_11146) {
                if (!lllllllllllllllllIIIIlIllIIlIIII.filter.test(lllllllllllllllllIIIIlIllIIlIIll)) continue;
                lllllllllllllllllIIIIlIllIIlIIII.value = lllllllllllllllllIIIIlIllIIlIIll;
                break;
            }
        }
        lllllllllllllllllIIIIlIllIIlIIII.changed();
        return (class_2248)lllllllllllllllllIIIIlIllIIlIIII.get();
    }

    public BlockSetting(String lllllllllllllllllIIIIlIllIlIllll, String lllllllllllllllllIIIIlIllIlIlllI, class_2248 lllllllllllllllllIIIIlIllIllIlIl, Consumer<class_2248> lllllllllllllllllIIIIlIllIllIlII, Consumer<Setting<class_2248>> lllllllllllllllllIIIIlIllIlIlIll, IVisible lllllllllllllllllIIIIlIllIlIlIlI, Predicate<class_2248> lllllllllllllllllIIIIlIllIllIIIl) {
        super(lllllllllllllllllIIIIlIllIlIllll, lllllllllllllllllIIIIlIllIlIlllI, lllllllllllllllllIIIIlIllIllIlIl, lllllllllllllllllIIIIlIllIllIlII, lllllllllllllllllIIIIlIllIlIlIll, lllllllllllllllllIIIIlIllIlIlIlI);
        BlockSetting lllllllllllllllllIIIIlIllIllIIII;
        lllllllllllllllllIIIIlIllIllIIII.filter = lllllllllllllllllIIIIlIllIllIIIl;
    }

    public static class Builder {
        private /* synthetic */ String name;
        private /* synthetic */ IVisible visible;
        private /* synthetic */ String description;
        private /* synthetic */ Consumer<class_2248> onChanged;
        private /* synthetic */ class_2248 defaultValue;
        private /* synthetic */ Consumer<Setting<class_2248>> onModuleActivated;
        private /* synthetic */ Predicate<class_2248> filter;

        public Builder filter(Predicate<class_2248> llIllIIIlIIlI) {
            Builder llIllIIIlIIIl;
            llIllIIIlIIIl.filter = llIllIIIlIIlI;
            return llIllIIIlIIIl;
        }

        public Builder defaultValue(class_2248 llIllIIlIIlII) {
            Builder llIllIIlIIlIl;
            llIllIIlIIlIl.defaultValue = llIllIIlIIlII;
            return llIllIIlIIlIl;
        }

        public Builder name(String llIllIIllIIII) {
            Builder llIllIIllIIIl;
            llIllIIllIIIl.name = llIllIIllIIII;
            return llIllIIllIIIl;
        }

        public Builder onChanged(Consumer<class_2248> llIllIIIllllI) {
            Builder llIllIIIlllIl;
            llIllIIIlllIl.onChanged = llIllIIIllllI;
            return llIllIIIlllIl;
        }

        public BlockSetting build() {
            Builder llIllIIIIIlll;
            return new BlockSetting(llIllIIIIIlll.name, llIllIIIIIlll.description, llIllIIIIIlll.defaultValue, llIllIIIIIlll.onChanged, llIllIIIIIlll.onModuleActivated, llIllIIIIIlll.visible, llIllIIIIIlll.filter);
        }

        public Builder onModuleActivated(Consumer<Setting<class_2248>> llIllIIIllIII) {
            Builder llIllIIIlIlll;
            llIllIIIlIlll.onModuleActivated = llIllIIIllIII;
            return llIllIIIlIlll;
        }

        public Builder() {
            Builder llIllIIllIlIl;
            llIllIIllIlIl.name = "undefined";
            llIllIIllIlIl.description = "";
        }

        public Builder description(String llIllIIlIlIII) {
            Builder llIllIIlIlIll;
            llIllIIlIlIll.description = llIllIIlIlIII;
            return llIllIIlIlIll;
        }

        public Builder visible(IVisible llIllIIIIllII) {
            Builder llIllIIIIlIll;
            llIllIIIIlIll.visible = llIllIIIIllII;
            return llIllIIIIlIll;
        }
    }
}

