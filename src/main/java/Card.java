public class Card {
    private String suit;
    private String value;
    private boolean faceUp;
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
        this.faceUp = false;
    }
    public String getSuit(){
// only return the suit if the card is face up
        if(faceUp){
            return suit;
        } else {
            return "#";
        }
    }
    public String getValue(){
// only return the value if the card is face up
        if(faceUp){
// this is the string value of the card
// i.e. A, K, Q, J, 10, 9 ...
            return value;
        } else {
            return "#";
        }
    }
    public int getPointValue(){
// only return the value if the card is face up
        if(faceUp){
    // @todo determine point value and return it
    // A = 11
    // K, Q, J = 10
    // all numeric cards are equal to their face value
            } else {
            return 0;
        }
        return 0;
    }

    public boolean isFaceUp(){
        return faceUp;
    }
    public void flip(){
        faceUp = !faceUp;
    }
}