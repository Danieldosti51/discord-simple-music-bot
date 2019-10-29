package events;

import music.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class MusicClearEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (!message.trim().equals("!clear")) {
            return;
        }

        AudioManager audioManager = event.getGuild().getAudioManager();
        if(!audioManager.isConnected()) {
            event.getChannel().sendMessage("not connected to a voice channel").queue();
            return;
        }

        PlayerManager manager = PlayerManager.getInstance();
        manager.clearTracks(event.getChannel());
    }

}
