package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
Alan Huang
CS 2450 Homework 1
Travel Expenses Calculator
 */

public class Main extends Application {

    TextField field1, field2, field3, field4, field5, field6;
    Button submitButton, cancelButton;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Create all labels and title
        Label titel = new Label ("Travel Expenses Calculator");
        Label label = new Label ("Enter your cost in US$ (100.10 for $100.10)");
        Label label1 = new Label ("(1) Number of days on the trip");
        Label label2 = new Label ("(2) Transportation cost (choose one only)");
        Label label3 = new Label ("(a) Airfare Cost ");
        Label label4 = new Label ("(b) Miles driven  ");
        Label label5 = new Label ("(3) Conference registration cost ");
        Label label6 = new Label ("(4) Lodging Cost (per night) ");
        Label label7 = new Label ("(5) Food cost (total) ");
        Label TotalExpensive = new Label("Total expenses: ");
        Label TotalExpensiveResult = new Label(" ");
        Label TotalCost = new Label("How much you own: ");
        Label TotalCostResult = new Label(" ");

        //Change font size
        titel.setFont(new Font("Arial", 14));
        label.setFont(new Font("Arial", 9));

        //Create all text field
        field1 = new TextField();
        field2 = new TextField();
        field3 = new TextField();
        field4 = new TextField();
        field5 = new TextField();
        field6 = new TextField();

        //Create submit button and Cancel button
        submitButton = new Button ("Submit");
        submitButton.setDisable(true);
        cancelButton = new Button ("Cancel");

        //only title and one label use vbox
        VBox vBox = new VBox (titel);
        VBox vBox1 = new VBox (label);

        //Create all hbox
        HBox hBox = new HBox(20, label1, field1);
        HBox hBox1 = new HBox(10, label2);
        HBox hBox2 = new HBox(15, label3, field2);
        HBox hBox3 = new HBox(10, label4, field3);
        HBox hBox4 = new HBox(12, label5, field4);
        HBox hBox5 = new HBox(32, label6, field5);
        HBox hBox6 = new HBox(75, label7, field6);
        HBox hBox7 = new HBox(20, submitButton, cancelButton);
        HBox hBox8 = new HBox(20, TotalExpensive, TotalExpensiveResult);
        HBox hBox9 = new HBox(20, TotalCost, TotalCostResult);

        //Alignment for both vbox and hbox
        vBox.setAlignment(Pos.CENTER);
        vBox1.setAlignment(Pos.BASELINE_RIGHT);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        hBox1.setAlignment(Pos.BASELINE_LEFT);
        hBox2.setAlignment(Pos.BASELINE_RIGHT);
        hBox3.setAlignment(Pos.BASELINE_RIGHT);
        hBox4.setAlignment(Pos.BASELINE_RIGHT);
        hBox5.setAlignment(Pos.BASELINE_RIGHT);
        hBox6.setAlignment(Pos.BASELINE_RIGHT);
        hBox7.setAlignment(Pos.CENTER);
        hBox8.setAlignment(Pos.BASELINE_RIGHT);
        hBox9.setAlignment(Pos.BASELINE_RIGHT);

        //Preset padding
        vBox.setPadding(new Insets(0,0,10,0));
        vBox1.setPadding(new Insets(0,0,0,0));
        hBox1.setPadding(new Insets(0,0,0,0));
        hBox3.setPadding(new Insets(0,0,0,0));
        hBox7.setPadding(new Insets(15,0,5,0));
        hBox8.setPadding(new Insets(0,0,5,0));
        hBox9.setPadding(new Insets(0,0,15,0));

        //Create TextFieldListener
        TextFieldListener listener = new TextFieldListener();
        field1.textProperty().addListener(listener);
        field2.textProperty().addListener(listener);
        field3.textProperty().addListener(listener);
        field4.textProperty().addListener(listener);
        field5.textProperty().addListener(listener);
        field6.textProperty().addListener(listener);

        //All function for submit button
        submitButton.setOnAction(event ->
        {
            double days = Double.parseDouble(field1.getText());
            String field2Str = field2.getText(); //airfare can be 0
            double airFare = field2Str == null || field2Str.trim().isEmpty() ? 0 : Double.parseDouble(field2Str.trim());
            String field3Str = field3.getText(); //miles can be 0
            double miles = field3Str == null || field3Str.trim().isEmpty() ? 0 : Double.parseDouble(field3Str.trim());
            double ConregCost = Double.parseDouble(field4.getText());
            double lodgingCost = Double.parseDouble(field5.getText());
            double foodCost = Double.parseDouble(field6.getText());

            //Total expensive will add all cost together
            TotalExpensiveResult.setText(" " + String.format("%.2f",( airFare+ foodCost
                    + ConregCost + (days * lodgingCost) ) )  );

            //if the value is negative then cost will become 0
            if ((foodCost-(days*47) < 0 ) )
            {
                foodCost = 0;
            }

            else
            {
                foodCost = (foodCost-(days*47));
            }

            if( (days*(lodgingCost-195))  < 0 )
            {
                lodgingCost = 0;
            }

            else
            {
                lodgingCost = (days*(lodgingCost-195));
            }

            //Cost result
            TotalCostResult.setText(" "+ String.format("%.2f",(foodCost  + (miles*0.42) +lodgingCost ) ) );
        });

        //Cancel button will reset all field
        cancelButton.setOnAction(event ->
        {
            field1.setText("");
            field2.setText("");
            field3.setText("");
            field4.setText("");
            field5.setText("");
            field6.setText("");
            field2.setDisable(false);
            field3.setDisable(false);
            TotalExpensiveResult.setText("");
            TotalCostResult.setText("");
        });


        // Grid pane insert
        GridPane gridPane = new GridPane ();
        gridPane.add(vBox, 0, 0);
        gridPane.add(hBox, 0, 1);
        gridPane.add(vBox1, 0, 2);
        gridPane.add(hBox1, 0, 3);
        gridPane.add(hBox2, 0, 4);
        gridPane.add(hBox3, 0, 5);
        gridPane.add(hBox4, 0, 6);
        gridPane.add(hBox5, 0, 7);
        gridPane.add(hBox6, 0, 8);
        gridPane.add(hBox7, 0, 9);
        gridPane.add(hBox8, 0, 10);
        gridPane.add(hBox9, 0, 11);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding( new Insets(10, 10, 10, 10));
        gridPane.setVgap( 10);
        gridPane.setHgap( 10);

        primaryStage.setTitle("Travel Expenses Calculator");
        primaryStage.setScene(new Scene (gridPane));
        primaryStage.show();

    }

    //This private class will prevent user input both mile and airfare.
    private class TextFieldListener implements ChangeListener<String>
    {
        @Override
        public void changed(ObservableValue<? extends String> source, String oldValue, String
                newValue)
        {
            String numTrip = field1.getText();
            String airFare = field2.getText();
            String miles = field3.getText();
            String confCost = field4.getText();
            String lodgingCost = field5.getText();
            String foodCost = field6.getText();

            //Prevent error if airfare and miles are both entered.
            if (airFare.trim().equals( "")  )
            {
                submitButton.setDisable(numTrip.trim().equals("") || miles.trim().equals("") || confCost.trim().equals("") ||
                        lodgingCost.trim().equals("") || foodCost.trim().equals(""));
                field2.setDisable(!miles.trim().equals(""));
            }

            else if (miles.trim().equals( "") )
            {
                submitButton.setDisable(numTrip.trim().equals( "") ||airFare.trim().equals( "") || confCost.trim().equals( "")||
                        lodgingCost.trim().equals( "")|| foodCost.trim().equals( ""));
                field3.setDisable(!airFare.trim().equals(""));
            }

            else
            {
                submitButton.setDisable(true);
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
