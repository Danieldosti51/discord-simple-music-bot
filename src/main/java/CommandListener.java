import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private final Manager manager;

    public CommandListener(Manager manager) {
        this.manager = manager;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if(message.startsWith(Constants.COMMAND_PREFIX)) {
            manager.performCommand(event);
        }
    }

}
