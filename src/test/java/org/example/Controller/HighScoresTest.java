//package org.example.Controller;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class HighScoresTest {
//
//    private ArrayList<Integer> highScoreArray;
//    private ArrayList<Integer> sortedHighScoreArray;
//    private Integer[] tmpArrayTest1 = {23,75,24,98};
//    private Integer[] tmpArrayTest3 = {1,2,3,23,23,76,77,77,88,88};
//    private HighScores hScore;
//
//    @Test
//    public void testSorting(){
//        this.highScoreArray = new ArrayList<>();
//        for (int i = 0; i< tmpArrayTest1.length; i++) {
//            this.highScoreArray.add(tmpArrayTest1[i]);
//        }
//        ArrayList<Integer> expectedArray = new ArrayList<>();
//        expectedArray.add(98);
//        expectedArray.add(75);
//        expectedArray.add(24);
//        expectedArray.add(23);
//        this.hScore = new HighScores();
//
//        this.sortedHighScoreArray = hScore.SortHighScores(this.highScoreArray);
//        for (int j = 0;j<expectedArray.size();j++) {
//            assertEquals(expectedArray.get(j), sortedHighScoreArray.get(j));
//        }
//    }
//    @Test
//    public void testSortingNull(){
//        HighScores hScore = new HighScores();
//
//        this.sortedHighScoreArray = hScore.SortHighScores(this.highScoreArray);
//        assertNull(sortedHighScoreArray);
//
//    }
//
//    @Test
//    public void testSortingAllSame(){
//        this.highScoreArray = new ArrayList<>();
//        for (int i=0;i<tmpArrayTest3.length;i++){
//            this.highScoreArray.add(tmpArrayTest3[i]);
//        }
//
//        HighScores hScore = new HighScores();
//        ArrayList<Integer> expectedArray = new ArrayList<>();
//        expectedArray.add(88);
//        expectedArray.add(88);
//        expectedArray.add(77);
//        expectedArray.add(77);
//        expectedArray.add(76);
//        expectedArray.add(23);
//        expectedArray.add(23);
//        expectedArray.add(3);
//        expectedArray.add(2);
//        expectedArray.add(1);
//
//        this.hScore = new HighScores();
//
//        this.sortedHighScoreArray = hScore.SortHighScores(this.highScoreArray);
//        for (int j = 0;j<expectedArray.size();j++) {
//            assertEquals(expectedArray.get(j), sortedHighScoreArray.get(j));
//        }
//
//    }
//
//
//}