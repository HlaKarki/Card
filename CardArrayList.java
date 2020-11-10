import java.util.*; //for Random and Array

/**
 * This class will create an array of the normal and premium cards
 */
public class CardArrayList implements CardList{
    private Card[] cards; //array for cards
    private int size;  //int size will hold the count of how many cards there are in the array
    private static Random rand = new Random();  //Random object is created here

    /**
     * This method will create an array of card with size 10 and set the int size to 0
     */
    public CardArrayList(){
        cards =  new Card[10];
        size = 0;
    }

    /**
     * This method will accept an int which will determine the size of the new array of cards
     * @param x This is the int which will hold the size value
     */
    public CardArrayList(int x){
        if(!(x<1)){                 //as long as x is not below 1, create the array, otherwise throw an exception
           cards = new Card[x];
           size = 0;
        }
        else{
            throw new IllegalArgumentException("An array of size "+x+" cannot be created!");
        }
    }

    @Override
    public String toString(){

        if(size==0){        //This was done this way because in the GUI code, stage 11, there was a space missing " "
            return "[0: :"+size+"]";  //rather than changing the GUI code, I compromised
        }

        //System.out.println(cards[0]+" "+cards[1]+" "+cards[2]); //debug
        String print="";
        for(int i = 0; i<size; i++){
            if(i==size-1){                  //as long as it is not the last element, keep adding the comma, if it is the last element, don't.
                print+=cards[i].toString();
            }
            else{
                print+=cards[i].toString() + ",";
            }
        }

        return "[0: "+print+" :"+size+"]";
    }

    /**
     * This method will return the size which is the number of cards the array is holding currently
     * @return This will return the private field variable size which holds the number of cards in the array
     */
    public int size(){
        return size;
    }

    /**
     * This method will add a card to the end of the array
     * @param x This is the card that is to be added to the array
     */
    public void add(Card x){
        if (!isRoom()) {        //as long as there is room, just add the card x. Otherwise, expand; i.e to make an array that is twice in size
            //System.out.println("HERE"); //debug
            expand();
        }

        cards[size]=x;
        size++;

    }

    /**
     * This method will remove the card at the very end of the array
     * @return This will return the card that is being removed
     */
    public Card remove(){
        if(size==0){
            throw new IndexOutOfBoundsException("There are no cards in the array!");
        }
        size--;
        return cards[size];
    }

    /**
     * This method will get the card at the location int x from the array
     * @param x The card at this index i.e. x will be returned
     * @return Returns the card at the index x
     */
    public Card get(int x){
        if(x<0 || x>cards.length) {    //if the index x is less than 0 or greater than size of the array, throw an exception
            throw new IndexOutOfBoundsException("The index is out of bound!");
        }

        return cards[x];
    }

    /**
     * This method will find the index of the given card x and return it
     * @param x This is the card whose index will be found and returned
     * @return Returns the index of the card x if found, otherwise it will return -1
     */
    public int indexOf(Card x){
        //System.out.println(cards[4]); //debug
        int cardPower, cardToughness;
        int xPower = x.getPower();
        int xToughness = x.getToughness();

        for(int i=0; i<size; i++){
            cardPower = cards[i].getPower();
            cardToughness = cards[i].getToughness();
            if(cardPower==xPower && cardToughness==xToughness){     //if the power and toughness matches, return the index of that card - otherwise return -1 (not found)
                return i;
            }
        }

        return -1;
    }

    /**
     * This method will add a card at the desired index
     * @param location This is the index at which the card will be added
     * @param x This is the card which is to be added
     */
    public void add(int location, Card x){
        if(location<0 || location>=size){ //as long as the location is less than size counter and greater than 0
            throw new IndexOutOfBoundsException("The location you have entered is out of bounds!");
        }
        else{
            //System.out.println(size); //debug
            if(!isRoom()){      //if there is no room, expand
                expand();
            }
            Card[] newCard = new Card[size+1];  //create a newCard array with size + 1
            for(int i=0; i<location; i++){      //add everything as it is from cards array until the location is reached
                newCard[i] = cards[i];
            }
            for(int i = location; i<size; i++){ //when reached, skip that index and keep adding things as it is
                newCard[i+1]=cards[i];
            }
            newCard[location]=x; //now add the card x at the index which was left before
            size++; //increment the size counter
            for(int i = 0; i<size; i++){ //now replace the cards array with the new array
                cards[i]=newCard[i];
            }
        }

    }

    /**
     * This method will remove the card at a given index
     * @param j This is the location of the card that is to be removed
     * @return Returns the card that is being removed
     */
    public Card remove(int j){
        Card lastElement = null;
        //System.out.println(cards[j]);
        if(size!=0 && j<size && j>=0){      //as long as there is some element in the array and the provided index is within the size counter
            lastElement = cards[j]; //add the card that is at the index j
            //size--;
            Card[] newCard = new Card[size-1];
            for(int i = 0; i<j; i++){       //add the elements as it is before reaching the index j to the newCard array
                newCard[i]=cards[i];
            }

            for(int i = j; i<size; i++){  //when it reaches index j, skip it and add other elements now at the index one less than i since an element was skipped
                if(i!=j){
                    newCard[i-1]=cards[i];
                }
            }
            size--;
            //cards = null;
            for(int i=0; i<size; i++){ //now replace the cards array with the newCard array
                cards[i]=newCard[i];
            }

            //System.out.println(lastElement);
        }
        else{
            throw new IndexOutOfBoundsException("The index is out of bounds or the array is empty!");
        }

        return lastElement;
    }

    /**
     * This method will sort the cards in the array using merge sort
     */
    public void sort(){
        Card[] noNullCard = new Card[size]; //first create a new array without the null values
        for(int i=0; i<size; i++){
            noNullCard[i]=cards[i];
        }
        mergeSort(noNullCard); //pass that new array without the null values to the mergeSort

        for(int i=0; i<size; i++){ //replace the cards array with the sorted array noNullCard
            cards[i]=noNullCard[i];
        }
    }

    private void mergeSort(Card[] x){
        if(x.length >= 2){ //as long as there are two cards available in an array

            Card[] left = Arrays.copyOfRange(x, 0, x.length/2);         //split the array in half, this is the left part
            Card[] right = Arrays.copyOfRange(x, x.length/2, x.length);    //this is the right part

            mergeSort(left);    //recur the left part so that only two cards will remain in the array
            mergeSort(right);   //recur the right part so that only two cards will remain in the array

            merge(x, left, right);  //add the elements from left and right array to one array x
        }
        //base case is the "invisible" else
    }

    private void merge(Card[] result, Card[] left, Card[] right){
        int indexLeft = 0;
        int indexRight = 0;

        for(int i=0; i<result.length; i++){
            //if the right index is greater than its size or if the left index is less than left array's size and the card at the left is less than the card in the right array
            //add that card from left array to the big last array result and increment the left index
            if(indexRight >= right.length || (indexLeft < left.length && ((left[indexLeft].compareTo(right[indexRight])==-1)))){
                result[i] = left[indexLeft];
                indexLeft++;
            }
            else{  //otherwise, add the card from the right array to the big last array result and increment the right index
                result[i] = right[indexRight];
                indexRight++;
            }
        }
    }

    /**
     * This method will shuffle the cards inside the array
     */
    public void shuffle(){
        //this will randomly pick two cards at different location in the array and swap them
        //it will do this 100 times just to make sure everything is shuffled thoroughly
        for(int i = 0; i<100; i++){
            int randomNumA = rand.nextInt(size);
            //System.out.println(randomNumA);
            int randomNumB = rand.nextInt(size);
            //System.out.println(randomNumB);
            if(randomNumA!=randomNumB){
                swap(randomNumA, randomNumB); //call the swap method
            }
        }
    }

    private boolean isRoom(){   //check if there is room by checking if the private field int size is now equal to the array size
        if(size==cards.length){ //return true if there is room and false if there is not
            //System.out.println(size); //debug
            return false;
        }
        return true;
    }

    private void expand(){  //expand the size of the array
        Card[] newCard = new Card[size*2];
        for(int i=0; i<size; i++){      //copy everything from the cards array to a newCard array which is set to a size twice as big as before
            newCard[i]=cards[i];
        }
        cards = new Card[size*2];
        cards = newCard;        //reset the cards array and replace it with the newCard array
        //System.out.println(cards.length); //debug
    }

    private void swap(int a, int b){        //swap by placing the card at index b to index a and vice versa
        Card one = cards[a];
        //System.out.println(one); //debug
        Card second = cards[b];
        //System.out.println(second); //debug

        cards[a]=second;
        cards[b]=one;

    }

    /**
     * This method will reset the cards array by clearing everything and setting the size of the cards array to 10 and size counter to 0
     */
    public void clear(){
        size = 0;
        cards = new Card[10];
    }
}
