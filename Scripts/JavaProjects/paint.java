
public class JavaFX_DrawOnCanvas extends Application { 

    private Pair<Double, Double> initialTouch;
    private Canvas layer = new Canvas();

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Canvas canvas = new Canvas(400, 400);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        Canvas newLayer = new Canvas(400, 400);
                        GraphicsContext context = newLayer.getGraphicsContext2D();
                        initDraw(context);

                        layer = newLayer;
                        root.getChildren().add(0, newLayer);
                        initialTouch = new Pair<>(event.getSceneX(), event.getSceneY());
                    }
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        GraphicsContext context = layer.getGraphicsContext2D();
                        context.clearRect(0, 0, layer.getWidth(), layer.getHeight());
                        context.strokeLine(initialTouch.getKey(), initialTouch.getValue(), event.getSceneX(), event.getSceneY());
                    }
                });

        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

    }

    public static void main(String[] args) {
        launch(args);
    }

}

