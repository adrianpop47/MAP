package utils.events;

import domain.MessageTask;

public class MessageTaskChangeEvent implements Event {
    private ChangeEvent type;
    private MessageTask data, oldData;

    public MessageTaskChangeEvent(ChangeEvent type, MessageTask data) {
        this.type = type;
        this.data = data;
    }
    public MessageTaskChangeEvent(ChangeEvent type, MessageTask data, MessageTask oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public ChangeEvent getType() {
        return type;
    }

    public MessageTask getData() {
        return data;
    }

    public MessageTask getOldData() {
        return oldData;
    }
}