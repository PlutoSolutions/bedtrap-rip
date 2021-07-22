/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2BooleanMap
 *  it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap
 *  net.minecraft.class_124
 *  net.minecraft.class_1299
 *  net.minecraft.class_1657
 *  net.minecraft.class_2561
 *  net.minecraft.class_2585
 *  net.minecraft.class_5250
 */
package minegame159.meteorclient.systems.modules.world;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.EntityAddedEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.EntityTypeListSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.friends.Friends;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.class_124;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_5250;

public class EntityLogger
extends Module {
    private final /* synthetic */ Setting<Boolean> friends;
    private final /* synthetic */ Setting<Boolean> playerNames;
    private final /* synthetic */ Setting<Object2BooleanMap<class_1299<?>>> entities;
    private final /* synthetic */ SettingGroup sgGeneral;

    public EntityLogger() {
        super(Categories.World, "entity-logger", "Sends a client-side chat alert if a specified entity appears in render distance.");
        EntityLogger lllllllllllllllllllIlIllIIIllIII;
        lllllllllllllllllllIlIllIIIllIII.sgGeneral = lllllllllllllllllllIlIllIIIllIII.settings.getDefaultGroup();
        lllllllllllllllllllIlIllIIIllIII.entities = lllllllllllllllllllIlIllIIIllIII.sgGeneral.add(new EntityTypeListSetting.Builder().name("entites").description("Select specific entities.").defaultValue((Object2BooleanMap<class_1299<?>>)new Object2BooleanOpenHashMap(0)).build());
        lllllllllllllllllllIlIllIIIllIII.playerNames = lllllllllllllllllllIlIllIIIllIII.sgGeneral.add(new BoolSetting.Builder().name("player-names").description("Shows the player's name.").defaultValue(true).build());
        lllllllllllllllllllIlIllIIIllIII.friends = lllllllllllllllllllIlIllIIIllIII.sgGeneral.add(new BoolSetting.Builder().name("friends").description("Logs friends.").defaultValue(true).build());
    }

    @EventHandler
    private void onEntityAdded(EntityAddedEvent lllllllllllllllllllIlIllIIIIllIl) {
        EntityLogger lllllllllllllllllllIlIllIIIIlllI;
        if (lllllllllllllllllllIlIllIIIIllIl.entity.method_5667().equals(lllllllllllllllllllIlIllIIIIlllI.mc.field_1724.method_5667())) {
            return;
        }
        if (lllllllllllllllllllIlIllIIIIlllI.entities.get().getBoolean((Object)lllllllllllllllllllIlIllIIIIllIl.entity.method_5864())) {
            String lllllllllllllllllllIlIllIIIlIIlI;
            if (lllllllllllllllllllIlIllIIIIllIl.entity instanceof class_1657 && !lllllllllllllllllllIlIllIIIIlllI.friends.get().booleanValue() && Friends.get().get((class_1657)lllllllllllllllllllIlIllIIIIllIl.entity) != null) {
                return;
            }
            if (lllllllllllllllllllIlIllIIIIlllI.playerNames.get().booleanValue() && lllllllllllllllllllIlIllIIIIllIl.entity instanceof class_1657) {
                String lllllllllllllllllllIlIllIIIlIIll = String.valueOf(new StringBuilder().append(lllllllllllllllllllIlIllIIIIllIl.entity.method_5820()).append(" (Player)"));
            } else {
                lllllllllllllllllllIlIllIIIlIIlI = lllllllllllllllllllIlIllIIIIllIl.entity.method_5864().method_5897().getString();
            }
            class_5250 lllllllllllllllllllIlIllIIIlIIIl = new class_2585(String.valueOf(new StringBuilder().append(lllllllllllllllllllIlIllIIIlIIlI).append(" "))).method_27692(class_124.field_1068);
            lllllllllllllllllllIlIllIIIlIIIl.method_10852((class_2561)new class_2585(" has spawned at ").method_27692(class_124.field_1080));
            lllllllllllllllllllIlIllIIIlIIIl.method_10852((class_2561)ChatUtils.formatCoords(lllllllllllllllllllIlIllIIIIllIl.entity.method_19538()));
            lllllllllllllllllllIlIllIIIlIIIl.method_10852((class_2561)new class_2585(".").method_27692(class_124.field_1080));
            lllllllllllllllllllIlIllIIIIlllI.info((class_2561)lllllllllllllllllllIlIllIIIlIIIl);
        }
    }
}

