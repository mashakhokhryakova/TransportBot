import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void onUpdateReceived(Update update){
        Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()) {
                case "/start":
                    sendMsg(message,"Привет, я бот, который может рассказать тебе, как можно на электричке доехать из одного города Свердловской области в другой, а также узнать расписание.");
                    break;
                default:
            }
        }
    }
    

    public String getBotUsername(){
        return "Sverdlovsk Transport bot";
    }

    public String getBotToken(){
        return "1332445590:AAGGT07TOY-2YxjzLOgEaLN95Iz2LtMY8Hs";
    }
}