package model.entity;

public class Stock {
    private int id;
    private String name;
    private double bid;
    private double minPrice;
    private double maxPrice;
    private boolean visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stock stock = (Stock) o;

        if (id != stock.id) return false;
        if (Double.compare(stock.bid, bid) != 0) return false;
        if (Double.compare(stock.minPrice, minPrice) != 0) return false;
        return Double.compare(stock.maxPrice, maxPrice) == 0 && visible == stock.visible && (name != null ? name.equals(stock.name) : stock.name == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(bid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (visible ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tid = ")
                .append(getId())
                .append("\tname = ")
                .append(getName())
                .append("\tbid = ")
                .append(getBid())
                .append("\tmin price = ")
                .append(getMinPrice())
                .append("\tmax price = ").append(getMaxPrice())
                .append("\n");
        return sb.toString();
    }
}
