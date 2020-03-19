// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(new File("../data/movies.csv"));
        reader.nextLine();
        MovieBST moviesTree = new MovieBST();
        while (reader.hasNextLine()) {
            String currentLine = reader.nextLine();
            Movie movie = Movie.parseMovieLine(currentLine);
            moviesTree.insert(movie);
        }
        Set<Movie> sample1 = moviesTree.subSet("Bug's Life", "Wagon");
        Set<Movie> sample2 = moviesTree.subSet("Back to the Future", "Hulk");
        Set<Movie> sample3 = moviesTree.subSet("Toy Story", "WALL-E");
        // uncomment for some huge outputs, especially sample 1
        // Utils.printMovieSet(sample1);
        // Utils.printMovieSet(sample2);
        Utils.printMovieSet(sample3);

        reader.close();
    }
}