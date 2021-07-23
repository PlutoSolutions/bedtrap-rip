/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.game.GetTooltipEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.KeybindSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.ByteCountDataOutput;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_124;
import net.minecraft.class_1262;
import net.minecraft.class_1291;
import net.minecraft.class_1292;
import net.minecraft.class_1293;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2588;
import net.minecraft.class_4174;
import net.minecraft.class_5250;

public class BetterTooltips
extends Module {
    public final Setting<Boolean> byteSize;
    private final Setting<Boolean> maps;
    private final SettingGroup sgGeneral;
    public final Setting<Boolean> showVanilla;
    public final Setting<Double> booksScale;
    public static final Color ECHEST_COLOR = new Color(0, 50, 50);
    private final Setting<Boolean> books;
    private final SettingGroup sgPreviews;
    private final Setting<Boolean> statusEffects;
    private final Setting<Boolean> beehive;
    private final Setting<Boolean> shulkers;
    public final Setting<Double> mapsScale;
    public final Setting<Boolean> echest;
    private final SettingGroup sgOther;
    private final Setting<Keybind> keybind;
    private final Setting<DisplayWhen> displayWhen;
    private final Setting<Boolean> shulkerCompactTooltip;
    private final Setting<Boolean> middleClickOpen;

    private class_5250 getStatusText(class_1293 class_12932) {
        class_2588 class_25882 = new class_2588(class_12932.method_5586());
        if (class_12932.method_5578() != 0) {
            class_25882.method_27693(String.format(" %d (%s)", class_12932.method_5578() + 1, class_1292.method_5577((class_1293)class_12932, (float)1.0f)));
        } else {
            class_25882.method_27693(String.format(" (%s)", class_1292.method_5577((class_1293)class_12932, (float)1.0f)));
        }
        if (class_12932.method_5579().method_5573()) {
            return class_25882.method_27692(class_124.field_1078);
        }
        return class_25882.method_27692(class_124.field_1061);
    }

    public static boolean willRenderBookPreview(class_1799 class_17992) {
        if (class_17992.method_7909() != class_1802.field_8674 && class_17992.method_7909() != class_1802.field_8360) {
            return false;
        }
        class_2487 class_24872 = class_17992.method_7969();
        if (class_24872 == null) {
            return false;
        }
        class_2499 class_24992 = class_24872.method_10554("pages", 8);
        return class_24992.size() >= 1;
    }

    public boolean shulkerCompactTooltip() {
        return this.isActive() && this.shulkerCompactTooltip.get() != false;
    }

    public boolean previewEChest() {
        return this.isActive() && this.isPressed() && this.echest.get() != false;
    }

    private boolean lambda$new$0() {
        return this.displayWhen.get() == DisplayWhen.Keybind;
    }

    private static int lambda$applyCompactShulkerTooltip$2(Object2IntMap object2IntMap, class_1792 class_17922) {
        return -object2IntMap.getInt((Object)class_17922);
    }

    private void lambda$appendTooltip$1(GetTooltipEvent.Append append, Pair pair) {
        class_1293 class_12932 = (class_1293)pair.getFirst();
        append.list.add(1, this.getStatusText(class_12932));
    }

    public boolean previewShulkers() {
        return this.isActive() && this.isPressed() && this.shulkers.get() != false;
    }

    @EventHandler
    private void modifyTooltip(GetTooltipEvent.Modify modify) {
        if (Utils.hasItems(modify.itemStack) && this.shulkers.get() != false && this.previewShulkers() || modify.itemStack.method_7909() == class_1802.field_8466 && this.echest.get() != false && this.previewEChest() || BetterTooltips.willRenderBookPreview(modify.itemStack) && this.books.get().booleanValue() && this.previewBooks()) {
            modify.y -= 10 * modify.list.size();
            modify.y -= 4;
        }
    }

    private boolean isPressed() {
        return this.keybind.get().isPressed() && this.displayWhen.get() == DisplayWhen.Keybind || this.displayWhen.get() == DisplayWhen.Always;
    }

    public boolean previewBooks() {
        return this.isActive() && this.isPressed() && this.books.get() != false;
    }

    public BetterTooltips() {
        super(Categories.Render, "better-tooltips", "Displays more useful tooltips for certain items.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgPreviews = this.settings.createGroup("Previews");
        this.sgOther = this.settings.createGroup("Other");
        this.displayWhen = this.sgGeneral.add(new EnumSetting.Builder().name("display-when").description("When to display previews.").defaultValue(DisplayWhen.Keybind).build());
        this.keybind = this.sgGeneral.add(new KeybindSetting.Builder().name("keybind").description("The bind for keybind mode.").defaultValue(Keybind.fromKey(342)).visible(this::lambda$new$0).build());
        this.showVanilla = this.sgGeneral.add(new BoolSetting.Builder().name("show-vanilla").description("Displays the vanilla tooltip as well as the preview.").defaultValue(true).build());
        this.middleClickOpen = this.sgGeneral.add(new BoolSetting.Builder().name("middle-click-open").description("Opens a GUI window with the inventory of the storage block when you middle click the item.").defaultValue(true).build());
        this.shulkers = this.sgPreviews.add(new BoolSetting.Builder().name("storage-blocks").description("Shows a preview of a shulker box when hovering over it in an inventory.").defaultValue(true).build());
        this.shulkerCompactTooltip = this.sgPreviews.add(new BoolSetting.Builder().name("compact-shulker-tooltip").description("Compacts the lines of the shulker tooltip.").defaultValue(true).visible(this.shulkers::get).build());
        this.echest = this.sgPreviews.add(new BoolSetting.Builder().name("echests").description("Shows a preview of your echest when hovering over it in an inventory.").defaultValue(true).build());
        this.maps = this.sgPreviews.add(new BoolSetting.Builder().name("maps").description("Shows a preview of a map when hovering over it in an inventory.").defaultValue(true).build());
        this.mapsScale = this.sgPreviews.add(new DoubleSetting.Builder().name("map-scale").description("The scale of the map preview.").defaultValue(1.0).min(1.0).sliderMax(5.0).visible(this.maps::get).build());
        this.books = this.sgPreviews.add(new BoolSetting.Builder().name("books").description("Shows contents of a book when hovering over it in an inventory.").defaultValue(true).build());
        this.booksScale = this.sgPreviews.add(new DoubleSetting.Builder().name("book-scale").description("The scale of the book preview.").defaultValue(1.0).min(1.0).sliderMax(5.0).visible(this.books::get).build());
        this.byteSize = this.sgOther.add(new BoolSetting.Builder().name("byte-size").description("Displays an item's size in bytes in the tooltip.").defaultValue(true).build());
        this.statusEffects = this.sgOther.add(new BoolSetting.Builder().name("status-effects").description("Adds list of status effects to tooltips of food items.").defaultValue(true).build());
        this.beehive = this.sgOther.add(new BoolSetting.Builder().name("beehive").description("Displays information about a beehive or bee nest.").defaultValue(true).build());
    }

    public boolean middleClickOpen() {
        return this.isActive() && this.middleClickOpen.get() != false;
    }

    public void applyCompactShulkerTooltip(class_1799 class_17992, List<class_2561> list) {
        class_2487 class_24872 = class_17992.method_7941("BlockEntityTag");
        if (class_24872 != null) {
            if (class_24872.method_10573("LootTable", 8)) {
                list.add((class_2561)new class_2585("???????"));
            }
            if (class_24872.method_10573("Items", 9)) {
                class_2371 class_23712 = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
                class_1262.method_5429((class_2487)class_24872, (class_2371)class_23712);
                Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap();
                for (class_1799 class_17993 : class_23712) {
                    if (class_17993.method_7960()) continue;
                    int n = object2IntOpenHashMap.getInt((Object)class_17993.method_7909());
                    object2IntOpenHashMap.put((Object)class_17993.method_7909(), n + class_17993.method_7947());
                }
                object2IntOpenHashMap.keySet().stream().sorted(Comparator.comparingInt(arg_0 -> BetterTooltips.lambda$applyCompactShulkerTooltip$2((Object2IntMap)object2IntOpenHashMap, arg_0))).limit(5L).forEach(arg_0 -> BetterTooltips.lambda$applyCompactShulkerTooltip$3((Object2IntMap)object2IntOpenHashMap, list, arg_0));
                if (object2IntOpenHashMap.size() > 5) {
                    list.add((class_2561)new class_2588("container.shulkerBox.more", new Object[]{object2IntOpenHashMap.size() - 5}).method_27692(class_124.field_1056));
                }
            }
        }
    }

    private static void lambda$applyCompactShulkerTooltip$3(Object2IntMap object2IntMap, List list, class_1792 class_17922) {
        class_5250 class_52502 = class_17922.method_7848().method_27661();
        class_52502.method_10852((class_2561)new class_2585(" x").method_27693(String.valueOf(object2IntMap.getInt((Object)class_17922))).method_27692(class_124.field_1080));
        list.add(class_52502);
    }

    @EventHandler
    private void appendTooltip(GetTooltipEvent.Append append) {
        class_2487 class_24872;
        class_2487 class_24873;
        int n;
        Object object;
        if (this.byteSize.get().booleanValue()) {
            try {
                append.itemStack.method_7953(new class_2487()).method_10713((DataOutput)ByteCountDataOutput.INSTANCE);
                int n2 = ByteCountDataOutput.INSTANCE.getCount();
                ByteCountDataOutput.INSTANCE.reset();
                object = n2 >= 1024 ? String.format("%.2f kb", Float.valueOf((float)n2 / 1024.0f)) : String.format("%d bytes", n2);
                append.list.add(new class_2585(String.valueOf(new StringBuilder().append(class_124.field_1080).append((String)object))));
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        if (this.statusEffects.get().booleanValue()) {
            class_4174 class_41742;
            if (append.itemStack.method_7909() == class_1802.field_8766) {
                class_2487 class_24874 = append.itemStack.method_7969();
                if (class_24874 != null && (object = class_24874.method_10554("Effects", 10)) != null) {
                    for (n = 0; n < object.size(); ++n) {
                        class_24873 = object.method_10602(n);
                        byte by = class_24873.method_10571("EffectId");
                        int n3 = class_24873.method_10545("EffectDuration") ? class_24873.method_10550("EffectDuration") : 160;
                        class_1293 class_12932 = new class_1293(class_1291.method_5569((int)by), n3, 0);
                        append.list.add(1, this.getStatusText(class_12932));
                        if (-4 <= 0) continue;
                        return;
                    }
                }
            } else if (append.itemStack.method_7909().method_19263() && (class_41742 = append.itemStack.method_7909().method_19264()) != null) {
                class_41742.method_19235().forEach(arg_0 -> this.lambda$appendTooltip$1(append, arg_0));
            }
        }
        if (this.beehive.get().booleanValue() && (append.itemStack.method_7909() == class_1802.field_20416 || append.itemStack.method_7909() == class_1802.field_20415) && (class_24872 = append.itemStack.method_7969()) != null) {
            class_2487 class_24875;
            object = class_24872.method_10562("BlockStateTag");
            if (object != null) {
                n = object.method_10550("honey_level");
                append.list.add(1, new class_2585(String.format("%sHoney level: %s%d%s.", class_124.field_1080, class_124.field_1054, n, class_124.field_1080)));
            }
            if ((class_24875 = class_24872.method_10562("BlockEntityTag")) != null) {
                class_24873 = class_24875.method_10554("Bees", 10);
                append.list.add(1, new class_2585(String.format("%sBees: %s%d%s.", class_124.field_1080, class_124.field_1054, class_24873.size(), class_124.field_1080)));
            }
        }
        if (Utils.hasItems(append.itemStack) && this.shulkers.get() != false && !this.previewShulkers() || append.itemStack.method_7909() == class_1802.field_8466 && this.echest.get() != false && !this.previewEChest() || append.itemStack.method_7909() == class_1802.field_8204 && this.maps.get() != false && !this.previewMaps() || append.itemStack.method_7909() == class_1802.field_8674 && this.books.get() != false && !this.previewBooks() || append.itemStack.method_7909() == class_1802.field_8360 && this.books.get().booleanValue() && !this.previewBooks()) {
            append.list.add(new class_2585(""));
            append.list.add(new class_2585(String.valueOf(new StringBuilder().append("Hold ").append(class_124.field_1054).append(this.keybind).append(class_124.field_1070).append(" to preview"))));
        }
    }

    public boolean previewMaps() {
        return this.isActive() && this.isPressed() && this.maps.get() != false;
    }

    public static enum DisplayWhen {
        Keybind,
        Always;

    }
}

