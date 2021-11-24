package org.example.Controller;

import java.util.ArrayList;

public class User {
    /**
     * The ArrayList of scores from the database.
     */
    private final ArrayList<Integer> scores;
    /**
     * The ArrayList of names from the database.
     */
    private final ArrayList<String> names;
    /**
     * This method is used to receive a specific score from the scores ArrayList<Integer>.
     *
     * @param index: The index of the score you want to get.
     * @return       The Integer of the score from the ArrayList<Integer> at the specified index.
     */
    public Integer getScores(int index) {
        if (scores.size()>index) {
            return scores.get(index);
        }else{
            return null;
        }
    }
    /**
     * The method to get the name at a specified index from the name ArrayList<String>.
     *
     * @param index: The index of the name that you want to receive.
     * @return       The String of the name from the ArrayList<String> at the specified index.
     */
    public String getNames(int index) {
        if (names.size()>index) {
            return names.get(index);
        }else{
            return null;
        }
    }
    /**
     * The constructor for the User class. Used to instantiate the scores and names ArrayLists.
     *
     * @param scores: The ArrayList<Integer> for the scores.
     * @param names: The ArrayList<String> for the names.
     */
    public User(ArrayList<Integer> scores, ArrayList<String> names) {
        this.scores = scores;
        this.names = names;
    }
}
