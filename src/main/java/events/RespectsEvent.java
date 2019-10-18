package events;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RespectsEvent extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if (!message.startsWith("!f ")) {
            return;
        }
        if (message.trim().equals("!f") || message.length() == 3) {
            event.getChannel().sendMessage(event.getAuthor().getName()+" has paid their respects.").queue();
        } else {
            String argument = message.substring(3);
            event.getChannel().sendMessage(event.getAuthor().getName()+" has paid their respects to "+argument+".").queue();
        }

    }

}
