/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automenta.netention.swing.widget;

import automenta.netention.Detail;
import automenta.netention.Node;
import automenta.netention.Pattern;
import automenta.netention.impl.MemorySelf;
import automenta.netention.swing.Icons;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.collections15.IteratorUtils;
import org.apache.commons.collections15.multimap.MultiHashMap;

/**
 *
 * @author seh
 */
public class WhenPanel extends JPanel implements IndexView {

    float textScale = 1.25f;
    private final MemorySelf self;
    private JTree tree;
    private WhenTreeModel treeModel;

    public class WhenTreeModel extends DefaultTreeModel {

        public WhenTreeModel() {
            super(new DefaultMutableTreeNode("All"));
            refresh();
        }

        protected void refresh() {
            ((DefaultMutableTreeNode) root).removeAllChildren();


            List<Node> d = MemorySelf.getDetailsByTime(self.iterateNodes(), false);
            
            for (Node n : d) {
                    DefaultMutableTreeNode pNode = new DefaultMutableTreeNode(n);
                    ((DefaultMutableTreeNode) root).add(pNode);
                      
            }
        }
    }

    public WhenPanel(MemorySelf self) {
        super(new BorderLayout());


        this.self = self;

    }

    public void refresh() {
        removeAll();

        treeModel = new WhenTreeModel();
        tree = new JTree(treeModel);

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {

            public Component getTreeCellRendererComponent(JTree tree,
                Object value, boolean sel, boolean expanded, boolean leaf,
                int row, boolean hasFocus) {

                super.getTreeCellRendererComponent(tree, value, sel,
                    expanded, leaf, row, hasFocus);

                Object nodeObj = ((DefaultMutableTreeNode) value).getUserObject();

                if (nodeObj instanceof Pattern) {
                    Pattern p = (Pattern)nodeObj;
                    setText(p.getName());
                    setToolTipText(p.getID());                    
                }
                else if (nodeObj instanceof Detail) {
                    Detail d = (Detail)nodeObj;
                    setText("<html><b>" + d.getName() + "</b><br/>" + d.getWhen() + "</html>");
                    
                }
                        
                // check whatever you need to on the node user object
                setIcon(getObjectIcon(nodeObj));

                if (!leaf) {
                    if (expanded) {
                        setFont(getFont().deriveFont(Font.PLAIN));
                    } else {
                        setFont(getFont().deriveFont(Font.BOLD));
                    }
                } else {
                    setFont(getFont().deriveFont(Font.PLAIN));
                }


                setBorderSelectionColor(
                    sel ? getBackgroundSelectionColor() : getBackground());



                return this;


            }

            public Icon getObjectIcon(Object o) {
                if (o instanceof Detail) {
                    Detail d = (Detail) o;
                    return Icons.getDetailIcon(self, d);
                } else if (o instanceof Pattern) {
                    Pattern p = (Pattern) o;
                    return Icons.getPatternIcon(p);
                }
                return Icons.getIcon("thought");
            }
        };


        {
            renderer.setFont(getFont().deriveFont((float) (getFont().getSize() * textScale)));

            final int b = 6;
            renderer.setBorder(new EmptyBorder(b, b, b, b));


        }


        tree.setCellRenderer(renderer);
        tree.setRootVisible(false);

        add(tree, BorderLayout.CENTER);
        
        updateUI();
    }

    public JTree getTree() {
        return tree;
    }

    /** expands to the first path containing 'd' as a leaf */
    public void selectObject(Detail d) {
        //TODO impl this
    }
}