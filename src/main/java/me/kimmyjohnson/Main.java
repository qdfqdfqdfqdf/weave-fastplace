package me.kimmyjohnson;

import me.kimmyjohnson.command.FastplaceCommand;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.event.*;
import net.weavemc.loader.api.event.SubscribeEvent;
import net.weavemc.loader.api.event.TickEvent;

public class Main implements ModInitializer {
    public static boolean enabled = false;
    public static int delay;
    public static void isEnabled(boolean value)
    {
        enabled = value;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Fastplace has been " + (enabled ? "enabled" : "disabled") + "."));
    }

    public static boolean isPlayerInGame() {
        return (Minecraft.getMinecraft().thePlayer != null) && (Minecraft.getMinecraft().theWorld != null);
    }

    @SubscribeEvent
    public void onTick(TickEvent e)
    {
        if(isPlayerInGame() && Minecraft.getMinecraft().inGameHasFocus && enabled)
        {
            ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
            if (item == null || !(item.getItem() instanceof ItemBlock))
            {
                return;
            }
            else
            {
                Minecraft.getMinecraft().rightClickDelayTimer = 0;
            }
        }
        if(!isPlayerInGame() || !Minecraft.getMinecraft().inGameHasFocus || !enabled)
        {
            return;
        }
    }

    @Override
    public void preInit() {
        EventBus.subscribe(this);
        System.out.println("Initializing Fastplace!");
        CommandBus.register(new FastplaceCommand());
    }


}