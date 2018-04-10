import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class JournalSRP {
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String text) {
        entries.add(text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    // SRP Violation - Journal is taking new concerns/responsibility below
    public void saveToFile(String fileName) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(fileName)) {
            out.println(toString());
        }
    }

    public void loadFromFile(String fileName) {
    }
}

class Journal {
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String text) {
        entries.add(text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }
}

class JournalPersistence {
    public void saveToFile(Journal journal, String fileName) throws FileNotFoundException {
        try (PrintStream out = new PrintStream(fileName)) {
            out.println(journal.toString());
        }
    }

    public Journal loadFromFile(String fileName) {
        return new Journal();
    }
}