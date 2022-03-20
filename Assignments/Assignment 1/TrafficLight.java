
public class TrafficLight {

    private enum TrafficLightColor {
        RED, YELLOW, GREEN
    
    };
    
    public TrafficLightColor CurrentLight;// = traficLightColor.RED;

    public TrafficLight() {
        CurrentLight = TrafficLightColor.RED;

    }

    public void changeLight(TrafficLightColor lightColor) {
        switch (CurrentLight) {
            
            case RED:
                CurrentLight = TrafficLightColor.GREEN;
                break;
            
            case YELLOW:
                CurrentLight = TrafficLightColor.RED;
                break;
            
            case GREEN:
                CurrentLight = TrafficLightColor.YELLOW;
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
