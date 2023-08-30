package amdocs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		
		System.out.println(JDBCConnection.getConnection());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc= new Scanner(System.in);
		PetDAO pd = new PetDAO();
		Pet pet = new Pet();
		Boolean f= true;
		while(f) {
			System.out.println("Enter your choice:");
			System.out.println("1. Add new pet details");
			System.out.println("2. Update Pet Price and Vaccination Status");
			System.out.println("3. Delete Pet Details");
			System.out.println("4. View all pets");
			System.out.println("5. Count pets by category");
			System.out.println("6. Find by price");
			System.out.println("7. Find by vaccination status for pet type");
			System.out.println("8. Exit");
			
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				
//				System.out.print("Enter Pet ID: ");
//				pet.setPetId(sc.nextInt());		
				
			    System.out.print("Enter Pet Category: ");
			    pet.setPetCat(br.readLine());
			    
			    System.out.print("Enter Pet Type: ");
			    pet.setPetType(br.readLine());
			    
			    System.out.print("Enter Pet Color: ");
			    pet.setPetColor(br.readLine());
			    
			    System.out.print("Enter Pet age: ");
			    pet.setPetAge(sc.nextInt());
			    
			    System.out.print("Enter pet price: ");
			    pet.setPetPrice(sc.nextDouble());
			    
			    System.out.print("Enter pet Vaccination: ");
			    pet.setPetVac(sc.nextBoolean());
			    
			    System.out.print("Enter pet Food habit: ");
			    pet.setPetFh(br.readLine());
			    
			    int rowsInserted = pd.addNewPet(pet);

		        if (rowsInserted == 1) {
		            System.out.println("Pet details added successfully.");
		        } else {
		            System.out.println("Failed to add pet details.");
		        }
			    break;

			case 2:
				System.out.print("Enter the category ID: ");
		        int categoryId = sc.nextInt();

		        System.out.print("Enter the new pet price: ");
		        double newPrice = sc.nextDouble();

		        System.out.print("Enter the new vaccination status (true/false): ");
		        boolean newVaccinationStatus = sc.nextBoolean();

		        boolean updateSuccessful = pd.updatePetPriceAndVaccinationStatus(categoryId, newPrice, newVaccinationStatus);

		        if (updateSuccessful) {
		            System.out.println("Pet details updated successfully.");
		        } else {
		            System.out.println("Failed to update pet details.");
		        }

  				break;
  				
			case 3:
				System.out.print("Enter Pet ID that you want to Delete: ");
				pd.deletePet(sc.nextInt());
  				
  				break;
			case 4:

				List<Pet> allPets = pd.showAllPets();

				for (Pet pets : allPets) {
				    System.out.println("Pet ID: " + pets.getPetId());
				    System.out.println("Pet Category: " + pets.getPetCat());
				    System.out.println("Pet Type: " + pets.getPetType());
				    System.out.println("Pet Color: " + pets.getPetColor());
				    System.out.println("Pet Age: " + pets.getPetAge());
				    System.out.println("Pet Price: " + pets.getPetPrice());
				    System.out.println("Pet Vaccination: " + pets.getPetVac());
				    System.out.println("Pet Food Habit: " + pets.getPetFh());
				   
				    System.out.println("--------------------------");
				}
				break;
  				

//			case 5:
//				System.out.print("Enter Pet ID which you want to search: ");
//				List<Pet> petDet = pd.search(sc.nextInt());
//
//				for (Pet pets : petDet) {
//				    System.out.println("Pet ID: " + pets.getPetId());
//				    System.out.println("Pet Category: " + pets.getPetCat());
//				    System.out.println("Pet Type: " + pets.getPetType());
//				    System.out.println("Pet Color: " + pets.getPetColor());
//				    System.out.println("Pet Age: " + pets.getPetAge());
//				    System.out.println("Pet Price: " + pets.getPetPrice());
//				    System.out.println("Pet Vaccination: " + pets.getPetVac());
//				    System.out.println("Pet Foor Habit: " + pets.getPetFh());
//				   
//				    System.out.println("--------------------------");
//				}
//				break;
				
			case 5:
				System.out.print("Enter the pet category: ");
		        String petCategory = sc.nextLine();

		        int petCount = pd.countPetsByCategory(petCategory);

		        System.out.println("Number of pets in category " + petCategory + ": " + petCount);

		        break;
		        
			case 6:
				System.out.print("Enter the minimum price: ");
		        double minPrice = sc.nextDouble();

		        System.out.print("Enter the maximum price: ");
		        double maxPrice = sc.nextDouble();

		        List<Pet> petsInPriceRange = pd.searchByPrice(minPrice, maxPrice);

		        if (petsInPriceRange.isEmpty()) {
		            System.out.println("No pets found in the specified price range.");
		        } else {
		            System.out.println("Pets within the price range:");
		            for (Pet pets : petsInPriceRange) {
		            	System.out.println("Pet ID: " + pets.getPetId());
					    System.out.println("Pet Category: " + pets.getPetCat());
					    System.out.println("Pet Type: " + pets.getPetType());
					    System.out.println("Pet Color: " + pets.getPetColor());
					    System.out.println("Pet Age: " + pets.getPetAge());
					    System.out.println("Pet Price: " + pets.getPetPrice());
					    System.out.println("Pet Vaccination: " + pets.getPetVac());
					    System.out.println("Pet Foor Habit: " + pets.getPetFh());
		            }
		        }
		        break;
		        
			case 7:
				System.out.print("Enter the pet category: ");
		        String petType = sc.nextLine();

		        int vaccinatedCount = pd.countByVaccinationStatusForPetType(petType);

		        System.out.println("Number of vaccinated " + petType + "s: " + vaccinatedCount);
			    break;
			    
			case 8:
				f=false;
				break;
			default:
				System.out.println("Invalid option selected. Choose again!");
			
			}
		}
		sc.close();
	}
}
