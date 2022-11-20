package site.ewrey.DB_Bot.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import site.ewrey.DB_Bot.JPA.UserEntity;
import site.ewrey.DB_Bot.JPA.UserService;


public class BotController extends TelegramLongPollingBot {

    @Autowired
    UserService service = new UserService();
    @Override
    public String getBotUsername() {
        return "YOUR_NAME";
    }
    @Override
    public String getBotToken() {
        return "YOUR_TOKEN";
    }
    @Async
    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        boolean hasText = update.hasMessage() && update.getMessage().hasText();
        String command = update.getMessage().getText();
        if (hasText && command.equals("/addMe")){
            if (!service.existsByChatId(chatId)){
                UserEntity entity = new UserEntity();
                entity.setChatId(chatId);
                entity.setSubscribe(false);
                service.save(entity);
                sendMessage(update,"That's OK!");
            }
            else sendMessage(update, "U are in table yet!");
        }
        else if (hasText && command.equals("/me")){
            sendMessage(update, service.inTable(chatId).toString());
        }

    }

    public void sendMessage(Update update, String text){
        try {
            execute(
                    SendMessage.builder()
                            .chatId((update.hasMessage()) ? update.getMessage().getChatId() : update.getCallbackQuery().getFrom().getId())
                            .text(text)
                            .build());
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
