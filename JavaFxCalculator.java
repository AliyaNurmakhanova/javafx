import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;



public class JavaFxCalculator extends Application {
    private TextField tfDisplay ;
    private Button[] btns ;
    private String [] btnLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "x",
            "C", "0", "=", "/"
    } ;
    private int result ;
    private String inStr = "0" ;
    private char lastOperator = ' ' ;

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String currentBtnLabel = ((Button)event.getSource()).getText() ;
            switch (currentBtnLabel){
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if(inStr.equals("0")){
                        inStr = currentBtnLabel ;
                    }else{
                        inStr += currentBtnLabel ;
                    }
                    tfDisplay.setText(inStr);

                    if (lastOperator == '='){
                        result = 0 ;
                        lastOperator = ' ' ;
                    }

                    break;

                case "+":
                    compute() ;
                    lastOperator = '+' ;
                    break;
                case "-":
                    compute() ;
                    lastOperator = '-' ;
                    break;
                case "x":
                    compute() ;
                    lastOperator = 'x' ;
                    break;
                case "/":
                    compute() ;
                    lastOperator = '/' ;
                    break;
                case "=":
                    compute() ;
                    lastOperator = '=' ;
                    break;
                case "C":
                    result = 0 ;
                    inStr = "0" ;
                    lastOperator = ' ' ;
                    tfDisplay.setText("0");
                    break;
            }
        }
    } ;

    private void compute(){
        int inNum = Integer.parseInt(inStr) ;
        inStr = "0" ;
        if(lastOperator == ' ')
            result = inNum ;
        else if(lastOperator == '+')
            result += inNum ;
        else if(lastOperator == '-')
            result -= inNum ;
        else if(lastOperator == 'x')
            result *= inNum ;
        else if(lastOperator == '/')
            result /= inNum ;
        else if (lastOperator == '='){

        }
            tfDisplay.setText(result+"");
    }

    @Override
    public void start(Stage stage) throws Exception {
        tfDisplay = new TextField("0") ;
        tfDisplay.setEditable(false);

        int numRows = 4 ;
        int numCols = 4 ;
        GridPane paneButton = new GridPane() ;
        paneButton.setPadding(new Insets(15, 0, 15, 0));
        paneButton.setVgap(5);
        paneButton.setHgap(5);

        ColumnConstraints[] columns = new ColumnConstraints[numCols];
        for (int i = 0; i < numCols; ++i) {
            columns[i] = new ColumnConstraints();
            columns[i].setHgrow(Priority.ALWAYS) ;
            columns[i].setFillWidth(true);
            paneButton.getColumnConstraints().add(columns[i]);
        }

        btns = new Button[16];
        for (int i = 0; i < btns.length; ++i) {
            btns[i] = new Button(btnLabels[i]);
            btns[i].setOnAction(handler) ;
            btns[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            paneButton.add(btns[i], i % numCols, i / numCols);
        }

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setTop(tfDisplay);
        root.setCenter(paneButton);

        stage.setScene(new Scene(root, 300, 300));
        stage.setTitle("JavaFX Calculator");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

