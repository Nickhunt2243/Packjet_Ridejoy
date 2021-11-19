package org.example.Controller;

import java.util.ArrayList;

public class User {
    ArrayList<Integer> Scores;
    ArrayList<String> Names;

    public User(ArrayList<Integer> scores, ArrayList<String> names) {
        this.Scores = (ArrayList<Integer>) scores.clone();
        this.Names= (ArrayList<String>) names.clone();
    }

    public Integer getScores(int i) {
        if (Scores.size()>i) {
            return Scores.get(i);
        }else{
            return null;
        }
    }

    public String getNames(int i) {
        if (Names.size()>i) {
            return Names.get(i);
        }else{
            return null;
        }
    }
    public int getSize(){
        return Scores.size();
    }
}
