package Laba_2;

public class Picture implements Present {
    @Override
    public Boolean itCanBePresented() {
        return true;
    }

    @Override
    public String whoAmI() {
        return "picture";
    }
}
