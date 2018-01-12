package l15.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class StockMarket {
    private String name;
    private String location;
    private Date date;
    List<Stock> stocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockMarket that = (StockMarket) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location) &&
                Objects.equals(date, that.date) &&
                Objects.equals(stocks, that.stocks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, location, date, stocks);
    }

    @Override
    public String toString() {
        return "StockMarket{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", stocks=" + stocks +
                '}';
    }
}
