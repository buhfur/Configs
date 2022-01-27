/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class DrawingApplication extends Application {
    
    static Color color;
    static String mode="";
    static double length;
    Line line = new Line();
    Rectangle rect = new Rectangle();
    Circle circle = new Circle();
    
    
    @Override
    public void start(Stage primaryStage) {

    // create HBox widgets 
    ColorPicker colorPicker = new ColorPicker();
    
    Label label = new Label("Paint Width");
    
     //create a scroll bar
    ScrollBar scrollBar = new ScrollBar();
    scrollBar.setMin(5);
    scrollBar.setMax(75);
    scrollBar.setValue(20);
    
    // change color
    colorPicker.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
               color = colorPicker.getValue();
            }
    });
     
    Button paintBtn = new Button("Paint");
    
    Button strokeBtn = new Button("Stroke");
    Button fillStrokeBtn = new Button("Fill Stroke");
    
    Button rectangleBtn = new Button("Rectangle");
    Button fillRectBtn = new Button("Fill Rectangle");
    
    Button circleBtn = new Button("Circle");
    Button fillCircleBtn = new Button("Fill Circle");
    
    Button saveBtn = new Button("Save");
    Button openBtn = new Button("Open");
    
    // add event listeners to each button 
    paintBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Paint";
            }
        });
    
    strokeBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Stroke";
            }
        });
    
    fillStrokeBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Fill Stroke";
            }
        });
    
    rectangleBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Rectangle";
            }
        });
    
    fillRectBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Fill Rect";
            }
        });
    
    circleBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Circle";
            }
        });
    
    fillCircleBtn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                mode="Fill Circle";
            }
        });
    
    //Creating a VBox 
    VBox btns = new VBox(11);
        btns.getChildren().addAll(colorPicker,label,scrollBar,paintBtn,strokeBtn,fillStrokeBtn,rectangleBtn,fillRectBtn,circleBtn,fillCircleBtn,saveBtn,openBtn);
        btns.setStyle("-fx-background-color: #999");
        btns.setPrefWidth(125);
        
     Canvas canvas = new Canvas(1200,800);
     GraphicsContext context = canvas.getGraphicsContext2D();
     context.setLineWidth(1);
     
     canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
           
            if(mode == "Paint"){
                context.setFill(colorPicker.getValue());
                length = scrollBar.getValue();
            }
            
            else if(mode == "Stroke"){
                context.setStroke(colorPicker.getValue());
                context.beginPath();
       
            }
            
            else if (mode == "Fill Stroke"){
                
                context.setStroke(colorPicker.getValue());
                context.setFill(colorPicker.getValue());
                context.beginPath();
                line.setStartX(e.getX());
                line.setStartY(e.getY());
            }
            
            else if(mode == "Rectangle"){
               
                context.setStroke(colorPicker.getValue());
                
                rect.setX(e.getX());
                rect.setY(e.getY());
                
            }
            
            else if (mode == "Fill Rect"){
                context.setFill(colorPicker.getValue());
                
                rect.setX(e.getX());
                rect.setY(e.getY());
            }
            
            else if(mode=="Circle"){
                context.setStroke(colorPicker.getValue());
          
                circle.setCenterX(e.getX());
                circle.setCenterY(e.getY());
            }
            
            else if(mode=="Fill Circle"){
                context.setFill(colorPicker.getValue());
                
                circle.setCenterX(e.getX());
                circle.setCenterY(e.getY());
            }
        }
    });
    
     canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
           
            if(mode == "Paint"){
                context.fillRect(e.getX(),e.getY(),length,length);
            }
            
            else if(mode == "Stroke" || mode =="Fill Stroke"){
                context.lineTo(e.getX(), e.getY());
                context.stroke();
            }
            
            
        }
    });
    
     canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
           
          
            
            if(mode == "Stroke"){
              
                context.closePath();
            }
            else if(mode == "Fill Stroke"){
                line.setEndX(e.getX());
                line.setEndY(e.getY());
                context.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
                context.fill();
            }
            
            else if(mode=="Rectangle"){
                
                rect.setWidth(Math.abs(e.getX()-rect.getX()));
                rect.setHeight(Math.abs(e.getY()-rect.getY()));
                
                if(rect.getX()>e.getX()){
                    rect.setX(e.getX());
                }
                
                if(rect.getY()>e.getY()){
                    rect.setY(e.getY());
                }
                
                context.strokeRect(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
            
            else if(mode=="Fill Rect"){
                
                rect.setWidth(Math.abs(e.getX()-rect.getX()));
                rect.setHeight(Math.abs(e.getY()-rect.getY()));
                
                if(rect.getX()>e.getX()){
                    rect.setX(e.getX());
                }
                
                if(rect.getY()>e.getY()){
                    rect.setY(e.getY());
                }
                
                context.fillRect(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
            
            else if(mode=="Circle"){
                
                circle.setRadius(Math.sqrt(Math.pow(circle.getCenterX()-e.getX(),2) + Math.pow(circle.getCenterY()-e.getY(),2)));
                

                context.strokeOval(circle.getCenterX(),circle.getCenterY(),circle.getRadius(),circle.getRadius());
    
            }
            
            else if(mode=="Fill Circle"){
                
                circle.setRadius(Math.sqrt(Math.pow(circle.getCenterX()-e.getX(),2) + Math.pow(circle.getCenterY()-e.getY(),2)));
                
                context.fillOval(circle.getCenterX(),circle.getCenterY(),circle.getRadius(),circle.getRadius());
    
            }
          
        }
    });
    
     // Open
        openBtn.setOnAction((e)->{
            FileChooser openFile = new FileChooser();
            openFile.setTitle("Open File");
            File file = openFile.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    InputStream io = new FileInputStream(file);
                    Image img = new Image(io);
                    context.drawImage(img, 0, 0);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
        });
        
        // Save
        saveBtn.setOnAction((e)->{
            FileChooser savefile = new FileChooser();
            savefile.setTitle("Save File");
            
            File file = savefile.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    WritableImage writableImage = new WritableImage(1200, 800);
                    canvas.snapshot(null, writableImage);
                    RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                    ImageIO.write(renderedImage, "png", file);
                } catch (IOException ex) {
                    System.out.println("Error!");
                }
            }
            
        });
    // setting up layout 
    BorderPane borderPane = new BorderPane();
    borderPane.setLeft(btns);
    borderPane.setCenter(canvas);

    Scene scene = new Scene(borderPane);
     
     // show stage
     primaryStage.setScene(scene);
     primaryStage.setTitle("Drawing Application");
     primaryStage.show();
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
