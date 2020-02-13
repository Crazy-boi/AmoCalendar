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

    //Presets
    Border introBtn1Border = new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));

    //Nodes
    VBox inVbOuter;
        Label inLbName;
        Button inBtnStart;

    BorderPane mBpOuter;
        HBox mHbTop;
            Label mLbTopDate;
        StackPane mVbLeft;
            Button mBtnLeft;
        StackPane mVbRight;
            Button mBtnRight;
        StackPane mPMid;
            Label mLb1;
        GridPane mGpMid;
            Button[] mBtnDate;

    //Stuff
    String testForMLb1;

    static final Paint BLACK = Paint.valueOf("#000");
    static final Paint WHITE = Paint.valueOf("#fff");
    static final Paint VERY_LIGHT_BLUE = Paint.valueOf("#eff");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle(APP_TITLE);
        window.getIcons().add(new Image(Calendar.class.getResourceAsStream("icon.png")));
        window.setMinWidth(450);
        window.setMinHeight(350);

        //Definitions
        //Intro
        inVbOuter = new VBox();
            inLbName = new Label();
            inBtnStart = new Button();

        introScene = new Scene(inVbOuter, width, height);

        //Main
        mBpOuter = new BorderPane();
            mHbTop = new HBox();
                mLbTopDate = new Label();
            mVbLeft = new StackPane();
                mBtnLeft = new Button();
            mVbRight = new StackPane();
                mBtnRight = new Button();
            mPMid = new StackPane();
                mLb1 = new Label();
            mGpMid = new GridPane();
                mBtnDate = new Button[42];


        for (int i = 0; i < mBtnDate.length; i++) {
            mBtnDate[i] = initBtnDate();
        }

        mainScene = new Scene(mBpOuter, width, height);

        //Advanced
        //Intro scene
        inLbName.setText(APP_TITLE);
        inLbName.setFont(new Font("Times New Roman", 80));
        inLbName.setTextFill(Paint.valueOf("#ddd"));
        inLbName.setBackground(new Background(new BackgroundFill(Paint.valueOf("#456"), new CornerRadii(25), new Insets(-20))));

        inBtnStart.setText("Start");
        inBtnStart.setPrefSize(220, 40);
        inBtnStart.setFont(stdFont(30));
        inBtnStart.setBorder(introBtn1Border);
        inBtnStart.setOnAction(e -> {
            calcDates();
            test();
            window.setScene(mainScene);
        });

        inVbOuter.setAlignment(Pos.CENTER);
        inVbOuter.setSpacing(50);
        inVbOuter.getChildren().add(inLbName);
        inVbOuter.getChildren().add(inBtnStart);


        //Main scene
        //bp left
        mLbTopDate.setText("Montag, 03. Februar 2020");
        mLbTopDate.setFont(stdFont(23));
        mLbTopDate.setTextFill(VERY_LIGHT_BLUE);
        mLbTopDate.setPadding(new Insets(0, 0, 0, 15));

        //bp left
        mBtnLeft.setText("<");
        mBtnLeft.setFont(stdFont(40));
        mBtnLeft.setTextFill(Paint.valueOf("#aad"));
        mBtnLeft.setBackground(null);
        mBtnLeft.setOnAction(e -> {
            shiftView(false);
        });

        //bp center
        mLb1.setText("XD");
        mLb1.setFont(new Font("Times New Roman", 200));
        test();
        mLb1.setOnMouseClicked(e -> {
            window.setScene(introScene);
        });

        //bp right
        mBtnRight.setText(">");
        mBtnRight.setFont(stdFont(40));
        mBtnRight.setTextFill(Paint.valueOf("#aad"));
        mBtnRight.setBackground(null);
        mBtnRight.setOnAction(e -> {
            shiftView(true);
        });

        //container
        mHbTop.setAlignment(Pos.CENTER_LEFT);
        mHbTop.setBackground(bgColor("#456"));
        mHbTop.setPrefHeight(50);
        mHbTop.getChildren().add(mLbTopDate);

        mVbLeft.setAlignment(Pos.CENTER);
        mVbLeft.setBackground(bgColor("#345"));
        mVbLeft.setOnMouseClicked(e -> {
            shiftView(false);
        });
        mVbLeft.getChildren().add(mBtnLeft);

        mVbRight.setAlignment(Pos.CENTER);
        mVbRight.setBackground(bgColor("#345"));
        mVbRight.setOnMouseClicked(e -> {
            shiftView(true);
        });
        mVbRight.getChildren().add(mBtnRight);

        mPMid.setAlignment(Pos.CENTER);
        mPMid.setBackground(bgColor("#234", 0, 0));
        mPMid.getChildren().add(mLb1);

        mGpMid.setBackground(bgColor("#234"));
        mGpMid.setPadding(new Insets(15));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mGpMid.add(mBtnDate[i*7+j], i, j);
            }
        }

        mBpOuter.setTop(mHbTop);
        mBpOuter.setLeft(mVbLeft);
        mBpOuter.setCenter(mGpMid);
        mBpOuter.setRight(mVbRight);


        window.setScene(mainScene);
        window.show();
    }

    Button initBtnDate() {
        Button b = new Button("23");
        b.prefWidthProperty().bind(mGpMid.widthProperty().divide(2));
        b.setBackground(bgColor("#445"));
        b.setFont(stdFont(24));
        b.setTextFill(VERY_LIGHT_BLUE);
        b.setOnMousePressed(e -> {
            b.setBackground(bgColor("#223"));
        });
        b.setOnMouseReleased(e -> {
            b.setBackground(bgColor("#445"));
        });
        return b;
    }

    void test() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 6; i++)
            s.append(Integer.toHexString((int) (Math.random() * 16)));
        testForMLb1 = s.toString();
        mLb1.setTextFill(Paint.valueOf("#"+testForMLb1));
    }

    String getCurrentDate() {
        //String s = Calc.currentDate();

        //Format

        return null;
    }

    void calcDates() {

    }

    void shiftView(boolean toFuture) {
        System.out.println(toFuture);
    }

    //Comfy methods
    Background bgColor(String c, double ... d) {
        return new Background(new BackgroundFill(Paint.valueOf(c), new CornerRadii((d.length>0)?d[0]:0), new Insets((d.length>1)?d[1]:0)));
    }

    Font stdFont(double s) {
        return new Font("Arial black", s);
    }
}
