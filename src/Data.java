public class Data implements Comparable<Data> {

    private String c;
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

    public int getOrder() {
        return order;
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
        if (freq == o.freq)
            return order - o.order;
        return freq - o.freq;
    }
}
