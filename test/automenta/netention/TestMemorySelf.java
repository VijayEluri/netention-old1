/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.netention;

import automenta.netention.impl.MemorySelf;
import automenta.netention.linker.hueristic.DefaultHeuristicLinker;
import automenta.netention.value.integer.IntProp;
import automenta.netention.value.integer.IntegerEquals;
import automenta.netention.value.integer.IntegerIs;
import automenta.netention.value.string.StringProp;
import junit.framework.TestCase;

/**
 *
 * @author seh
 */
public class TestMemorySelf extends TestCase {

    public void testMemorySelf() {
        Self s = new MemorySelf("me", "Me");

        //add properties
        {
            Property prop1 = new StringProp("prop1", "String Property");
            s.addProperty(prop1);

            Property prop2 = new IntProp("prop2", "Integer Property");
            s.addProperty(prop2);
        }
        
        //add patterns
        {
            Pattern p1 = new Pattern("pat1");
            p1.properties.put("prop1", 1.0);
            s.addPattern(p1);

            Pattern p2 = new Pattern("pat2");
            p2.properties.put("prop2", 0.7);
            s.addPattern(p2);
        }

        Detail ma = new Detail("a", Mode.Real, "pat1", "pat2");
        s.addDetail(ma);
        Detail mb = new Detail("b", Mode.Imaginary, "pat1");
        s.addDetail(mb);

        //add details
        {
            
            assertEquals(2, s.getAvailableProperties(ma, "pat1", "pat2").size());

            ma.add("prop1", new IntegerIs(0));

            //now that a property is added, it should be one less available
            assertEquals(1, s.getAvailableProperties(ma, "pat1", "pat2").size());


            mb.add("prop1", new IntegerEquals(0));
        }

        //test linker
        {
            s.link(new DefaultHeuristicLinker(s));
//            SimpleDynamicDirectedGraph<Node, Link> g = s.getGraph();
//            assertEquals(2, g.getNodes().size());
//            assertEquals(1, g.getEdges().size());
        }
    }
}
