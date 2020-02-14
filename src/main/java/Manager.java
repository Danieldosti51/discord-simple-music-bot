import commands.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Manager {

    private final HashMap<String, Command> commandMap = new HashMap<>();

    public Manager() {
        createCommand(new ClearCommand());
        createCommand(new LeaveCommand());
        createCommand(new PlayCommand());
        createCommand(new RespectsCommand());
        createCommand(new SkipCommand());
        createCommand(new PauseCommand());
        createCommand(new ResumeCommand());
    }

    void createCommand(Command command) {
        commandMap.put(command.getToken(), command);
    }

    void performCommand(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw().substring(1);
        final String[] tokens = message.split("\\s+");

        if(commandMap.containsKey(tokens[0])) {
            List<String> args = Arrays.asList(tokens).subList(1, tokens.length);
            commandMap.get(tokens[0]).perform(event, args);
        }
    }

}
