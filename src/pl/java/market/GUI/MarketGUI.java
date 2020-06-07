package pl.java.market.GUI;

import pl.java.market.Market;
import pl.java.market.MarketItem;
import pl.java.market.ProducerManager;
import pl.java.market.common.Consumer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;

public class MarketGUI extends JFrame {
    public JPanel mainPanel;
    public JPanel buttonsPanel;
    // Declarations of buttons to buy fruit

    private JButton bananaButton;
    private JButton grapeButton;
    private JButton pineappleButton;
    private JButton watermelonButton;

    // Declaration of spinners with amount of fruits to buy

    private JSpinner bananasSpinner;
    private JSpinner grapeSpinner;
    private JSpinner pineapplesSpinner;
    private JSpinner watermelonSpinner;

    // Declaration of text field with amount of fruits
    private JTextField bananasAmountTextField;
    private JTextField grapesAmountTextField;
    private JTextField pineapplesAmountTextField;
    private JTextField watermelonsAmountTextField;


    // Amount of fruits
    private Integer amountOfBanas;
    private Integer amountOfGrapes;
    private Integer amountOfPineapples;
    private Integer amountOfWatermelons;

    // Amount of fruits to buy
    private Integer bananasToBuy;
    private Integer grapesToBuy;
    private Integer pineapplesToBuy;
    private Integer watermelonsToBuy;

    public MarketGUI(String title) {
        super(title);


        ProducerManager producerManager = new ProducerManager();
        Market market = new Market(producerManager, this);
        market.openMarket();

        setBananasAmountTextField(0);
        setGrapesAmountTextField(0);
        setPineapplesAmountTextField(0);
        setWatermelonsAmountTextField(0);


        ImageIcon img = new ImageIcon("pl/java/market/img/market_full.png");
//        JLabel jLabel = new JLabel();
//        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/market_empty.png"));
//        jLabel.setIcon(imageIcon);
//        imagePanel.add(jLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        // Zakup przedmiotów
        bananaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<MarketItem, Integer> groceriesMap = new EnumMap<>(MarketItem.class);
                groceriesMap.put(MarketItem.BANANA, bananasToBuy);
                Consumer consumer = new Consumer(market, groceriesMap);
                consumer.start();
            }
        });

        grapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<MarketItem, Integer> groceriesMap = new EnumMap<>(MarketItem.class);
                groceriesMap.put(MarketItem.GRAPE, grapesToBuy);
                Consumer consumer = new Consumer(market, groceriesMap);
                consumer.start();
            }
        });

        pineappleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<MarketItem, Integer> groceriesMap = new EnumMap<>(MarketItem.class);
                groceriesMap.put(MarketItem.PINEAPPLE, pineapplesToBuy);
                Consumer consumer = new Consumer(market, groceriesMap);
                consumer.start();
            }
        });

        watermelonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<MarketItem, Integer> groceriesMap = new EnumMap<>(MarketItem.class);
                groceriesMap.put(MarketItem.WATERMELON, watermelonsToBuy);
                Consumer consumer = new Consumer(market, groceriesMap);
                consumer.start();
            }
        });

        // Nasłuchy zmiany stanu spinnera
        bananasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bananasToBuy = (Integer) bananasSpinner.getValue();
            }
        });

        grapeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                grapesToBuy = (Integer) grapeSpinner.getValue();
            }
        });

        pineapplesSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pineapplesToBuy = (Integer) pineapplesSpinner.getValue();
            }
        });

        watermelonSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                watermelonsToBuy = (Integer) watermelonSpinner.getValue();
            }
        });
    }

    public void setBananasAmountTextField(Integer amountOfBananas) {
        bananasAmountTextField.setText("Available bananas: " + amountOfBananas);
    }

    public void setGrapesAmountTextField(Integer amountOfGrapes) {
        grapesAmountTextField.setText("Available grapes: " + amountOfGrapes);
    }

    public void setPineapplesAmountTextField(Integer amountOfPineapples) {
        pineapplesAmountTextField.setText("Available pineapples: " + amountOfPineapples);
    }

    public void setWatermelonsAmountTextField(Integer amountOfWatermelons) {
        watermelonsAmountTextField.setText("Available watermelons: " + amountOfWatermelons);
    }
}
