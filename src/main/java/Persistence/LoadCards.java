package Persistence;

import java.io.IOException;
import java.util.List;

public interface LoadCards {
    
    public List<String> loadCards() throws IOException;

}
