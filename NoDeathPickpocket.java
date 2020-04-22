package net.runelite.client.plugins.nodeathpickpocket;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.Player;
import net.runelite.api.Skill;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;


@Slf4j
@PluginDescriptor(
        name = "No Death Pickpocket",
        description = "Never die when pickpocketing"
)

public class NoDeathPickpocket extends Plugin {



    @Provides
    NoDeathPickpocketConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(NoDeathPickpocketConfig.class);
    }

    @Inject
    private NoDeathPickpocketConfig config;

    @Inject
    private Client client;

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {

        int actionParam = event.getActionParam();
        String menuOption = event.getMenuOption();
        String menuTarget = event.getMenuTarget();
        MenuAction menuAction = event.getMenuAction();
        int id = event.getId();
        Player localPlayer = client.getLocalPlayer();
        int healthRatio = localPlayer.getHealthRatio();
        int health = localPlayer.getHealth();

        // int currentHealth = actor.getHealth();
        // boolean consumed - event.consumed;

//        System.out.println("Action Parameter: " + actionParam);
//        System.out.println("Menu Option: " + menuOption);
//        System.out.println("Menu Target: " + menuTarget);
//        System.out.println("Menu Action: " + menuAction);
//        System.out.println("ID: " + id);
//        System.out.println("Total Level: " + totalLevel);
//        System.out.println();

        if (menuOption.equals("Pickpocket") && checkLowHitpoints()) {
            event.consume();
            //System.out.println(client.getBoostedSkillLevel(Skill.HITPOINTS));
            //System.out.println("A knight was clicked at health: " + health + " and health ratio: " + healthRatio);
        }



    }

    private boolean checkLowHitpoints()
    {
        if (config.getHitpointsThreshold() == 0)
        {
            return false;
        }
        if (client.getBoostedSkillLevel(Skill.HITPOINTS) <= config.getHitpointsThreshold())
        {
            return true;
        }

        return false;
    }

}
