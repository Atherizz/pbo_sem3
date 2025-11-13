package percobaan_2;

public class Program
{
    public static void main(String[] args)
    {
        Rektor pakRektor = new Rektor();

        Mahasiswa mahasiswaBiasa = new Mahasiswa("Charlie");
        Sarjana sarjanaCumlaude = new Sarjana("Dini");
        PascaSarjana masterCumlaude = new PascaSarjana("Elok");

        // pakRektor.beriSertifikatCumlaude(mahasiswaBiasa);
        pakRektor.beriSertifikatCumlaude(sarjanaCumlaude);
        sarjanaCumlaude.kuliahDiKampus();
        pakRektor.beriSertifikatCumlaude(masterCumlaude);
        masterCumlaude.kuliahDiKampus();

        pakRektor.beriSertifikatMawapres(masterCumlaude);
        pakRektor.beriSertifikatMawapres(sarjanaCumlaude);


        
    }
}
