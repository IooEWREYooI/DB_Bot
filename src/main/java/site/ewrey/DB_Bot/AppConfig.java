package site.ewrey.DB_Bot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import site.ewrey.DB_Bot.Commands.BotController;

@Configuration
@ComponentScan("site.ewrey.DB_Bot")
public class AppConfig {

    @Bean
    public BotController registration(){
        BotController bot = new BotController();
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(bot);
            System.out.println("Бот @"+bot.getBotUsername()+" успешно запущен!!!");
        } catch (Exception e){
            e.printStackTrace();
        }
        return bot;
    }
}
