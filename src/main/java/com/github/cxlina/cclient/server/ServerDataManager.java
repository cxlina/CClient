package com.github.cxlina.cclient.server;

import com.github.cxlina.cclient.io.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ServerDataManager {

    private Configuration serversConfig;
    List<ServerData> serverDataList;

    public ServerDataManager() {
        this.serversConfig = new Configuration("./servers.yml");
        this.serverDataList = new ArrayList<>();
    }

    public void load() {
        List<String> serverDataListSerialized = this.serversConfig.getFile().getStringList("servers");
        for (String data : serverDataListSerialized) {
            this.serverDataList.add(ServerData.deserialize(data));
        }
    }

    public void save() {
        List<String> serverDataListSerialized = new ArrayList<>();
        for (ServerData serverData : this.serverDataList)
            serverDataListSerialized.add(serverData.serialize());
        this.serversConfig.set("servers", serverDataListSerialized);
    }

    public List<ServerData> getServerDataList() {
        return serverDataList;
    }
}
