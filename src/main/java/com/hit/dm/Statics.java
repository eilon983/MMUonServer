package main.java.com.hit.dm;

public class Statics {

    private int capacity;
    private String algorithm;
    private int numOfRequests;
    private int numOfDataModels;
    private int numOfSwaps;
    private static Statics single_instance = null;

    private Statics()
    {
        numOfDataModels=0;
        numOfRequests=0;
        numOfSwaps=0;
    }

    public void setCapacity(int n)
    {capacity = n;}
    public void setAlgorithm(String s)
    {algorithm = s;}
    public void incRequests()
    { numOfRequests++; }
    public void incDataModels(int n)
    { numOfDataModels+=n; }
    public void incSwaps(int n)
    {numOfSwaps+=n;}

    public void resetStatics()
    {
        numOfDataModels=0;
        numOfRequests=0;
        numOfSwaps=0;
    }
    public String toString()
    {
        return "Capacity:_" + capacity +"\n" +
                "Algorithm:_" + algorithm +"\n" +
                "Total_number_of_requests:_" + numOfRequests + "\n" +
                "Total_number_of_DataModels:_" + numOfDataModels +"\n" +
                "Total_number_of_DataModels_swaps:_" + numOfSwaps +"\n";
    }

    public static Statics getInstance()
    {
        if (single_instance == null)
            single_instance = new Statics();

        return single_instance;
    }
}
