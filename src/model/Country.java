package model;

public class Country {

    private int countryId;
    private String name;

    public Country(int countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return name.equals(country.name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                '}';
    }
}
