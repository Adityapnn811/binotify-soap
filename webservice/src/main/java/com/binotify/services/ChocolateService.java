package com.binotify.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface ChocolateService {
    @WebMethod
    public String createChocolateDatabase();

    @WebMethod
    public String addChocolateDatabase(String name, int price);
}
