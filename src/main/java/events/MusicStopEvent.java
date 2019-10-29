package events;

import music.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class MusicStopEvent extends ListenerAdapter{

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (!message.trim().equals("!skip")) {
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        if(!audioManager.isConnected()) {
            event.getChannel().sendMessage("not connected to a voice channel").queue();
            return;
        }

        PlayerManager manager = PlayerManager.getInstance();
        manager.skipTrack(event.getChannel());
    }

}
