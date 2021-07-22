/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  net.minecraft.class_124
 *  net.minecraft.class_1262
 *  net.minecraft.class_1291
 *  net.minecraft.class_1292
 *  net.minecraft.class_1293
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2371
 *  net.minecraft.class_2487
 *  net.minecraft.class_2499
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_2588
 *  net.minecraft.class_4174
 *  net.minecraft.class_5250
 */
package minegame159.meteorclient.systems.modules.render;

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
    public final /* synthetic */ Setting<Boolean> byteSize;
    private final /* synthetic */ Setting<Boolean> maps;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Boolean> showVanilla;
    public final /* synthetic */ Setting<Double> booksScale;
    public static final /* synthetic */ Color ECHEST_COLOR;
    private final /* synthetic */ Setting<Boolean> books;
    private final /* synthetic */ SettingGroup sgPreviews;
    private final /* synthetic */ Setting<Boolean> statusEffects;
    private final /* synthetic */ Setting<Boolean> beehive;
    private final /* synthetic */ Setting<Boolean> shulkers;
    public final /* synthetic */ Setting<Double> mapsScale;
    public final /* synthetic */ Setting<Boolean> echest;
    private final /* synthetic */ SettingGroup sgOther;
    private final /* synthetic */ Setting<Keybind> keybind;
    private final /* synthetic */ Setting<DisplayWhen> displayWhen;
    private final /* synthetic */ Setting<Boolean> shulkerCompactTooltip;
    private final /* synthetic */ Setting<Boolean> middleClickOpen;

    private class_5250 getStatusText(class_1293 lIllIIllIlIIIll) {
        class_2588 lIllIIllIlIIlII = new class_2588(lIllIIllIlIIIll.method_5586());
        if (lIllIIllIlIIIll.method_5578() != 0) {
            lIllIIllIlIIlII.method_27693(String.format(" %d (%s)", lIllIIllIlIIIll.method_5578() + 1, class_1292.method_5577((class_1293)lIllIIllIlIIIll, (float)1.0f)));
        } else {
            lIllIIllIlIIlII.method_27693(String.format(" (%s)", class_1292.method_5577((class_1293)lIllIIllIlIIIll, (float)1.0f)));
        }
        if (lIllIIllIlIIIll.method_5579().method_5573()) {
            return lIllIIllIlIIlII.method_27692(class_124.field_1078);
        }
        return lIllIIllIlIIlII.method_27692(class_124.field_1061);
    }

    public static boolean willRenderBookPreview(class_1799 lIllIIllIIllIll) {
        if (lIllIIllIIllIll.method_7909() != class_1802.field_8674 && lIllIIllIIllIll.method_7909() != class_1802.field_8360) {
            return false;
        }
        class_2487 lIllIIllIIlllIl = lIllIIllIIllIll.method_7969();
        if (lIllIIllIIlllIl == null) {
            return false;
        }
        class_2499 lIllIIllIIlllII = lIllIIllIIlllIl.method_10554("pages", 8);
        return lIllIIllIIlllII.size() >= 1;
    }

    public boolean shulkerCompactTooltip() {
        BetterTooltips lIllIIllllllIIl;
        return lIllIIllllllIIl.isActive() && lIllIIllllllIIl.shulkerCompactTooltip.get() != false;
    }

    public boolean previewEChest() {
        BetterTooltips lIllIIlllllIlIl;
        return lIllIIlllllIlIl.isActive() && lIllIIlllllIlIl.isPressed() && lIllIIlllllIlIl.echest.get() != false;
    }

    private static /* synthetic */ int lambda$applyCompactShulkerTooltip$2(Object2IntMap lIllIIllIIIIlll, class_1792 lIllIIllIIIIlII) {
        return -lIllIIllIIIIlll.getInt((Object)lIllIIllIIIIlII);
    }

    public boolean previewShulkers() {
        BetterTooltips lIllIIlllllllII;
        return lIllIIlllllllII.isActive() && lIllIIlllllllII.isPressed() && lIllIIlllllllII.shulkers.get() != false;
    }

    @EventHandler
    private void modifyTooltip(GetTooltipEvent.Modify lIllIIlllIIIIIl) {
        BetterTooltips lIllIIlllIIIIlI;
        if (Utils.hasItems(lIllIIlllIIIIIl.itemStack) && lIllIIlllIIIIlI.shulkers.get() != false && lIllIIlllIIIIlI.previewShulkers() || lIllIIlllIIIIIl.itemStack.method_7909() == class_1802.field_8466 && lIllIIlllIIIIlI.echest.get() != false && lIllIIlllIIIIlI.previewEChest() || BetterTooltips.willRenderBookPreview(lIllIIlllIIIIIl.itemStack) && lIllIIlllIIIIlI.books.get().booleanValue() && lIllIIlllIIIIlI.previewBooks()) {
            lIllIIlllIIIIIl.y -= 10 * lIllIIlllIIIIIl.list.size();
            lIllIIlllIIIIIl.y -= 4;
        }
    }

    private boolean isPressed() {
        BetterTooltips lIllIIllllIllII;
        return lIllIIllllIllII.keybind.get().isPressed() && lIllIIllllIllII.displayWhen.get() == DisplayWhen.Keybind || lIllIIllllIllII.displayWhen.get() == DisplayWhen.Always;
    }

    public boolean previewBooks() {
        BetterTooltips lIllIIlllllIIII;
        return lIllIIlllllIIII.isActive() && lIllIIlllllIIII.isPressed() && lIllIIlllllIIII.books.get() != false;
    }

    public BetterTooltips() {
        super(Categories.Render, "better-tooltips", "Displays more useful tooltips for certain items.");
        BetterTooltips lIllIIlllllllll;
        lIllIIlllllllll.sgGeneral = lIllIIlllllllll.settings.getDefaultGroup();
        lIllIIlllllllll.sgPreviews = lIllIIlllllllll.settings.createGroup("Previews");
        lIllIIlllllllll.sgOther = lIllIIlllllllll.settings.createGroup("Other");
        lIllIIlllllllll.displayWhen = lIllIIlllllllll.sgGeneral.add(new EnumSetting.Builder().name("display-when").description("When to display previews.").defaultValue(DisplayWhen.Keybind).build());
        lIllIIlllllllll.keybind = lIllIIlllllllll.sgGeneral.add(new KeybindSetting.Builder().name("keybind").description("The bind for keybind mode.").defaultValue(Keybind.fromKey(342)).visible(() -> {
            BetterTooltips lIllIIlIlllIllI;
            return lIllIIlIlllIllI.displayWhen.get() == DisplayWhen.Keybind;
        }).build());
        lIllIIlllllllll.showVanilla = lIllIIlllllllll.sgGeneral.add(new BoolSetting.Builder().name("show-vanilla").description("Displays the vanilla tooltip as well as the preview.").defaultValue(true).build());
        lIllIIlllllllll.middleClickOpen = lIllIIlllllllll.sgGeneral.add(new BoolSetting.Builder().name("middle-click-open").description("Opens a GUI window with the inventory of the storage block when you middle click the item.").defaultValue(true).build());
        lIllIIlllllllll.shulkers = lIllIIlllllllll.sgPreviews.add(new BoolSetting.Builder().name("storage-blocks").description("Shows a preview of a shulker box when hovering over it in an inventory.").defaultValue(true).build());
        lIllIIlllllllll.shulkerCompactTooltip = lIllIIlllllllll.sgPreviews.add(new BoolSetting.Builder().name("compact-shulker-tooltip").description("Compacts the lines of the shulker tooltip.").defaultValue(true).visible(lIllIIlllllllll.shulkers::get).build());
        lIllIIlllllllll.echest = lIllIIlllllllll.sgPreviews.add(new BoolSetting.Builder().name("echests").description("Shows a preview of your echest when hovering over it in an inventory.").defaultValue(true).build());
        lIllIIlllllllll.maps = lIllIIlllllllll.sgPreviews.add(new BoolSetting.Builder().name("maps").description("Shows a preview of a map when hovering over it in an inventory.").defaultValue(true).build());
        lIllIIlllllllll.mapsScale = lIllIIlllllllll.sgPreviews.add(new DoubleSetting.Builder().name("map-scale").description("The scale of the map preview.").defaultValue(1.0).min(1.0).sliderMax(5.0).visible(lIllIIlllllllll.maps::get).build());
        lIllIIlllllllll.books = lIllIIlllllllll.sgPreviews.add(new BoolSetting.Builder().name("books").description("Shows contents of a book when hovering over it in an inventory.").defaultValue(true).build());
        lIllIIlllllllll.booksScale = lIllIIlllllllll.sgPreviews.add(new DoubleSetting.Builder().name("book-scale").description("The scale of the book preview.").defaultValue(1.0).min(1.0).sliderMax(5.0).visible(lIllIIlllllllll.books::get).build());
        lIllIIlllllllll.byteSize = lIllIIlllllllll.sgOther.add(new BoolSetting.Builder().name("byte-size").description("Displays an item's size in bytes in the tooltip.").defaultValue(true).build());
        lIllIIlllllllll.statusEffects = lIllIIlllllllll.sgOther.add(new BoolSetting.Builder().name("status-effects").description("Adds list of status effects to tooltips of food items.").defaultValue(true).build());
        lIllIIlllllllll.beehive = lIllIIlllllllll.sgOther.add(new BoolSetting.Builder().name("beehive").description("Displays information about a beehive or bee nest.").defaultValue(true).build());
    }

    static {
        ECHEST_COLOR = new Color(0, 50, 50);
    }

    public boolean middleClickOpen() {
        BetterTooltips lIllIIllIIlIlll;
        return lIllIIllIIlIlll.isActive() && lIllIIllIIlIlll.middleClickOpen.get() != false;
    }

    public void applyCompactShulkerTooltip(class_1799 lIllIIllIllIIII, List<class_2561> lIllIIllIllIIlI) {
        class_2487 lIllIIllIllIIIl = lIllIIllIllIIII.method_7941("BlockEntityTag");
        if (lIllIIllIllIIIl != null) {
            if (lIllIIllIllIIIl.method_10573("LootTable", 8)) {
                lIllIIllIllIIlI.add((class_2561)new class_2585("???????"));
            }
            if (lIllIIllIllIIIl.method_10573("Items", 9)) {
                class_2371 lIllIIllIllIllI = class_2371.method_10213((int)27, (Object)class_1799.field_8037);
                class_1262.method_5429((class_2487)lIllIIllIllIIIl, (class_2371)lIllIIllIllIllI);
                Object2IntOpenHashMap lIllIIllIllIlIl = new Object2IntOpenHashMap();
                for (class_1799 lIllIIllIllIlll : lIllIIllIllIllI) {
                    if (lIllIIllIllIlll.method_7960()) continue;
                    int lIllIIllIlllIII = lIllIIllIllIlIl.getInt((Object)lIllIIllIllIlll.method_7909());
                    lIllIIllIllIlIl.put((Object)lIllIIllIllIlll.method_7909(), lIllIIllIlllIII + lIllIIllIllIlll.method_7947());
                }
                lIllIIllIllIlIl.keySet().stream().sorted(Comparator.comparingInt(arg_0 -> BetterTooltips.lambda$applyCompactShulkerTooltip$2((Object2IntMap)lIllIIllIllIlIl, arg_0))).limit(5L).forEach(arg_0 -> BetterTooltips.lambda$applyCompactShulkerTooltip$3((Object2IntMap)lIllIIllIllIlIl, lIllIIllIllIIlI, arg_0));
                if (lIllIIllIllIlIl.size() > 5) {
                    lIllIIllIllIIlI.add((class_2561)new class_2588("container.shulkerBox.more", new Object[]{lIllIIllIllIlIl.size() - 5}).method_27692(class_124.field_1056));
                }
            }
        }
    }

    private static /* synthetic */ void lambda$applyCompactShulkerTooltip$3(Object2IntMap lIllIIllIIIllIl, List lIllIIllIIIllII, class_1792 lIllIIllIIIllll) {
        class_5250 lIllIIllIIIlllI = lIllIIllIIIllll.method_7848().method_27661();
        lIllIIllIIIlllI.method_10852((class_2561)new class_2585(" x").method_27693(String.valueOf(lIllIIllIIIllIl.getInt((Object)lIllIIllIIIllll))).method_27692(class_124.field_1080));
        lIllIIllIIIllII.add(lIllIIllIIIlllI);
    }

    @EventHandler
    private void appendTooltip(GetTooltipEvent.Append lIllIIlllIIlllI) {
        class_2487 lIllIIlllIlIIlI;
        BetterTooltips lIllIIlllIIllll;
        if (lIllIIlllIIllll.byteSize.get().booleanValue()) {
            try {
                String lIllIIllllIIIII;
                lIllIIlllIIlllI.itemStack.method_7953(new class_2487()).method_10713((DataOutput)ByteCountDataOutput.INSTANCE);
                int lIllIIllllIIIIl = ByteCountDataOutput.INSTANCE.getCount();
                ByteCountDataOutput.INSTANCE.reset();
                if (lIllIIllllIIIIl >= 1024) {
                    String lIllIIllllIIIlI = String.format("%.2f kb", Float.valueOf((float)lIllIIllllIIIIl / 1024.0f));
                } else {
                    lIllIIllllIIIII = String.format("%d bytes", lIllIIllllIIIIl);
                }
                lIllIIlllIIlllI.list.add(new class_2585(String.valueOf(new StringBuilder().append((Object)class_124.field_1080).append(lIllIIllllIIIII))));
            }
            catch (IOException lIllIIlllIlllll) {
                lIllIIlllIlllll.printStackTrace();
            }
        }
        if (lIllIIlllIIllll.statusEffects.get().booleanValue()) {
            class_4174 lIllIIlllIlIlll;
            if (lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_8766) {
                class_2499 lIllIIlllIllIIl;
                class_2487 lIllIIlllIllIII = lIllIIlllIIlllI.itemStack.method_7969();
                if (lIllIIlllIllIII != null && (lIllIIlllIllIIl = lIllIIlllIllIII.method_10554("Effects", 10)) != null) {
                    for (int lIllIIlllIllIlI = 0; lIllIIlllIllIlI < lIllIIlllIllIIl.size(); ++lIllIIlllIllIlI) {
                        class_2487 lIllIIlllIllllI = lIllIIlllIllIIl.method_10602(lIllIIlllIllIlI);
                        byte lIllIIlllIlllIl = lIllIIlllIllllI.method_10571("EffectId");
                        int lIllIIlllIlllII = lIllIIlllIllllI.method_10545("EffectDuration") ? lIllIIlllIllllI.method_10550("EffectDuration") : 160;
                        class_1293 lIllIIlllIllIll = new class_1293(class_1291.method_5569((int)lIllIIlllIlllIl), lIllIIlllIlllII, 0);
                        lIllIIlllIIlllI.list.add(1, lIllIIlllIIllll.getStatusText(lIllIIlllIllIll));
                    }
                }
            } else if (lIllIIlllIIlllI.itemStack.method_7909().method_19263() && (lIllIIlllIlIlll = lIllIIlllIIlllI.itemStack.method_7909().method_19264()) != null) {
                lIllIIlllIlIlll.method_19235().forEach(lIllIIlIlllllIl -> {
                    BetterTooltips lIllIIlIllllIll;
                    class_1293 lIllIIlIlllllII = (class_1293)lIllIIlIlllllIl.getFirst();
                    lIllIIlIllllllI.list.add(1, lIllIIlIllllIll.getStatusText(lIllIIlIlllllII));
                });
            }
        }
        if (lIllIIlllIIllll.beehive.get().booleanValue() && (lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_20416 || lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_20415) && (lIllIIlllIlIIlI = lIllIIlllIIlllI.itemStack.method_7969()) != null) {
            class_2487 lIllIIlllIlIIll;
            class_2487 lIllIIlllIlIlII = lIllIIlllIlIIlI.method_10562("BlockStateTag");
            if (lIllIIlllIlIlII != null) {
                int lIllIIlllIlIllI = lIllIIlllIlIlII.method_10550("honey_level");
                lIllIIlllIIlllI.list.add(1, new class_2585(String.format("%sHoney level: %s%d%s.", new Object[]{class_124.field_1080, class_124.field_1054, lIllIIlllIlIllI, class_124.field_1080})));
            }
            if ((lIllIIlllIlIIll = lIllIIlllIlIIlI.method_10562("BlockEntityTag")) != null) {
                class_2499 lIllIIlllIlIlIl = lIllIIlllIlIIll.method_10554("Bees", 10);
                lIllIIlllIIlllI.list.add(1, new class_2585(String.format("%sBees: %s%d%s.", new Object[]{class_124.field_1080, class_124.field_1054, lIllIIlllIlIlIl.size(), class_124.field_1080})));
            }
        }
        if (Utils.hasItems(lIllIIlllIIlllI.itemStack) && lIllIIlllIIllll.shulkers.get() != false && !lIllIIlllIIllll.previewShulkers() || lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_8466 && lIllIIlllIIllll.echest.get() != false && !lIllIIlllIIllll.previewEChest() || lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_8204 && lIllIIlllIIllll.maps.get() != false && !lIllIIlllIIllll.previewMaps() || lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_8674 && lIllIIlllIIllll.books.get() != false && !lIllIIlllIIllll.previewBooks() || lIllIIlllIIlllI.itemStack.method_7909() == class_1802.field_8360 && lIllIIlllIIllll.books.get().booleanValue() && !lIllIIlllIIllll.previewBooks()) {
            lIllIIlllIIlllI.list.add(new class_2585(""));
            lIllIIlllIIlllI.list.add(new class_2585(String.valueOf(new StringBuilder().append("Hold ").append((Object)class_124.field_1054).append(lIllIIlllIIllll.keybind).append((Object)class_124.field_1070).append(" to preview"))));
        }
    }

    public boolean previewMaps() {
        BetterTooltips lIllIIlllllIIlI;
        return lIllIIlllllIIlI.isActive() && lIllIIlllllIIlI.isPressed() && lIllIIlllllIIlI.maps.get() != false;
    }

    public static enum DisplayWhen {
        Keybind,
        Always;


        private DisplayWhen() {
            DisplayWhen llllllllllllllllllIllllIlllIIIII;
        }
    }
}

