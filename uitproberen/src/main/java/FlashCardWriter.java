import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashCardWriter {
    private JPanel pnlBackground;
    private JTextField txtVoorkant;
    private JTextField txtAchterkant;
    private JTextField txtModule;
    private JTextField txtBestand;
    private JLabel lblVoorkant;
    private JLabel lblAchterkant;
    private JLabel lblModule;
    private JLabel lblBestand;
    private JButton btnLoadFileButton;
    private JButton btnSaveCardButton;
    private JButton btnClearButton;

    public FlashCardWriter() {
        btnLoadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
