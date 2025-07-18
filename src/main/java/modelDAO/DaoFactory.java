package modelDAO;

import modelDAOImpl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao (){
       return new SellerDaoJDBC();
    }

}
