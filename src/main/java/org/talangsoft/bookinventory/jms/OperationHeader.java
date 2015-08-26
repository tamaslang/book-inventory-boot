package org.talangsoft.bookinventory.jms;

import javax.jms.JMSException;
import javax.jms.Message;

public enum OperationHeader implements Header {
    CREATE("Create"),
    UPDATE("Update"),
    DELETE("Delete");

    private static final String NAME = "Operation";
    private final String value;

    OperationHeader(String value) {
        this.value = value;
    }

    public String getName() {
        return NAME;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public Message applyToMessage(Message message) throws JMSException{
        message.setStringProperty(getName(),getValue());
        return message;
    }

}
