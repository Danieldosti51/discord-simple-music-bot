import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class bot {

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(Constants.OAUTH_KEY).build();

        jda.addEventListener(new CommandListener(new Manager()));
    }

}
