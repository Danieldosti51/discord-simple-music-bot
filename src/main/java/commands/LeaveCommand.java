package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class LeaveCommand implements Command {

    @Override
    public String getToken() {
        return "leave";
    }

    @Override
    public void perform(GuildMessageReceivedEvent event, List<String> args) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        if(!audioManager.isConnected()) {
            event.getChannel().sendMessage("Not connected to a voice channel!").queue();
            return;
        }
        audioManager.closeAudioConnection();
        event.getChannel().sendMessage("Okay, disconnected.").queue();
    }

}
