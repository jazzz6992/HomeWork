package task1.entity;

import java.util.Objects;

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
        return id == stock.id &&
                Double.compare(stock.bid, bid) == 0 &&
                Double.compare(stock.minPrice, minPrice) == 0 &&
                Double.compare(stock.maxPrice, maxPrice) == 0 &&
                visible == stock.visible &&
                Objects.equals(name, stock.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, bid, minPrice, maxPrice, visible);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bid=" + bid +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", visible=" + visible +
                '}';
    }
}
