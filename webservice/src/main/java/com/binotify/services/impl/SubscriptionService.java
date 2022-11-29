package com.binotify.services.impl;

import javax.jws.WebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.binotify.services.models.SubscriptionModel;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SubscriptionService {
        // Endpoint buat get All subscription Request (PENDING)
        @WebMethod(action = "getSubscriptionReq")
        public List<SubscriptionModel> getSubscriptionReq(@WebParam(name = "apiKey") String apiKey);

        // Endpoint buat create new subs req
        @WebMethod(action = "createSubscriptionReq")
        public Boolean createSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Approve Subs Req
        @WebMethod(action = "approveSubscriptionReq")
        public Boolean approveSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Reject Subs Req
        @WebMethod(action = "rejectSubscriptionReq")
        public Boolean rejectSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Check Subs Req
        @WebMethod(action = "checkEndpointRequest")
        public List<SubscriptionModel> checkEndpointRequest(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);
}
