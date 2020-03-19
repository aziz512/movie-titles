import java.util.LinkedHashSet;
import java.util.Set;

// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

public class MovieBST {
    private MovieNode first;

    public Set<Movie> subSet(String start, String end) {
        // use LinkedHashSet because it maintains insertion order
        LinkedHashSet<Movie> subset = new LinkedHashSet<Movie>();
        collectMoviesBetween(start, end, this.first, subset);
        return subset;
    }

    private void collectMoviesBetween(String start, String end, MovieNode currentNode, Set<Movie> collector) {
        if (currentNode == null) {
            return;
        }

        collectMoviesBetween(start, end, currentNode.left, collector);
        if (Utils.isMovieInRange(currentNode, start, end)) {
            collector.add(new Movie(currentNode));
        }
        collectMoviesBetween(start, end, currentNode.right, collector);
    }

    public void insert(Movie movie) {
        MovieNode newNode = new MovieNode(movie);
        if (first == null) {
            first = newNode;
        } else {
            insertNode(newNode, first);
        }
    }

    private void insertNode(MovieNode node, MovieNode currentNode) {
        if (node.compareTo(currentNode) <= 0) {
            // should go on left of current node
            if (currentNode.left == null) {
                currentNode.left = node;
            } else {
                insertNode(node, currentNode.left);
            }
        } else {
            // should go on right of current node
            if (currentNode.right == null) {
                currentNode.right = node;
            } else {
                insertNode(node, currentNode.right);
            }
        }
    }
}

class MovieNode extends Movie {
    public MovieNode left;
    public MovieNode right;

    public MovieNode(Movie movie) {
        super(movie.title, movie.releaseYear);
    }
}