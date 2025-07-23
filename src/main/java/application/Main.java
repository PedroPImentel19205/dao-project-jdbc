package application;

import model.entities.Department;
import model.entities.Seller;
import modelDAO.DaoFactory;
import modelDAO.SellerDao;

import java.sql.Connection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==== TEST 1: seller findById ====");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        sc.close();
    }

}
