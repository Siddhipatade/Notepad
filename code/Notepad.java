import java.io.*;
import java.awt.datatransfer.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Notepad extends JFrame implements ActionListener, WindowListener {
    File fnameContainer;
    JTextArea jta = new JTextArea();
    JLabel lineLabel = new JLabel("Lines: 1");

    private JToggleButton darkModeToggle;

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        Exiting();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void Exiting() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to save the file?", "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            saveFile();
            System.exit(0);
        } else if (option == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    public void EditFile(String fname) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String s;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((s = d.readLine()) != null) {
            jta.setText(jta.getText() + s + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void createMenuItem(JMenu jm, String txt, KeyStroke accelerator) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        if (accelerator != null) {
            jmi.setAccelerator(accelerator);
        }
        jm.add(jmi);
    }

    public void createFindReplaceMenuItem(JMenu jm, String txt, KeyStroke accelerator) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        if (accelerator != null) {
            jmi.setAccelerator(accelerator);
        }
        jm.add(jmi);
    }

    public void createZoomMenuItem(JMenu jm, String txt, KeyStroke accelerator, int zoomFactor) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        if (accelerator != null) {
            jmi.setAccelerator(accelerator);
        }
        jmi.setActionCommand("Zoom" + zoomFactor);
        jm.add(jmi);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (e.getActionCommand().equals("New")) {
            this.setTitle("Untitled.txt - Notepad");
            jta.setText("");
            fnameContainer = null;
        } else if (e.getActionCommand().equals("Open")) {
            int ret = jfc.showDialog(null, "Open");
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File fyl = jfc.getSelectedFile();
                    openFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName() + " - Notepad");
                    fnameContainer = fyl;
                } catch (IOException ers) {
                }
            }
        } else if (e.getActionCommand().equals("Save")) {
            saveFile();
        } else if (e.getActionCommand().equals("Exit")) {
            Exiting();
        } else if (e.getActionCommand().equals("Copy")) {
            jta.copy();
        } else if (e.getActionCommand().equals("Paste")) {
            jta.paste();
        } else if (e.getActionCommand().equals("Cut")) {
            jta.cut();
        } else if (e.getActionCommand().equals("Zoom In")) {
            zoomIn();
        } else if (e.getActionCommand().equals("Zoom Out")) {
            zoomOut();
        } else if (e.getActionCommand().equals("Find")) {
            showFindDialog();
        } else if (e.getActionCommand().equals("Replace")) {
            showReplaceDialog();
        } else if (e.getActionCommand().equals("Toggle Dark Mode")) {
            toggleDarkMode();
        }
    }

    public void openFile(String fname) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String s;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((s = d.readLine()) != null) {
            jta.setText(jta.getText() + s + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void saveFile() {
        JFileChooser jfc = new JFileChooser();
        if (fnameContainer != null) {
            jfc.setCurrentDirectory(fnameContainer);
            jfc.setSelectedFile(fnameContainer);
        } else {
            jfc.setSelectedFile(new File("Untitled.txt"));
        }

        int ret = jfc.showSaveDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File fyl = jfc.getSelectedFile();
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fyl));
                dos.writeBytes(jta.getText());
                dos.close();
                this.setTitle(fyl.getName() + " - Notepad");
                fnameContainer = fyl;
            } catch (Exception ers2) {
            }
        }
    }

    public void zoomIn() {
        Font currentFont = jta.getFont();
        int currentSize = currentFont.getSize();
        int newSize = currentSize + 2;
        jta.setFont(currentFont.deriveFont((float) newSize));
    }

    public void zoomOut() {
        Font currentFont = jta.getFont();
        int currentSize = currentFont.getSize();
        int newSize = Math.max(currentSize - 2, 12);
        jta.setFont(currentFont.deriveFont((float) newSize));
    }

    public void showFindDialog() {
        String searchTerm = JOptionPane.showInputDialog(this, "Find:");
        if (searchTerm != null) {
            String text = jta.getText();
            int index = text.indexOf(searchTerm);
            if (index != -1) {
                jta.setSelectionStart(index);
                jta.setSelectionEnd(index + searchTerm.length());
            } else {
                JOptionPane.showMessageDialog(this, "Text not found!");
            }
        }
    }

    public void showReplaceDialog() {
        String searchTerm = JOptionPane.showInputDialog(this, "Find:");
        if (searchTerm != null) {
            String replaceTerm = JOptionPane.showInputDialog(this, "Replace with:");
            if (replaceTerm != null) {
                String text = jta.getText();
                String newText = text.replace(searchTerm, replaceTerm);
                if (!text.equals(newText)) {
                    jta.setText(newText);
                    JOptionPane.showMessageDialog(this, "Replacement complete!");
                } else {
                    JOptionPane.showMessageDialog(this, "Text not found!");
                }
            }
        }
    }

    public static void main(String[] args) {
        Notepad notp = new Notepad();
    }

    public void toggleDarkMode() {
        if (darkModeToggle.isSelected()) {
            // Enable Dark Mode
            jta.setBackground(Color.BLACK);
            jta.setForeground(Color.WHITE);
        } else {
            // Disable Dark Mode
            jta.setBackground(Color.WHITE);
            jta.setForeground(Color.BLACK);
        }
    }

    public Notepad() {
        Font fnt = new Font("Arial", Font.PLAIN, 15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmview = new JMenu("View");
        JMenu jmhelp = new JMenu("Help");

        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);
        jta.setFont(fnt);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        con.add(sbrText, BorderLayout.CENTER);
        con.add(lineLabel, BorderLayout.SOUTH);
        createMenuItem(jmfile, "New", KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        createMenuItem(jmfile, "Open", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        createMenuItem(jmfile, "Save", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        jmfile.addSeparator();
        createMenuItem(jmfile, "Exit", null);
        createMenuItem(jmedit, "Cut", KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        createMenuItem(jmedit, "Copy", KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        createMenuItem(jmedit, "Paste", KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        createFindReplaceMenuItem(jmedit, "Find", KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        createFindReplaceMenuItem(jmedit, "Replace", KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        createZoomMenuItem(jmview, "Zoom In", KeyStroke.getKeyStroke(KeyEvent.VK_ADD, KeyEvent.CTRL_DOWN_MASK),
                1);
        createZoomMenuItem(jmview, "Zoom Out", KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,
                KeyEvent.CTRL_DOWN_MASK), -1);
        createMenuItem(jmhelp, "About Notepad", null);
        darkModeToggle = new JToggleButton("Toggle Dark Mode");
        darkModeToggle.addActionListener(this);
        jmview.add(darkModeToggle);
        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmview);
        jmb.add(jmhelp);
        setJMenuBar(jmb);
        addWindowListener(this);
        jta.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLineLabel();
            }

            public void removeUpdate(DocumentEvent e) {
                updateLineLabel();
            }

            public void changedUpdate(DocumentEvent e) {
                updateLineLabel();
            }
        });
        setSize(500, 500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);
    }

    private void updateLineLabel() {
        String[] lines = jta.getText().split("\\n");
        int lineCount = lines.length;
        if (lineCount > 0 && lines[lineCount - 1].trim().isEmpty()) {
            lineCount--;
        }
        lineLabel.setText("Lines: " + lineCount);
    }
}
