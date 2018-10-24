package gui;

import data.GoodOutputs;
import data.GoodPixels;
import data.ReadWriteFile;
import gui.components.CustomPanel;
import gui.components.DrawingPanel;
import neural.Train;
import neural.TrainingSet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainGui extends JFrame {

    private final int RESOLUTION = 7;

    private Train networkTrainer;

    private JPanel mainPanel;
    private DrawingPanel drawingPanel;
    private CustomPanel resultPanel;

    private JButton clearButton;
    private JButton trainButton;
    private JButton transformButton;
    private JButton trainNetworkButton;
    private JButton drawLetterButton;
    private JTextField trainingSetsAmount;
    private JComboBox<String> drawLetterCombo;
    private JComboBox<String> trainAsCombo;
    private JTextArea outputTextArea;

    public MainGui() {
        super("Drawing letters using neural networks");

        networkTrainer = new Train();

        setMainPanel();
        setLeftSide();
        setCenterArea();
        setRightSide();
        setOutputPanel();

        setOnClicks();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(new Dimension(1260, 500));
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        new MainGui();
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        setContentPane(mainPanel);
    }

    private void setLeftSide() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(410, 440));

        drawLetterButton = new JButton("Draw:");
        drawLetterCombo = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});

        drawingPanel = new DrawingPanel(400, 400, RESOLUTION);

        panel.add(drawLetterButton);
        panel.add(drawLetterCombo);
        panel.add(drawingPanel);

        mainPanel.add(panel);
    }

    private void setCenterArea() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(200, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        trainNetworkButton = new JButton("Train X times:");
        trainingSetsAmount = new JFormattedTextField("5000");
        trainingSetsAmount.setMaximumSize(new Dimension(100, 30));
        trainingSetsAmount.setPreferredSize(new Dimension(100, 30));
        centerPanel.add(trainNetworkButton, gbc);
        centerPanel.add(trainingSetsAmount, gbc);

        centerPanel.add(Box.createVerticalStrut(50));


        centerPanel.add(Box.createVerticalStrut(50));

        transformButton = new JButton(">>");
        centerPanel.add(transformButton, gbc);

        centerPanel.add(Box.createVerticalStrut(50));

        clearButton = new JButton("Clear");
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(clearButton, gbc);

        centerPanel.add(Box.createVerticalStrut(50));

        centerPanel.add(new JLabel("Train as:", SwingConstants.CENTER), gbc);

        trainAsCombo = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        trainAsCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        trainAsCombo.setMaximumSize(new Dimension((int) trainAsCombo.getPreferredSize().getWidth(), 30));
        centerPanel.add(trainAsCombo, gbc);

        trainButton = new JButton("Train");
        centerPanel.add(trainButton, gbc);

        mainPanel.add(centerPanel);
    }

    private void setRightSide() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        resultPanel = new CustomPanel(400, 400, RESOLUTION);
        panel.add(resultPanel);
        mainPanel.add(panel);
    }

    private void setOutputPanel() {
        JPanel outputPanel = new JPanel();
        outputPanel.setPreferredSize(new Dimension(200, 430));

        outputTextArea = new JTextArea();
        outputTextArea.setPreferredSize(new Dimension(200, 430));
        outputPanel.add(outputTextArea);

        mainPanel.add(outputPanel);
    }

    private void setOnClicks() {
        clearButton.addActionListener(e -> drawingPanel.clear());

        trainButton.addActionListener(e -> {
            String letter = (String) trainAsCombo.getSelectedItem();
            networkTrainer.addTrainingSet(new TrainingSet(drawingPanel.getPixels(), GoodOutputs.getInstance().getGoodOutput(letter)));
            ReadWriteFile.saveToFile(drawingPanel.getPixels(), letter);
        });

        transformButton.addActionListener(e -> {
            networkTrainer.setInputs(drawingPanel.getPixels());

            ArrayList<Double> outputs = networkTrainer.getOutputs();
            int index = 0;
            for (int i = 0; i < outputs.size(); i++) {
                if (outputs.get(i) > outputs.get(index))
                    index = i;
            }

            updateTextArea();

            trainAsCombo.setSelectedIndex(index);
            resultPanel.drawLetter(GoodPixels.getInstance().getGoodPixels(index));
        });

        trainNetworkButton.addActionListener(e -> {
            int number = 0;
            try {
                number = Integer.parseInt(trainingSetsAmount.getText());
            } catch (Exception x) {
                JOptionPane.showMessageDialog(this, "Wrong input", "ERROR", JOptionPane.PLAIN_MESSAGE);
            }

            networkTrainer.train(number);
        });

        drawLetterButton.addActionListener(e -> {
            String letter = (String) drawLetterCombo.getSelectedItem();
            ArrayList<Integer> goodPixels = GoodPixels.getInstance().getGoodPixels(letter);
            drawingPanel.drawLetter(goodPixels);
        });

        drawLetterCombo.addActionListener(e -> {
            String letter = (String) drawLetterCombo.getSelectedItem();
            ArrayList<Integer> goodPixels = GoodPixels.getInstance().getGoodPixels(letter);
            drawingPanel.drawLetter(goodPixels);
        });

    }

    private void updateTextArea() {
        StringBuilder sb = new StringBuilder();
        sb.append("Percentage : \n \n");
        ArrayList<Double> outputs = networkTrainer.getOutputs();
        int confidence = 0;
        int error = 0;
        for (int i = 0; i < outputs.size(); i++) {
            int letterValue = i + 48;
            sb.append((char) letterValue);
            double value = outputs.get(i);
            System.out.println(value);
            if (value < 0.01)
                value = 0;
            if (value > 0.99)
                value = 1;

            value *= 1000;
            int x = (int) (value);
            value = x / 1000.0;

            value = value * 100;

            int dataValue = (int) value;
            confidence = confidence + dataValue;
            if (confidence > 100) {
                confidence = confidence / 10;
            }
            if (confidence <= 100) {
                if (confidence < dataValue) {
                    confidence = dataValue;
                }
            }

            error = 100 - confidence;


            sb.append("\t " + dataValue + " %");
            sb.append("\n");
        }
        sb.append("\n Confidence \t " + confidence + " % \n ");
        sb.append("Error \t " + error + " %");
        outputTextArea.setText(sb.toString());
        System.out.println("=====================================================");
    }

}
