public class Data implements Comparable<Data> {

    private String c;
    private int freq;

    public Data(String c, int freq) {
        this.c = c;
        this.freq = freq;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void incFreq() {
        freq++;
    }

    @Override
    public String toString() {
        return "Data{" +
                "c=" + c +
                ", freq=" + freq +
                '}';
    }

    @Override
    public int compareTo(Data o) {
        return freq - o.freq;
    }
}
