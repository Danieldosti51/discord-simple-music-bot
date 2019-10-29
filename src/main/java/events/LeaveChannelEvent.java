package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveChannelEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(!message.trim().toLowerCase().equals("!leave")) return;
        AudioManager audioManager = event.getGuild().getAudioManager();

        if(!audioManager.isConnected()) {
            event.getChannel().sendMessage("not connected to a voice channel").queue();
            return;
        }

        audioManager.closeAudioConnection();
        event.getChannel().sendMessage("okay, disconnected").queue();
    }

}
