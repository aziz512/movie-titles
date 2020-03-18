import java.util.ArrayList;

// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

public class Movie implements Comparable<Movie> {
    public String title;
    public Integer releaseYear;

    public Movie(String title, Integer releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public Movie(Movie another) {
        this(another.title, another.releaseYear);
    }

    public int compareTo(String title) {
        return this.title.compareToIgnoreCase(title);
    }

    public int compareTo(Movie another) {
        return this.compareTo(another.title);
    }

    public static Movie parseMovieLine(String currentLine) {
        // [movieId,title,genres]
        ArrayList<String> data = Utils.parseCSVLine(currentLine);
        // clean up quotation marks in some entries
        String title = data.get(1).replaceFirst("^(\")*([^\"]+)(\")*$", "$2").trim();
        String titleFormatRegex = "(.*)\\(([0-9]+)\\)$";
        Integer year;
        try {
            year = Integer.parseInt(title.replaceFirst(titleFormatRegex, "$2"));
        } catch (NumberFormatException e) {
            // some entries don't have release years
            year = null;
        }
        // remove release date from title
        title = data.get(1).replaceFirst(titleFormatRegex, "$1").trim();
        return new Movie(title, year);
    }
}