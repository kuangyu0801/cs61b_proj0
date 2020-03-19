public class NBody {
    // returning a radius read from file
    public static double readRadius(String inString){
        In in = new In(inString);
        int firstItemInFile = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // returning an array of Planets from file
    public static Planet[] readPlanets(String inString){
        In in = new In(inString);
        int numPlanet = in.readInt();
        Planet[] aPlanet = new Planet[numPlanet];
        double radius = in.readDouble();
        for(int i=0; i<numPlanet; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            aPlanet[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return aPlanet;
    }

    public static void drawBackground(){
        String backGround = "images/starfield.jpg";
        double size = 100;
        StdDraw.setScale(-size, size);
        /* Clears the drawing window. */
        StdDraw.clear();
        StdDraw.picture(0, 0, backGround, size, size);
    }

    public static void main(String[] args){

        //Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        String backGround = "images/starfield.jpg";
        double radius = NBody.readRadius(fileName);
        double clk = 0;
        Planet[] aPlanet = NBody.readPlanets(fileName);
        int numPlanet = aPlanet.length;

        StdDraw.setScale(-radius, radius);

        StdDraw.enableDoubleBuffering();

        while (clk < T) {

            double[] xForces = new double[numPlanet];
            double[] yForces = new double[numPlanet];
            /*Calculate the net x and y forces for each planet*/
            /*Call update on each of the planets*/
            for(int i = 0; i < numPlanet; i++){
                xForces[i] = aPlanet[i].calcNetForceExertedByX(aPlanet);
                yForces[i] = aPlanet[i].calcNetForceExertedByY(aPlanet);
                aPlanet[i].update(dt, xForces[i], yForces[i]);
            }

            /*Draw the background image*/
            StdDraw.clear();
            StdDraw.picture(0, 0, backGround);
            /*Draw all of the planets*/
            for(int i = 0; i < numPlanet; i++){
                aPlanet[i].draw();
            }
            /*Show the offscreen buffer*/
            StdDraw.show();
            /*Pause the animation for 10 milliseconds*/
            StdDraw.pause(10);
            /*Increase time variable by dt.*/
            clk += dt;
        }

        StdOut.printf("%d\n", aPlanet.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < aPlanet.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    aPlanet[i].xxPos, aPlanet[i].yyPos, aPlanet[i].xxVel,
                    aPlanet[i].yyVel, aPlanet[i].mass, aPlanet[i].imgFileName);
        }
    }
}
