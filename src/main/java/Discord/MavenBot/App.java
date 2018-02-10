package Discord.MavenBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

import javax.security.auth.login.LoginException;

import Discord.Chatterbot.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter {

	private static ChatterBotSession bot2session;
	private static boolean premission = true;

	public static void main(String[] args) throws Exception {
		ChatterBotFactory factory = new ChatterBotFactory();
		ChatterBot bot2 = factory.create(ChatterBotType.PANDORABOTS, "b0dafd24ee35a477"); // ID of the pandorasbots chatbot u wana use
		bot2session = bot2.createSession();
		JDA jda = new JDABuilder(AccountType.BOT)
				.setToken("Enter Bot token here").setStatus(OnlineStatus.ONLINE)
				.buildBlocking();
		jda.addEventListener(new App());
		System.out.println("Finished loading");
	}

	public void onMessageReceived(MessageReceivedEvent e) {
		Message msg = e.getMessage();
		MessageChannel channel = e.getChannel();
		if (msg.getContentRaw().startsWith("::join") || msg.getContentRaw().startsWith("::play")               //Another bot already used theese commands
				|| msg.getContentRaw().startsWith("::playing") || msg.getContentRaw().startsWith("::resume")   //Another bot already used theese commands
				|| msg.getContentRaw().startsWith("::skip") || msg.getContentRaw().startsWith("::stop")        //Another bot already used theese commands
				|| msg.getContentRaw().startsWith("::summon") || msg.getContentRaw().startsWith("::volume")    //Another bot already used theese commands
				|| msg.getContentRaw().startsWith("::help")){
			return;
		}else if (msg.getContentRaw().startsWith("::")) {
			String[] message = msg.getContentRaw().split("::");
			try {
				String response = bot2session.think(message[1]);
				channel.sendMessage(response).tts(true).queue();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
