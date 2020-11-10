/**
 * This class will extend the class Card and will be printed out slightly differently than the normal cards
 */
public class PremiumCard extends Card{
    /**
     * This will call the default super constructor
     */
    public PremiumCard() {
        super();
    }

    /**
     * This will call the Card(int x) here
     * @param x This is the value that will used to set the power and toughness
     */
    public PremiumCard(int x) {
        super(x);
    }

    /**
     * This will call the Card(int p, int t)
     * @param p This is the value that will be used to set the power of the premium card
     * @param t This is the value that will be used to set the toughness of the premium card
     */
    public PremiumCard(int p, int t) {
        super(p, t);
    }

    @Override
    public String toString(){
        int cost = getCost();
        return "{{"+getPower()+"/"+getToughness()+":"+cost+"}}";
    }

}
