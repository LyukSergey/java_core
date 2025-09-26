package streams;

public class StringStatistics {

    private long count;
    private long totalLength;
    private String initials;

    public StringStatistics(long count, long totalLength, String initials) {
        this.count = count;
        this.totalLength = totalLength;
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "StringStatistics{" +
                "count=" + count +
                ", totalLength=" + totalLength +
                ", initials='" + initials + '\'' +
                '}';
    }

}
