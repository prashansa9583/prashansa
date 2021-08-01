package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DiceRollSnake extends Application {

    public int playerPos[][] = new int[10][10];
    public int sAndLPos[][] = new int[6][6];

    public int rand;
    public Label randResult;

    public Label gameResult;
    public Label DiceResult;

    public static final int tileSize = 60;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int player1Position = 1;
    public int player2Position = 1;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public int player1XPos = 30;
    public int player1YPos = 570;

    public int player2XPos = 30;
    public int player2YPos = 570;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;

    private  ImageView Diceimage;

    private Group titleGroup = new Group();

    private Parent createContent(){

        Pane root = new Pane();
        root.setPrefSize(width * tileSize, (height * tileSize) + 80);
        root.getChildren().addAll(titleGroup);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Tile tile = new Tile(tileSize, tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                titleGroup.getChildren().add(tile);

            }

        }

        player1 = new Circle(30);
        player1.setId("Player 1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("Style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(30);
        player2.setId("Player 2");
        player2.setFill(Color.RED);
        player2.getStyleClass().add("Style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button1 = new Button("Player 1");
        button1.setTranslateX(170);
        button1.setTranslateY(610);
        button1.setStyle("-fx-border-color: #b22222; -fx-border-width: 2px; -fx-background-color: #FFB6C1; -fx-text-fill: #000000;-fx-font-size: 1.4em;");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player1Position += rand;
                        movePlayer1();
                        translatePlayer(player1XPos, player1YPos, player1);

                        player1Turn = false;
                        player2Turn = true;
                        if(gameStart)
                            gameResult.setText("Player 2 Turn ");
                            gameResult.setTranslateX(10);
                            gameResult.setTranslateY(650);
                            gameResult.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 14));
                            gameResult.setTextFill(Color.INDIGO);


                        if(player1XPos == 60 && player1YPos == 570)
                            translatePlayer(player1XPos = 60, player1YPos = 390,player1);
                    }
                }
            }
        });


        Button button2 = new Button("Player 2");
        button2.setTranslateX(470);
        button2.setTranslateY(610);
        button2.setStyle("-fx-border-color: #483d8b; -fx-border-width: 2px; -fx-background-color: #d8bfd8; -fx-text-fill: #00008b;-fx-font-size: 1.4em;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        player2Position += rand;
                        movePlayer2();
                        translatePlayer(player2XPos, player2YPos, player2);

                        player2Turn = false;
                        player1Turn = true;
                        if(gameStart)
                            gameResult.setText("Player 1 Turn ");
                            gameResult.setTranslateX(10);
                            gameResult.setTranslateY(650);
                            gameResult.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 14));
                            gameResult.setTextFill(Color.MAROON);

                        if(player2XPos == 60 && player2YPos == 570)
                            translatePlayer(player2XPos = 60, player2YPos = 390,player1);
                    }
                }
            }
        });


        gameButton = new Button("Start Game");
        gameButton.setTranslateX(7);
        gameButton.setTranslateY(610);
        gameButton.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 1.2px; -fx-background-color: #F5DEB3; -fx-text-fill: #A0522D;-fx-font-size: 1.2em;");


        gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(!gameStart) {
                    gameStart = true;
                    randResult.setText("");
                    randResult.setTranslateX(300);
                    gameButton.setText("Game Started");
                    player1XPos = 30;
                    player1YPos = 570;

                    player2XPos = 30;
                    player2YPos = 570;

                    player1Position = 1;
                    player2Position = 1;

                    posCir1 = 1;
                    posCir2 = 1;

                    player1.setTranslateX(player1XPos);
                    player1.setTranslateY(player1YPos);
                    player2.setTranslateX(player2XPos);
                    player2.setTranslateY(player2YPos);

                    rand = (int)(Math.random() * 2 +1);
                    if(rand == 1)
                    {
                        player1Turn = true;
                        gameResult.setText("");

                    }else
                    {
                        player2Turn = true;
                        gameResult.setText("");

                    }
                }

            }
        });


        randResult = new Label("");
        randResult.setTranslateX(250);
        randResult.setTranslateY(610);
        gameResult = new Label();
        gameResult.setTranslateX(300);
        gameResult.setTranslateY(640);

        Image img = new Image("snakeladder.png");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);

        Diceimage = new ImageView();
        Diceimage.setFitWidth(60);
        Diceimage.setFitHeight(60);
        Diceimage.setTranslateX(335);
        Diceimage.setTranslateY(610);

        titleGroup.getChildren().addAll(imageView,player1, player2, button1, button2, gameButton,randResult,Diceimage,gameResult);

        return root;

    }

    public void indicateDiceRoll(int diceRoll) {
        switch (diceRoll) {
            case 1:
                Image dice1 = new Image("Dice1.PNG");
                Diceimage.setImage(dice1);
                break;
            case 2:
                Image dice2 = new Image("Dice2.PNG");
                Diceimage.setImage(dice2);
                break;
            case 3:
                Image dice3 = new Image("Dice3.PNG");
                Diceimage.setImage(dice3);
                break;
            case 4:
                Diceimage.setImage(new Image("Dice4.PNG"));
                break;
            case 5:
                Diceimage.setImage(new Image("Dice5.PNG"));
                break;
            case 6:
                Diceimage.setImage(new Image("Dice6.PNG"));
                break;
        }
    }
    public void getDiceValue(){
        rand = (int)(Math.random() * 6 +1);
        indicateDiceRoll(rand);
    }

    public void movePlayer1() {

        if (player1XPos == 60 && player1YPos == 570) {
            player1XPos = 60;
            player1YPos = 390;
        }

        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player1XPos += 60;
            }
            if (posCir1 % 2 == 0) {
                player1XPos -= 60;
            }
            if (player1XPos > 570) {
                player1YPos -= 60;
                player1XPos -= 60;
                posCir1++;
            }
            if (player1XPos < 30) {
                player1YPos -= 60;
                player1XPos += 60;
                posCir1++;
            }

            if (player1XPos < 30 || player1YPos < 30 ||player1Position >= 100) {
                player1XPos = 30;
                player1YPos = 30;

                gameResult.setText("Player one Won");
                gameResult.setTranslateX(10);
                gameResult.setTranslateY(250);
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer1();
    }

    // New positions of player1 for Snakes and Ladders
    public void moveNewPlayer1(){

        if(player1Position == 3){
            player1XPos = 90; player1YPos = 390;
            posCir1 += 3;
            player1Position = 39;
        }
        if(player1Position == 10){
            player1XPos = 511; player1YPos = 510;
            posCir1 += 1;
            player1Position = 12;
        }
        if(player1Position == 27){
            player1XPos = 450; player1YPos = 270;
            posCir1 += 3;
            player1Position = 53;
        }
        if(player1Position == 56){
            player1XPos = 210; player1YPos = 90;
            posCir1 += 3;
            player1Position = 84;
        }
        if(player1Position == 61 || player1Position == 99){
            player1XPos = 90; player1YPos = 30;
            posCir1 += 3;
            player1Position = 99;
        }
        if(player1Position == 72){
            player1XPos = 570; player1YPos = 90;
            posCir1 += 1;
            player1Position = 90;
        }
        if(player1Position == 16){
            player1XPos = 450; player1YPos = 510;
            player1Position = 13;
        }
        if(player1Position == 31){
            player1XPos = 210; player1YPos = 570;
            posCir1 -= 3;
            player1Position = 4;
        }
        if(player1Position == 47) {
            player1XPos = 270; player1YPos = 450;
            posCir1 -= 2;
            player1Position = 25;
        }if(player1Position == 63){
            player1XPos = 30; player1YPos = 270;
            posCir1 -= 1;
            player1Position = 60;
        }
        if(player1Position == 66 ){
            player1XPos = 510; player1YPos = 270;
            posCir1 -= 1;
            player1Position = 52;
        }
        if(player1Position == 97){
            player1XPos = 330; player1YPos = 150;
            posCir1 -= 2;
            player1Position = 75;
        }
        if(player1Position >= 100){
            player1XPos = 30; player1YPos = 30;
            posCir1 = 10;
            player1Position = 100;
        }

    }

    public void movePlayer2() {

        if (player2XPos == 60 && player2YPos == 570) {
            player2XPos = 60;
            player2YPos = 390;
        }

        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos += 60;
            }
            if (posCir2 % 2 == 0) {
                player2XPos -= 60;
            }
            if (player2XPos > 570) {
                player2YPos -= 60;
                player2XPos -= 60;
                posCir2++;
            }
            if (player2XPos < 30) {
                player2YPos -= 60;
                player2XPos += 60;
                posCir2++;
            }

            if (player2XPos < 30 || player2YPos < 30 || player2Position >= 100) {
                player2XPos = 30;
                player2YPos = 30;

                gameResult.setText("Player two Won");
                gameResult.setTranslateX(10);
                gameResult.setTranslateY(250);
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer2();
    }

    // New positions of player2 for Snakes and Ladders
    public void moveNewPlayer2(){

        if(player2Position == 3){
            player2XPos = 90; player2YPos = 390;
            posCir2 += 3;
            player2Position = 39;
        }
        if(player2Position == 10){
            player2XPos = 511; player2YPos = 510;
            posCir2 += 1;
            player2Position = 12;
        }
        if(player2Position == 27){
            player2XPos = 450; player2YPos = 270;
            posCir2 += 3;
            player2Position = 53;
        }
        if(player2Position == 56){
            player2XPos = 210; player2YPos = 90;
            posCir2 += 3;
            player2Position =84;
        }
        if(player2Position == 61 || player2Position == 99 ){
            player2XPos = 90; player2YPos = 30;
            posCir2 += 3;
            player2Position = 99;
        }
        if(player2Position == 72){
            player2XPos = 570; player2YPos = 90;
            posCir2 += 1;
            player2Position = 90;
        }
        if(player2Position == 16){
            player2XPos = 450; player2YPos = 510;
            player2Position = 13;
        }
        if(player2Position == 31){
            player2XPos = 210; player2YPos = 570;
            posCir2 -= 3;
            player2Position = 4;
        }
        if(player2Position == 47){
            player2XPos = 270; player2YPos = 450;
            posCir2 -= 2;
            player2Position = 25;
        }if(player2Position == 63){
            player2XPos = 30; player2YPos = 270;
            posCir2 -= 1;
            player2Position = 60;
        }
        if(player2Position == 66){
            player2XPos = 510; player2YPos = 270;
            posCir2 -= 1;
            player2Position = 52;
        }if(player2Position == 97){
            player2XPos = 330; player2YPos = 150;
            posCir2 -= 2;
            player2Position = 75;
        }
        if(player2Position >= 100){
            player2XPos = 30; player2YPos = 30;
            posCir2 = 10;
            player2Position = 100;
        }

        player2Turn = false;
        player1Turn = true;
        if(gameStart)
            gameResult.setText("Player One turn");
    }

    public void translatePlayer(int x, int y, Circle b){

        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Snake and Ladder Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
