package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEventPopup {

    static boolean confirm = false;

    public static boolean show(String dayInfo) {
        Stage window = new Stage();
        GridPane gp = new GridPane();

        Button btnConfirm = new Button("Done");
        btnConfirm.setOnAction(e -> {
            confirm = true;
            // save that shit in whatever way you chose to
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(e -> {
            window.close();
        });

        gp.addRow(0, btnCancel, btnConfirm);

        gp.setBackground(Calendar.bg(Calendar.DATE_BG_BLUE));
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(50);

        Scene scene = new Scene(gp, 600, 600);
        window.setScene(scene);

        window.setTitle(String.format("%s.%s.%s", dayInfo.substring(0,2), dayInfo.substring(2,4), dayInfo.substring(4)) + ": Add Calendar Entry");
        window.getIcons().add(new Image(Calendar.class.getResourceAsStream("icon.png")));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        return confirm;
    }
}
