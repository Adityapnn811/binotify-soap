package com.binotify.services.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionModelDao {
    public static List<SubscriptionModel> getSubscriptionReq(ResultSet res) {
        ArrayList<SubscriptionModel> subscriptionReq = new ArrayList<>();
        try {
            while (res.next()) {
                SubscriptionModel subs = new SubscriptionModel(res.getInt("creator_id"), res.getInt("subscriber_id"),
                        res.getString("status"));
                subscriptionReq.add(subs);
            }
        } catch (NullPointerException e) {
            System.out.println("Result is empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriptionReq;
    }
}
