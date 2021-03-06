/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.netention.swing.property;

import automenta.netention.Detail;
import automenta.netention.Self;
import automenta.netention.Value;
import automenta.netention.value.Comment;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author seh
 */
public class CommentPanel extends PropertyOptionPanel {

    final int rows = 2;
    final int cols = 40;
    
    public CommentPanel(Self self, Detail d, final Comment comment, boolean editable) {
        super(self, d, comment, editable);
        
        
        setCurrentOption(new PropertyOption<Comment>(" ") {

            final JTextArea p = new JTextArea(rows, cols);
            
            @Override
            public Comment widgetToValue(Comment value) {
                //value.setText(p.getText());
                comment.setText(p.getText());
                return value;
            }

            @Override
            public Comment newDefaultValue() {
                return new Comment();
            }

            @Override
            public JPanel newEditPanel(Comment value) {
                JPanel j = new JPanel(new BorderLayout(4,4));
                
                p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                p.setText(value.getText());
                j.add(p, BorderLayout.CENTER);
                
                return j;
            }

            @Override
            public boolean accepts(Value v) {
                return (v instanceof Comment);
            }

            
        });
        refresh();
    }
    
    public boolean showsTypeSelect() {
        return false;
    }
    
    public boolean showsLabel() {
        return true;
    }
    
}