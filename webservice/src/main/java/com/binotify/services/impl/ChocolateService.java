package com.binotify.services.impl;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ChocolateService {
    @WebMethod
    public String createChocolateDatabase();

    @WebMethod
    public String addChocolateDatabase(@WebParam(name = "name") String name, @WebParam(name = "price") int price);
}
