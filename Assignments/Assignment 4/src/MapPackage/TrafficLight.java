package MapPackage;

public class TrafficLight {

    /**
     * 
     * 
     * 
     */
    public enum TrafficLightColor {
        RED, /*YELLOW,*/ GREEN
    
    }

    // private int countTillRedToGreen = 20, countTillYellowToRed = 5;
    
    private TrafficLightColor CurrentLight;// = traficLightColor.RED;

    /**
     * 
     * 
     * 
     */
    public TrafficLight() {
        CurrentLight = TrafficLightColor.RED;

    }

    // public void decCount() {
    //     this.countTillRedToGreen --;

    // }

    // public int getCurrCountTillSwitch() {
    //     return this.countTillRedToGreen;
    // }

    /**
     * 
     * 
     * 
     */
    public void changeLight() {
        switch (CurrentLight) {
            case RED:
                CurrentLight = TrafficLightColor.GREEN;
                break;
            
            // case YELLOW:
            //     CurrentLight = TrafficLightColor.RED;
            //     break;
            
            case GREEN:
                CurrentLight = TrafficLightColor.RED;
                break;

        }

    }

    /**
     * 
     * 
     * 
     * @return
     */
    public TrafficLightColor getCurrentLight() {
        return this.CurrentLight;

    }

    /**
     * 
     * 
     * 
     * @param newColor
     * @return
     */
    public boolean setLightColor(TrafficLightColor newColor) {
        this.CurrentLight = newColor;

        return true;

    }

    /**
     * 
     * 
     * 
     * @return
     */
    public String getLightColor() {
        return "The light is " + CurrentLight;

    }
    
}
