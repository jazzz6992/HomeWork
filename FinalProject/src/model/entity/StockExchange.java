package model.entity;

import java.util.Date;
import java.util.List;

public class StockExchange {
    private String name;
    private String location;
    private Date date;
    private List<Stock> stock;

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

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockExchange that = (StockExchange) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return (location != null ? location.equals(that.location) : that.location == null) && (date != null ? date.equals(that.date) : that.date == null) && (stock != null ? stock.equals(that.stock) : that.stock == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append("\n")
                .append(getLocation())
                .append("\n")
                .append(getDate().toString())
                .append("\n")
                .append("Stocks:\n");
        return sb.toString();
    }
}
