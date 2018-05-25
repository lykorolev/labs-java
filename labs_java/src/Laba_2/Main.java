package Laba_2;

public class Main {
    public static void main(String[] args)
    {
        Product[] products = {
                new Book(),
                new Shoe(),
                new Toy(),
                new Picture()
        };

        for (int i = 0; i < products.length; i++) {
            if (products[i] instanceof Present) {
                System.out.println(products[i].whoAmI()+ "\t" + ((Present)products[i]).itCanBePresented());
            }
            else {
                System.out.println(products[i].whoAmI());
            }
        }
    }
}