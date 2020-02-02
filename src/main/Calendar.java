package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calendar extends Application {

    public static final String APP_TITLE = "AmoCalendar";

    public static int width = 800, height = 600;

    public static Stage window;
    public static Scene introScene, mainScene;

    static Border introBtn1Border = new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));

    static Label mLb1;
    static String testForMLb1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle(APP_TITLE);
        window.getIcons().add(new Image(Calendar.class.getResourceAsStream("icon.png")));

        //Definitions
        //Intro
        VBox inVbOuter = new VBox();
        Label inLbName = new Label();
        Button inBtnStart = new Button();

        introScene = new Scene(inVbOuter, width, height);

        //Main
        BorderPane mBpOuter = new BorderPane();
        mLb1 = new Label();

        mainScene = new Scene(mBpOuter, width, height);

        //Advanced
        //Intro scene
        inLbName.setText(APP_TITLE);
        inLbName.setFont(new Font("Times New Roman", 80));
        inLbName.setTextFill(Paint.valueOf("#ddd"));
        inLbName.setBackground(new Background(new BackgroundFill(Paint.valueOf("#456"), new CornerRadii(25), new Insets(-20))));

        inBtnStart.setText("Start");
        inBtnStart.setPrefSize(220, 40);
        inBtnStart.setFont(new Font("Arial black", 30));
        inBtnStart.setBorder(introBtn1Border);
        inBtnStart.setOnAction(e -> {
            calcDates();
            test();
            window.setScene(mainScene);
        });

        inVbOuter.setAlignment(Pos.CENTER);
        inVbOuter.setSpacing(50);
        inVbOuter.getChildren().addAll(inLbName, inBtnStart);


        //Main scene
        mLb1.setText("XD");
        mLb1.setFont(new Font("404Error", 200));
        test();
        mLb1.setOnMouseClicked(e -> {
            window.setScene(introScene);
        });
        mBpOuter.setCenter(mLb1);


        window.setScene(introScene);
        window.show();
    }

    static void test() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 6; i++)
            s.append(Integer.toHexString((int) (Math.random() * 16)));
        testForMLb1 = s.toString();
        mLb1.setTextFill(Paint.valueOf("#"+testForMLb1));
    }

    static void calcDates() {

    }
}
