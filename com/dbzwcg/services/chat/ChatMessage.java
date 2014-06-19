package com.dbzwcg.services.chat;

import com.dbzwcg.types.ChatType;
import org.joda.time.DateTime;

public class ChatMessage {

    private String destinationUser;
    private String sourceUser;
    private String message;
    private ChatType type;
    private String time;
    
    private void createTime () {
        DateTime dt = new DateTime();
        this.time = "[" + dt.dayOfMonth().getAsShortText() + "/" 
                        + dt.monthOfYear().getAsShortText() + "/" 
                        + dt.year().getAsShortText() + " "
                        + dt.getHourOfDay() + ":"
                        + dt.getMinuteOfHour() + ":"
                        + dt.getSecondOfMinute() + "]";
    }
    
    public ChatMessage() {
        createTime();
    }
    
    public ChatMessage(String message) {
        this();
        this.message = message;
    }
    
    // Maybe use JODA to create a static method to convert time into DateTime
    
    public String getMessage() {
        return this.message;
    }
    
    public ChatType getType() {
        return this.type;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getSourceUser() {
        return this.sourceUser;
    }
    
    public String getDestinationUser() {
        return this.destinationUser;
    }
}