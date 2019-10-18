package events;

import music.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MusicStopEvent extends ListenerAdapter{

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (!message.startsWith("!skip")) {
            return;
        }
        PlayerManager manager = PlayerManager.getInstance();
        manager.skipTrack(event.getChannel());
    }

}
