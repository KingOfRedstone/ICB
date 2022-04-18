package de.JFP.ICB.API;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private String name;
    private List<ChannelListener> listeners = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
    }

    public void addListener(ChannelListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void onMessage(String message) {
        for (ChannelListener listener : listeners) {
            listener.onMessage(message);
        }
    }

    public String getName() {
        return name;
    }

}
