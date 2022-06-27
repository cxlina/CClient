package com.github.cxlina.cclient.server;

public class ServerData {

    private final String displayName;
    private final String serverAddress;
    private final int serverPort;

    public ServerData(String displayName, String serverAddress, int serverPort) {
        this.displayName = displayName;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String serialize() {
        return "displayName=" + this.displayName + ";serverAddress=" + this.serverAddress + ";serverPort=" + this.serverPort;
    }

    public static ServerData deserialize(String data) throws NumberFormatException {
        String[] values = data.split(";");
        return new ServerData(values[0].substring(12), values[1].substring(14), Integer.parseInt(values[2].substring(11)));
    }
}
