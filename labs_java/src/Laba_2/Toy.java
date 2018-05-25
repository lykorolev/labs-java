package Laba_2;

public class Toy implements Present {

    @Override
    public Boolean itCanBePresented() {
        return false;
    }

    @Override
    public String whoAmI() {
        return "toy";
    }
}