import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class newProj extends Application{
   
   TextField txtnum1, txtnum2;
   Button btnadd, btnsub, btndiv, btnmul, btnclear;
   Label lblanswer;
     
    @Override
    public void start(Stage stage) {
        //make the controls
        txtnum1=new TextField();
        txtnum2=new TextField();
        btnadd=new Button("+");
        btnsub=new Button("-");
        btnclear=new Button("Clear");
        lblanswer=new Label("?");
        //center text in label
        lblanswer.setAlignment(Pos.CENTER);
       
        //make container for app
        GridPane root = new GridPane();
        //put container in middle of scene
        root.setAlignment(Pos.CENTER);
        //setspacing between controls in grid
        root.setHgap(10);
        root.setVgap(10);
        //add to grid, cell by cell
        root.add(btnadd,0,0);
        root.add(btnsub,1,0);
        root.add(txtnum1, 0,2);
        root.add(txtnum2,1,2);
        //last 2 rows span across 2 columns
        //col, rol, colspan, rowspan
        root.add(lblanswer,0,3,2,1);
        root.add(btnclear,0,4,2,1);
        //attach buttons to code in separate method
        btnadd.setOnAction(e -> btncode(e));
        btnsub.setOnAction(e -> btncode(e));
        btnclear.setOnAction(e -> btncode(e));
        //usual stuff
        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }
   
    public void btncode(ActionEvent e)
    {
       int num1, num2, answer;
        String operation;
        //e tells us which button was clicked
        if(e.getSource()==btnclear)
        {
            txtnum1.setText("");
            txtnum2.setText("");
            lblanswer.setText("?");
            txtnum1.requestFocus();
            return;
        }
        //read numbers in from textfields
        num1=Integer.parseInt(txtnum1.getText());
        num2=Integer.parseInt(txtnum2.getText());
        if(e.getSource()==btnadd){
        		Addition add = new Addition(num1, num2);
            add.evaluate(num1, num2);
        }
        else{
        		Subtraction sub = new Subtraction(num1, num2);
            sub.evaluate(num1, num2);
        }
    }
   
    public static void main(String[] args) {
        launch(args);
    }
    
    public abstract class Opp{
        double x, y;
        public Opp(int x, int y){
            this.x = x;
            this.y = y;
        }

        public abstract void evaluate(int x, int y);
    }


    public class Addition extends Opp{
        public Addition(int x, int y) {
            super(x, y);
        }

        @Override
        public void evaluate(int x, int y) {
            String operation = " + ";
            int solution = x + y;
            lblanswer.setText("" + x + operation + y + " = " + solution);
        }
    }

    public class Subtraction extends Opp{
        public Subtraction(int x, int y) {
            super(x, y);
        }

        @Override
        public void evaluate(int x, int y) {
            String operation = " - ";
            int solution = x - y;
            lblanswer.setText("" + x + operation + y + " = " + solution);

        }
    }
   
}
