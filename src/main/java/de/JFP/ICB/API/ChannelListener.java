package de.JFP.ICB.API;

public interface ChannelListener {

    void onMessage(String message);

    void setChannel(String channelName);

}
