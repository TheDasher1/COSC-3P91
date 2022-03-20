package MapPackage;

public class TrafficLight {

    public enum TrafficLightColor {
        RED, /*YELLOW,*/ GREEN
    
    };
    private int countTillRedToGreen = 20, countTillYellowToRed = 5;
    
    public TrafficLightColor CurrentLight;// = traficLightColor.RED;

    public TrafficLight() {
        CurrentLight = TrafficLightColor.RED;

    }

    public void decCount() {
        this.countTillRedToGreen --;

    }

    public int getCurrCountTillSwitch() {
        return this.countTillRedToGreen;
    }

    public void changeLight(TrafficLightColor lightColor) {
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

    public void setLightColor(TrafficLightColor newColor) {
        this.CurrentLight = newColor;

    }

    public String getLightColor() {
        return "The light is " + CurrentLight;

    }
    
}
