package compulsory.application;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.ErrorManager;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    private static final ErrorManager logger =  new ErrorManager();

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);

        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
    }

    private void save(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose directory to save");
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(frame.canvas.image, "PNG", fileChooser.getSelectedFile());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void load(ActionEvent e){
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG images:   *.png", "png"));
            fileChooser.setDialogTitle("Specify the file you want to load:");
            int userSelection = fileChooser.showSaveDialog(frame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                frame.canvas.image = ImageIO.read(fileChooser.getSelectedFile());
                frame.canvas.repaint();
            }
        }
        catch (IOException ie){
            ie.printStackTrace();
        }
    }

    private void reset(ActionEvent e){
        frame.canvas.createOffScreenImage();
        frame.canvas.repaint();
    }

    private void exit(ActionEvent e){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}

