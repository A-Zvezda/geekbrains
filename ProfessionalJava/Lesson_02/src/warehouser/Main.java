package warehouser;

import org.sqlite.core.DB;

public class Main {

    public static void main(String[] args) {
	    DBProcessor.connection();
	    DBProcessor.createTable();
		DBProcessor.emptyTable();
	    DBProcessor.fillTable();
    }
}
