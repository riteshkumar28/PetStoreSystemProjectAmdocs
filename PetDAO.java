package amdocs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
//	public void addPetDetails(Pet pet) {
//        try (Connection connection = JDBCConnection.getConnection()) {
//            String query = "INSERT INTO pet_store_system (pet_id, pet_category, pet_type, pet_color, pet_age, pet_retail_price, pet_vaccination, pet_food_habit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, pet.getPetId());
//            statement.setString(2, pet.getPetCat());
//            statement.setString(3, pet.getPetType());
//            statement.setString(4, pet.getPetColor());
//            statement.setInt(5, pet.getPetAge());
//            statement.setDouble(6, pet.getPetPrice());
//            statement.setBoolean(7, pet.getPetVac());
//            statement.setString(8, pet.getPetFh());
//            
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	}
	public int addNewPet(Pet pet) {
        int rowsInserted = 0;
        String query = "INSERT INTO pet_store_system (pet_id, pet_category, pet_type, pet_color, pet_age, pet_retail_price, pet_vaccination, pet_food_habit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
          statement.setInt(1, pet.getPetId());
          statement.setString(2, pet.getPetCat());
          statement.setString(3, pet.getPetType());
          statement.setString(4, pet.getPetColor());
          statement.setInt(5, pet.getPetAge());
          statement.setDouble(6, pet.getPetPrice());
          statement.setBoolean(7, pet.getPetVac());
          statement.setString(8, pet.getPetFh());
          
          //statement.executeUpdate();

          rowsInserted = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }
	
    public boolean updatePetPriceAndVaccinationStatus(int categoryId, double newPrice, boolean newVaccinationStatus) {
        boolean isSuccess = false;
        String query = "UPDATE pet_store_system SET pet_retail_price = ?, pet_vaccination = ? WHERE pet_id = ?";

        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setBoolean(2, newVaccinationStatus);
            preparedStatement.setInt(3, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();

            isSuccess = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    
	public List<Pet> showAllPets() {
        List<Pet> pets = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String query = "SELECT * FROM pet_store_system ";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pet pet = new Pet();

                pet.setPetId(resultSet.getInt("pet_id"));
                pet.setPetCat(resultSet.getString("pet_category"));
                pet.setPetType(resultSet.getString("pet_type"));
                pet.setPetColor(resultSet.getString("pet_color"));
                pet.setPetAge(resultSet.getInt("pet_age"));
                pet.setPetPrice(resultSet.getDouble("pet_retail_price"));
                pet.setPetVac(resultSet.getBoolean("pet_vaccination"));
                pet.setPetFh(resultSet.getString("pet_food_habit"));
                
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }
//	 public void updatePet(Pet pet) {
//	        try (Connection connection = JDBCConnection.getConnection()) {
//	            String query = "UPDATE pet_store_system  SET pet_category = ?, pet_type = ?, pet_color = ?, pet_age = ?, pet_retail_price = ?, pet_vaccination = ?, pet_food_habit = ? WHERE pet_id = ?";
//	            PreparedStatement statement = connection.prepareStatement(query);
//	           
//	            
//	            statement.setString(1, pet.getPetCat());
//	            statement.setString(2, pet.getPetType());
//	            statement.setString(3, pet.getPetColor());
//	            statement.setInt(4, pet.getPetAge());
//	            statement.setDouble(5, pet.getPetPrice());
//	            statement.setBoolean(6, pet.getPetVac());
//	            statement.setString(7, pet.getPetFh());
//	            statement.setInt(8, pet.getPetId());
//	            
//	            statement.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
	
	
	 
	 public void deletePet(int petId) {
	        try (Connection connection = JDBCConnection.getConnection()) {
	        	
	            String query = "DELETE FROM pet_store_system  WHERE pet_id = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, petId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
//	 public List<Pet> search(int petId) {
//		 List<Pet> pets = new ArrayList<>();
//		 try(Connection connection = JDBCConnection.getConnection()){
//			 String query = "SELECT * FROM pet_store_system  WHERE pet_id = ?";
//			 PreparedStatement statement = connection.prepareStatement(query);
//			 statement.setInt(1, petId);
//			 ResultSet resultSet = statement.executeQuery();
//
//	            while (resultSet.next()) {
//	            	Pet pet = new Pet();
//
//	                pet.setPetId(resultSet.getInt("pet_id"));
//	                pet.setPetCat(resultSet.getString("pet_category"));
//	                pet.setPetType(resultSet.getString("pet_type"));
//	                pet.setPetColor(resultSet.getString("pet_color"));
//	                pet.setPetAge(resultSet.getInt("pet_age"));
//	                pet.setPetPrice(resultSet.getDouble("pet_retail_price"));
//	                pet.setPetVac(resultSet.getBoolean("pet_vaccination"));
//	                pet.setPetFh(resultSet.getString("pet_food_habit"));
//	                
//	                pets.add(pet);
//	            }
//			 
//		 }
//		 catch(SQLException e) {
//			 e.printStackTrace();
//		 }
//		 return pets;
//	 }
	 public int countPetsByCategory(String petCategory) {
	        int count = 0;
	        String query = "SELECT COUNT(*) AS count FROM pet_store_system WHERE pet_category = ?";

	        try (Connection connection = JDBCConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, petCategory);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                count = resultSet.getInt("count");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return count;
	    }
	 public int countByVaccinationStatusForPetType(String petType) {
	        int count = 0;
	        String query = "SELECT COUNT(*) AS count FROM pet_store_system WHERE pet_category = ? AND pet_vaccination = 1";

	        try (Connection connection = JDBCConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setString(1, petType);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                count = resultSet.getInt("count");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return count;
	    }
	 public List<Pet> searchByPrice(double minPrice, double maxPrice) {
	        List<Pet> petList = new ArrayList<>();
	        String query = "SELECT * FROM pet_store_system WHERE pet_retail_price BETWEEN ? AND ?";

	        try (Connection connection = JDBCConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setDouble(1, minPrice);
	            preparedStatement.setDouble(2, maxPrice);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {

	                Pet pet = new Pet();
	                pet.setPetId(resultSet.getInt("pet_id"));
	                pet.setPetCat(resultSet.getString("pet_category"));
	                pet.setPetType(resultSet.getString("pet_type"));
	                pet.setPetColor(resultSet.getString("pet_color"));
	                pet.setPetAge(resultSet.getInt("pet_age"));
	                pet.setPetPrice(resultSet.getDouble("pet_retail_price"));
	                pet.setPetVac(resultSet.getBoolean("pet_vaccination"));
	                pet.setPetFh(resultSet.getString("pet_food_habit"));
	                petList.add(pet);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return petList;
	    }
}
