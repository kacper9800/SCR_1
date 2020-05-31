package pl.java.market.GUI;

import pl.java.market.Market;
import pl.java.market.MarketItem;
import pl.java.market.ProducerManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MarketGUI extends JFrame {
    public JPanel mainPanel;

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
    private Integer ananasToBuy;
    private Integer banasToBuy;
    private Integer grapesToBuy;
    private Integer watermelonsToBuy;


    public BufferedImage loadImage() {
        try {
            return ImageIO.read(new File("./img/market_empty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    public void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
    }

    public MarketGUI(String title) {
        super(title);

        ProducerManager producerManager = new ProducerManager();
        Market market = new Market(producerManager);
        market.openMarket();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        // Zakup przedmiotów


        bananaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.consumeMarketItem(MarketItem.BANANA);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });


        grapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.consumeMarketItem(MarketItem.GRAPE);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        pineappleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.consumeMarketItem(MarketItem.PINEAPPLE);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
        watermelonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    market.consumeMarketItem(MarketItem.WATERMELON);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Nasłuch zmiany stanu spinnera

        pineapplesSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {


            }
        });
        bananasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
        grapeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
        watermelonSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
    }


//
//    public MarketGUI(String title) throws HeadlessException {
//        super();
//        try {
//            BufferedImage backgroundImg = ImageIO.read(new File("./img/market_empty.png"));
//
//            JFrame frame = new JFrame(title);
//
//            frame.setContentPane(new JLabel(new ImageIcon(backgroundImg)));
//            frame.setLayout(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridwidth = GridBagConstraints.REMAINDER;
//
//            frame.add(new JButton("Buy Bananas"), gbc);
//
//            ProducerManager producerManager = new ProducerManager();
//
//            Market market = new Market(producerManager);
//            // Market zostaje otwarty - magazyn zostaje uzupelniany
//            market.openMarket();
//
//
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        ananasButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(ananasSpinner.getValue());
//
//            }
//        });
//        bananaButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        grapeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        mainPanel.addComponentListener(new ComponentAdapter() {
//        });
//        watermelonButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        ananasSpinner.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                super.componentMoved(e);
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//            }
//        });
//        ananasButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        ananasSpinner.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                super.componentMoved(e);
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//            }
//        });
//        bananasSpinner.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                super.componentMoved(e);
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//            }
//        });
//        grapeSpinner.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                super.componentMoved(e);
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//            }
//        });
//        watermelonSpinner.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//            }
//
//            @Override
//            public void componentMoved(ComponentEvent e) {
//                super.componentMoved(e);
//            }
//
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//            }
//        });
//        watermelonSpinner.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println(e);
//            }
//        });
//    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
