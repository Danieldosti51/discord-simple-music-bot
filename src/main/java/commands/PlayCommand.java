package commands;

import music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import youtube.YouTubeSearcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PlayCommand implements Command {

    @Override
    public String getToken() {
        return "play";
    }

    @Override
    public void perform(GuildMessageReceivedEvent event, List<String> args) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel()){
            event.getChannel().sendMessage("Not currently in a channel!").queue();
            return;
        }

        if(args.size() == 0) {
            event.getChannel().sendMessage("No arguments provided!").queue();
            return;
        }
        String argument = String.join(" ", args);
        if(!isUrl(argument)){
            YouTubeSearcher searcher = new YouTubeSearcher();
            String id = searcher.searchFor(argument);
            argument = "https://www.youtube.com/watch?v="+id;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        audioManager.openAudioConnection(voiceChannel);

        PlayerManager manager = PlayerManager.getInstance();
        manager.loadAndPlay(event.getChannel(), argument);
    }

    private boolean isUrl(String input) {
        try {
            new URL(input);
            return true;
        } catch (MalformedURLException ignored) {
            return false;
        }
    }

}
