package modelDAOImpl;

import JDBC.DB;
import JDBC.DbException;
import model.entities.Department;
import model.entities.Seller;
import modelDAO.SellerDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC (Connection conn){
        this.conn = conn;
    }

    public SellerDaoJDBC() {
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                 "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUE "
                    + "(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4,obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Unexpected error! No rows Affected!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st =  conn.prepareStatement(
                  "SELECT seller.*,department.Name as DepName "
                    +"FROM seller INNER JOIN department "
                    +"ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");

                  st.setInt(1,id);
                  rs = st.executeQuery();

                  if (rs.next()) {
                      Department dep = instatiateDepartment(rs);
                      Seller obj = instatiateSeller(rs,dep);
                      return obj;
                  }
        }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
       Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);

        return obj;
    }

    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st =  conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "+
                            "FROM seller INNER JOIN department " +
                            "ON seller.DepartmentId = department.Id " +
                            "ORDER BY Name ");

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer , Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }

                Seller obj = instatiateSeller(rs,dep);
                list.add(obj);
            }
            return list;
        }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st =  conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "+
                     "FROM seller INNER JOIN department " +
                     "ON seller.DepartmentId = department.Id " +
                     "WHERE DepartmentId = ? "+
                     "ORDER BY Name ");

            st.setInt(1,department.getId());
            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer , Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instatiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"),dep);
                }

                Seller obj = instatiateSeller(rs,dep);
                list.add(obj);
            }
            return list;
        }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

}
