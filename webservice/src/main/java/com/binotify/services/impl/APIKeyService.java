package com.binotify.services.impl;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface APIKeyService {
    @WebMethod
    public String generateKey(@WebParam(name = "serviceName") String serviceName);
}
