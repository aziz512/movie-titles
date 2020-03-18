// Aziz Yokubjonov - aziz.yokubjonov@gmail.com
// GitHub: @aziz512
// azizwrites.xyz

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Playlist {
    public Song first;
    public SongHistoryList history = new SongHistoryList();

    public Playlist(File[] files) throws FileNotFoundException {
        Scanner reader;
        for (File file : files) {
            if (!file.getName().contains(".csv"))
                return;
            System.out.printf("Reading %s...\n", file.getName());
            reader = new Scanner(file);
            reader.nextLine();
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                // [rank, song name, artist, streams, url]
                String[] data = Utils.parseCSVLine(line).toArray(new String[0]);
                if (data[1].contains(" ")) { // trim quotation marks
                    data[1] = data[1].substring(1, data[1].length() - 1);
                }
                this.addSong(new Song(data[1], data[2]));
            }
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addSong(Song newSong) {
        this.addElement(newSong);
    }

    private void addElement(Song newElement) {
        if (this.isEmpty()) {
            this.first = newElement;
        } else {
            this.addElement(null, this.first, newElement);
        }
    }

    private void addElement(Song prevElem, Song currentElem, Song newElement) {
        if (currentElem == null) { // newElement will be the last in the list
            prevElem.setNext(newElement);
        } else if (newElement.compareTo(currentElem) < 0) {
            // new element will come before current element
            if (prevElem == null) {
                this.first = newElement;
                this.first.setNext(currentElem);
            } else {
                newElement.setNext(currentElem);
                prevElem.setNext(newElement);
            }
        } else if (newElement.compareTo(currentElem) == 0) {
            return; // don't add duplicates
        } else {
            // new element is greater --> should come after current element
            addElement(currentElem, currentElem.getNext(), newElement);
        }
    }

    public Song listenToSong() {
        Song returnVal = first;
        if (first != null) {
            this.first = first.getNext();
        }
        this.history.addSong(returnVal);
        return returnVal;
    }
}
