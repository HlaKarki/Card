import java.util.*; //for Random

/**
 * This class will hold the power and toughness of a card as well as calculate the cost of it.
 */
public class Card implements Comparable<Card>{
    private int power;                  //holds the power of the card
    private int toughness;              //holds the toughness of the card

    private static Random rand = new Random(); //Random object is created here

    /**
    This method will create a card with random power and toughness that is within the range 1-100
     */
    public Card(){
        power = rand.nextInt(100)+1;
        toughness = rand.nextInt(100)+1;
    }

    /**
     * This method will create a card with equal power and toughness using the value from the parameter
     * @param x This is the value that will set the card's power and toughness
     */
    public Card(int x){
        if(x>0 && x<=100){              //checking if int x is within 1-100
            power = toughness = x;
        }
        else                            //throwing exception here
            throw new IllegalArgumentException("You can only create cards that is within the range 1-100 for its power and toughness");
    }

    /**
     * This method will create a card with the given power and toughness
     * @param Power This is the value to be set as the card's power
     * @param Toughness This is the value to be set as the card's toughness
     */
    public Card(int Power, int Toughness){

        if(Power>0 && Power<=100 && Toughness>0 && Toughness<=100){ //if both power and toughness is over 0 and less than 101 then creat the card
            power = Power;
            toughness = Toughness;
        }
        else{         //otherwise throw an exception
            throw new IllegalArgumentException("The power or toughness is out of range!");
        }

    }
    /**
     * This method will return the power of the card
     */
    public int getPower(){
        return power;
    }

    /**
     * This method will return the toughness of the card
     * @return
     */
    public int getToughness(){
        return toughness;
    }

    /**
     * This method will calculate the cost and return it
     * @return
     */
    public int getCost(){
        return (int) Math.ceil((Math.pow(((2*power)+toughness),0.5)*10));
    }

    @Override
    public String toString(){
        return "["+power+"/"+toughness+":"+getCost()+"]";
    }

    /**
     * This method will decrement the power and toughness of a card by 2
     */
    public void weaken(){
        power-=2;
        toughness-=2;
        if(power<1){        //if the power or toughness is below 1 after being weakened, set it to 1
            power = 1;
        }
        if(toughness<1){
            toughness = 1;
        }

    }

    /**
     * This method will increment the power and toughness of a card by 2
     */
    public void boost(){
        power +=2;
        toughness +=2;
        if(power>100){      //if the power or toughness is beyond 100 after being boosted, set it to 100
            power = 100;
        }
        if(toughness>100){
            toughness = 100;
        }
    }

    /**
     * This method will compare two cards by looking at the cost; if the costs are equal, it'll look at power; if the powers are equal, it'll look at the toughness
     * @param x This is the card to be compared to
     * @return This will return 1 if the card is greater than the Card x and -1 otherwise
     */
    public int compareTo(Card x) {

        if(getCost() == x.getCost()) {              //if cost of the card equals x.cost than use power to check

            if(power == x.getPower()) {                 //if power of the card equals x.power than use toughness to check

                if(toughness == x.getToughness() || toughness > x.getToughness()) {     //if toughness of the card equals x.toughness than just consider that the card is greater than card x
                    return 1;
                }
                else {
                    return -1;
                }

            }
            else if(power > x.getPower()) {
                return 1;
            }
            else {
                return -1;
            }

        }
        else if(getCost() > x.getCost()) {
            return 1;
        }
        else {
            return -1;
        }

    }

}
