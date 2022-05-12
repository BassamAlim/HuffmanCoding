public class Data implements Comparable<Data> {

    private final String c;
    private int freq;
    private final int order;

    public Data(String c, int freq, int order) {
        this.c = c;
        this.freq = freq;
        this.order = order;
    }

    public String getC() {
        return c;
    }

    public int getFreq() {
        return freq;
    }

    public void incFreq() {
        freq++;
    }

    @Override
    public String toString() {
        return "Data{" + "c=" + c + ", freq=" + freq + '}';
    }

    @Override
    public int compareTo(Data o) {
        if (freq == o.freq)
            return order - o.order;
        return freq - o.freq;
    }
}
