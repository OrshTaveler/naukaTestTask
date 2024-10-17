
import database.*;

public class main {
    public static void main(String[] args) throws Exception {
        PSQL.makeConnection("jdbc:postgresql://localhost/nauka", "postgres", "admin");
        Test.tests();
        
    }
}
