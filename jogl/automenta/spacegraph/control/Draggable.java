/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.spacegraph.control;

import automenta.spacegraph.math.linalg.Vec2f;

/**
 *
 * @author me
 */
public interface Draggable {
    public void onDragStart(Pointer pointer, Vec2f worldStart);
    public void onDragging(Pointer pointer, Vec2f world);
    public void onDragEnd(Pointer pointer, Vec2f worldEnd);
}
