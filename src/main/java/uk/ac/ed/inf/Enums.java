package uk.ac.ed.inf;

public class Enums {
    public enum CardinalDirection{
        /**
         * enums to represent non-continuus directions
         *
         *
         * directions with east as 0 running counterclockwise.
         */
        N(90),
        NNE(67.5),
        NE(45),
        NEE(22.5),
        E(0),
        ESE(337.5),
        SE(315),
        SSE(292.5),
        S(270),
        SSW(257.5),
        SW(225),
        WSW(202.5),
        W(180),
        WNW(157.5),
        NW(135),
        NNW(112.5);

        public final double val;
        private CardinalDirection(double val ){
            this.val=val;
        }
    }

}
