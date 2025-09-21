package percobaan_1;
public class Processor {
    private String merk;
    private double cache;

    public Processor(String merk, double cache) {
        this.merk = merk;
        this.cache = cache;
    }

    public Processor() {

    }

    public String getMerk() {
        return merk;
    }
    public double getCache() {
        return cache;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    public void info() {
        System.out.println("Merk Processor: " + merk);
        System.out.println("Cache Memory: " + cache);
    }
}