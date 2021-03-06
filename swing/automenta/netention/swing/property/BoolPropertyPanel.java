/**
 * 
 */
package automenta.netention.swing.property;

import automenta.netention.*;
import automenta.netention.swing.util.TransparentFlowPanel;
import automenta.netention.value.bool.BoolEquals;
import automenta.netention.value.bool.BoolIs;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class BoolPropertyPanel extends PropertyOptionPanel {

    public static class TrueFalseCombo extends JComboBox {

        public TrueFalseCombo(boolean v) {
            super();
            addItem("True");
            addItem("False");
            setSelectedIndex(v ? 0 : 1);
        }

        public boolean isTrue() {
            return (getSelectedIndex() == 0);
        }


    }

    public BoolPropertyPanel(Self s, Detail d, PropertyValue v, boolean editable) {
        super(s, d, v, editable);

        if (getMode() != Mode.Imaginary) {
            addOption(new PropertyOption<BoolIs>("is") {

                private TrueFalseCombo combo;

                @Override public JPanel newEditPanel(BoolIs v) {
                    setValue(v);
                    setReal();

                    JPanel p = new TransparentFlowPanel();
                    combo = new TrueFalseCombo(v.getValue());
                    p.add(combo);
                    return p;
                }

                @Override public BoolIs widgetToValue(BoolIs r) {
                    r.setValue(combo.getSelectedIndex() == 0);
                    return r;
                }

                @Override public boolean accepts(Value v) {
                    return v.getClass().equals(BoolIs.class);
                }

                @Override public BoolIs newDefaultValue() {
                    return new BoolIs(false);
                }
            });

        } 
        if (getMode() != Mode.Real) {

            addOption(new PropertyOption<BoolEquals>("?  ") {

                private TrueFalseCombo combo;

                @Override public JPanel newEditPanel(BoolEquals v) {
                    setValue(v);
                    setImaginary();

                    JPanel p = new TransparentFlowPanel();
                    combo = new TrueFalseCombo(v.getValue());
                    p.add(combo);
                    return p;
                }

                @Override public boolean accepts(Value v) {
                    return v.getClass().equals(BoolEquals.class);
                }

                @Override public BoolEquals widgetToValue(BoolEquals r) {
                    r.setValue(combo.getSelectedIndex() == 0);
                    return r;
                }

                @Override public BoolEquals newDefaultValue() {
                    return new BoolEquals(false);
                }
            });

        }

        refresh();

    }
}
