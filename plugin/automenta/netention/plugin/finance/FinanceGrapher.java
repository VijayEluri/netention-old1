/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.netention.plugin.finance;

import automenta.netention.Link;
import automenta.netention.Node;
import automenta.netention.graph.ValueEdge;
import automenta.netention.link.In;
import automenta.netention.link.Next;
import automenta.netention.node.TimePoint;
import automenta.netention.plugin.finance.PublicBusiness.BusinessPerformance;
import com.syncleus.dann.graph.MutableBidirectedGraph;
import java.util.Collection;
import java.util.Date;
import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;

/**
 *
 * @author seh
 */
public class FinanceGrapher {


    public static void run(Collection<PublicBusiness> businesses, MutableBidirectedGraph<Node,ValueEdge<Node, Link>> target, int yearFrom, int yearTo, boolean connectAllPointsToBusiness) {
        MultiMap<Date,BusinessPerformance> perfs = new MultiHashMap();


        for (PublicBusiness pb : businesses) {
            target.add(pb);
            
            BusinessPerformance lastBp = null;
            for (BusinessPerformance bp : pb.getPerformance()) {
                int y = bp.start.getYear() + 1900;
                if (!(y >= yearFrom) && (y <= yearTo))
                    continue;
                
                target.add(bp);
                
                if (lastBp != null) {
                    target.add(lastBp);
                    target.add(new ValueEdge(new Next(), lastBp, bp));
                }

                if ((connectAllPointsToBusiness) || (lastBp == null)) {
                    target.add(new ValueEdge(new In(), pb, bp));
                }

                perfs.put(bp.start, bp);
                lastBp = bp;
            }
        }

         TimePoint lt = null;
        
        for (Date d : perfs.keySet()) {
            Collection<BusinessPerformance> lb = perfs.get(d);
            TimePoint t = new TimePoint(d);
            target.add(t);
            
            for (BusinessPerformance bp : lb) {
                target.add(new ValueEdge(new In(), t, bp));
            }
//            if (lt!=null) {
//                target.addEdge(new Next(), lt)
//            }
            lt = t;
        }

        
    }

}
