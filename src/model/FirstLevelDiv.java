package model;

public class FirstLevelDiv {

    private int divisionId;
    private String name;
    private int countryId;

    public FirstLevelDiv(int divisionId, String name, int countryId) {
        this.divisionId = divisionId;
        this.name = name;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstLevelDiv that = (FirstLevelDiv) o;
        return divisionId == that.divisionId &&
                countryId == that.countryId &&
                name.equals(that.name);
    }

    @Override
    public String toString() {
        return "FirstLevelDiv{" +
                "divisionId=" + divisionId +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
