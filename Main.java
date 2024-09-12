/* MySql Code :
CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255),
    release_year INT,
    rating DECIMAL(3,1)
);
 */
// Java Code
import java.sql.*;
import java.util.Scanner;

    class maMovieRating {
    public static void main(String[] args) {
        try {
            // Replace with your actual database credentials
            String url = "jdbc:mysql://localhost:3306/your_database_name";
            String username = "your_username";
            String password = "your_password";

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement object
            Statement statement = connection.createStatement();

            // Main loop for user interaction
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Add a movie");
                System.out.println("2. Rate a movie");
                System.out.println("3. View movie ratings");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Prompt user for movie details
                        System.out.print("Enter movie title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter release year: ");
                        int releaseYear = scanner.nextInt();

                        // Insert movie into the database
                        String insertQuery = "INSERT INTO movies (title, genre, release_year) VALUES ('" + title + "', '" + genre + "', " + releaseYear + ")";
                        statement.executeUpdate(insertQuery);
                        System.out.println("Movie added successfully!");
                        break;

                    case 2:
                        // Prompt user for movie ID and rating
                        System.out.print("Enter movie ID: ");
                        int movieId = scanner.nextInt();
                        System.out.print("Enter rating (0-5): ");
                        double rating = scanner.nextDouble();

                        // Update movie rating in the database
                        String updateQuery = "UPDATE movies SET rating = " + rating + " WHERE id = " + movieId;
                        statement.executeUpdate(updateQuery);
                        System.out.println("Rating updated successfully!");
                        break;

                    case 3:
                        // Retrieve all movies and their ratings
                        String selectQuery = "SELECT * FROM movies";
                        ResultSet resultSet = statement.executeQuery(selectQuery);

                        // Print the results
                        System.out.println("Movie Ratings:");
                        while (resultSet.next()) {
                            System.out.println(resultSet.getInt("id") + ". " + resultSet.getString("title") + " (" + resultSet.getString("genre") + ") - " + resultSet.getDouble("rating"));
                        }
                        break;

                    case 4:
                        // Close the connection and exit
                        connection.close();
                        System.out.println("Exiting...");
                        System.exit(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
