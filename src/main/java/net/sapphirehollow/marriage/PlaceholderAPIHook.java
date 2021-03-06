package net.sapphirehollow.marriage;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.sapphirehollow.marriage.controllers.MarriageController;
import net.sapphirehollow.marriage.storage.PlayerStorage;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlaceholderAPIHook extends PlaceholderExpansion {


    @Override
    public boolean persist(){ return true; }

    @Override
    public boolean canRegister(){ return true; }

    @Override
    public String getIdentifier() {
        return "marriage";
    }

    @Override
    public String getAuthor() {
        return Marriage.instance.getDescription().getAuthors().get(0);
    }

    @Override
    public String getVersion() {
        return Marriage.instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) return "";
        PlayerStorage storage = Marriage.getStorageController().getPlayerStorage(player);

        switch (identifier) {
            case "heart_space": return storage.getChatPrefix(true);
            case "heart": return storage.getChatPrefix(false);
            case "married_list": return uuidListToNames(storage.getMarriages());
            case "married_list_full":
                if (storage.getMarriages().size() == 0) return "";
                return ChatColor.GOLD + "Marriages: " + ChatColor.YELLOW + uuidListToNames(storage.getMarriages());
            case "married_list_full_new":
                if (storage.getMarriages().size() == 0) return "";
                return ChatColor.GOLD + "Marriages: " + ChatColor.YELLOW + uuidListToNames(storage.getMarriages()) + "\n";
            case "new_married_list_full":
                if (storage.getMarriages().size() == 0) return "";
                return "\n" + ChatColor.GOLD + "Marriages: " + ChatColor.YELLOW + uuidListToNames(storage.getMarriages());
            case "new_married_list_full_new":
                if (storage.getMarriages().size() == 0) return "";
                return "\n" + ChatColor.GOLD + "Marriages: " + ChatColor.YELLOW + uuidListToNames(storage.getMarriages()) + "\n";
            case "engaged_list": return uuidListToNames(storage.getEngagements());
            case "engaged_list_full":
                if (storage.getEngagements().size() == 0) return "";
                return ChatColor.GOLD + "Engagements: " + ChatColor.YELLOW + uuidListToNames(storage.getEngagements());
            case "engaged_list_full_new":
                if (storage.getEngagements().size() == 0) return "";
                return ChatColor.GOLD + "Engagements: " + ChatColor.YELLOW + uuidListToNames(storage.getEngagements()) + "\n";
            case "new_engaged_list_full":
                if (storage.getEngagements().size() == 0) return "";
                return "\n" + ChatColor.GOLD + "Engagements: " + ChatColor.YELLOW + uuidListToNames(storage.getEngagements());
            case "new_engaged_list_full_new":
                if (storage.getEngagements().size() == 0) return "";
                return "\n" + ChatColor.GOLD + "Engagements: " + ChatColor.YELLOW + uuidListToNames(storage.getEngagements()) + "\n";
            default: return "";
        }
    }

    private static String uuidListToNames(List<String> uuids) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < uuids.size(); i++) {
            String rawId = uuids.get(i);
            OfflinePlayer player = Marriage.instance.getServer().getOfflinePlayer(UUID.fromString(rawId));
            string.append(player.getName());
            if (i < uuids.size() - 1) string.append(", ");
        }
        return string.toString();

    }
}