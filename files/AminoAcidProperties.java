import java.util.HashMap;

public class AminoAcidProperties {
    private final HashMap<String, Integer> codonCounts;
    private final int startRefFrame;
    private final int min;
    private final int max;
    private final StringBuilder dna;

    public AminoAcidProperties(StringBuilder dna, int startRefFrame, int min, int max) {
        codonCounts = new HashMap<>();
        this.startRefFrame = startRefFrame;
        this.min = min;
        this.max = max;
        this.dna = dna;
    }

    private void buildCodonMap(int startRefFrame, StringBuilder dna) {
        codonCounts.clear();
        for (int i = startRefFrame; i < dna.length(); i += 3) {
            try {
                if (codonCounts.containsKey(dna.substring(i, i + 3))) {
                    codonCounts.put(dna.substring(i, i + 3), codonCounts.get(dna.substring(i, i + 3)) + 1);
                } else {
                    codonCounts.put(dna.substring(i, i + 3), 1);
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public void printCodonCounts() {
        buildCodonMap(startRefFrame, dna);
        System.out.println("Codon counts in reading frame " + startRefFrame);
        System.out.println("-------------------------------------");
        for (String codon : codonCounts.keySet()) {
            if (codonCounts.get(codon) >= min && codonCounts.get(codon) <= max) {
                System.out.println(codon.toUpperCase() + ": " + codonCounts.get(codon));
            }
        }
    }
}
