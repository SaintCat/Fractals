/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.saintcat.client.fractals;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Callback;
import static ru.saintcat.client.fractals.LineOperations.getLinePointsArray;
import static ru.saintcat.client.fractals.LineOperations.transferLine;
import ru.saintcat.client.fractals.basic.PatternFactory;
import ru.saintcat.client.fractals.basic.PatternType;
import ru.saintcat.client.fractals.basic.ShapeFactory;
import ru.saintcat.client.fractals.basic.ShapeType;
import ru.saintcat.client.fractals.enums.ReflectionXAxis;
import ru.saintcat.client.fractals.enums.ReflectionXAxisByIteration;
import ru.saintcat.client.fractals.enums.ReflectionXAxisBySegmentNumber;

/**
 *
 * @author Chernyshov
 */
public class FractalController implements Initializable {

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private AnchorPane paintPanel;
    @FXML
    private AnchorPane viewPane;
    @FXML
    private Pane patternPanel;
    @FXML
    private Label coordinatsLabel;
    @FXML
    private TextField iterNumberField;
    @FXML
    private TextField minSizeField;
    @FXML
    private ComboBox<ShapeType> blankShapeBox;
    @FXML
    private Label infoLabel;
    @FXML
    private ComboBox<ReflectionXAxis> xAxisComboBox;
    @FXML
    private ComboBox<ReflectionXAxisByIteration> xAxisByIteration;
    @FXML
    private ComboBox<ReflectionXAxisBySegmentNumber> xAxisBySegmentNumber;
    @FXML
    private ComboBox<PatternType> patternComboBox;
    private List<Line> fractalLines = new ArrayList<>();
    private List<Line> shablonLines = new ArrayList<>();
    private List<Line> basisLines = FXCollections.observableArrayList();

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (basisLines == null || basisLines.isEmpty()) {
            infoLabel.setText("Нарисуйте основу для фрактала или выберите готовую из заготовок");
            return;
        } else {
            infoLabel.setText("");
        }

        if (shablonLines == null || shablonLines.isEmpty()) {
            infoLabel.setText("Выберите шаблон из готовых шаблонов в правом верхнем углу");
            return;
        } else {
            infoLabel.setText("");
        }

        Integer maxItem;
        Double deltaSize;
        try {
            maxItem = Integer.valueOf(iterNumberField.getText());
        } catch (Exception ex) {
            maxItem = 5;

        }
        try {
            deltaSize = Double.valueOf(minSizeField.getText());
        } catch (Exception ex) {
            System.out.println(ex);
            deltaSize = 0.0;
        }

        int v1 = xAxisComboBox.getValue().getValue();
        int v2 = xAxisByIteration.getValue().getValue();
        int v3 = xAxisBySegmentNumber.getValue().getValue();

        Task<List<Line>> task = FractalBuilder.getTeragonBuilder(basisLines, shablonLines, deltaSize, maxItem, v1, v2, v3);
        progressIndicator.progressProperty().bind(task.progressProperty());
        progressIndicator.visibleProperty().bind(task.runningProperty());
        viewPane.disableProperty().bind(task.runningProperty());

        final long startTime = System.currentTimeMillis();

        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                paintPanel.getChildren().removeAll(fractalLines);
                fractalLines.clear();
                fractalLines = (List<Line>) t.getSource().getValue();
//                paintPanel.getChildren().addAll(fractalLines);
                long time = System.currentTimeMillis() - startTime;
                infoLabel.setText("Фрактал построен за " + time + "мс");
                Task2 t2 = new Task2(fractalLines, 0);
                timer.schedule(t2, 0);
            }
        });

        new Thread(task).start();

    }

    @FXML
    void handleDeleteLineAction(ActionEvent event) {
        if (basisLines.size() >= 1) {
            Line line = basisLines.get(basisLines.size() - 1);
            basisLines.remove(basisLines.size() - 1);
            paintPanel.getChildren().remove(line);
            line = null;
        }

    }

    @FXML
    void handleClearAction(ActionEvent event) {
        clearPaintPanel();
    }

    private void clearPaintPanel() {
        paintPanel.getChildren().clear();
        fractalLines.clear();
        basisLines.clear();
    }

    private void clearPatternPanel() {
        patternPanel.getChildren().clear();
        shablonLines.clear();
    }

    public static double arg(double x, double y) {
        double res = Math.acos(x / Math.sqrt(x * x + y * y)) * (y >= 0 ? 1 : -1);
        return res;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paintPanel.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                coordinatsLabel.setText("X=" + t.getX() + " Y=" + t.getY());
            }
        });
        xAxisComboBox.setItems(FXCollections.observableArrayList(ReflectionXAxis.values()));
        xAxisByIteration.setItems(FXCollections.observableArrayList(ReflectionXAxisByIteration.values()));
        xAxisBySegmentNumber.setItems(FXCollections.observableArrayList(ReflectionXAxisBySegmentNumber.values()));
        blankShapeBox.setItems(FXCollections.observableArrayList(ShapeType.values()));
        xAxisComboBox.setValue(ReflectionXAxis.NO);
        xAxisByIteration.setValue(ReflectionXAxisByIteration.NO);
        xAxisBySegmentNumber.setValue(ReflectionXAxisBySegmentNumber.NO);
        paintPanel.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseLeftClicked);
        paintPanel.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseRightClicked);
        blankShapeBox.valueProperty().addListener(new ChangeListener<ShapeType>() {
            @Override
            public void changed(ObservableValue<? extends ShapeType> ov, ShapeType t, ShapeType t1) {
                clearPaintPanel();
                basisLines = ShapeFactory.getLines(t1);
                paintPanel.getChildren().addAll(basisLines);
            }
        });
        patternComboBox.setItems(FXCollections.observableArrayList(PatternType.values()));
        patternComboBox.valueProperty().addListener(new ChangeListener<PatternType>() {
            @Override
            public void changed(ObservableValue<? extends PatternType> ov, PatternType t, PatternType t1) {
                clearPatternPanel();
                shablonLines = PatternFactory.getLines(t1);
//                paintPanel.getChildren().addAll(shablonLines);
                for (Line line : shablonLines) {
                    double[][] first = MatrixOperations.multiply(getLinePointsArray(line), MatrixOperations.createScalingMatrix(patternPanel.getWidth(), -patternPanel.getHeight()));
                    Line created = new Line(first[0][0], first[0][1], first[1][0], first[1][1]);
                    created = transferLine(created, 0, patternPanel.getHeight() / 2);
                    patternPanel.getChildren().add(created);
                }
            }
        });

        patternComboBox.setCellFactory(new Callback<ListView<PatternType>, ListCell<PatternType>>() {
            @Override
            public ListCell<PatternType> call(ListView<PatternType> p) {
                return new PatternTypeListCell();
            }
        });

        patternComboBox.setButtonCell(new PatternTypeListCell());
    }
    private List<Line> lAll = new ArrayList<>();
    private Line l = new Line(-1, -1, -1, -1);
    private Point2D p;
    private EventHandler mouseMoved = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (p != null) {
                paintPanel.getChildren().remove(l);
                l = new Line(p.getX(), p.getY(), t.getX(), t.getY());
                paintPanel.getChildren().add(l);
            }
        }
    };
    private EventHandler mouseLeftClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY) {
                p = new Point2D.Double(t.getX(), t.getY());
                paintPanel.addEventHandler(MouseEvent.MOUSE_MOVED, mouseMoved);
                if (l.getEndX() != -1) {
                    Line l2 = new Line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
                    basisLines.add(l2);
                    drawLine(l2);
                }
            }
        }
    };
    private EventHandler mouseRightClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.SECONDARY) {
                paintPanel.removeEventHandler(MouseEvent.MOUSE_MOVED, mouseMoved);
                paintPanel.getChildren().remove(l);
                l = new Line(-1, -1, -1, -1);
            }
        }
    };

    public void drawLine(Line line) {
        paintPanel.getChildren().add(line);
    }

    public void drawLine(Line line, Color color) {
        line.setStroke(color);
        drawLine(line);
    }

    public void drawLine(Line line, double scale, double shift) {
        line.setStartX(line.getStartX() * scale + shift);
        line.setStartY(line.getStartY() * scale + shift);
        line.setEndX(line.getEndX() * scale + shift);
        line.setEndY(line.getEndY() * scale + shift);
        paintPanel.getChildren().add(line);
    }
    private static int moveTime = 10;
    private static Timer timer = new Timer();

    public class Task2 extends TimerTask {

        private List<Line> lines;
        private int index;
        private int step;

        Task2(List<Line> lines, int index) {
            this.lines = lines;
            this.index = index;
            step = lines.size() / 100;
            if (step < 2) {
                step = 2;
            }
        }

        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                public void run() {
                    if (index > lines.size()) {
                        return;
                    }
                    for (int i = index; i < index + step; i++) {
                        drawLine(lines.get(i));
                    }
                    timer.schedule(new Task2(lines, index + step), moveTime);
                }
            });
        }
    }

    private static class PatternTypeListCell extends ListCell<PatternType> {

        private final ImageView view;

        PatternTypeListCell() {
            view = new ImageView();
        }

        @Override
        protected void updateItem(PatternType item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setGraphic(null);
                setText(null);
            } else {
                view.setImage(item.getIcon());
                setGraphic(view);
                setText("");
            }
        }
    }
}
