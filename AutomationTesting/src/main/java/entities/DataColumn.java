package entities;

public class DataColumn {

    private String Key;
    private String Value;

    public DataColumn(String key, String value)
    {
        this.Key = key;
        this.Value = value;
    }

    public String getKey(){return this.Key;}
    public String getValue(){return this.Value;}
}
