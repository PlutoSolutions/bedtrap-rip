/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1802
 *  net.minecraft.class_2499
 *  net.minecraft.class_2519
 *  net.minecraft.class_2520
 *  net.minecraft.class_2583
 *  net.minecraft.class_2596
 *  net.minecraft.class_2820
 *  net.minecraft.class_465
 */
package minegame159.meteorclient.systems.modules.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.TextHandlerAccessor;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.StringSetting;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.FindItemResult;
import minegame159.meteorclient.utils.player.InvUtils;
import net.minecraft.class_1802;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2583;
import net.minecraft.class_2596;
import net.minecraft.class_2820;
import net.minecraft.class_465;

public class BookBot
extends Module {
    private /* synthetic */ boolean firstTime;
    private /* synthetic */ PrimitiveIterator.OfInt stream;
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Integer> noOfBooks;
    private static final /* synthetic */ Random RANDOM;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<String> name;
    private /* synthetic */ class_2499 pages;
    private final /* synthetic */ Setting<String> fileName;
    private final /* synthetic */ StringBuilder pageSb;
    private /* synthetic */ boolean firstChar;
    private static final /* synthetic */ int LINE_WIDTH;
    private final /* synthetic */ StringBuilder lineSb;
    private /* synthetic */ String fileString;
    private /* synthetic */ int ticksLeft;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int nextChar;
    private final /* synthetic */ Setting<Integer> noOfPages;
    private /* synthetic */ int booksLeft;

    @Override
    public void onActivate() {
        BookBot llIlIIIlI;
        llIlIIIlI.booksLeft = llIlIIIlI.noOfBooks.get();
        llIlIIIlI.firstTime = true;
    }

    @EventHandler
    private void onTick(TickEvent.Post llIIIlllI) {
        FindItemResult llIIlIlll;
        BookBot llIIIllII;
        if (llIIIllII.mc.field_1755 instanceof class_465) {
            return;
        }
        if (llIIIllII.booksLeft <= 0) {
            llIIIllII.toggle();
            return;
        }
        FindItemResult llIIIllIl = InvUtils.find(class_1802.field_8674);
        if (!llIIIllIl.isHotbar() && !llIIIllIl.isOffhand() && (llIIlIlll = InvUtils.findEmpty()).isHotbar()) {
            InvUtils.move().from(llIIIllIl.slot).toHotbar(llIIlIlll.slot);
        }
        if (!(llIIIllIl = InvUtils.findInHotbar(class_1802.field_8674)).found()) {
            return;
        }
        InvUtils.swap(llIIIllIl.getSlot());
        if (InvUtils.findInHotbar(class_1802.field_8674).getHand() == null) {
            return;
        }
        if (llIIIllII.ticksLeft > 0) {
            llIIIllII.ticksLeft -= 50;
            return;
        }
        llIIIllII.ticksLeft = llIIIllII.delay.get();
        if (llIIIllII.mode.get() == Mode.Random) {
            IntStream llIIlIllI = RANDOM.ints(128, 1112063).map(lIllIllIl -> lIllIllIl < 55296 ? lIllIllIl : lIllIllIl + 2048);
            llIIIllII.stream = llIIlIllI.limit(23000L).iterator();
            llIIIllII.firstChar = true;
            llIIIllII.writeBook();
        } else if (llIIIllII.mode.get() == Mode.Ascii) {
            IntStream llIIlIlIl = RANDOM.ints(32, 127);
            llIIIllII.stream = llIIlIlIl.limit(35000L).iterator();
            llIIIllII.firstChar = true;
            llIIIllII.writeBook();
        } else if (llIIIllII.mode.get() == Mode.File) {
            if (llIIIllII.firstTime) {
                File llIIlIIII = new File(MeteorClient.FOLDER, llIIIllII.fileName.get());
                if (!llIIlIIII.exists()) {
                    llIIIllII.error("The file you specified doesn't exist in the meteor folder.", new Object[0]);
                    return;
                }
                try {
                    String llIIlIIlI;
                    BufferedReader llIIlIlII = new BufferedReader(new FileReader(llIIlIIII));
                    StringBuilder llIIlIIll = new StringBuilder();
                    while ((llIIlIIlI = llIIlIlII.readLine()) != null) {
                        llIIlIIll.append(llIIlIIlI).append('\n');
                    }
                    llIIlIlII.close();
                    llIIIllII.firstTime = false;
                    llIIIllII.fileString = String.valueOf(llIIlIIll);
                    llIIIllII.stream = llIIIllII.fileString.chars().iterator();
                    llIIIllII.firstChar = true;
                    llIIIllII.writeBook();
                }
                catch (IOException llIIlIIIl) {
                    llIIIllII.error("Failed to read the file.", new Object[0]);
                }
            } else if (llIIIllII.stream != null) {
                llIIIllII.writeBook();
            } else if (llIIIllII.booksLeft > 0) {
                llIIIllII.stream = llIIIllII.fileString.chars().iterator();
                llIIIllII.writeBook();
            }
        }
    }

    private boolean readChar() {
        BookBot lIllIllll;
        if (!lIllIllll.stream.hasNext()) {
            lIllIllll.stream = null;
            return false;
        }
        lIllIllll.nextChar = lIllIllll.stream.nextInt();
        return true;
    }

    static {
        LINE_WIDTH = 113;
        RANDOM = new Random();
    }

    public BookBot() {
        super(Categories.Misc, "book-bot", "Writes an amount of books full of characters or from a file.");
        BookBot llIlIIlII;
        llIlIIlII.sgGeneral = llIlIIlII.settings.getDefaultGroup();
        llIlIIlII.mode = llIlIIlII.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode of the book bot.").defaultValue(Mode.Ascii).build());
        llIlIIlII.name = llIlIIlII.sgGeneral.add(new StringSetting.Builder().name("name").description("The name you want to give your books.").defaultValue("Meteor on Crack!").build());
        llIlIIlII.fileName = llIlIIlII.sgGeneral.add(new StringSetting.Builder().name("file-name").description("The name of the text file (.txt NEEDED)").defaultValue("book.txt").build());
        llIlIIlII.noOfPages = llIlIIlII.sgGeneral.add(new IntSetting.Builder().name("no-of-pages").description("The number of pages to write per book.").defaultValue(100).min(1).max(100).sliderMax(100).build());
        llIlIIlII.noOfBooks = llIlIIlII.sgGeneral.add(new IntSetting.Builder().name("no-of-books").description("The number of books to make (or until the file runs out).").defaultValue(1).build());
        llIlIIlII.delay = llIlIIlII.sgGeneral.add(new IntSetting.Builder().name("delay").description("The amount of delay between writing books in milliseconds.").defaultValue(300).min(75).sliderMin(75).sliderMax(600).build());
        llIlIIlII.pages = new class_2499();
        llIlIIlII.ticksLeft = 0;
        llIlIIlII.pageSb = new StringBuilder();
        llIlIIlII.lineSb = new StringBuilder();
    }

    @Override
    public void onDeactivate() {
        llIIlllll.booksLeft = 0;
        llIIlllll.pages = new class_2499();
    }

    private void writeBook() {
        BookBot lIllllIII;
        lIllllIII.pages.clear();
        if (lIllllIII.firstChar) {
            lIllllIII.readChar();
            lIllllIII.firstChar = false;
        }
        for (int lIllllIlI = 0; lIllllIlI < lIllllIII.noOfPages.get(); ++lIllllIlI) {
            lIllllIII.pageSb.setLength(0);
            boolean lIllllIll = false;
            for (int lIlllllII = 0; lIlllllII < 13; ++lIlllllII) {
                boolean lIlllllIl;
                block6: {
                    lIllllIII.lineSb.setLength(0);
                    float lIllllllI = 0.0f;
                    lIlllllIl = false;
                    do {
                        float lIlllllll = ((TextHandlerAccessor)lIllllIII.mc.field_1772.method_27527()).getWidthRetriever().getWidth(lIllllIII.nextChar, class_2583.field_24360);
                        if (lIllllIII.nextChar == 10) {
                            if (!lIllllIII.readChar()) {
                                lIlllllIl = true;
                            }
                            break block6;
                        }
                        if (!(lIllllllI + lIlllllll < 113.0f)) break block6;
                        lIllllIII.lineSb.appendCodePoint(lIllllIII.nextChar);
                        lIllllllI += lIlllllll;
                    } while (lIllllIII.readChar());
                    lIlllllIl = true;
                }
                lIllllIII.pageSb.append((CharSequence)lIllllIII.lineSb).append('\n');
                if (!lIlllllIl) continue;
                lIllllIll = true;
                break;
            }
            lIllllIII.pages.add((Object)class_2519.method_23256((String)String.valueOf(lIllllIII.pageSb)));
            if (lIllllIll) break;
        }
        lIllllIII.mc.field_1724.method_6047().method_7959("pages", (class_2520)lIllllIII.pages);
        lIllllIII.mc.field_1724.method_6047().method_7959("author", (class_2520)class_2519.method_23256((String)"squidoodly"));
        lIllllIII.mc.field_1724.method_6047().method_7959("title", (class_2520)class_2519.method_23256((String)lIllllIII.name.get()));
        lIllllIII.mc.field_1724.field_3944.method_2883((class_2596)new class_2820(lIllllIII.mc.field_1724.method_6047(), true, lIllllIII.mc.field_1724.field_7514.field_7545));
        --lIllllIII.booksLeft;
    }

    public static enum Mode {
        File,
        Random,
        Ascii;


        private Mode() {
            Mode lIllIIIIlIlllIl;
        }
    }
}

