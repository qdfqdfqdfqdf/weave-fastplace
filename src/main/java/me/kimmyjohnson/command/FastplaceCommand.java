package me.kimmyjohnson.command;

import net.weavemc.loader.api.command.Command;
import org.jetbrains.annotations.NotNull;

import static me.kimmyjohnson.Main.isEnabled;
import static me.kimmyjohnson.Main.enabled;
public class FastplaceCommand extends Command {
    public FastplaceCommand() {
        super("fastplace");
    }

    @Override
    public void handle(@NotNull String[] args) {
        isEnabled(!enabled);
    }
}
