/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automenta.netention.neuron;

/*
 * Part of Simbrain--a java-based neural network kit
 * Copyright (C) 2005,2007 The Authors.  See http://www.simbrain.net/credits
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */


/**
 * <b>IzhikevichNeuron</b>. Default values correspond to "tonic spiking".
 */
public class IzhikevichNeuron extends SpikingNeuron {

    /** Recovery. */
    private double recovery = 0;

    /** A. */
    private double a = .02;

    /** B. */
    private double b = .2;

    /** C. */
    private double c = -65;

    /** D. */
    private double d = 6;

//    /** Noise dialog. */
//    private RandomSource noiseGenerator = new RandomSource();

    /** Add noise to the neuron. */
    private boolean addNoise = false;

    /**
     * Default constructor needed for external calls which create neurons then
     * set their parameters.
     */
    public IzhikevichNeuron() {
    }

    /**
     * Updates the neuron.
     */
    public void update(double dt) {
        double inputs = getWeightedInputs();

        if (addNoise) {
            //inputs += noiseGenerator.getRandom();
        }

        recovery += (dt * (a * ((b * activation) - recovery)));

        double val = activation
                + (dt * (((.04 * (activation * activation))
                        + (5 * activation) + 140)
                        - recovery + inputs));

        if (val > 30) {
            val = c;
            recovery += d;
            setHasSpiked(true);
        } else {
            setHasSpiked(false);
        }

        setBuffer(val);
    }

    /**
     * @return Returns the a.
     */
    public double getA() {
        return a;
    }

    /**
     * @param a The a to set.
     */
    public void setA(final double a) {
        this.a = a;
    }

    /**
     * @return Returns the b.
     */
    public double getB() {
        return b;
    }

    /**
     * @param b The b to set.
     */
    public void setB(final double b) {
        this.b = b;
    }

    /**
     * @return Returns the c.
     */
    public double getC() {
        return c;
    }

    /**
     * @param c The c to set.
     */
    public void setC(final double c) {
        this.c = c;
    }

    /**
     * @return Returns the d.
     */
    public double getD() {
        return d;
    }

    /**
     * @param d The d to set.
     */
    public void setD(final double d) {
        this.d = d;
    }


    /**
     * @return Returns the addNoise.
     */
    public boolean getAddNoise() {
        return addNoise;
    }

    /**
     * @param addNoise The addNoise to set.
     */
    public void setAddNoise(final boolean addNoise) {
        this.addNoise = addNoise;
    }

//    /**
//     * @return Returns the noiseGenerator.
//     */
//    public RandomSource getNoiseGenerator() {
//        return noiseGenerator;
//    }
//
//    /**
//     * @param noiseGenerator The noiseGenerator to set.
//     */
//    public void setNoiseGenerator(final RandomSource noiseGenerator) {
//        this.noiseGenerator = noiseGenerator;
//    }
}