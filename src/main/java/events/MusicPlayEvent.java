package events;

import music.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import youtube.YouTubeSearcher;

import java.net.MalformedURLException;
import java.net.URL;

public class MusicPlayEvent extends ListenerAdapter {

    private static final String YOUTUBE_URL_FORMAT = "https://www.youtube.com/watch?v=";

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        if(!message.startsWith("!play")){
            return;
        }
        AudioManager audioManager = event.getGuild().getAudioManager();
        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel()){
            event.getChannel().sendMessage("Please join a channel").queue();
            return;
        }

        String argument;
        try{
            argument = message.substring(6);
        } catch (StringIndexOutOfBoundsException e) {
            event.getChannel().sendMessage("No argument provided").queue();
            return;
        }

        if(!isUrl(argument)){
            YouTubeSearcher searcher = new YouTubeSearcher();
            String id = searcher.searchFor(argument);
            argument = YOUTUBE_URL_FORMAT+id;
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
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
