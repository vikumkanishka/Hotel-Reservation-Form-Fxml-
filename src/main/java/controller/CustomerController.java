package controller;

import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

public class CustomerController implements CustomerInfoService{
    @Override
    public ObservableList<CustomerInfoDTO> getAllCustomers() {
        return null;
    }

    @Override
    public void addCustomer(String customerId, String firstName, String lastName, String email, String phone, String Address, String city, String date) {

    }

    @Override
    public void updateCustomer(String customerId, String firstName, String lastName, String email, String phone, String Address, String city, String date) {

    }

    @Override
    public void deleteCustomer(String customerId) {

    }
}
