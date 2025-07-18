package application;

import model.entities.Department;
import model.entities.Seller;
import modelDAO.DaoFactory;
import modelDAO.SellerDao;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Department obj = new Department(13,"books");

        Seller seller = new Seller(5,"Bob","bob@gmail.com",new Date(),2500.0,obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        sc.close();
    }

}
