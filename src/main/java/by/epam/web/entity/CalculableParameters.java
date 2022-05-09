package by.epam.web.entity;

public class CalculableParameters {
    private int number1;
    private int number2;
    private int number3;
    private String action;

    public CalculableParameters() {
        this.number1 = 0;
        this.number2 = 0;
        this.number3 = 0;
        this.action = "empty";
    }

    public CalculableParameters(int number1, int number2, int number3, String action) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.action = action;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public int getNumber1() {
        return this.number1;
    }

    public int getNumber2() {
        return this.number2;
    }

    public int getNumber3() {
        return this.number3;
    }

    public String getAction() {
        return this.action;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CalculableParameters calculableParameters = (CalculableParameters) o;
        return ((this.number1 == calculableParameters.number1 && this.action.equals(calculableParameters.action)));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) Double.doubleToLongBits(number1);
        result = 31 * result + action.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\n" + getClass().getSimpleName() + "{" +
                "first_number=" + number1 + "second_number=" + number2 + "third_number=" + number3 +
                ", action='" + action + "}";
    }
}
