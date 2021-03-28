package compulsory.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
  int size;
  final MainFrame frame;
  Color color = null;
  static JSpinner sidesField;
  public ConfigPanel(MainFrame frame){
      this.frame=frame;
      init();
  }
  private void init(){
      this.setBorder(BorderFactory.createDashedBorder(Color.black));
      this.setLayout(new FlowLayout(FlowLayout.CENTER, 20,10));

      var sizeInputLabel = new JLabel("Size: ");
      sizeInputLabel.setFont(new Font("Arial", Font.BOLD, 19));

      var sizeInputField = new JTextField("Write size here", 20);
      sizeInputField.setFont(new Font("Arial", Font.BOLD, 19));
      sizeInputField.setBorder(BorderFactory.createDashedBorder(Color.black));

      sizeInputField.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              size = Integer.parseInt(sizeInputField.getText());
          }
      });

      var sidesLabel = new JLabel("Sides: ");
      sidesLabel.setFont(new Font("Arial", Font.BOLD, 19));

      sidesField = new JSpinner(new SpinnerNumberModel(1,1,10,1));
      sidesField.setValue(6);
      sidesField.setFont(new Font("Arial", Font.BOLD, 19));

      var colorLabel = new JLabel("Color: ");
      colorLabel.setFont(new Font("Arial", Font.BOLD, 19));
      String[] colors = {"Random", "Black"};
      var colorCombo = new JComboBox<>(colors);



      ActionListener comboActionListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              var selectedColor = colorCombo.getSelectedObjects();
              if(selectedColor[0].equals("Black")){
                  color = Color.black;
              }
              else{
                  color = null;
              }
          }
      };

      colorCombo.addActionListener(comboActionListener);


      add(sizeInputLabel);
      add(sizeInputField);
      add(sidesLabel);
      add(sidesField);
      add(colorLabel);
      add(colorCombo);


  }

}
