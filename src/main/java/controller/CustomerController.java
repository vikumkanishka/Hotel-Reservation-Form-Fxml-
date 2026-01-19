package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.sql.*;

public class CustomerController implements CustomerInfoService{
    @Override
    public ObservableList<CustomerInfoDTO> getAllCustomers() {

//        CustomerInfoService customerInfoService= new CustomerController();
        ObservableList<CustomerInfoDTO> observableList= FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM Customers").executeQuery();

            observableList.clear();

            while (resultSet.next()){
                String customerId = resultSet.getString("customer_id");
                String customerName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String date = resultSet.getString("registered_date");

                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(customerId,customerName,lastName,email,phone,address,city,date);

                observableList.add(customerInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return observableList;
    }

    @Override
    public void addCustomer(String customerId, String firstName, String lastName, String email, String phone, String Address, String city, String date) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES(?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,customerId);
            preparedStatement.setObject(2,firstName);
            preparedStatement.setObject(3,lastName);
            preparedStatement.setObject(4,email);
            preparedStatement.setObject(5,phone);
            preparedStatement.setObject(6,Address);
            preparedStatement.setObject(7,city);
            preparedStatement.setObject(8,date);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String customerId, String firstName, String lastName, String email, String phone, String Address, String city, String date) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET ?,?,?,?,?,?,?,? WHERE customer_id = ?");

            preparedStatement.setObject(1,firstName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String customerId) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_reservation_system","root","200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?");

            preparedStatement.setObject(1,customerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
