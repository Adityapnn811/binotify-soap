package com.binotify.services.impl;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface SubscriptionService {
        // Endpoint buat get All subscription Request (PENDING)
        @WebMethod
        public String getSubscriptionReq();

        // Endpoint buat create new subs req
        @WebMethod
        public Boolean createSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Approve Subs Req
        @WebMethod
        public String approveSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Reject Subs Req
        @WebMethod
        public String rejectSubscriptionReq(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);

        // Endpoint buat Check Subs Req
        @WebMethod
        public String checkEndpointRequest(@WebParam(name = "creatorId") int creatorId,
                        @WebParam(name = "subscriberId") int subscriberId, @WebParam(name = "apiKey") String apiKey);
}
