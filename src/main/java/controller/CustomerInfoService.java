package controller;

import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

public interface CustomerInfoService {

    ObservableList <CustomerInfoDTO> getAllCustomers();

    void addCustomer(String customerId,String firstName,String lastName,String email,String phone,String Address,String city,String date);

    void updateCustomer(String customerId,String firstName,String lastName,String email,String phone,String Address,String city,String date);

    void deleteCustomer(String customerId);
}
