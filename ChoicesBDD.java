package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Choice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String jdbcUrl = "jdbc:mysql://localhost:3306/java_project";
        String user = "root";
        String password = "";
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");     
            
        	Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
        	
        	System.out.print("Que voulez vous faire ???\n\n1-Ajouter un éleve\n2-Supprimer un èleve\n3-changez les données d'un éleve\n4-Lire la liste \n\n>>");
        	String choice = scanner.nextLine();
        	
        	if (Integer.valueOf(choice) == 1) {
        	
	        	String sql= "INSERT INTO etudiants (nom, prenom, date_naissance, email, matricule) VALUES(?, ?, ?, ?, ?)";
	        	
	        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        	System.out.println("1/5 Nom de l'éleve >");
	        	String nom = scanner.nextLine();
	        	System.out.println("2/5 Prenom de l'éleve >");
	            String prenom = scanner.nextLine();
	        	System.out.println("3/5 Date de naissance de l'éleve >");
	            String dateNaissance = scanner.nextLine();
	        	System.out.println("4/5 email de l'éleve >");
	            String email = scanner.nextLine();
	        	System.out.println("5/5 Matricule de l'éleve >");
	            String matricule =  scanner.nextLine();
	            
	        	preparedStatement.setString(1, nom);
	        	preparedStatement.setString(2, prenom);
	        	preparedStatement.setString(3, dateNaissance);
	        	preparedStatement.setString(4, email);
	        	preparedStatement.setString(5, matricule);
	        	
	        	int lignesAffectes = preparedStatement.executeUpdate();
	        	
	        	if(lignesAffectes >0) {
	        		System.out.println("Insertion réussie");
	        	}else {
	        		System.out.println("Insertion échoué");
	        	}
	    		preparedStatement.close();
        	}else if (Integer.valueOf(choice) == 2) {
                java.sql.Statement stmt = connection.createStatement();
                String sql = "SELECT * FROM etudiants";
                ResultSet res = stmt.executeQuery(sql);
                
                while(res.next()){
                    int id = res.getInt("id");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    System.out.print("ID : " + id);
                    System.out.print(", Nom : " + nom);
                    System.out.println(", Prenom : " + prenom);
                 } 
                System.out.print("Veuillez rentrez l'id de l'élève quz vosu souhaiter supprimer de la base de donnée >");
                choice = scanner.nextLine();
                
	        	String sql1= "DELETE FROM etudiants WHERE id = ?";
	        	PreparedStatement preparedStatement = connection.prepareStatement(sql1);
	        	preparedStatement.setString(1, choice);
	        	
	        	int lignesAffectes = preparedStatement.executeUpdate();
	        	if(lignesAffectes >0) {
	        		System.out.println("Suppression réussie");
	        	}else {
	        		System.out.println("Suppression échouée");
	        	}
	        	
        	}else if (Integer.valueOf(choice) == 3) {
                java.sql.Statement stmt = connection.createStatement();
                String sql = "SELECT * FROM etudiants";
                ResultSet res = stmt.executeQuery(sql);
                
                while(res.next()){
                    int id = res.getInt("id");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    System.out.print("ID : " + id);
                    System.out.print(", Nom : " + nom);
                    System.out.println(", Prenom : " + prenom);
                 } 
                System.out.print("Veuillez rentrez l'id de l'élève quz vous souhaiter modifié de la base de donnée >");
                choice = scanner.nextLine();
                System.out.println("Que voulez vous modifiez comme données ? >");
                String choice1 = scanner.nextLine();
                System.out.println("Par quelle valeur voulez-vous modifiez la donnée ? >");
                String choice2 = scanner.nextLine();
	        	String sql1= "UPDATE etudiants SET "+choice1+" = ? WHERE id = ?";
	        	PreparedStatement preparedStatement = connection.prepareStatement(sql1);
	        	preparedStatement.setString(1, choice2);
	        	preparedStatement.setString(2, choice);
	        	
	        	int lignesAffectes = preparedStatement.executeUpdate();
	        	if(lignesAffectes >0) {
	        		System.out.println("Changement réussie");
	        	}else {
	        		System.out.println("Changement échouée");
	        	}
        	}else if (Integer.valueOf(choice) == 4) {
                java.sql.Statement stmt = connection.createStatement();
                String sql = "SELECT * FROM etudiants";
                ResultSet res = stmt.executeQuery(sql);
                
                while(res.next()){
                    int id = res.getInt("id");
                    String nom = res.getString("nom");
                    String prenom = res.getString("prenom");
                    System.out.print("ID : " + id);
                    System.out.print(", Nom : " + nom);
                    System.out.println(", Prenom : " + prenom);
                 } 
        	}else {
        		System.out.println("Vous n'avez pas pris un choix valide :[");
        	}
	        	
    		connection.close();
    		
        	
        }catch(SQLException | ClassNotFoundException errorInfos) {
        	errorInfos.printStackTrace();
        }
		
		scanner.close();
		
	}
}
