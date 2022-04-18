package de.JFP.ICB.API;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {

    private static List<Channel> registeredChannels = new ArrayList<>();

    public static Channel getChannel(String name) {
        for (Channel channel : registeredChannels) {
            if (channel.getName().equals(name)) {
                return channel;
            }
        }
        Channel newChannel = new Channel(name);
        registeredChannels.add(newChannel);
        return newChannel;
    }

    public static void onMessage(String message) {
        JSONObject in = new JSONObject(message);
        for (Channel channel : registeredChannels) {
            if (in.getString("name").equals(channel.getName())) {
                channel.onMessage(message);
            }
        }
    }

}
