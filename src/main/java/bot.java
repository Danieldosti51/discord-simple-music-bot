import events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class bot {

    private static final String OAUTH_KEY = "#";    //TYPE OAUTH2 KEY HERE
    private static final String ACTIVITY = "#";     //TYPE ACTIVITY HERE

    public static void main(String[] args) throws LoginException {
        JDA jda = new JDABuilder(OAUTH_KEY).build();

        jda.addEventListener(new MusicPlayEvent());
        jda.addEventListener(new MusicStopEvent());
        jda.addEventListener(new MusicClearEvent());
        jda.addEventListener(new RespectsEvent());
        jda.addEventListener(new LeaveChannelEvent());

        jda.getPresence().setActivity(Activity.watching(ACTIVITY)); //optional
    }

}
