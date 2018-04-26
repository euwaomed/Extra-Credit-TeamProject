import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.input.*;

public class PingPong extends Application implements EventHandler<KeyEvent> {

    final int WIDTH = 600;
    final int HEIGHT = 400;

    final double ballRadius = 20;
    double ballX = WIDTH / 2.0;
    double ballY = HEIGHT / 2.0;
    double xSpeed = -3;
    double ySpeed = -3;

    final double racketWidth = 100;
    final double racketHeight = 20;
    double racket1X = WIDTH / 2.0 - racketWidth / 2.0;
    double racket1Y = HEIGHT * 0.90 - racketHeight / 2.0;
    double racket1XSpeed = 0;
    double racket1YSpeed = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        primaryStage.setTitle("Red Ball Program");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));

        Circle circle = new Circle();
        circle.setCenterX(ballX);
        circle.setCenterY(ballY);
        circle.setRadius(ballRadius);
        circle.setFill(Color.ORANGERED);

        root.getChildren().add(circle);

        Rectangle racket1 = new Rectangle();
        racket1.setX(racket1X);
        racket1.setY(racket1Y);
        racket1.setWidth(racketWidth);
        racket1.setHeight(racketHeight);
        racket1.setFill(Color.BLUE);

        root.getChildren().add(racket1);


        final Box keyboardNode = new Box();
        keyboardNode.setFocusTraversable(true);
        keyboardNode.requestFocus();
        keyboardNode.setOnKeyPressed(this);
        keyboardNode.setOnKeyReleased(this);

        root.getChildren().add(keyboardNode);

        primaryStage.show();


        AnimationTimer animator = new AnimationTimer(){

            @Override
            public void handle(long arg0) {

                // UPDATE circle
                ballX += xSpeed;
                ballY += ySpeed;

                if (ballX + ballRadius >= WIDTH)
                {
                    ballX = WIDTH - ballRadius;
                    xSpeed *= -1;
                }
                if (ballX - ballRadius < 0)
                {
                    ballX = 0 + ballRadius;
                    xSpeed *= -1;
                }

                if (ballY + ballRadius >= racket1Y)
                {
                    if (ballY >= racket1Y + racketHeight) {
                        System.out.println("GAME OVER");

                        ballX = WIDTH / 2.0;
                        ballY = HEIGHT / 2.0;
                        xSpeed = 0;
                        ySpeed = 0;
                        //animator.stop();

                    }
                    else if (ballX >= racket1X && ballX <= racket1X + racketWidth) {
                        ballY = racket1Y - ballRadius;
                        ySpeed *= -1;
                    }
                }
                if (ballY - ballRadius < 0)
                {
                    ballY = 0 + ballRadius;
                    ySpeed *= -1;
                }

                // RENDER circle
                circle.setCenterX(ballX);
                circle.setCenterY(ballY);

                // UPDATE racket1
                racket1X += racket1XSpeed;
                racket1Y += racket1YSpeed;

                if (racket1X + racketWidth >= WIDTH)
                {
                    racket1X = WIDTH - racketWidth;
                }
                else if (racket1X < 0)
                {
                    racket1X = 0;
                }

                if (racket1Y + racketHeight >= HEIGHT)
                {
                    racket1Y = HEIGHT - racketHeight;
                }
                else if (racket1Y < 0)
                {
                    racket1Y = 0;
                }

                // RENDER racket1
                racket1.setX(racket1X);
                racket1.setY(racket1Y);
            }
        };

        animator.start();
    }

    @Override
    public void handle(KeyEvent arg0) {
        final double factor = 3;
        //System.out.println (arg0.getCode().getName() + "  " + arg0.getEventType().getName());

        if (arg0.getEventType() == KeyEvent.KEY_PRESSED) {
            if (arg0.getCode() == KeyCode.UP ) {
                racket1YSpeed = -factor;
            }
            else if (arg0.getCode() == KeyCode.DOWN ) {
                racket1YSpeed = factor;
            }
            else if (arg0.getCode() == KeyCode.LEFT ) {
                racket1XSpeed = -factor;
            }
            else if (arg0.getCode() == KeyCode.RIGHT ) {
                racket1XSpeed = factor;
            }
        }
        else if (arg0.getEventType() == KeyEvent.KEY_RELEASED) {
            racket1XSpeed = 0;
            racket1YSpeed = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}