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
    private boolean firstTime;
    private PrimitiveIterator.OfInt stream;
    private final SettingGroup sgGeneral;
    private final Setting<Integer> noOfBooks;
    private static final Random RANDOM;
    private final Setting<Mode> mode;
    private final Setting<String> name;
    private class_2499 pages;
    private final Setting<String> fileName;
    private final StringBuilder pageSb;
    private boolean firstChar;
    private static final int LINE_WIDTH;
    private final StringBuilder lineSb;
    private String fileString;
    private int ticksLeft;
    private final Setting<Integer> delay;
    private int nextChar;
    private final Setting<Integer> noOfPages;
    private int booksLeft;

    @Override
    public void onActivate() {
        this.booksLeft = this.noOfBooks.get();
        this.firstTime = true;
    }

    private static int lambda$onTick$0(int n) {
        return n < 55296 ? n : n + 2048;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        Object object;
        if (this.mc.field_1755 instanceof class_465) {
            return;
        }
        if (this.booksLeft <= 0) {
            this.toggle();
            return;
        }
        FindItemResult findItemResult = InvUtils.find(class_1802.field_8674);
        if (!findItemResult.isHotbar() && !findItemResult.isOffhand() && ((FindItemResult)(object = InvUtils.findEmpty())).isHotbar()) {
            InvUtils.move().from(findItemResult.slot).toHotbar(((FindItemResult)object).slot);
        }
        if (!(findItemResult = InvUtils.findInHotbar(class_1802.field_8674)).found()) {
            return;
        }
        InvUtils.swap(findItemResult.getSlot());
        if (InvUtils.findInHotbar(class_1802.field_8674).getHand() == null) {
            return;
        }
        if (this.ticksLeft > 0) {
            this.ticksLeft -= 50;
            return;
        }
        this.ticksLeft = this.delay.get();
        if (this.mode.get() == Mode.Random) {
            object = RANDOM.ints(128, 1112063).map(BookBot::lambda$onTick$0);
            this.stream = object.limit(23000L).iterator();
            this.firstChar = true;
            this.writeBook();
        } else if (this.mode.get() == Mode.Ascii) {
            object = RANDOM.ints(32, 127);
            this.stream = object.limit(35000L).iterator();
            this.firstChar = true;
            this.writeBook();
        } else if (this.mode.get() == Mode.File) {
            if (this.firstTime) {
                object = new File(MeteorClient.FOLDER, this.fileName.get());
                if (!((File)object).exists()) {
                    this.error("The file you specified doesn't exist in the meteor folder.", new Object[0]);
                    return;
                }
                try {
                    String string;
                    BufferedReader bufferedReader = new BufferedReader(new FileReader((File)object));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((string = bufferedReader.readLine()) != null) {
                        stringBuilder.append(string).append('\n');
                    }
                    bufferedReader.close();
                    this.firstTime = false;
                    this.fileString = String.valueOf(stringBuilder);
                    this.stream = this.fileString.chars().iterator();
                    this.firstChar = true;
                    this.writeBook();
                }
                catch (IOException iOException) {
                    this.error("Failed to read the file.", new Object[0]);
                }
            } else if (this.stream != null) {
                this.writeBook();
            } else if (this.booksLeft > 0) {
                this.stream = this.fileString.chars().iterator();
                this.writeBook();
            }
        }
    }

    private boolean readChar() {
        if (!this.stream.hasNext()) {
            this.stream = null;
            return false;
        }
        this.nextChar = this.stream.nextInt();
        return true;
    }

    static {
        LINE_WIDTH = 113;
        RANDOM = new Random();
    }

    public BookBot() {
        super(Categories.Misc, "book-bot", "Writes an amount of books full of characters or from a file.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode of the book bot.").defaultValue(Mode.Ascii).build());
        this.name = this.sgGeneral.add(new StringSetting.Builder().name("name").description("The name you want to give your books.").defaultValue("Meteor on Crack!").build());
        this.fileName = this.sgGeneral.add(new StringSetting.Builder().name("file-name").description("The name of the text file (.txt NEEDED)").defaultValue("book.txt").build());
        this.noOfPages = this.sgGeneral.add(new IntSetting.Builder().name("no-of-pages").description("The number of pages to write per book.").defaultValue(100).min(1).max(100).sliderMax(100).build());
        this.noOfBooks = this.sgGeneral.add(new IntSetting.Builder().name("no-of-books").description("The number of books to make (or until the file runs out).").defaultValue(1).build());
        this.delay = this.sgGeneral.add(new IntSetting.Builder().name("delay").description("The amount of delay between writing books in milliseconds.").defaultValue(300).min(75).sliderMin(75).sliderMax(600).build());
        this.pages = new class_2499();
        this.ticksLeft = 0;
        this.pageSb = new StringBuilder();
        this.lineSb = new StringBuilder();
    }

    @Override
    public void onDeactivate() {
        this.booksLeft = 0;
        this.pages = new class_2499();
    }

    private void writeBook() {
        this.pages.clear();
        if (this.firstChar) {
            this.readChar();
            this.firstChar = false;
        }
        for (int i = 0; i < this.noOfPages.get(); ++i) {
            this.pageSb.setLength(0);
            boolean bl = false;
            for (int j = 0; j < 13; ++j) {
                boolean bl2;
                block6: {
                    this.lineSb.setLength(0);
                    float f = 0.0f;
                    bl2 = false;
                    do {
                        float f2 = ((TextHandlerAccessor)this.mc.field_1772.method_27527()).getWidthRetriever().getWidth(this.nextChar, class_2583.field_24360);
                        if (this.nextChar == 10) {
                            if (!this.readChar()) {
                                bl2 = true;
                            }
                            break block6;
                        }
                        if (!(f + f2 < 113.0f)) break block6;
                        this.lineSb.appendCodePoint(this.nextChar);
                        f += f2;
                    } while (this.readChar());
                    bl2 = true;
                }
                this.pageSb.append((CharSequence)this.lineSb).append('\n');
                if (!bl2) continue;
                bl = true;
                break;
            }
            this.pages.add((Object)class_2519.method_23256((String)String.valueOf(this.pageSb)));
            if (bl) break;
            if (3 == 3) continue;
            return;
        }
        this.mc.field_1724.method_6047().method_7959("pages", (class_2520)this.pages);
        this.mc.field_1724.method_6047().method_7959("author", (class_2520)class_2519.method_23256((String)"squidoodly"));
        this.mc.field_1724.method_6047().method_7959("title", (class_2520)class_2519.method_23256((String)this.name.get()));
        this.mc.field_1724.field_3944.method_2883((class_2596)new class_2820(this.mc.field_1724.method_6047(), true, this.mc.field_1724.field_7514.field_7545));
        --this.booksLeft;
    }

    public static enum Mode {
        File,
        Random,
        Ascii;

    }
}

