class Rna{

    String transcribe(String dnaStrand) {
        String rnaStrand = new String();
        for (int idx = 0; idx < dnaStrand.length(); idx++) {
            if(dnaStrand.charAt(idx) == 'G') {
                rnaStrand += 'C';
            }
            else if(dnaStrand.charAt(idx) == 'C') {
                rnaStrand += 'G';
            }
            else if(dnaStrand.charAt(idx) == 'T') {
                rnaStrand += 'A';
            }
            else if(dnaStrand.charAt(idx) == 'A'){
                rnaStrand += 'U';
            }
        }
        return rnaStrand;
    }

    public static void main(String args[]) {
        Rna r = new Rna();
        String dnaStrand = "GCTA";
        String rnaStrand = r.transcribe(dnaStrand);
        System.out.println(rnaStrand);
    }
}