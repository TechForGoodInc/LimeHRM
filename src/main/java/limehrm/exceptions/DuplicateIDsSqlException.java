package limehrm.exceptions;

import limehrm.util.db.RuntimeSqlException;

public class DuplicateIDsSqlException extends RuntimeSqlException {
    public DuplicateIDsSqlException() {
        super("Unable to add row to table (ID column cannot contain duplicate IDs)");
    }
}
