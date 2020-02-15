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
            HBox[] mHbDate;

    //Stuff
    String testForMLb1;
    boolean[][] mHbDateMouse = new boolean[42][2];

    static final Paint BLACK = Paint.valueOf("#000");
    static final Paint WHITE = Paint.valueOf("#fff");
    static final Paint VERY_LIGHT_BLUE = Paint.valueOf("#eff");
    static final Paint DATE_BG_BLUE = Paint.valueOf("#234");

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
        inVbOuter = new VBox();
            inLbName = new Label();
            inBtnStart = new Button();

        introScene = new Scene(inVbOuter, 800, 600);

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
                mHbDate = new HBox[42];


        for (int i = 0; i < mHbDate.length; i++) {
            mHbDate[i] = initHbDate(i);
        }

        mainScene = new Scene(mBpOuter);

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
        mBtnLeft.setMinWidth(90);
        mBtnLeft.setPrefWidth(90);
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
        mBtnRight.setMinWidth(90);
        mBtnRight.setPrefWidth(90);
        mBtnRight.setFont(stdFont(40));
        mBtnRight.setTextFill(Paint.valueOf("#aad"));
        mBtnRight.setBackground(null);
        mBtnRight.setOnAction(e -> {
            shiftView(true);
        });

        //container
        mHbTop.setMinSize(0, 50);
        mHbTop.setPrefSize(0, 50);
        mHbTop.setAlignment(Pos.CENTER_LEFT);
        mHbTop.setBackground(bgColor("#456"));
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

        mGpMid.setBackground(bg(DATE_BG_BLUE));
        mGpMid.setPadding(new Insets(15));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mGpMid.add(mHbDate[i*7+j], j, i);
            }
        }

        mBpOuter.setTop(mHbTop);
        mBpOuter.setLeft(mVbLeft);
        mBpOuter.setCenter(mGpMid);
        mBpOuter.setRight(mVbRight);


        window.setMinWidth(16+ mBtnLeft.getMinWidth() + mGpMid.getPadding().getLeft() + mHbDate[0].getMinWidth()*7 + mGpMid.getPadding().getRight() + mBtnRight.getMinWidth());
        window.setMinHeight(38+ mHbTop.getMinHeight() + mGpMid.getPadding().getTop() + mHbDate[0].getMinHeight()*6 + mGpMid.getPadding().getBottom());

        window.setWidth(750);
        window.setHeight(700);

        window.setScene(mainScene);
        window.show();
    }

    HBox initHbDate(int i) {
        HBox b = new HBox();
        b.setMinSize(60, 80);
        b.prefWidthProperty().bind(mGpMid.widthProperty());
        b.prefHeightProperty().bind(mGpMid.heightProperty());
        b.setBackground(bgColor("#445"));
        b.setPadding(new Insets(0));
        b.setAlignment(Pos.TOP_CENTER);
        //b.setFont(stdFont(24));
        //b.setTextFill(VERY_LIGHT_BLUE);
        b.setOnMouseEntered(e -> {
            b.setBackground(bgColor("#334"));
        });
        b.setOnMouseExited(e -> {
            if (mHbDateMouse[i][0])
                mHbDateMouse[i][1] = true;
            else
                b.setBackground(bgColor("#445"));
        });
        b.setOnMousePressed(e -> {
            mHbDateMouse[i][0] = true;
            b.setBackground(bgColor("#223"));
        });
        b.setOnMouseReleased(e -> {
            if (mHbDateMouse[i][1])
                b.setBackground(bgColor("#445"));
            else {
                b.setBackground(bgColor("#445"));
                AddEventPopup.show("DDMMYYYY");
            }
            mHbDateMouse[i][0] = false;
            mHbDateMouse[i][1] = false;
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
    public static Background bg(Paint p, double ... d) {
        return new Background(new BackgroundFill(p, new CornerRadii((d.length>0)?d[0]:0), new Insets((d.length>1)?d[1]:0)));
    }

    public static Background bgColor(String c, double ... d) {
        return new Background(new BackgroundFill(Paint.valueOf(c), new CornerRadii((d.length>0)?d[0]:0), new Insets((d.length>1)?d[1]:0)));
    }

    public static Font stdFont(double s) {
        return new Font("Arial black", s);
    }
}
