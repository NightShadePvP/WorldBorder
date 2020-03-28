package com.wimbli.WorldBorder.cmd;

import com.google.common.collect.Sets;
import com.wimbli.WorldBorder.Config;
import com.wimbli.WorldBorder.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class CmdClean extends WBCmd {
    public CmdClean() {
        name = permission = "clean";
        minParams = maxParams = 0;

        addCmdExample(nameEmphasized() + "- Clean the worldborder config of unused worlds");
        helpText = "When there are a lot of worlds in the worldborder config that is not used by the UHC game, use this command";
    }

    @Override
    public void execute(CommandSender sender, Player player, List<String> params, String worldName) {
        if (player != null)
            Config.log("Clearing config....");

        FileConfiguration configuration = WorldBorder.plugin.getConfig();
        List<String> worlds = WorldBorder.plugin.getServer().getWorlds().stream().map(World::getName).collect(Collectors.toList());
        HashSet<String> toRemove = Sets.newHashSet();

        ConfigurationSection section = configuration.getConfigurationSection("worlds");
        for (String key : section.getKeys(false)) {
            if (worlds.contains(key)) continue;

            toRemove.add(key);
        }

        toRemove.forEach(s -> section.set(s, null));
        WorldBorder.plugin.saveConfig();
        Config.load(WorldBorder.plugin, true);

        if (player != null)
            sender.sendMessage("Cleared " + toRemove.size() + " worlds from config!");
    }
}
