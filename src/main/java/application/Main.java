package application;

import model.entities.Department;
import model.entities.Seller;
import modelDAO.DaoFactory;
import modelDAO.SellerDao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
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

        System.out.println("\n==== TEST 2: seller findByDepartment ====");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 3: seller findAll ====");
        list = sellerDao.findAll();

        for (Seller obj : list){
            System.out.println(obj);
        }

        sc.close();
    }

}
