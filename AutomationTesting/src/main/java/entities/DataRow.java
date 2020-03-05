package entities;

import java.util.LinkedList;
import java.util.function.Predicate;
import static java.lang.System.err;

public class DataRow {

    public LinkedList<DataColumn> DataColumns;

    public DataRow() {
        DataColumns = new LinkedList<>();
    }

    public String getColumnValue(String columnHeader) {
        try {
            Predicate<DataColumn> predicate = c -> c.getKey().equals(columnHeader);
            DataColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj.getValue();
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return "";
        }

    }

    public DataColumn getColumn(String columnHeader) {
        try {
            Predicate<DataColumn> predicate = c -> c.getKey().equals(columnHeader);
            DataColumn obj = DataColumns.stream().filter(predicate).findFirst().get();
            return obj;
        } catch (Exception ex) {
            err.println("[ERROR] Could not find column - " + columnHeader + " - in table row");
            return null;
        }

    }
}
