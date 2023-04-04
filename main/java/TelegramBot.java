import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {
    public ArrayList<Double> arr = new ArrayList<>();
    int len,number;
    long chatId = 501841070L;

    public static void main(String[] args) throws TelegramApiException {
        TelegramBot telegramBot = new TelegramBot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()){
            try {
                processCallbackQuery(update.getCallbackQuery());
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (update.hasMessage()){
            try {
                handleMessahe(update.getMessage());

                String messageText = update.getMessage().getText();
                arr.add(Double.valueOf(messageText));
                if (arr.size()== len && number == 1){

                    String result = String.format("%.3f",(Math.pow(5* arr.get(0),arr.get(3)* arr.get(4)) / (arr.get(1)+arr.get(2))) - Math.sqrt(Math.abs(Math.cos(Math.pow(arr.get(4),3)))));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 2){
                    String result = String.format("%.3f",((Math.abs(arr.get(1) - arr.get(2))) / Math.pow(1+2*arr.get(1),arr.get(0))) - Math.exp(1+arr.get(3)));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 3){
                    String result = String.format("%.3f",Math.sqrt(arr.get(0) + arr.get(1)*arr.get(3) + Math.pow(Math.sqrt(Math.sin(arr.get(3))),1.0/3.0)));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 4){
                    String result = String.format("%.3f",Math.log(Math.pow(arr.get(0),7)) + Math.atan(arr.get(1)*arr.get(1)) + (Math.PI / (Math.sqrt(Math.abs(arr.get(0) + arr.get(1))))));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 5){
                    String result = String.format("%.3f",Math.pow(((Math.pow(arr.get(0) + arr.get(1),2) / arr.get(2) + arr.get(3)) + Math.exp(Math.sqrt(arr.get(4)+1))),1.0/5.0));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 6){
                    String result = String.format("%.3f",Math.exp((2*Math.sin(4*arr.get(0)) + Math.pow(Math.cos(arr.get(0)*arr.get(0)),2)) / 3 * arr.get(0)));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len = 0;
                    number = 0;
                }
                if (arr.size()== len && number == 7){
                    String result = String.format("%.3f",1.0/4* (((1+arr.get(0)*arr.get(0))/(1-arr.get(0))) + 1.0/2.0 * Math.tan(arr.get(0))));
                    execute(SendMessage.builder().chatId(chatId).text("ответ = "+ result).build());
                    arr.clear();
                    len =  0;
                    number = 0;
                }

            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void handleMessahe(Message message) throws TelegramApiException {
        if (message.hasText() && message.hasEntities()){
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e-> "bot_command".equals(e.getType())).findFirst();

            if (commandEntity.isPresent()){
               String command = message.getText().substring(commandEntity.get().getOffset(),commandEntity.get().getLength());
                if (command.equals("/option")) {
                    ArrayList<InlineKeyboardButton> buttons = new ArrayList<>();
                    for (int i = 1; i < 8; i++) {
                        buttons.add(InlineKeyboardButton.builder().text(String.valueOf(i)).callbackData(String.valueOf(i)).build());
                    }
                    execute(SendMessage.builder().text("Выберете вариант").chatId(message.getChatId().toString()).replyMarkup(InlineKeyboardMarkup.builder().keyboard(Collections.singleton(buttons)).build()).build());
                }
            }
        }
    }

    public void processCallbackQuery(CallbackQuery buttonQuery) throws TelegramApiException {
        Message message = buttonQuery.getMessage();
         if (buttonQuery.getData().equals("1")){
             number = 1;
             arr.clear();
             len = 5;
             execute(SendMessage.builder().text("Введите a, b, c, n, x \n ").chatId(message.getChatId().toString()).build());
         }
         if (buttonQuery.getData().equals("2")){
             number = 2;
             arr.clear();
             len = 4;
             execute(SendMessage.builder().text("Введите a, x, y, w \n ").chatId(message.getChatId().toString()).build());
        }
        if (buttonQuery.getData().equals("3")){
            number = 3;
            arr.clear();
            len = 4;
            execute(SendMessage.builder().text("Введите a0, a1, a2, x \n ").chatId(message.getChatId().toString()).build());
        }
        if (buttonQuery.getData().equals("4")){
            number = 4;
            arr.clear();
            len = 2;
            execute(SendMessage.builder().text("Введите a, x \n ").chatId(message.getChatId().toString()).build());
        }
        if (buttonQuery.getData().equals("5")){
            number = 5;
            arr.clear();
            len = 5;
            execute(SendMessage.builder().text("Введите a, b, c, d, x \n ").chatId(message.getChatId().toString()).build());
        }
        if (buttonQuery.getData().equals("6")){
            number = 6;
            arr.clear();
            len = 1;
            execute(SendMessage.builder().text("Введите x \n ").chatId(message.getChatId().toString()).build());
        }
        if (buttonQuery.getData().equals("7")){
            number = 7;
            arr.clear();
            len = 1;
            execute(SendMessage.builder().text("Введите x \n ").chatId(message.getChatId().toString()).build());
        }
    }
    @Override
    public String getBotUsername() {
        return "@calculatorBot";
    }
    @Override
    public String getBotToken(){
        return "6042166027:AAGVpMfHehSbqwC5_1_IsOQ_yHHFw4NRN3A";
    }
}