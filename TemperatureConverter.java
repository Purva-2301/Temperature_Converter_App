import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TemperatureConverter extends Application {
private TextField inputField;
private ComboBox<String> conversionComboBox;
private Label resultLabel;
public static void main(String[] args) {
    launch(args);
}

@Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Temperature Converter");

    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(10));
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    Label inputLabel = new Label("Temperature:");
    gridPane.add(inputLabel, 0, 0);

    inputField = new TextField();
    gridPane.add(inputField, 1, 0);

    conversionComboBox = new ComboBox<>();
    conversionComboBox.getItems().addAll("Celsius to Fahrenheit", "Celsius to Kelvin", "Fahrenheit to Celsius");
    conversionComboBox.setValue("Celsius to Fahrenheit");
    gridPane.add(conversionComboBox, 1, 1);

    Button convertButton = new Button("Convert");
    GridPane.setConstraints(convertButton, 1, 2);
    gridPane.getChildren().add(convertButton);

    BorderPane resultPane = new BorderPane();
    resultPane.setPadding(new Insets(10));
    resultLabel = new Label();
    resultLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #007bff;");
    resultPane.setTop(new Label("Result:"));
    resultPane.setCenter(resultLabel);

    convertButton.setOnAction(e -> convertTemperature());

    VBox vbox = new VBox(10);
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(gridPane, convertButton);

    BorderPane mainPane = new BorderPane();
    mainPane.setCenter(vbox);
    mainPane.setBottom(resultPane);

    Scene scene = new Scene(mainPane, 300, 200);
    scene.getStylesheets().add("style.css"); // Add custom CSS for styling
    primaryStage.setScene(scene);
    primaryStage.show();
}

private void convertTemperature() {
    String inputText = inputField.getText();
    try {
        double celsius = Double.parseDouble(inputText);
        String conversionType = conversionComboBox.getValue();

        double result;
        String resultText;

        switch (conversionType) {
            case "Celsius to Fahrenheit":
                result = celsius * 9 / 5 + 32;
                resultText = String.format("%.2f °F", result);
                break;
            case "Celsius to Kelvin":
                result = celsius + 273.15;
                resultText = String.format("%.2f K", result);
                break;
            case "Fahrenheit to Celsius":
                result = (celsius - 32) * 5 / 9;
                resultText = String.format("%.2f °C", result);
                break;
            default:
                resultText = "Invalid conversion type";
                break;
        }

        resultLabel.setText(resultText);
    } catch (NumberFormatException ex) {
        resultLabel.setText("Invalid input. Please enter a number.");
    }
}
}

