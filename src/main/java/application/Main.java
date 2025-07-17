package application;

import model.entities.Department;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Department obj = new Department(13,"books");
        System.out.println(obj);

        sc.close();
    }

}
