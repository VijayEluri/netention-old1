/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automenta.netention.swing;

import automenta.netention.Self;
import automenta.netention.impl.MemorySelfData;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author seh
 */
public class LoadSaveJSON extends JPanel {

    public LoadSaveJSON(final Self self) {
        super(new BorderLayout());

        final JTextArea jta = new JTextArea();
        jta.setEditable(false);
        jta.setOpaque(false);

        JPanel loadPanel = new JPanel(new BorderLayout());
        {
            final JTextField loadFile = new JTextField();
            loadPanel.add(loadFile, BorderLayout.CENTER);

            JButton loadButton = new JButton("Load File/URL");
            loadButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    final String path = loadFile.getText();
                    try {
                        MemorySelfData msd = MemorySelfData.loadJSON(path);
                        jta.append("Loaded from " + path + "\n");
                        msd.mergeTo(self);
                    } catch (Exception ex) {
                        jta.append(ex.toString() + " when loading from " + path);
                        ex.printStackTrace();
                    }

                }
            });
            loadPanel.add(loadButton, BorderLayout.EAST);
        }
        add(loadPanel, BorderLayout.NORTH);

        add(new JScrollPane(jta), BorderLayout.CENTER);

        JPanel savePanel = new JPanel(new BorderLayout());
        {
            final JTextField saveFile = new JTextField();
            savePanel.add(saveFile, BorderLayout.CENTER);

            JPanel op = new JPanel(new FlowLayout());
            
                final JCheckBox includeDetails = new JCheckBox("Include Details");
                includeDetails.setSelected(true);
                op.add(includeDetails);

                final JCheckBox includeSchema = new JCheckBox("Include Schema");
                includeSchema.setSelected(false);
                op.add(includeSchema);

            

            savePanel.add(op, BorderLayout.SOUTH);


            JButton saveButton = new JButton("Save File");
            saveButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    final String path = saveFile.getText();
                    try {
                        Self.saveJSON(self, path, includeDetails.isSelected(), includeSchema.isSelected());
                        jta.append("Saved to " + path + "\n");
                    } catch (Exception ex) {
                        jta.append(ex.toString() + " when saving to " + path + "\n");
                    }

                }
            });
            savePanel.add(saveButton, BorderLayout.EAST);
        }
        add(savePanel, BorderLayout.SOUTH);
    }
}
