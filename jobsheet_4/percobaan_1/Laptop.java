package percobaan_1;
public class Laptop {
    private String merk;
    private Processor processor;

    public Laptop(String merk, Processor processor) {
        this.merk = merk;
        this.processor = processor;
    }

    public Laptop() {
    }

    public String getMerk() {
        return merk;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void info() {
        System.out.println("Merk Laptop: " + merk);
        processor.info();
    }
}
