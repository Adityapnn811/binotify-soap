package com.binotify.services.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionModel {
    private int creatorId;
    private int subscriberId;
    private String status;

    public SubscriptionModel(int creatorId, int subscriberId, String status) {
        this.creatorId = creatorId;
        this.subscriberId = subscriberId;
        this.status = status;
    }

    // Create getter setter
    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(int subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
