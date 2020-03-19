public class Planet {
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; //  Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; // Its mass
    public String imgFileName; //

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dist = 0;
        dist = Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos)+ (yyPos - p.yyPos)*(yyPos - p.yyPos));
        return dist;
    }

    public double calcForceExertedBy(Planet p){
        double dist = this.calcDistance(p);
        double force;
        double constG = 6.67e-11;
        if(dist == 0) {
            force = 0;
        } else {
            force = (this.mass * p.mass * constG) / (dist * dist);
        }
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double forceX = (this.calcDistance(p) == 0) ? 0 : this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p){
        double forceY = (this.calcDistance(p) == 0) ? 0 : this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] p){
        double netForceX = 0;
        for(int i = 0; i < p.length;  i+=1){
            netForceX = this.calcForceExertedByX(p[i]) + netForceX;
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double netForceY = 0;
        for(int i = 0; i < p.length;  i+=1){
            netForceY = this.calcForceExertedByY(p[i]) + netForceY;
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        // Calculate the acceleration
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        // Calculate the new velocity
        this.xxVel = this.xxVel + aX * dt;
        this.yyVel = this.yyVel + aY * dt;
        // Calculate the new position
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw(){

        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        //System.out.println("[X:"+ Double.toString(this.xxPos) +"][Y:" + Double.toString(this.yyPos) + "]" + this.imgFileName );
    }
}
