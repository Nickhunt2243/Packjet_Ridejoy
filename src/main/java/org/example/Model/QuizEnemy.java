package org.example.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

public class QuizEnemy extends Enemy{


    /**
     * The constructor QuizEnemy will take in a random Y position for the zapper enemy type and creates the image
     * view with the appropriate x,y position, image, and fit. It will also create a HashMap of the corresponding
     * images to make toggling through the animations easier.
     *
     * @param randomYPos: The random Y position for this enemy.
     */
    public QuizEnemy(int randomYPos) {
        super(enemyType.QUIZMISSILE);
        Image quizImage3 = new Image(new File("Image/quiz3.png").toURI().toString());
        Image quizImage2 = new Image(new File("Image/quiz2.png").toURI().toString());
        Image quizImage1 = new Image(new File("Image/quiz1.png").toURI().toString());

        setImageHash(quizImage1, quizImage2, quizImage3);
        ImageView showQuizMissile = new ImageView(quizImage1);
        showQuizMissile.setX(SCREEN_WIDTH - 50);
        showQuizMissile.setY(randomYPos);
        showQuizMissile.setFitWidth(116.5);
        showQuizMissile.setFitHeight(108);
        setImageView(showQuizMissile);
    }
}
