package modelDAO;

import JDBC.DB;
import modelDAOImpl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao (){
       return new SellerDaoJDBC(DB.getConnection());
    }

}
